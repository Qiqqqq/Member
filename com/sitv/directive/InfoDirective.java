package com.sitv.directive;


import javax.servlet.http.HttpServletRequest;

import com.sitv.dao.InfoDao;

import net.sf.json.JSONArray;


public class InfoDirective {
	public InfoDirective(){
		System.out.print("InfoDirective \n");
	}
	public JSONArray excute(HttpServletRequest request){
		System.out.print("InfoDirective¡ª¡ªprint \n");
		String classname = request.getParameter("func");
		System.out.print(classname + "\n");
		Integer id = 1;
		Integer[] theme_ids = {123,311};
		System.out.print("infoDao 1 \n");
		InfoDao dao = new InfoDao();
		dao.getResult(id, theme_ids);
		JSONArray st = dao.getResult(id, theme_ids);
		dao.setDB(id, theme_ids);
		return st;
	}

    //private InfoDao dao;
}
