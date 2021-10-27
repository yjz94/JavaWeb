package cn.fishland.javaweb.bean;

/**
 * 文章点赞等交互数据
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/24 11:20 下午
 */
public class Praise extends BasBean {

    private Integer thumbsUp;
    private Integer thumbsDown;
    private Integer message;
    private Integer status;

    public Integer getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(Integer thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public Integer getThumbsDown() {
        return thumbsDown;
    }

    public void setThumbsDown(Integer thumbsDown) {
        this.thumbsDown = thumbsDown;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
