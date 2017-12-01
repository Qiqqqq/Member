package com.sitv.dao;



import java.util.Date;

import com.sitv.base.BaseDao;
import com.sitv.base.QueryHandler;

import net.sf.json.JSONArray;

public class InfoDao extends BaseDao{
	public InfoDao(){
		System.out.print("InfoDao \n");
	}
	public JSONArray getResult(long id, String UserName, long SiteID, String Password, String Status, String Source, Date AddTime, String AddUser, String LogoFile){
		System.out.print("infoDao getResult start \n");
		QueryHandler queryHandler = getSelectQueryHandler("zcmember");
		if (notEmpty(id)){
			queryHandler.condition("bean.ID = ").setParameter(id);
		}
		if (notEmpty(UserName)){
			queryHandler.condition("bean.UserName = ").setParameter(UserName);
		}
		if (notEmpty(SiteID)){
			queryHandler.condition("bean.SiteID = ").setParameter(SiteID);
		}
		if (notEmpty(Password)){
			queryHandler.condition("bean.Password = ").setParameter(Password);
		}
		if (notEmpty(Status)){
			queryHandler.condition("bean.Status = ").setParameter(Status);
		}
		if (notEmpty(Source)){
			queryHandler.condition("bean.Source = ").setParameter(Source);
		}
		if (notEmpty(AddTime)){
			queryHandler.condition("bean.AddTime = ").setParameter(AddTime);
		}
		if (notEmpty(AddUser)){
			queryHandler.condition("bean.AddUser = ").setParameter(AddUser);
		}
		if (notEmpty(LogoFile)){
			queryHandler.condition("bean.LogoFile = ").setParameter(LogoFile);
		}
	
		//queryHandler.condition("bean.theme_id in ").setParameter(theme_id);
		System.out.print(queryHandler.getSql());
		return getData(queryHandler);
	}
	
	public JSONArray setDB(long id, String UserName, long SiteID, String Password, String Status, String Source, Date AddTime, String AddUser, String LogoFile){
		System.out.print("infoDao setDB start \n");
		QueryHandler queryHandler = getInsertQueryHandler("zcmember");
		
		queryHandler.addCondition("ID", "UserName", "SiteID", "Password", "Status", "Source", "AddTime", "AddUser", "LogoFile").setChangeParameter(id, UserName, SiteID, Password, Status, Source, AddTime, AddUser, LogoFile);
		//queryHandler.addCondition("content_id").setChangeParameter(444);
		
		System.out.print(queryHandler.getSql());
		return setDB(queryHandler);
	}
	public JSONArray updateDB(long id, String UserName, long SiteID, String Password, String Status, String Source, Date AddTime, String AddUser, String LogoFile){
		System.out.print("infoDao setDB start \n");
		QueryHandler queryHandler = getUpdateQueryHandler("zcmember");
//		if (notEmpty(id)){
//			queryHandler.updateCondition("bean.ID = ").setParameter(id);
//		}
//		if (notEmpty(UserName)){
//			queryHandler.updateCondition("bean.UserName = ").setParameter(UserName);
//		}
		if (notEmpty(SiteID)){
			queryHandler.updateCondition("bean.SiteID = ").setParameter(SiteID);
		}
		if (notEmpty(Password)){
			queryHandler.updateCondition("bean.Password = ").setParameter(Password);
		}
		if (notEmpty(Status)){
			queryHandler.updateCondition("bean.Status = ").setParameter(Status);
		}
		if (notEmpty(Source)){
			queryHandler.updateCondition("bean.Source = ").setParameter(Source);
		}
		if (notEmpty(AddTime)){
			queryHandler.updateCondition("bean.AddTime = ").setParameter(AddTime);
		}
		if (notEmpty(AddUser)){
			queryHandler.updateCondition("bean.AddUser = ").setParameter(AddUser);
		}
		if (notEmpty(LogoFile)){
			queryHandler.updateCondition("bean.LogoFile = ").setParameter(LogoFile);
		}
		if (notEmpty(UserName)){
			queryHandler.condition("bean.UserName = ").setParameter(UserName);
		}
		//queryHandler.addCondition("content_id").setChangeParameter(444);
		
		System.out.print(queryHandler.getSql());
		return setDB(queryHandler);
	}
	public JSONArray Verify(String UserName,String Password){
		QueryHandler queryHandler = getSelectQueryHandler("zcmember");
		if (notEmpty(UserName)){
			queryHandler.condition("bean.UserName = ").setParameter(UserName);
		}
		if (notEmpty(Password)){
			queryHandler.condition("bean.Password = ").setParameter(Password);
		}
		return DoVerify(queryHandler);
	}
	
	public JSONArray registerVerify(String UserName){
		QueryHandler queryHandler = getSelectQueryHandler("zcmember");
		if (notEmpty(UserName)){
			queryHandler.condition("bean.UserName = ").setParameter(UserName);
		}
		return isRegisted(queryHandler);
	}
	
	public JSONArray initScore(long MemberID, String ScoreType, long Score, String AddUser, Date AddTime, String ModifyUser, Date ModifyTime){
		QueryHandler queryHandler = getInsertQueryHandler("zcmemberscorerela");
		queryHandler.addCondition("MemberID", "ScoreType", "Score", "AddUser", "AddTime", "ModifyUser", "ModifyTime").setChangeParameter(MemberID, ScoreType, Score, AddUser, AddTime, ModifyUser, ModifyTime);


		return setDB(queryHandler);
	}
}
