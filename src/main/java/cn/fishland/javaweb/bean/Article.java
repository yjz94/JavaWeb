package cn.fishland.javaweb.bean;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 储存文章对象
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/23 10:46 上午
 */
public class Article {

    protected Integer id;
    protected Timestamp createDate;
    protected Timestamp updateDate;
    private String content;
    private String title;
    private String tags;
    private Integer status;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Article{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", status=" + status +
                ", id=" + id +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
