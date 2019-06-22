package cn.edu.nuc.DataBase;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MoneyNote implements MultiItemEntity {

    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    private int itemType;

    //data
    private String money = null;
    private String time = null;
    private String text = null;

    public MoneyNote(int itemType, String money, String time, String text) {
        this.itemType = itemType;
        this.money = money;
        this.time = time;
        this.text = text;
    }

    public static int getLEFT() {
        return LEFT;
    }

    public static int getRIGHT() {
        return RIGHT;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public int getItemType() {
        return itemType;
    }
}
