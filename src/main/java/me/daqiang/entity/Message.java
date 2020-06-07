package me.daqiang.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName Message
 * @Description TODO
 * @Author daqiang
 * @Date 2020/2/14 6:24 下午
 * @Version 1.0
 **/
@Data
public class Message {
    public String mid;
    @Getter
    @Setter
    public String content;
}
