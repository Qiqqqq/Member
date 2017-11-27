package com.sitv.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.sitv.DbUtil.ConnectionPool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseDao {
	protected JSONArray getData(QueryHandler queryHandler){
		ConnectionPool pool = new ConnectionPool();
		String result="";
		JSONArray jsonarray = new JSONArray();  
		JSONObject jsonobj = new JSONObject(); 
		try {
			Connection con = pool.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(queryHandler.getSql());
			ResultSetMetaData rsmd = rs.getMetaData() ;
			int count=rsmd.getColumnCount();
			String[] name=new String[count];
			for(int i=0;i<count;i++){
				name[i]=rsmd.getColumnName(i+1);
			}
        	while (rs.next()) {
    			for(int j=0;j<count;j++){
    				jsonobj.put(name[j], rs.getString(name[j])); 
    			}
    			jsonarray.add(jsonobj); 
        		
        	}
			//result = rs.getString(1);
        	rs.close();
        	statement.close();
        	pool.releaseConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonarray;
	}
	protected Integer setDB(QueryHandler queryHandler){
		ConnectionPool pool = new ConnectionPool();
		Integer result=null;
		try {
			Connection con = pool.getConnection();
			Statement statement = con.createStatement();
			result = statement.executeUpdate(queryHandler.getSql());

			//result = rs.getString(1);
        	statement.close();
        	pool.releaseConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
    public static QueryHandler getQueryHandler(String sql) {
        return new QueryHandler(sql);
    }
    public static QueryHandler getDeleteQueryHandler(String sql) {
        return getQueryHandler("delete from").append(sql).append("bean");
    }

    public static QueryHandler getUpdateQueryHandler(String sql) {
        return getQueryHandler("update ").append(sql).append("bean");
    }
    public static QueryHandler getInsertQueryHandler(String sql) {
        return getQueryHandler("INSERT INTO ").append(sql);
    }
    public static QueryHandler getSelectQueryHandler(String sql) {
        return getQueryHandler("select * from").append(sql).append("bean");
    }
}
