package me.daqiang.utils.thread;

import me.daqiang.utils.thread.Sleeper;

/**
 * @ClassName Joiner
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/2/9 7:35 下午
 * @Version 1.0
 **/
public class Joiner extends Thread {
    private Sleeper sleeper;

    Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted...");
        }
        System.out.println(getName() + " join completed.");
    }
}
