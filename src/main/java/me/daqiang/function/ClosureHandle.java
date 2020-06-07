package me.daqiang.function;

import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * @ClassName ClosureHandle
 * @Description classdes
 * @Author daqiang
 * @Date 2020/3/21 11:45 上午
 * @Version 1.0
 **/

@FunctionalInterface
public interface ClosureHandle<I1, I2 extends Consumer> {

    ClosureHandle apply(I1 i1, I2 i2);
}
