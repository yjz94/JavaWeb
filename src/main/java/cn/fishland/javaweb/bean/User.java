package cn.fishland.javaweb.bean;

/**
 * 用户实体类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/17 10:50 上午
 */
public class User {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer status;
    private String code;
    private String photo;

    public User() {
    }

    public User(String name, String email, String password, Integer status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public User(Integer id, String name, String email, String password, Integer status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", code='" + code + '\'' +
                '}';
    }
}
