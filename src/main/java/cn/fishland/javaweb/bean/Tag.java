package cn.fishland.javaweb.bean;

/**
 * 标签类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/24 11:22 下午
 */
public class Tag extends BasBean {

    private String name;
    private String master;
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", name='" + name + '\'' +
                ", master='" + master + '\'' +
                ", status=" + status +
                '}';
    }
}
