package com.sitv.directive;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.sitv.dao.InfoDao;

import net.sf.json.JSONArray;


public class InfoDirective {
	public InfoDirective(){
		System.out.print("InfoDirective \n");
	}
	public JSONArray excute(HttpServletRequest request){
		System.out.print("InfoDirective——print \n");
		String operation = request.getParameter("operation");
		JSONArray result = new JSONArray();  
		String id_tmp = request.getParameter("id");
		String UserName = request.getParameter("userName");
		String SiteID_tmp = request.getParameter("SiteID");
		String Password = request.getParameter("Password");
		String Status = request.getParameter("Status");
		String Source = request.getParameter("Source");
		//String AddTime_tmp = request.getParameter("AddTime");
		String AddUser = request.getParameter("AddUser");
		String LogoFile = request.getParameter("LogoFile");
		Date AddTime = new Date();
		long id = 0;
		long SiteID = 0;
		try {
			id =Long.parseLong(id_tmp);
			SiteID =Long.parseLong(SiteID_tmp);
			} catch (NumberFormatException e) {
				System.out.print("请输入数字\n");
			}
//		try
//		{
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			AddTime = sdf.parse(AddTime_tmp);
//		}
//		catch (ParseException e)
//		{
//			System.out.println(e.getMessage());
//		}
		if (null != operation){
			InfoDao dao = new InfoDao();
			//查询
			if (("get").equals(operation)){
				result = dao.getResult(id, UserName, SiteID, Password, Status, Source, AddTime, AddUser, LogoFile);

			}
			//注册
			else if (("add").equals(operation)){
				String name = "id";
				String tableName = "zcmember";
				result = dao.registerVerify(UserName);
				result.getJSONObject(0);
				String isregisted = result.getJSONObject(0).getString("result");
				if (("OK!").equals(isregisted)){
					long maxid = dao.getLastId(name, tableName);
					maxid = maxid+1;				
					result = dao.setDB(maxid, UserName, SiteID, Password, Status, Source, AddTime, AddUser, LogoFile);
					dao.initScore(maxid, "Default", 0, "admin", AddTime, "system", AddTime);
				}
//				else {
//					
//				}
//				long maxid = dao.getLastId(name, tableName);
//				maxid = maxid+1;				
//				result = dao.setDB(maxid, UserName, SiteID, Password, Status, Source, AddTime, AddUser, LogoFile);

			}
			//修改资料
			else if (("update").equals(operation)){
				result = dao.updateDB(id, UserName, SiteID, Password, Status, Source, AddTime, AddUser, LogoFile);

			}
			else if (("delete").equals(operation)){
				
			}
			//登陆
			else if (("Verify").equals(operation)){
				result = dao.Verify(UserName, Password);

			}
		}

		return result;





	}

    //private InfoDao dao;
}
