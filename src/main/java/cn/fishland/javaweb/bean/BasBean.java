package cn.fishland.javaweb.bean;

import java.sql.Timestamp;

/**
 * 所有实体类的基础类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/20 7:31 下午
 */
public abstract class BasBean {

    protected Integer id;
    protected Timestamp createDate;
    protected Timestamp updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
