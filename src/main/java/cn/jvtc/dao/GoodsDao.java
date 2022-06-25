package cn.jvtc.dao;

import cn.jvtc.po.Goods;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * The interface Goods dao.
 */
public interface GoodsDao {
    /**
     * 添加商品记录
     *
     * @param goods the goods
     * @return the int
     */
    int save(@Param("goods") Goods goods);

    /**
     * 模糊查找当前用户商品集合
     *
     * @param goods the goods
     * @return the list
     */
    Page<Goods> findAll(@Param("goods") Goods goods);

    /**
     * 修改商品记录
     *
     * @param goods the goods
     * @return int int
     */
    int update(@Param("goods") Goods goods);

    /**
     * 通过id删除记录
     *
     * @param id the id
     * @return int int
     */
    int delete(@Param("id") Integer id);
}
