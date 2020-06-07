package me.daqiang.utils.block;

/**
 * @ClassName SynchronizedBlokced
 * @Description TODO
 * @Author daqiang
 * @Date 2020/2/11 11:54 下午
 * @Version 1.0
 **/
public class SynchronizedBlokced implements Runnable {

    SynchronizedBlokced() {
        new Thread(() -> {
            f();
        });
    }

    private synchronized void f() {
        while (true)
            Thread.yield();
    }

    @Override
    public void run() {
        System.out.println("Trying to call f()");
        f();
        System.out.println("Exiting SynchronizedBlocked.run()");
    }
}
