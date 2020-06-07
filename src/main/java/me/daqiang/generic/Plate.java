package me.daqiang.generic;


/**
 * @ClassName Plate
 * @Description classdes
 * @Author daqiang
 * @Date 2020/3/21 11:51 上午
 * @Version 1.0
 **/
public class Plate<T> {
    private T item;
    public Plate(T t) { item = t; }
    public void set(T t) { item = t; }
    public T get() { return item; }

    public static void main(String[] args) {
        Plate<Fruit> plate = new Plate<Fruit>(new Apple());
        plate.set(new Apple());

        System.out.println(plate.get().getClass().equals(Apple.class));
        System.out.println(plate.get() instanceof Apple);
    }
}
