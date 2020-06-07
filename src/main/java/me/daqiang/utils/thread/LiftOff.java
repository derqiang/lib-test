package me.daqiang.utils.thread;





import java.util.concurrent.TimeUnit;

/**
 * @ClassName LiftOff
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/2/8 5:55 下午
 * @Version 1.0
 **/
public class LiftOff implements Runnable {

    protected int countDown = 10;
    private static int tasksCount = 0;
    private final int id = tasksCount++;

    public LiftOff() {}
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + ((countDown > 0) ? countDown : "LiftOff!") + ")," ;
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {

            }
            Thread.yield(); // Java 线程机制的一部分，告诉【调度器】， 目前可以切换给其他任务执行了。优化的方式之一。
        }
    }



}


