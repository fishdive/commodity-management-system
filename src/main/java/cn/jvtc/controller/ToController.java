package cn.jvtc.controller;

import cn.jvtc.po.User;
import cn.jvtc.utils.UserConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * The type To controller.
 *
 * @author 雷族
 */
@Controller
@RequestMapping()
public class ToController {
    /**
     * To index string.
     *
     * @return the string
     */
    @RequestMapping("/toIndex.action")
    public String toIndex() {
        return "html/index";
    }

    /**
     * To login string.
     *
     * @return the string
     */
    @RequestMapping("/toLogin.action")
    public String toLogin() {
        return "html/login";
    }

    /**
     * To register string.
     *
     * @return the string
     */
    @RequestMapping("/toRegister.action")
    public String toRegister() {
        return "html/register";
    }

    /**
     * To goods string.
     *
     * @return the string
     */
    @RequestMapping("/toGoods.action")
    public String toGoods() {
        return "html/goods";
    }

    /**
     * To user string.
     *
     * @return the string
     */
    @RequestMapping("/toUser.action")
    public String toUser(HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user != null && UserConstant.USER_GRADE_ORDINARY.equals(user.getGrade())) {
            System.out.println("普通用户权限不足，请联系管理员");
            return "html/error";
        }
        return "html/user";
    }
}
