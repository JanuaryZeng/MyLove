package cn.edu.nuc.DataBase;

import android.graphics.Bitmap;

import java.util.List;

public class FriendNote {

    private String id = null;
    private String icon = null;
    private String name = null;

    private String text = null;
    private String time = null;
    private List<String> photoUrl = null;
    private String gender = null;


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(List<String> photoUrl) {

        this.photoUrl = photoUrl;
    }

    public FriendNote(){}

    public FriendNote(String icon, String name, String text, String time, List<String> photoUrl) {
        this.icon = icon;
        this.name = name;
        this.text = text;
        this.time = time;
        this.photoUrl = photoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
