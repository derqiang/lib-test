package me.daqiang.reactor;

import reactor.core.publisher.Flux;

import java.sql.Time;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Combine
 * @Description classdes
 * @Author daqiang
 * @Date 2020/3/11 9:22 上午
 * @Version 1.0
 **/
public class Combine {

    public static Flux<String> uplodImgToOSS() {

        return Flux.generate(stringSynchronousSink -> {
            System.out.println("uplodImgToOSS - 当前线程: " + Thread.currentThread());
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {

            }
            stringSynchronousSink.next("https://baidu.com");
            stringSynchronousSink.complete();
        });
    }

    public static Flux<Integer> updateUserInfo() {

        return Flux.interval(Duration.ofSeconds(1)).generate(integerSynchronousSink -> {

            System.out.println("uplodImgToOSS - 当前线程: " + Thread.currentThread());
            integerSynchronousSink.next(1000);
            integerSynchronousSink.complete();
        });
    }
}
