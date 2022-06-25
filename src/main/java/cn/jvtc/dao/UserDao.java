package cn.jvtc.dao;

import cn.jvtc.po.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;


/**
 * The interface User cn.jtvc.dao.
 */
public interface UserDao {
    /**
     * 添加用户记录
     *
     * @param user the user
     * @return the int
     */
    int save(@Param("user") User user);

    /**
     * 通过用户名和密码精准查找用户
     *
     * @param user the user
     * @return the user
     */
    User find(@Param("user") User user);


    /**
     * 模糊查找用户集合
     *
     * @param user the user
     * @return the list
     */
    Page<User> findAll(@Param("user") User user);

    /**
     * 修改用户记录
     *
     * @param user the user
     * @return int int
     */
    int update(@Param("user") User user);

    /**
     * 通过唯一用户名删除记录
     *
     * @param name the name
     * @return int int
     */
    int delete(@Param("name") String name);
}
