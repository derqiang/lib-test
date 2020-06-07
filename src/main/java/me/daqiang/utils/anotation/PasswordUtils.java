package me.daqiang.utils.anotation;

import me.daqiang.utils.anotation.define.UseCase;

import java.util.List;

/**
 * @ClassName PasswordUtils
 * @Description TODO
 * @Author daqiang
 * @Date 2020/2/12 12:17 下午
 * @Version 1.0
 **/
public class PasswordUtils {

    @UseCase(id = 47, des = "Pwds must contain at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase(id = 48)
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id = 49, des = "New Password con't equal previously used ones")
    public boolean checkForNewPassword(List<String> prevPwds, String pwd) {
        return !prevPwds.contains(pwd);
    }

}
