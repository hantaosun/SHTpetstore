package com.sht.controller;

import com.sht.pojo.Cart;
import com.sht.pojo.CartItem;
import com.sht.pojo.Item;
import com.sht.service.CatalogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Controller
@RequestMapping("/Cart.action")
@SessionAttributes({"cart", "workingItemId"})
public class CartController extends AbstractController {

    private static final String VIEW_CART = "cart/Cart";
    private static final String CHECK_OUT = "cart/Checkout";

    @Autowired
    private CatalogService catalogService;

    @ModelAttribute("cart")
    public Cart initCart() {
        return new Cart();
    }

    @GetMapping
    public String viewCart(@ModelAttribute("cart") Cart cart) {
        return VIEW_CART;
    }

    @GetMapping(params = "addItemToCart")
    public String addItemToCart(
            @RequestParam(name = "workingItemId") String workingItemId,
            @ModelAttribute("cart") Cart cart,
            Model model
    ) {
        if (workingItemId == null || workingItemId.trim().isEmpty()) {
            setMessage("Invalid item ID: cannot add item to cart.", model);
            return ERROR;
        }

        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
        } else {
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }
        return "redirect:/Cart.action";
    }

    @GetMapping(params = "removeItemFromCart")
    public String removeItemFromCart(
            @RequestParam("workingItemId") String workingItemId,
            @ModelAttribute("cart") Cart cart,
            Model model
    ) {
        if (workingItemId == null || workingItemId.trim().isEmpty()) {
            setMessage("Invalid item ID: cannot remove item from cart.", model);
            return ERROR;
        }

        Item item = cart.removeItemById(workingItemId);

        if (item == null) {
            setMessage("Attempted to remove null CartItem from Cart.", model);
            return ERROR;
        } else {
            return "redirect:/Cart.action";
        }
    }

    @PostMapping(params = "updateCartQuantities")
    public String updateCartQuantities(HttpServletRequest request, @ModelAttribute("cart") Cart cart) {
        Iterator<CartItem> cartItems = cart.getCartItems();
        while (cartItems.hasNext()) {
            CartItem cartItem = cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                int quantity = Integer.parseInt(request.getParameter(itemId));
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItems.remove();
                }
            } catch (NumberFormatException e) {
                // ignore invalid numeric input on purpose
            }
        }
        return "redirect:/Cart.action";
    }

    @GetMapping(params = "checkOut")
    public String checkOut(@ModelAttribute("cart") Cart cart) {
        return CHECK_OUT;
    }
}
