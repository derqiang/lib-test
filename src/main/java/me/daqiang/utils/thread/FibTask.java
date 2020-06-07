package me.daqiang.utils.thread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName FibTask
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/2/8 9:58 下午
 * @Version 1.0
 **/
public class FibTask implements Callable<ArrayList<Integer>> {

    private int n = 1;
    private int a = 1;
    private int b = 1;

    static private int taskCount = 0;
    private int id = taskCount++;

    FibTask() {
    }
    FibTask(int n) {
        this.n = n;
    }

    @Override
    public ArrayList<Integer> call() throws Exception {
        System.out.println("Task ID：" + this.id);
        ArrayList<Integer> fibs = new ArrayList<>();
        while (b <= n) {
            TimeUnit.SECONDS.sleep(1);
            fibs.add(a);
            int tmp = b;
            b = a + b;
            a = tmp;
        }
        return fibs;
    }
}
