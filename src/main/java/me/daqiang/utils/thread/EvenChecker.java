package me.daqiang.utils.thread;

/**
 * @ClassName EvenChecker
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/2/10 3:16 下午
 * @Version 1.0
 **/
public class EvenChecker implements Runnable {

    private IntGenerator generator;
    private final int id;

    EvenChecker(IntGenerator g, int ident) {
        id = ident;
        generator = g;
    }

    @Override
    public void run() {
        while (!generator.isCancled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even!");
                generator.cancle(); // Cancels all EvenCheckers
            }
        }
    }
}
