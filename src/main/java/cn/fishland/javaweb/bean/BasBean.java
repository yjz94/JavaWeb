package cn.fishland.javaweb.bean;

import java.sql.Date;

/**
 * 所有实体类的基础类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/20 7:31 下午
 */
public abstract class BasBean {

    protected Integer id;
    protected Date createDate;
    protected Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
