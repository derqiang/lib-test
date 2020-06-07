package me.daqiang.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/1/28 6:20 下午
 * @Version 1.0
 **/
public class User {

    private Integer id;
    private String user_name;
    private Date birthday;
    private Integer age;
    private String phone;


    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getUser_name() {
        return user_name;
    }

}
