package cn.fishland.javaweb.dao;

import cn.fishland.javaweb.bean.Praise;

import java.util.List;

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

    /**
     * 获得Praise
     *
     * @param articleIds master
     * @return praise集合
     */
    List<Praise> queryPraiseListByMaster(List<String> articleIds);

    /**
     * 获得排名第一的交互数据
     *
     * @return 交互数据对象
     */
    Praise topPraise();

    /**
     * 保存交互数据
     *
     * @param praise 交互数据对象
     * @return true表示保存成功
     */
    boolean savePraise(Praise praise);

}
