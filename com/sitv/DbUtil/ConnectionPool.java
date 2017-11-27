package com.sitv.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConnectionPool {

    private static DataSource dataSource = null;
//    public ConnectionPool() {
//    	if (dataSource == null){
//    		dataSource= new ComboPooledDataSource("mysql2");
//    	}
//        // TODO Auto-generated constructor stub
//    }
    static{
           //dataSource资源只能初始化一次
           dataSource= new ComboPooledDataSource("mysql");
   		try {
   			releaseConnection(getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           System.out.print("init dataSource \n");
    }
	       /**
	        * 释放连接
	        * @param connection
	        */
	       public static void releaseConnection(Connection connection){
	              try {
	                     if(connection != null ) {
	                            connection.close();
	                     }
	              }catch (Exception e) {
	                     e.printStackTrace();
	              }
	       }

	       /**
	        * 获取连接
	        * @return
	        * @throws SQLException
	        */
	       public static Connection getConnection() throws SQLException{
	              return dataSource.getConnection();
	       }
	
}
