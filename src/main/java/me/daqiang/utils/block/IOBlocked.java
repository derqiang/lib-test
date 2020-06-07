package me.daqiang.utils.block;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName IOBlocked
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/2/11 11:47 下午
 * @Version 1.0
 **/
public class IOBlocked implements Runnable {

    private InputStream input;
    IOBlocked(InputStream in) {
        input = in;
    }
    @Override
    public void run() {
        try {
            System.out.println("Wating for read()");
            input.read();
        } catch (IOException ioe) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted from blocked I/O");
            } else {
                throw new RuntimeException();
            }
        }
    }
}
