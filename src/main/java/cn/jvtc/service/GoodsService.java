package cn.jvtc.service;

import cn.jvtc.po.Goods;
import cn.jvtc.po.PageBean;

import java.util.List;

/**
 * The interface Goods cn.jtvc.service.
 *
 * @author 雷族
 */
public interface GoodsService {
    /**
     * 添加商品
     *
     * @param goods the goods
     * @return the int
     * @throws Exception the exception
     */
    int add(Goods goods) throws Exception;

    /**
     * 模糊查询商品集合
     *
     * @param goods the goods
     * @return the list
     */
    List<Goods> findAll(Goods goods);

    /**
     * 通过用户id查找该用户全部商品数据
     *
     * @param userId the userId
     * @return the list
     */
    List<Goods> findAll(Integer userId);

    /**
     * 修改用户记录
     *
     * @param goods the goods
     * @return the int
     * @throws Exception the exception
     */
    int update(Goods goods) throws Exception;

    /**
     * 删除用户
     *
     * @param id the id
     * @return the int
     * @throws Exception the exception
     */
    int delete(Integer id) throws Exception;


    /**
     * Find by page page bean.
     *
     * @param goods    the goods
     * @param pageCode the page code
     * @param pageSize the page size
     * @return the page bean
     */
    public PageBean findByPage(Goods goods, int pageCode, int pageSize);
}
