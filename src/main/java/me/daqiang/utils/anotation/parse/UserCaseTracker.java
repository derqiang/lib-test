package me.daqiang.utils.anotation.parse;

import me.daqiang.utils.anotation.PasswordUtils;
import me.daqiang.utils.anotation.define.UseCase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName UserCaseTracker
 * @Description TODO
 * @Author daqiang
 * @Date 2020/2/12 12:16 下午
 * @Version 1.0
 **/
public class UserCaseTracker {

    public void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println("Found Use Case: " + uc.id() + " " + uc.des());
                useCases.remove(Integer.valueOf(uc.id()));
            }
        }

        for (int i : useCases)
            System.out.println("Warning Missing use case - " + i);
    }

    public static void test() {
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        new UserCaseTracker().trackUseCases(useCases, PasswordUtils.class);
    }
}
