package cn.jvtc.interceptor;

import cn.jvtc.po.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 雷族
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求的URL
        //登录注册和登录注册跳转请求放行
        String url = request.getRequestURI();
        if (url.indexOf("/toLogin.action") > 0 || url.indexOf("/toRegister.action") > 0 || url.indexOf("/login.action") > 0 || url.indexOf("/register.action") > 0) {
            return true;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return true;
        }

        //session中用户不存在，则跳转至登录页面
        request.getRequestDispatcher("/html/login.html").forward(request, response);
        return false;
    }
}
