package com.sht.controller;

import com.sht.controller.AbstractController;
import com.sht.pojo.Account;
import com.sht.pojo.Product;
import com.sht.service.AccountService;
import com.sht.service.CatalogService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Spring MVC 版账户控制器（替代原 Stripes 的 AccountActionBean）
 * 核心改造：
 * 1. @SessionAttributes 绑定会话级数据（account、myList、authenticated）
 * 2. 用 Spring 校验替代 Stripes 的 @Validate
 * 3. 用 RedirectAttributes 传递重定向消息
 */
@Controller
@RequestMapping("/Account.action")
// 关键：指定需要存入会话的属性名，Spring 会自动管理这些数据的会话生命周期
@SessionAttributes({"account", "myList", "authenticated"})
public class AccountController extends AbstractController {

    // 原静态常量：保持不变（只读列表，类加载时初始化）
    private static final List<String> LANGUAGE_LIST;
    private static final List<String> CATEGORY_LIST;

    static {
        LANGUAGE_LIST = Collections.unmodifiableList(Arrays.asList("english", "japanese"));
        CATEGORY_LIST = Collections.unmodifiableList(Arrays.asList("FISH", "DOGS", "REPTILES", "CATS", "BIRDS"));
    }

    // 原 @SpringBean → Spring 的 @Autowired
    @Autowired
    private AccountService accountService;
    @Autowired
    private CatalogService catalogService;

    // 视图路径常量（适配 Spring 视图解析器，前缀/后缀在配置类中定义）
    private static final String NEW_ACCOUNT = "account/NewAccountForm";
    private static final String EDIT_ACCOUNT = "account/EditAccountForm";
    private static final String SIGNON = "account/SignonForm";

    // ==================== 原 ActionBean 的 getter/setter 改造 ====================
    // 1. 语言/分类列表：提供给页面读取（原 getLanguages/getCategories）
    @ModelAttribute("languages")
    public List<String> getLanguages() {
        return LANGUAGE_LIST;
    }

    @ModelAttribute("categories")
    public List<String> getCategories() {
        return CATEGORY_LIST;
    }

    // 2. 会话级属性的初始化（首次访问时创建空 Account）
    @ModelAttribute("account")
    public Account initAccount() {
        return new Account();
    }

    @ModelAttribute("authenticated")
    public boolean initAuthenticated() {
        return false;
    }

    // ==================== 原业务方法改造 ====================
    // 1. 原 newAccountForm() → 匹配 params = "newAccountForm" 的 GET 请求
    @GetMapping(params = "newAccountForm")
    public String newAccountForm() {
        // 原 ForwardResolution(NEW_ACCOUNT) → 直接返回视图名
        return NEW_ACCOUNT;
    }

    // 2. 原 newAccount() → 处理新账户创建（带参数校验）
    @PostMapping(params = "newAccount")
    public String newAccount(
            // 用 Spring 校验替代 Stripes 的 @Validate
            @Validated(AccountGroup.NewAccount.class) @ModelAttribute("account") Account account,
            Model model, // 用于更新会话属性
            RedirectAttributes redirectAttrs // 重定向消息传递
    ) {
        // 原业务逻辑：插入账户 → 查询账户 → 初始化 myList → 标记已认证
        accountService.insertAccount(account);
        Account savedAccount = accountService.getAccount(account.getUsername());
        List<Product> myList = catalogService.getProductListByCategory(savedAccount.getFavouriteCategoryId());

        // 更新会话属性（@SessionAttributes 会自动同步到 HttpSession）
        model.addAttribute("account", savedAccount);
        model.addAttribute("myList", myList);
        model.addAttribute("authenticated", true);

        // 原 RedirectResolution → Spring 重定向
        return "redirect:/Catalog.action";
    }

    // 3. 原 editAccountForm() → 匹配 params = "editAccountForm" 的 GET 请求
    @GetMapping(params = "editAccountForm")
    public String editAccountForm() {
        return EDIT_ACCOUNT;
    }

    // 4. 原 editAccount() → 处理账户编辑
    @PostMapping(params = "editAccount")
    public String editAccount(
            @Validated(AccountGroup.EditAccount.class) @ModelAttribute("account") Account account,
            Model model
    ) {
        // 原业务逻辑：更新账户 → 重新查询 → 刷新 myList
        accountService.updateAccount(account);
        Account updatedAccount = accountService.getAccount(account.getUsername());
        List<Product> myList = catalogService.getProductListByCategory(updatedAccount.getFavouriteCategoryId());

        // 更新会话属性
        model.addAttribute("account", updatedAccount);
        model.addAttribute("myList", myList);

        return "redirect:/Catalog.action";
    }

    // 5. 原 signonForm() → @DefaultHandler 对应默认请求（无 params 时）
    @GetMapping
    public String signonForm() {
        return SIGNON;
    }

    // 6. 原 signon() → 处理登录逻辑
    @PostMapping(params = "signon")
    public String signon(
            // 单独校验登录参数（也可绑定到 Account）
            @NotBlank(message = "用户名不能为空") @RequestParam("username") String username,
            @NotBlank(message = "密码不能为空") @RequestParam("password") String password,
            Model model,
            RedirectAttributes redirectAttrs,
            HttpSession session
    ) {
        // 原业务逻辑：查询账户
        Account account = accountService.getAccount(username, password);

        if (account == null) {
            // 原 setMessage() → 用 Model 传递错误消息（转发场景）
            setMessage("Invalid username or password.  Signon failed.",model);
            // 原 clear() → 清空会话属性
            clearAccount(model);
            return SIGNON;
        } else {
            // 密码置空（安全）
            account.setPassword(null);
            List<Product> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());

            // 更新会话属性
            model.addAttribute("account", account);
            model.addAttribute("myList", myList);
            model.addAttribute("authenticated", true);

            // 原 s.setAttribute("accountBean", this) → 如需保留可手动存 Session
            session.setAttribute("accountBean", this);

            return "redirect:/Catalog.action";
        }
    }

    // 7. 原 signoff() → 处理登出
    @GetMapping(params = "signoff")
    public String signoff(
            HttpSession session,
            // 清理 @SessionAttributes 绑定的属性
            SessionStatus sessionStatus
    ) {
        // 原 session.invalidate() → 销毁会话（或用 sessionStatus 清理绑定属性）
        session.invalidate();
        // 标记会话属性完成，Spring 会清理 @SessionAttributes 绑定的属性
        sessionStatus.setComplete();
        // 原 clear() → 清空本地数据
        clearAccount(null);

        return "redirect:/Catalog.action";
    }

    // ==================== 辅助方法 ====================
    // 原 clear() → 清空账户相关数据
    private void clearAccount(Model model) {
        if (model != null) {
            model.addAttribute("account", new Account());
            model.addAttribute("myList", null);
            model.addAttribute("authenticated", false);
        }
    }

    // ==================== 校验分组（替代 Stripes 的 on 属性） ====================
    // 用于区分不同场景的校验（newAccount/editAccount/signon）
    public interface AccountGroup {
        interface NewAccount {}
        interface EditAccount {}
        interface Signon {}
    }

    // 如需对 Account 的字段按分组校验，可在 Account 类中加注解：
    // @NotBlank(groups = {AccountGroup.NewAccount.class, AccountGroup.Signon.class})
    // private String username;
}