package cn.jvtc.service.impl;

import cn.jvtc.dao.GoodsDao;
import cn.jvtc.po.Goods;
import cn.jvtc.po.PageBean;
import cn.jvtc.service.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 雷族
 */
@Service()
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    /**
     * 添加商品
     *
     * @param goods the goods
     * @return the int
     * @throws Exception the exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(Goods goods) throws Exception {
        if (goods.getUserId() != null) {
            return goodsDao.save(goods);
        }
        throw new Exception("添加商品用户id不能为空！");
    }

    /**
     * 模糊查询商品集合
     *
     * @param goods the goods
     * @return the list
     */
    @Override
    public List<Goods> findAll(Goods goods) {
        return goodsDao.findAll(goods);
    }

    /**
     * 通过用户id查找该用户全部商品数据
     *
     * @param userId the userId
     * @return the list
     */
    @Override
    public List<Goods> findAll(Integer userId) {
        return goodsDao.findAll(new Goods(userId));
    }

    /**
     * 修改用户记录
     *
     * @param goods the goods
     * @return the int
     * @throws Exception the exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Goods goods) throws Exception {
        if (goods.getId() != null) {
            return goodsDao.update(goods);
        }
        throw new Exception("条件id不能为空！");
    }

    /**
     * 删除用户
     *
     * @param id the id
     * @return the int
     * @throws Exception the exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Integer id) throws Exception {
        return goodsDao.delete(id);
    }

    /**
     * Find by page page bean.
     *
     * @param goods    the goods
     * @param pageCode the page code
     * @param pageSize the page size
     * @return the page bean
     */
    @Override
    public PageBean findByPage(Goods goods, int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode, pageSize);

        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        Page<Goods> page = goodsDao.findAll(goods);
        return new PageBean(page.getTotal(), page.getResult());
    }
}
