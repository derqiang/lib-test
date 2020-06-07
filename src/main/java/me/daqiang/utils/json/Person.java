package me.daqiang.utils.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * @ClassName Person
 * @Description classdes
 * @Author daqiang
 * @Date 2020/3/17 1:10 下午
 * @Version 1.0
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    private String name;
    private Integer age;
    private String address;

    private List<Person> personList;

    public Person(String name, Integer age, String address, List<Person> personList) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.personList = personList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
