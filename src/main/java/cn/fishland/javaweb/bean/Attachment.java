package cn.fishland.javaweb.bean;

import java.sql.Blob;
import java.sql.Date;

/**
 * 相关附件
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/20 7:29 下午
 */
public class Attachment extends BasBean {

    private String name;
    private Integer status;
    private Integer type;
    private Blob file;
    private String contentType;

    public Attachment() {
    }

    public Attachment(String name, Integer status, Integer type, Blob file) {
        this.createDate = new Date(System.currentTimeMillis());
        this.name = name;
        this.status = status;
        this.type = type;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", id=" + id +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
