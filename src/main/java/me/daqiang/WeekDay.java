package me.daqiang;

public enum WeekDay {

    SUN(0, "周日"),
    FRI(5, "周五");

    private int code;
    private String des;

    WeekDay(int code, String des) {
        this.code = code;
        this.des = des;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }
}
