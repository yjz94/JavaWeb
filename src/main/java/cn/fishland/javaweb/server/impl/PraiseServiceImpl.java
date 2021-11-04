package cn.fishland.javaweb.server.impl;

import cn.fishland.javaweb.bean.Praise;
import cn.fishland.javaweb.dao.PraiseDao;
import cn.fishland.javaweb.dao.impl.PraiseDaoImpl;
import cn.fishland.javaweb.server.PraiseService;

import java.util.List;

/**
 * 文章交互数据服务实现类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/29 10:45 下午
 */
public class PraiseServiceImpl implements PraiseService {

    public static PraiseDao praiseDao;

    static {
        praiseDao = new PraiseDaoImpl();
    }

    @Override
    public Praise getPraiseByMaster(String master) {
        return null;
    }

    @Override
    public List<Praise> praiseListByMaster(List<String> masters) {
        return praiseDao.queryPraiseListByMaster(masters);
    }

    @Override
    public Praise topArticle() {
        return praiseDao.topPraise();
    }

    @Override
    public int deletePraise(String... master) {
        return praiseDao.delete(master);
    }

}
