package me.daqiang.utils.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JsonCode
 * @Description classdes
 * @Author daqiang
 * @Date 2020/3/17 1:09 下午
 * @Version 1.0
 **/
public class JsonCode {

    private final String jsonstr = "{\"降压\":[\"代文\",\"厄贝沙坦\",\"替米沙坦\",\"卡托普利\",\"蒙诺\",\"雅施达\",\"达爽\",\"伲福达\",\"拜新同\",\"尼群地平\",\"波依定\",\"络活喜\"],\"降脂\":[\"可定\",\"立普妥\",\"阿乐\",\"辛伐他汀\",\"匹伐他汀\",\"非诺贝特\"],\"抗凝\":[\"阿司匹林\",\"阿司匹林肠溶片\",\"泰嘉\",\"华法林钠\",\"培达\"],\"扩冠\":[\"欣康\",\"依姆多\",\"可力\"],\"降糖\":[\"胰岛素\",\"二甲双胍\",\"格列喹酮\",\"格列吡嗪\",\"格列齐特\",\"格列美脲\",\"罗格列酮\",\"吡格列酮\"]}";
    private Person person = new Person("daqiang", Integer.valueOf(14), "河北邢台", null);
    private final String pJsonstr = "{\"name\":\"daqiang\",\"personList\":[{\"name\":\"damiao\"}]}";

    // json -> object
    public void json2Object() {
        Person sug = JSON.parseObject(pJsonstr, Person.class);
        System.out.println(sug.getName());
        System.out.println(sug.getPersonList());


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Person person = objectMapper.readValue(pJsonstr, Person.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(person.getName());

    }
    // json -> object list
    public void json2ObjList() {

    }

    public void obj2JsonStr() {
        String json = JSON.toJSONString(this.person);
        System.out.println(json);
    }

    // Map -> POJO
    public void json2Map2Object() {

        Map<String, Object> personMap = new HashMap<>();
        personMap.put("name", "daqiang");
        personMap.put("age", 24);
        personMap.put("address", "河北邢台");

        ObjectMapper m = new ObjectMapper();
        Person person = m.convertValue(personMap, Person.class);
        System.out.print(person);
        System.out.println(person.getName());
    }

    // string -> pojo


    public static void main(String[] args) {
        new JsonCode().json2Object();
    }
}
