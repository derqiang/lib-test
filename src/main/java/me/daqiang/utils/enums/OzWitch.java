package me.daqiang.utils.enums;


public enum OzWitch {
    WEST("Miss Gulch, aka the Wicked Witch of the West"),
    NORTH("Glinda, the Good Witch of the North"),
    EAST("Wichked Witch of the East , wearer of the Ruby " + "Slippers, crushed by Dorothy's house");

    private String des;
    OzWitch(String des) {
        this.des = des;
    }
    public String getDes() {
        return des;
    }
}
