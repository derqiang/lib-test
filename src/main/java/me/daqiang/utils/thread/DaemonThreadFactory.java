package me.daqiang.utils.thread;

import java.util.concurrent.ThreadFactory;

/**
 * @ClassName DaemonThreadFactory
 * @Description classdes
 * @Author daqiang
 * @Date 2020/3/19 5:40 下午
 * @Version 1.0
 **/
public class DaemonThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
