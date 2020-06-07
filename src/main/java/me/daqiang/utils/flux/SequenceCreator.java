package me.daqiang.utils.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author daqiang
 * @date 2020/4/12 11:43 上午
 **/

public class SequenceCreator {
    public Consumer<List<Integer>> consumer;

    public Flux<Integer> createNumSeq() {
        return Flux.create(sink -> SequenceCreator.this.consumer = items -> items.forEach(sink::next));
    }


    public Flux<Object> testFlux() {
        return Flux.create(sink -> {
            System.out.println(" 订单生产中 。。。 ");
            SequenceCreator.this.consumer = item -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    for (int i = 0; i < 10; i++) {
                        sink.next("给你 - " + i);
                    }
                    sink.complete();
                } catch (Exception e) {
                    sink.error(e);
                }
            };
        }, FluxSink.OverflowStrategy.LATEST).doOnRequest(l -> {
            System.out.println(" 来订单啦 -> " + l);
        }).doOnNext(next -> {
            next = next + "next";
            System.out.println(" next finished! -> " + next);
        }).doOnCancel(() -> {
            System.out.println("消费者 取消订单 ---- ");
        }).doOnError(error -> {
            System.out.println("外面消费情况出现异常 -> " + error.getMessage());
        }).doFinally(done -> {
            System.out.println("mono done -> " + done);
        });
    }

    public Flux<Object> testMono() {
        return null;
    }
}
