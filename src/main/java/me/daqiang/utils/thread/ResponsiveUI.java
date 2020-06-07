package me.daqiang.utils.thread;

/**
 * @ClassName ResponsiveUI
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/2/9 11:33 下午
 * @Version 1.0
 **/
public class ResponsiveUI extends Thread {

    public static volatile double d = 1L;

    ResponsiveUI() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (true) {
            d = d + (Math.PI + Math.E) / d;
        }
    }
}
