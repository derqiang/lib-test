package me.daqiang.utils.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CircularSet
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/2/10 4:44 下午
 * @Version 1.0
 **/
public class CircularSet {

    private int[] array;
    private int len;
    private int index = 0;

    public CircularSet(int size) {
        array = new int[size];
        len = size;

        for (int i = 0; i < size; i++)
            array[i] = -1;
    }

    public synchronized void add(int i) {
        array[index] = i;
        index = ++index % len;
    }

    public synchronized boolean contains(int val) {
        for (int i = 0; i < len; i++)
            if (array[i] == val) return true;
        return false;
    }
}

class SerialNumberChecker {
    private static final int SIZE = 10;
    private static CircularSet series = new CircularSet(1000);

    private static ExecutorService exec = Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable {
        @Override
        public void run() {
            while (true) {
            }
        }
    }
}
