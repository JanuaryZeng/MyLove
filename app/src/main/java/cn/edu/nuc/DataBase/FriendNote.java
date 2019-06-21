package cn.edu.nuc.DataBase;

import android.graphics.Bitmap;

import java.util.List;

public class FriendNote {


    private Bitmap icon = null;
    private String name = null;

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
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

    private String text = null;
    private String time = null;
    private List<String> photoUrl = null;

    public FriendNote(Bitmap icon, String name, String text, String time, List<String> photoUrl) {
        this.icon = icon;
        this.name = name;
        this.text = text;
        this.time = time;
        this.photoUrl = photoUrl;
    }


}
