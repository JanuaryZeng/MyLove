package cn.edu.nuc.DataBase;

import android.graphics.Bitmap;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MoneyNote implements MultiItemEntity {

    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    private int itemType = 0;

    //data
    private int icon = -1;
    private String money = null;
    private String time = null;
    private String text = null;
    public MoneyNote(){}

    public MoneyNote(int itemType, int icon, String money, String time, String text) {
        this.itemType = itemType;
        this.icon = icon;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
