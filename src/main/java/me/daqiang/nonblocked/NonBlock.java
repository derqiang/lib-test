package me.daqiang.nonblocked;

import me.daqiang.utils.thread.DaemonThreadFactory;
import org.apache.ibatis.executor.Executor;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName NonBlock
 * @Description classdes
 * @Author daqiang
 * @Date 2020/3/19 5:44 下午
 * @Version 1.0
 **/
public class NonBlock {

    public void go() throws Exception {

        System.out.println("-------> 执行必要代码 1");

        Flux.range(1, 100).filter(slink -> {
            return slink % 2 == 0;
        }).subscribe(System.out::println);

        System.out.println("-------> 执行必要代码 2");
        System.out.println("<------- 返回用户数据啦 End！ ");
    }

    public static void main(String[] args) throws Exception {
        new NonBlock().go();
        new NonBlock().go();
        new NonBlock().go();
        new NonBlock().go();
        new NonBlock().go();


//        TimeUnit.SECONDS.sleep(100);
    }
}
