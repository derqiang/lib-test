package me.daqiang;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * @ClassName KAppTest
 * @Description TODO
 * @Author daqiang
 * @Date 2020/2/15 5:29 下午
 * @Version 1.0
 **/
public class KAppTests  {


    @Test
    public void setup() {
        System.out.println("Start Execute Java KAppTest Testcase ...");
    }

    @Test
    public void fluxTest() {

        Flux.create(fluxSink -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000 * i);
                    fluxSink.next(i);
                }
            } catch (InterruptedException e) {
            }

            fluxSink.next("1");
        }).doOnComplete(() -> {
            System.out.println("任务整体完成！");
        }).subscribe(System.out::println);

    }

    @Test
    public void monoTest() {
        Mono.justOrEmpty(Optional.of(1)).subscribe(System.out::println);
    }

    @Test
    public void fileTest() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("/Users/dongfuqiang/Desktop/PAN/SpaceV/Labs/Test/Java/lib-test/data/nio-data.txt", "rw");

        // Basic Channel Test
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while (buf.hasRemaining()) {
                System.out.println((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }

        aFile.close();
    }
    @Test
    public void fileTest2() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("/Users/dongfuqiang/Desktop/PAN/SpaceV/Labs/Test/Java/lib-test/data/nio-data.txt", "rw");

        System.out.println( "长度" + aFile.length());
        System.out.println(" 文本头指针: " + aFile.getFilePointer());
        aFile.seek(4);
        System.out.println(" 再次获取文本头指针: " + aFile.getFilePointer());

        aFile.write(1);
        aFile.write("daqiang".getBytes());
        aFile.writeByte(3);;
    }


    @Test
    public void dateTest() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        int num = cal.get(Calendar.WEEK_OF_YEAR);
        System.out.println(String.valueOf(cal.get(Calendar.YEAR)) + "->" + num);
    }


}
