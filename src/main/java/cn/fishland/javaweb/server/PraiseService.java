package cn.fishland.javaweb.server;

import cn.fishland.javaweb.bean.Praise;

import java.util.List;

/**
 * 文章交互数据服务类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/29 10:41 下午
 */
public interface PraiseService {

    /**
     * 根据文章articleId查询交互数据
     *
     * @param master {@link cn.fishland.javaweb.bean.Article} 的articleId属性值相对应
     * @return 不成功返回null
     */
    Praise getPraiseByMaster(String master);

    /**
     * 根据master查询内容
     *
     * @param masters master列表
     * @return 交互数据集合
     */
    List<Praise> praiseListByMaster(List<String> masters);

    /**
     * top第一交互数据
     * @return
     */
    Praise topArticle();

    /**
     * 删除交互内容
     * @param master
     * @return 执行条数
     */
    int deletePraise(String... master);

}
