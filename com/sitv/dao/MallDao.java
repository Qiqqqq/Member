package com.sitv.dao;

import java.util.Date;

import com.sitv.base.BaseDao;
import com.sitv.base.QueryHandler;

import net.sf.json.JSONArray;

public class MallDao extends BaseDao{
	public MallDao(){
		System.out.print("MallDao \n");
	}
	
	public JSONArray addScore(long MemberID, long Score, Date ModifyTime, String ModifyUser){
		System.out.print("MallDao addScore start \n");
		QueryHandler queryHandler = getUpdateQueryHandler("zcmemberscorerela");
//		if (notEmpty(id)){
//			queryHandler.updateCondition("bean.ID = ").setParameter(id);
//		}
//		if (notEmpty(UserName)){
//			queryHandler.updateCondition("bean.UserName = ").setParameter(UserName);
//		}
		if (notEmpty(ModifyTime)){
			queryHandler.updateCondition("bean.ModifyTime = ").setParameter(ModifyTime);
		}
		if (notEmpty(ModifyUser)){
			queryHandler.updateCondition("bean.ModifyUser = ").setParameter(ModifyUser);
		}
		if (notEmpty(Score)){
			queryHandler.updateCondition("bean.Score = bean.Score + ").setParameter(Score);
		}

		if (notEmpty(MemberID)){
			queryHandler.condition("bean.MemberID = ").setParameter(MemberID);
		}
		//queryHandler.addCondition("content_id").setChangeParameter(444);
		
		System.out.print(queryHandler.getSql());
		return setDB(queryHandler);
	}
	
	public JSONArray minusScore(long MemberID, long Score, Date ModifyTime, String ModifyUser){
		System.out.print("MallDao minusScore start \n");
		QueryHandler queryHandler = getUpdateQueryHandler("zcmemberscorerela");
//		if (notEmpty(id)){
//			queryHandler.updateCondition("bean.ID = ").setParameter(id);
//		}
//		if (notEmpty(UserName)){
//			queryHandler.updateCondition("bean.UserName = ").setParameter(UserName);
//		}
		if (notEmpty(ModifyTime)){
			queryHandler.updateCondition("bean.ModifyTime = ").setParameter(ModifyTime);
		}
		if (notEmpty(ModifyUser)){
			queryHandler.updateCondition("bean.ModifyUser = ").setParameter(ModifyUser);
		}
		if (notEmpty(Score)){
			queryHandler.updateCondition("bean.Score = bean.Score - ").setParameter(Score);
		}

		if (notEmpty(MemberID)){
			queryHandler.condition("bean.MemberID = ").setParameter(MemberID);
		}
		//queryHandler.addCondition("content_id").setChangeParameter(444);
		
		System.out.print(queryHandler.getSql());
		return setDB(queryHandler);
	}
}
