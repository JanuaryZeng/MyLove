package cn.edu.nuc.DataBase;

import android.graphics.Bitmap;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class PencilNote implements MultiItemEntity {
    public static final int UP = 1;
    public static final int Down = 2;
    public static final int TYPE = 3;
    public static final int Fin = 4;
    public static final int Blank = 5;
    private int itemType;
    private String date = null;
    private int icon = -1;
    private String name = null;
    private String text = null;
    private String type = null;
    private int typeNum = 0;
    private float money = 0;
    private int year = 0;
    private int month = 0;
    private int day = 0;

    private int id = -1;

    public PencilNote(){}

    public PencilNote(int itemType, int icon, String name, String text) {
        this.itemType = itemType;
        this.icon = icon;
        this.name = name;
        this.text = text;
    }

    public PencilNote(int itemType, int icon, String name, String text, float money, int year, int month, int day) {
        this.itemType = itemType;
        this.icon = icon;
        this.name = name;
        this.text = text;
        this.money = money;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public PencilNote(int itemType, String type, int typeNum) {
        this.itemType = itemType;
        this.type = type;
        this.typeNum = typeNum;
    }


    public static int getUP() {
        return UP;
    }

    public static int getDown() {
        return Down;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }


    @Override
    public int getItemType() {
        return itemType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(int typeNum) {
        this.typeNum = typeNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
