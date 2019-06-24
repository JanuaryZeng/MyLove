package cn.edu.nuc.DBOpenHelper;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nuc.DataBase.MoneyNote;
import cn.edu.nuc.Helper.BitString;
import cn.edu.nuc.Helper.TimeForm;

public class DBMoneyNote {
    private Connection conn=null; //打开数据库对象
    private PreparedStatement ps=null;//操作整合sql语句的对象
    private ResultSet rs=null;//查询结果的集合

    //DBService 对象
    public static DBMoneyNote dbService=null;

    /**
     * 构造方法 私有化
     * */

    private DBMoneyNote(){

    }
    /**
     * 获取MySQL数据库单例类对象
     * */

    public static DBMoneyNote getDbService(){

        if(dbService==null){
            dbService=new DBMoneyNote();
        }
        return dbService;
    }


    public List<MoneyNote> select(final int loverID){
        //结果存放集合
        //MySQL 语句
        Log.e("asd","*************************************");
        final String sql="select * from moneychangetable where loverid = ?";
        //获取链接数据库对象

        List<MoneyNote> list=new ArrayList<MoneyNote>();
        conn = DBOpenHelper.getConn();
        if (conn == null)
            Log.e("asd", "********************");
        try {
            if (conn != null && (!conn.isClosed())) {
                ps = (PreparedStatement) conn.prepareStatement(sql);
                if (ps != null) {
                    ps.setInt(1, loverID);
                    rs = ps.executeQuery();
                    if (rs != null) {
                        while (rs.next()) {
                            MoneyNote moneyNote = new MoneyNote();
                            moneyNote.setText(rs.getString("moneytypeid"));
                            Log.e("qwerty", "1233333333333333333333");
                            moneyNote.setTime(rs.getString("moneydate"));
                            moneyNote.setMoney(rs.getString("moneynumber"));
                            String sql1 = "select * from moneytypetable where moneynumber = ?";
                            PreparedStatement ps1 = conn.prepareStatement(sql1);
                            ps1.setString(1, rs.getString("moneytypeid"));
                            ResultSet rs1 = ps1.executeQuery();
                            rs1.next();
                            moneyNote.setIcon(rs1.getInt("moneytypeicon"));
                            moneyNote.setItemType(rs1.getInt("moneydirction"));
                            list.add(moneyNote);
                            DBOpenHelper.closeAll(conn,ps,rs);//关闭相关操作
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    /**
     * 删除数据  删
     * */

    public int delete(int loverID,String time){
        int result=-1;
        if((!StringUtils.isEmpty(time))){
            //获取链接数据库对象
            conn= DBOpenHelper.getConn();
            //MySQL 语句
            String sql="delete from MoneyChangeTable where loverID = ? and MoneyDate=?";
            try {
                boolean closed=conn.isClosed();
                if((conn!=null)&&(!closed)){
                    ps= (PreparedStatement) conn.prepareStatement(sql);
                    ps.setInt(1,loverID);
                    ps.setString(2, time);
                    result=ps.executeUpdate();//返回1 执行成功
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return result;
    }

    /**
     * 修改数据库中某个对象的状态   改
     * */

    public int update(int loverID,MoneyNote money){
        int result=-1;
        if(null != money){
            //获取链接数据库对象
            conn= DBOpenHelper.getConn();
            //MySQL 语句
            String sql="update MoneyChangeTable set moneytypeid = ? , moneydate = ?" +
                    ", moneynumber = ? where lover = ?";
            try {
                boolean closed=conn.isClosed();
                if(conn!=null&&(!closed)){
                    ps= (PreparedStatement) conn.prepareStatement(sql);
                    ps.setString(1,money.getText());//第一个参数state 一定要和上面SQL语句字段顺序一致
                    ps.setString(2,TimeForm.getNowTime());//第二个参数 phone 一定要和上面SQL语句字段顺序一致
                    ps.setString(3,money.getMoney());
                    ps.setInt(4,loverID);
                    result=ps.executeUpdate();//返回1 执行成功
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return result;
    }

    /**
     * 批量向数据库插入数据   增
     * */

    public int insert(int loverID,MoneyNote money){
        int result=-1;
        if(money!=null){
            //获取链接数据库对象
            conn= DBOpenHelper.getConn();
            //MySQL 语句
            String sql="INSERT INTO MoneyChangeTable (loverid,moneytypeid,moneydate,moneynumber) VALUES (?,?,?,?)";
            try {
                boolean closed=conn.isClosed();
                if((conn!=null)&&(!closed)){
                    ps= (PreparedStatement) conn.prepareStatement(sql);
                    ps.setInt(1,loverID);
                    ps.setString(2,money.getText());
                    ps.setString(3,money.getTime());
                    ps.setString(4,money.getMoney());
                    result=ps.executeUpdate();//返回1 执行成功
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return result;
    }

}
