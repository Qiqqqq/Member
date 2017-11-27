package com.sitv.dao;

import com.sitv.base.BaseDao;
import com.sitv.base.QueryHandler;

import net.sf.json.JSONArray;

public class InfoDao extends BaseDao{
	public InfoDao(){
		System.out.print("InfoDirective \n");
	}
	public JSONArray getResult(Integer id, Integer[] theme_id){
		System.out.print("infoDao start \n");
		QueryHandler queryHandler = getSelectQueryHandler("review_user");
		
		queryHandler.condition("bean.id = ").setParameter(id);
		queryHandler.condition("bean.theme_id in ").setParameter(theme_id);
		System.out.print(queryHandler.getSql());
		return getData(queryHandler);
	}
	
	public Integer setDB(Integer id, Integer[] theme_id){
		System.out.print("infoDao start \n");
		QueryHandler queryHandler = getInsertQueryHandler("cms_article_recommend");
		
		queryHandler.addCondition("id","content_id").setChangeParameter(4,444);
		//queryHandler.addCondition("content_id").setChangeParameter(444);
		
		System.out.print(queryHandler.getSql());
		return setDB(queryHandler);
	}
	
}
