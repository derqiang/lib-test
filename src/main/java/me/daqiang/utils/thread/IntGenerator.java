package me.daqiang.utils.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName IntGenerator
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/2/10 2:28 下午
 * @Version 1.0
 **/
public abstract class IntGenerator {

    private volatile boolean cancled = false;

    public abstract int next();
    public void cancle() {
        cancled = true;
    }

    public boolean isCancled() {
        return cancled;
    }
}

class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;
    @Override
    public int next() {
        // 递增操作不是原子操作
        ++currentEvenValue; // 危险操作 ， 并发访问
//        Thread.currentThread().yield();
        ++currentEvenValue;
        return currentEvenValue;
    }
}

class SynchronizedGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    @Override
    public synchronized int next() { //
        // 递增操作不是原子操作
        ++currentEvenValue;
        Thread.yield(); // Cause failure faster
        ++currentEvenValue;
        return currentEvenValue;
    }
}

class MutexEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();

    @Override
    public synchronized int next() { //
        lock.lock();
        try {
            ++currentEvenValue;
//        Thread.yield(); // Cause failure faster
            ++currentEvenValue;
            return currentEvenValue;
        } finally {
            lock.unlock();
        }
    }
}