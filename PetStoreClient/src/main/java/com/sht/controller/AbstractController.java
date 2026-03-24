package com.sht.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.Serializable;

public abstract class AbstractController implements Serializable {

    private static final long serialVersionUID = -1767714708233127983L;

    // 保留原错误页面常量（路径格式和 Spring MVC 视图解析器匹配）
    protected static final String ERROR = "common/Error"; 
    // 补充：如果视图解析器配置了前缀/后缀（如 prefix=/WEB-INF/jsp/，suffix=.jsp），
    // 这里只需写 "common/Error"，框架会自动拼接为 /WEB-INF/jsp/common/Error.jsp

    // ========== 核心改造：替代原 setMessage() 方法 ==========
    /**
     * 添加请求级别的提示消息（对应原 setMessage()）
     * @param model Spring MVC 的 Model（请求作用域，页面可直接读取）
     * @param value 提示消息内容
     */
    protected void setMessage( String value,Model model) {
        // 往 Model 中添加消息，页面用 ${message} 读取（和原逻辑等价）
        model.addAttribute("message", value);
        // 若需支持多消息，可存入 List：model.addAttribute("messages", new ArrayList<>(List.of(value)));
    }

    /**
     * 重定向场景的消息传递（原 Stripes 重定向传消息也需特殊处理，对应 Spring 的 RedirectAttributes）
     * @param redirectAttrs 重定向属性（消息会存入 flash 作用域，重定向后页面仍能读取）
     * @param value 提示消息内容
     */
    protected void setRedirectMessage(RedirectAttributes redirectAttrs, String value) {
        redirectAttrs.addFlashAttribute("message", value);
    }

    protected HttpServletRequest getRequest() {
        // 方式1：子类通过 @Autowired 注入 HttpServletRequest，这里提供 getter
        // 方式2：通过 RequestContextHolder 获取（无侵入）
        return ((ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).getRequest();
    }
    protected HttpSession getSession() {
        return getRequest().getSession();
    }
    protected HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).getResponse();
    }
}