package cn.fishland.javaweb.server.impl;

import cn.fishland.javaweb.bean.Tag;
import cn.fishland.javaweb.dao.TagDao;
import cn.fishland.javaweb.dao.impl.TagDaoImpl;
import cn.fishland.javaweb.server.TagService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 标签服务实现类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/11/5 7:45 下午
 */
public class TagServiceImpl implements TagService {

    private static TagDao tagDao;

    static {
        tagDao = new TagDaoImpl();
    }

    @Override
    public List<String> getAllTagName() {
        List<Tag> tags = tagDao.queryAllTag();
        if (CollectionUtils.isEmpty(tags)) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (Tag tag : tags) {
            if (tag.getStatus() == 1) {
                list.add(tag.getName());
            }
        }
        return list;
    }

    @Override
    public boolean insert(Tag tag) {
        int insert = tagDao.insert(tag);
        if (insert == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAll(int... ids) {
        for (int id : ids) {
            tagDao.deleteById(id);
        }
        return true;
    }

    @Override
    public boolean deleteAllByMaster(String... masters) {
        for (String master : masters) {
            tagDao.deleteByMaster(master);
        }
        return true;
    }

}
