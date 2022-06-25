package cn.jvtc.service.impl;

import cn.jvtc.dao.UserDao;
import cn.jvtc.po.PageBean;
import cn.jvtc.po.User;
import cn.jvtc.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type User cn.jtvc.service.
 *
 * @author 雷族
 */
@Service()
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * Add int.
     *
     * @param user the user
     * @return the int
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(User user) throws Exception {
        if (user.getName() != null && !"".equals(user.getName())) {
            if (userDao.findAll(new User(user.getName())).size() > 0) {
                throw new Exception("用户名已存在，请重新创建！");
            }
            return userDao.save(user);
        }
        throw new Exception("用户名不能为空");
    }

    /**
     * Find user user.
     *
     * @param user the user
     * @return the user
     */
    @Override
    public User find(User user) throws Exception {
        if (user.getName() != null && !"".equals(user.getName()) && user.getPassword() != null && !"".equals(user.getPassword())) {
            return userDao.find(user);
        }
        throw new Exception("用户名和密码不能为空");
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    @Override
    public List<User> findAll(User user) {

        return userDao.findAll(user);
    }

    /**
     * Find all list.
     *
     * @param creator the creator
     * @return the list
     */
    @Override
    public List<User> findAll(String creator) {
        User user = new User();
        user.setCreator(creator);
        return userDao.findAll(user);
    }

    /**
     * Update int.
     *
     * @param user the user
     * @return the int
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(User user) throws Exception {
        if (user.getName() != null && !"".equals(user.getName())) {
            return userDao.update(user);
        }
        throw new Exception("用户名不能为空");
    }

    /**
     * Delete int.
     *
     * @param name the name
     * @return the int
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(String name) throws Exception {
        if (name != null && !"".equals(name)) {
            return userDao.delete(name);
        }
        throw new Exception("用户名不能为空");
    }

    /**
     * Find by page page bean.
     *
     * @param user     the user
     * @param pageCode the page code
     * @param pageSize the page size
     * @return the page bean
     */
    @Override
    public PageBean findByPage(User user, int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode, pageSize);

        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        Page<User> page = userDao.findAll(user);
        return new PageBean(page.getTotal(), page.getResult());
    }
}
