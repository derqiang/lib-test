package me.daqiang.nonblocked;

import me.daqiang.utils.thread.DaemonThreadFactory;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * @ClassName InitSeesion
 * @Description classdes
 * @Author daqiang
 * @Date 2020/3/19 5:42 下午
 * @Version 1.0
 **/
public class InitSeesions implements Callable<List<Session>> {

    private List<Session> sessions = new ArrayList<>();
    private static ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());

    private Session session(Consumer<Session> ss) {
        Session s = new Session();
        this.sessions.add(s);
        ss.accept(s);
        return s;
    }
    public static InitSeesions NewSeesion(Consumer<Session> s) {
        InitSeesions init = new InitSeesions();
        init.session(s);
        return init;
    }

    public InitSeesions add(Consumer<Session> s) {
        this.session(s);
        return this;
    }

    public void run() throws Exception {
        Future<List<Session>> results = executorService.submit(this);
        while (results.isDone()) {
            List<Session> sessions = results.get( 1, TimeUnit.SECONDS);
            System.out.println(sessions);
        }
    }

    public Future<List<Session>> run1() throws  Exception {
        return executorService.submit(this);
    }

    @Override
    public List<Session> call() throws Exception {
        this.sessions.forEach(s -> {
            try {
                System.out.println("将要初始化第 " + this.sessions.indexOf(s) + "个会话");
                LocalDateTime curTime = LocalDateTime.now();
                TimeUnit.SECONDS.sleep(3 * this.sessions.indexOf(s));
                System.out.println("第" + this.sessions.indexOf(s) + "个会话，耗时 " + ChronoUnit.SECONDS.between(curTime, LocalDateTime.now()));
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });
        return this.sessions;
    }
}
