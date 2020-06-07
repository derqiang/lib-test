package me.daqiang.utils.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author daqiang
 * @date 2020/4/11 3:31 下午
 **/
public class TestFuture {

    public Future<String> calAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            TimeUnit.MICROSECONDS.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }



}
