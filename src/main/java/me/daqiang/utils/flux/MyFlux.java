package me.daqiang.utils.flux;

import java.util.function.Function;
import java.util.stream.Collectors;
import me.daqiang.generic.Apple;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.Logger;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @author daqiang
 * @date 2020/4/12 11:15 上午
 **/
public class MyFlux {

    public static Flux<Integer> fibonaciiNewStates() {
        return Flux.generate(() -> Tuples.of(0, 1), (state, sink) -> {
            sink.next(state.getT1());
            return Tuples.of(state.getT2(), state.getT1() + state.getT2());
        });
    }

    public static Flux<Integer> fibonacciState(Integer limit) {
        return Flux.generate(() -> new Fib(0, 1), (state, sink) -> {
           sink.next(state.getFormer());
           if (state.getLatter() > limit) {
               sink.complete();
           }
           int temp = state.getFormer();
           state.setFormer(state.getLatter());
           state.setLatter(temp + state.getLatter());
           return state;
        });
    }

    public static void fibonacciMultiSeqCreate() {

        List<Integer> seq1 = fibonaciiNewStates().take(3).collectList().block();
        List<Integer> seq2 = fibonaciiNewStates().take(4).collectList().block();

        SequenceCreator fg = new SequenceCreator();
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                fg.consumer.accept(seq2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                fg.consumer.accept(seq1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        List<Integer> consolidated = new ArrayList<>();

//        fg.createNumSeq().handle((num, sink) -> {
//            if (num % 2 == 0) {
//                sink.next(num / 2);
//            }
//        }).subscribe(System.out::println);

        fg.createNumSeq().doOnNext(wait -> {
            System.out.println("开始等" + wait);
            Flux.range(1, 1).takeWhile(condtion -> {
                try {
                    System.out.println(" 直到  -> ");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }).blockFirst();
        }).subscribe(System.out::println);

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //[0, 0, 1, 1, 2, 1, 1]
        //[0, 0, 1, 1, 2, 1, 1]
        //[0, 0, 1, 1, 1, 1, 2]
        //[0, 0, 1, 1, 2, 1, 1]
//        System.out.println(consolidated);

    }
    public static void createSubscribers(Flux<Integer> flux) {
        IntStream.range(1, 5).forEach(value ->
                flux.subscribe(integer -> System.out.println("---------" + value + " consumer processed "
                        + integer + " using thread: " + Thread.currentThread().getName())));
    }

    // 只属于a ， 只属于b， 同时属于a，b
    public static Tuple3<Flux<String>,Flux<String>,Flux<String>> join(List<String> a, List<String> b) {
        return Tuples.of(
            Flux.fromIterable(a).flatMap(item -> Flux.fromIterable(b)
                .reduce(false, (l, r) -> l | r.equals(item))
                .filter(r -> !r).map(i -> item)),
            Flux.fromIterable(b).flatMap(item -> Flux.fromIterable(a)
                .reduce(false, (l, r) -> l | r.equals(item))
                .filter(r -> !r).map(i -> item)),
            Flux.fromIterable(a).flatMap(item -> Flux.fromIterable(b)
                .reduce(false, (l, r) -> l | r.equals(item))
                .filter(r -> r).map(i -> item))
        );
    }

    public static void main(String[] args) throws Exception {

//        MyFlux.fibonaciiNewStates().take(6).subscribe(System.out::println);
//        MyFlux.fibonacciState(18).subscribe(System.out::println);
//        MyFlux.fibonacciMultiSeqCreate();

        Flux.range(1, 10).filter(i -> i%2==0).map(m -> {System.out.println(m); return m;}).subscribe();
    }

}
