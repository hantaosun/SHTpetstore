package com.sht.controller;

import com.sht.pojo.Account;
import com.sht.pojo.Cart;
import com.sht.pojo.Order;
import com.sht.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/Order.action")
@SessionAttributes({"order", "orderList"})
public class OrderController extends AbstractController {

    private static final List<String> CARD_TYPE_LIST =
            Collections.unmodifiableList(Arrays.asList("Visa", "MasterCard", "American Express"));

    private static final String NEW_ORDER   = "order/NewOrderForm";
    private static final String SHIPPING    = "order/ShippingForm";
    private static final String CONFIRM     = "order/ConfirmOrder";
    private static final String VIEW_ORDER  = "order/ViewOrder";
    private static final String LIST_ORDERS = "order/ListOrders";

    @Autowired
    private OrderService orderService;

    /** 为所有请求自动注入信用卡类型列表，页面用 ${creditCardTypes} 读取 */
    @ModelAttribute("creditCardTypes")
    public List<String> getCreditCardTypes() {
        return CARD_TYPE_LIST;
    }

    /** 首次访问时初始化空 Order，存入 session */
    @ModelAttribute("order")
    public Order initOrder() {
        return new Order();
    }

    /**
     * GET /Order.action?newOrderForm=
     * 进入下单页：检查登录状态和购物车，初始化订单
     */
    @GetMapping(params = "newOrderForm")
    public String newOrderForm(Model model, HttpSession session, RedirectAttributes redirectAttrs) {
        Account account = (Account) session.getAttribute("account");
        Boolean authenticated = (Boolean) session.getAttribute("authenticated");
        Cart cart = (Cart) session.getAttribute("cart");

        if (account == null || !Boolean.TRUE.equals(authenticated)) {
            setRedirectMessage(redirectAttrs, "You must sign on before attempting to check out. Please sign on and try checking out again.");
            return "redirect:/Account.action?signonForm=";
        } else if (cart == null || cart.getNumberOfItems() == 0) {
            setRedirectMessage(redirectAttrs, "An order could not be created because a cart could not be found.");
            return "redirect:/Cart.action";
        } else {
            Order order = new Order();
            order.initOrder(account, cart);
            model.addAttribute("order", order);
            return NEW_ORDER;
        }
    }

    /**
     * POST /Order.action?newOrder（含 shippingAddressRequired 参数）
     * 勾选了"发货到不同地址" → 跳转收货地址填写页
     */
    @PostMapping(params = {"newOrder", "shippingAddressRequired"})
    public String newOrderWithShipping(@ModelAttribute("order") Order order) {
        return SHIPPING;
    }

    /**
     * POST /Order.action?newOrder（不含 shippingAddressRequired）
     * 未勾选不同地址 / 收货地址页提交 → 进入确认页
     */
    @PostMapping(params = {"newOrder", "!shippingAddressRequired"})
    public String newOrder(@ModelAttribute("order") Order order) {
        return CONFIRM;
    }

    /**
     * GET /Order.action?newOrder=&confirmed=
     * 确认下单：持久化订单，清空购物车，跳转订单详情
     */
    @GetMapping(params = {"newOrder", "confirmed"})
    public String confirmOrder(
            @ModelAttribute("order") Order order,
            Model model,
            HttpSession session,
            SessionStatus sessionStatus,
            RedirectAttributes redirectAttrs
    ) {
        if (order == null || order.getLineItems() == null || order.getLineItems().isEmpty()) {
            setRedirectMessage(redirectAttrs, "An error occurred processing your order (order was null).");
            return "redirect:/Cart.action";
        }

        orderService.insertOrder(order);

        // 清空购物车
        session.setAttribute("cart", new Cart());

        // 清理本控制器的 @SessionAttributes（order / orderList）
        sessionStatus.setComplete();

        setRedirectMessage(redirectAttrs, "Thank you, your order has been submitted.");
        // 重定向到订单详情，让 viewOrder 方法重新查询并展示
        return "redirect:/Order.action?viewOrder=&orderId=" + order.getOrderId();
    }

    /**
     * GET /Order.action?listOrders=
     * 查看历史订单列表
     */
    @GetMapping(params = "listOrders")
    public String listOrders(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/Account.action?signonForm=";
        }
        model.addAttribute("orderList", orderService.getOrdersByUsername(account.getUsername()));
        return LIST_ORDERS;
    }

    /**
     * GET /Order.action?viewOrder=&orderId={id}
     * 查看指定订单详情（仅允许查看自己的订单）
     */
    @GetMapping(params = "viewOrder")
    public String viewOrder(
            @RequestParam("orderId") int orderId,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttrs
    ) {
        Account account = (Account) session.getAttribute("account");
        Order order = orderService.getOrder(orderId);

        if (account != null && account.getUsername().equals(order.getUsername())) {
            model.addAttribute("order", order);
            return VIEW_ORDER;
        } else {
            setRedirectMessage(redirectAttrs, "You may only view your own orders.");
            return "redirect:/Order.action?listOrders=";
        }
    }
}
