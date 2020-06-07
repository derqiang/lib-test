package me.daqiang.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName Role
 * @Description TODO
 * @Author daqiang
 * @Date 2020/2/12 4:53 下午
 * @Version 1.0
 **/
@AllArgsConstructor
@Getter
public enum  Role {
    DOCTOR("doctor"),
    PATIENT("patient");
    Role(String des) {}
}
