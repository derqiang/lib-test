package me.daqiang.utils.json;

/**
 * @ClassName MedicineSuggestionsContent
 * @Description classdes
 * @Author daqiang
 * @Date 2020/3/17 1:31 下午
 * @Version 1.0
 **/
public class MedicineSuggestionsContent {

    private String name;
    private String color;

    public MedicineSuggestionsContent(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
