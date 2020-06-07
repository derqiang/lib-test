package me.daqiang.utils.block;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SleepBlocked
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/2/11 11:45 下午
 * @Version 1.0
 **/
public class SleepBlocked implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("SleepBlocked InterruptedException!");
        }
    }
}
