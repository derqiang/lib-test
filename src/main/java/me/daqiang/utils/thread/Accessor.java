package me.daqiang.utils.thread;

import java.util.Random;

/**
 * @ClassName Accessor
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/2/10 11:01 下午
 * @Version 1.0
 **/
public class Accessor implements Runnable {

    private int id = 0;

    Accessor(int idn) {
        id = idn;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVarHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "#" + id + ":" + ThreadLocalVarHolder.get();
    }
}


class ThreadLocalVarHolder {
    // ThreadLocal 对象通常当做静态域存储， 只能通过get和set方法访问
    private static ThreadLocal<Integer> value =
            new ThreadLocal<Integer>() {
                private Random rand = new Random(47);

                @Override
                protected synchronized Integer initialValue() { // 使用 同步方式？
                    return rand.nextInt(10000);
                }
            };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }
}
