package cn.fishland.javaweb.bean;

/**
 * 储存文章对象
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/23 10:46 上午
 */
public class Article extends BasBean {

    private String articleId;
    private String content;
    private String title;
    private String tags;
    private Integer status;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
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
                "id=" + id +
                ", articleId='" + articleId + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", status=" + status +
                '}';
    }
}
