package cn.fishland.javaweb.bean;

/**
 * 文章点赞等交互数据
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/24 11:20 下午
 */
public class Praise extends BasBean {

    private Integer thumbsUp = 0;
    private Integer read = 0;
    private Integer thumbsDown = 0;
    private Integer message = 0;
    private Integer status;
    private String master;

    public Integer getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(Integer thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
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

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    @Override
    public String toString() {
        return "Praise{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", thumbsUp=" + thumbsUp +
                ", read=" + read +
                ", thumbsDown=" + thumbsDown +
                ", message=" + message +
                ", status=" + status +
                ", master='" + master + '\'' +
                '}';
    }
}
