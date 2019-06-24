package cn.edu.nuc.Helper;

public class IDHelper  {
    private static int loverID = -1;

    public static int getID(){
        return loverID;
    }

    public static int getLoverID() {
        return loverID;
    }

    public static void setLoverID(int loverID) {
        IDHelper.loverID = loverID;
    }
}
