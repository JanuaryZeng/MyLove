package cn.edu.nuc.DataBase;

import android.graphics.Bitmap;

public class FirstTimeNote {


    private String date = null;

    private Bitmap bitmap = null;

    public FirstTimeNote(String date, Bitmap bitmap) {
        this.date = date;
        this.bitmap = bitmap;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
