package cn.jvtc.service;

import cn.jvtc.po.PageBean;
import cn.jvtc.po.User;

import java.util.List;

/**
 * The interface User cn.jtvc.service.
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param user the user
     * @return the int
     * @throws Exception the exception
     */
    int add(User user) throws Exception;

    /**
     * 用户名密码精准查找用户
     *
     * @param user the user
     * @return the user
     * @throws Exception the exception
     */
    User find(User user) throws Exception;

    /**
     * 模糊查询用户集合
     *
     * @param user the user
     * @return the list
     */
    List<User> findAll(User user);

    /**
     * Find all list.
     *
     * @param creator the creator
     * @return the list
     */
    List<User> findAll(String creator);

    /**
     * 修改用户记录
     *
     * @param user the user
     * @return the int
     * @throws Exception the exception
     */
    int update(User user) throws Exception;

    /**
     * 删除用户
     *
     * @param name the name
     * @return the int
     * @throws Exception the exception
     */
    int delete(String name) throws Exception;

    /**
     * Find by page page bean.
     *
     * @param user     the user
     * @param pageCode the page code
     * @param pageSize the page size
     * @return the page bean
     */
    public PageBean findByPage(User user, int pageCode, int pageSize);
}
