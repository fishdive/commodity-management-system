package cn.jvtc.po;

import cn.jvtc.utils.UserConstant;

import java.io.Serializable;

/**
 * The type User.
 *
 * @author 雷族
 */
public class User implements Serializable {
    /**
     * 主键，自增
     */
    private Integer id;
    /**
     * 用户名，不为空，唯一
     */
    private String name;
    /**
     * 密码，为空时给默认值123456
     */
    private String password;
    /**
     * 状态：01启用，02弃用(默认为01)
     */
    private String state;
    /**
     * 等级：01管理员，02普通用户(默认为02)
     */
    private String grade;
    /**
     * 创建该账户的角色，分为自己注册和管理员创建
     */
    private String creator;
    /**
     * 修改记录角色
     */
    private String modifier;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param name the name
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new User.
     *
     * @param name     the name
     * @param password the password
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state '状态：01启用，02弃用(默认为01)'
     */
    public void setState(String state) {
        if (UserConstant.USER_STATE_STARTUP.equals(state) || UserConstant.USER_STATE_STOPPED.equals(state)) {
            this.state = state;
        }
    }

    /**
     * Gets grade.
     *
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets grade.
     *
     * @param grade the grade '等级：01管理员，02普通用户(默认为02)'
     */
    public void setGrade(String grade) {
        if (UserConstant.USER_GRADE_ADMIN.equals(grade) || UserConstant.USER_GRADE_ORDINARY.equals(grade)) {
            this.grade = grade;
        }
    }

    /**
     * Gets creator.
     *
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Sets creator.
     *
     * @param creator the creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * Gets modifier.
     *
     * @return the modifier
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * Sets modifier.
     *
     * @param modifier the modifier
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                ", grade='" + grade + '\'' +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                '}';
    }
}
