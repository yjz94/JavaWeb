package cn.fishland.javaweb.dao;

import cn.fishland.javaweb.bean.Praise;

/**
 * 文章交互数据数据库操作类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/29 10:46 下午
 */
public interface PraiseDao {

    /**
     * 根据master查询交互数据
     *
     * @param master 主文章articleId
     * @return 查询结果
     */
    Praise queryByMaster(String master);

}
