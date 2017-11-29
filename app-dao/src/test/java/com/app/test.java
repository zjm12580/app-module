package com.app;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by zhujiamin on 2017/11/20.
 */

public class test {

    @Test
    public void testDatasuurce(){

            String url = "jdbc:mysql://localhost:3306/app";
            String driver = "com.mysql.jdbc.Driver";
            try{
                Class.forName(driver);
            }catch(Exception e){
                System.out.println("无法加载驱动");
            }

            try {
                Connection con = DriverManager.getConnection(url,"root","123456");
                if(!con.isClosed())
                    System.out.println("success");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }

}
