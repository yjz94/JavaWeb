package cn.fishland.javaweb.server;

import cn.fishland.javaweb.bean.Praise;

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


}
