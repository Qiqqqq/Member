package com.sitv.base;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.sitv.DbUtil.ConnectionPool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseDao extends Base{
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
	
	protected JSONArray setDB(QueryHandler queryHandler){
		ConnectionPool pool = new ConnectionPool();
		JSONArray result=new JSONArray();
		JSONObject jsonobj = new JSONObject();
		try {
			Connection con = pool.getConnection();
			Statement statement = con.createStatement();
			int resultNum = statement.executeUpdate(queryHandler.getSql());
			if(resultNum > 0) {
				jsonobj.put("result", "OK!"); 
			}
			else{
				jsonobj.put("result", "fail!"); 
			}
			//result = rs.getString(1);
        	statement.close();
        	pool.releaseConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	protected JSONArray DoVerify(QueryHandler queryHandler){
		ConnectionPool pool = new ConnectionPool();
		JSONArray result = new JSONArray(); 
		JSONObject jsonobj = new JSONObject();
		try {
			Connection con = pool.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(queryHandler.getSql());
			if (rs.next()){
				jsonobj.put("result", "OK!"); 
			}
			else{
				jsonobj.put("result", "ÓÃ»§Ãû»òÃÜÂë´íÎó!"); 
			}
			result.add(jsonobj);
			//result = rs.getString(1);
			rs.close();
        	statement.close();
        	pool.releaseConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public long getLastId(String name, String tableName){
		QueryHandler queryHandler = getSelectMaxIdQueryHandler(name, tableName);
		System.out.print(queryHandler.getSql() + "\n");
		long id = 0L;
		try {
			ConnectionPool pool = new ConnectionPool();
			Connection con = pool.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(queryHandler.getSql());
			
        	while (rs.next()) {
        		id = rs.getLong("max("+name+")");
        	}
			//result = rs.getString(1);
        	rs.close();
        	statement.close();
        	pool.releaseConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}
	
	protected JSONArray isRegisted(QueryHandler queryHandler){
		ConnectionPool pool = new ConnectionPool();
		JSONArray result = new JSONArray(); 
		JSONObject jsonobj = new JSONObject();
		try {
			Connection con = pool.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(queryHandler.getSql());
			if (rs.next()){
				jsonobj.put("result", "ÓÃ»§ÃûÒÑ´æÔÚ£¡"); 
			}
			else{
				jsonobj.put("result", "OK!"); 
			}
			result.add(jsonobj);
			//result = rs.getString(1);
			rs.close();
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
    public static QueryHandler getSelectMaxIdQueryHandler(String name, String tableName) {
        return getQueryHandler("select max("+ name +") from").append(tableName);
    }
}
