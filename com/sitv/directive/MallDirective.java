package com.sitv.directive;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.sitv.dao.MallDao;

import net.sf.json.JSONArray;

public class MallDirective {
	public MallDirective(){
		System.out.print("InfoDirective \n");
	}
	public JSONArray excute(HttpServletRequest request){
		JSONArray result = new JSONArray();
		String operation = request.getParameter("operation");
		String MemberID_tmp = request.getParameter("MemberId");
		String Score_tmp = request.getParameter("Score");
		String ModifyUser = request.getParameter("ModifyUser");
		long MemberID = 0;
		long Score = 0;
		Date ModifyTime = new Date();
		try {
			MemberID =Long.parseLong(MemberID_tmp);
			Score =Long.parseLong(Score_tmp);
			} catch (NumberFormatException e) {
				System.out.print("«Î ‰»Î ˝◊÷\n");
			}
		if (null != operation){
			MallDao dao = new MallDao();
			if (("add").equals(operation)){
				result = dao.addScore(MemberID, Score, ModifyTime, ModifyUser);
			}
			else if (("minus").equals(operation)){
				result = dao.minusScore(MemberID, Score, ModifyTime, ModifyUser);
			}
		}
		return result;
	}
}
