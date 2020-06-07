package me.daqiang.utils.thread;

/**
 * @ClassName Sleeper
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/2/9 7:34 下午
 * @Version 1.0
 **/
public class Sleeper extends Thread {

    private int duration;
    Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + "was interrupted." + "isInterrupted():" + isInterrupted());
        }
        System.out.println(getName() + "has awakend");
    }
}




