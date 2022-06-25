package cn.jvtc.controller;

import cn.jvtc.po.User;
import cn.jvtc.service.UserService;
import cn.jvtc.utils.R;
import cn.jvtc.utils.UserConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * The type User controller.
 *
 * @author 雷族
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Login r.
     *
     * @param user    the user
     * @param session the session
     * @return the r
     */
    @RequestMapping(name = "登录请求处理", value = "/login.action", method = RequestMethod.POST)
    public @ResponseBody
    R login(@RequestBody User user, HttpSession session) {
        R r = new R();
        try {
            user = userService.find(user);
            if (user != null) {
                session.setAttribute("user", user);
                r.setData(user);
                //判断用户是否被禁用
                r.setFlag(UserConstant.USER_STATE_STARTUP.equals(user.getState()));
                r.setMsg(r.getFlag() ? "登录成功！" : "该账号已经被禁用！");
            } else {
                r.setMsg("登录失败！");
            }
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return r;
    }

    /**
     * Register r.
     *
     * @param user    the user
     * @param session the session
     * @return the r
     */
    @ResponseBody
    @RequestMapping("/register.action")
    public R register(@RequestBody User user, HttpSession session) {
        R r = new R();
        try {
            User userSession = (User) session.getAttribute("user");
            //通过session数据判断是注册还是管理员添加账户
            if (userSession != null) {
                //设置管理员为创建人
                user.setCreator(userSession.getName());
            }
            int add = userService.add(user);
            r.setFlag(add > 0);
            r.setMsg(r.getFlag() ? "注册成功！" : "注册失败！");
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return r;
    }

    /**
     * Find by con page r.
     *
     * @param user     the user
     * @param pageCode the page code
     * @param pageSize the page size
     * @param session  the session
     * @return the r
     */
    @RequestMapping("/findByConPage.action")
    public @ResponseBody
    R findByConPage(@RequestBody User user,
                    @RequestParam(value = "pageCode", required = false) int pageCode,
                    @RequestParam(value = "pageSize", required = false) int pageSize, HttpSession session) {
        R r = new R();
        try {
            //获取session会话保存的登录用户
            User userSession = (User) session.getAttribute("user");
            r.setFlag(userSession != null);
            if (userSession != null) {
                //通过user属性值条件查询用户数据
                //此处赋值只为查询当成创建人下数据，不做数据更改
                user.setCreator(userSession.getName());
                r.setData(userService.findByPage(user, pageCode, pageSize));
                r.setMsg("查询成功！");
            } else {
                r.setMsg("数据异常，该用户失效，请检查session");
            }

        } catch (Exception e) {
            r.setMsg(e.getMessage());
            e.printStackTrace();
        }

        return r;
    }

    /**
     * Delete r.
     *
     * @param name the name
     * @return the r
     */
    @DeleteMapping("/delete.action")
    public @ResponseBody
    R delete(String name) {
        R r = new R();
        try {
            int delete = userService.delete(name);
            r.setData(delete);
            r.setFlag(delete > 0);
            r.setMsg(r.getFlag() ? "删除" + delete + "记录成功" : "删除失败");

        } catch (Exception e) {
            r.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return r;
    }

    /**
     * Update r.
     *
     * @param user    the user
     * @param session the session
     * @return the r
     */
    @RequestMapping(value = "/update.action", method = RequestMethod.POST)
    public @ResponseBody
    R update(@RequestBody User user, HttpSession session) {
        R r = new R();
        try {
            User userSession = (User) session.getAttribute("user");
            //设置当前操作用户为修改人
            user.setModifier(userSession.getName());
            int update = userService.update(user);
            r.setData(update);
            r.setFlag(update > 0);
            r.setMsg(r.getFlag() ? "修改" + update + "记录成功" : "修改失败");
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return r;
    }

    /**
     * Logout string.
     *
     * @param session the session
     * @return the string
     */
    @RequestMapping("/logout.action")
    public String logout(HttpSession session) {
        //退出登录
        session.removeAttribute("user");
        return "html/login";
    }

    /**
     * Gets name.
     *
     * @param session the session
     * @return the name
     */
    @RequestMapping("/getName.action")
    public @ResponseBody
    R getName(HttpSession session) {
        R r = new R();
        try {
            User user = (User) session.getAttribute("user");
            r.setFlag(user != null);
            r.setData(user != null ? user.getName() : "未知用户");
            r.setMsg(r.getFlag() ? "查找成功" : "失败，session未保存数据");
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return r;
    }
}
