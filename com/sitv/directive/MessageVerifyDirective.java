package com.sitv.directive;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


import com.sitv.dao.InfoDao;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class MessageVerifyDirective {
	public JSONArray excute(HttpServletRequest request) throws IOException{
		System.out.print("MessageVerify¡ª¡ªprint \n");
		HttpClient httpClient = new HttpClient();

		String url = "http://sms.10690221.com/hy/";
		String uid = "80917";
		String auth = EncoderByMd5("Vd&5fl&");
		String mobile = "15045918256";
        String content=java.net.URLEncoder.encode("²âÊÔÏûÏ¢", "gbk");
		PostMethod postMethod = new PostMethod(url);
		
		NameValuePair[] data = {
					new NameValuePair("uid", uid),
					new NameValuePair("auth", auth),
					new NameValuePair("mobile", mobile),
					new NameValuePair("expid", "0"),
					new NameValuePair("msg",content ) };
		postMethod.setRequestBody(data);
		int statusCode = httpClient.executeMethod(postMethod);
			
		if (statusCode == HttpStatus.SC_OK) {
			String sms = postMethod.getResponseBodyAsString();
			System.out.println("result:" + sms);
		}
		System.out.println("statusCode="+statusCode);
		Integer id = 1;
		Integer[] theme_ids = {123,311};
		System.out.print("MessageVerifyDao 1 \n");
		InfoDao dao = new InfoDao();
		dao.getResult(id, theme_ids);
		JSONArray st = dao.getResult(id, theme_ids);
		dao.setDB(id, theme_ids);
		return st;
	}
	public static String EncoderByMd5(String buf) {  
	    try {  
	        MessageDigest digist = MessageDigest.getInstance("MD5");  
	        byte[] rs = digist.digest(buf.getBytes("UTF-8"));  
	        StringBuffer digestHexStr = new StringBuffer();  
	        for (int i = 0; i < 16; i++) {  
	            digestHexStr.append(byteHEX(rs[i]));  
	        }  
	        return digestHexStr.toString();  
	    } catch (Exception e) {  
	        //logger.error(e.getMessage(), e);  
	    }  
	    return null;  
	  
	}  
	public static String byteHEX(byte ib) {  
	    char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
	    char[] ob = new char[2];  
	    ob[0] = Digit[(ib >>> 4) & 0X0F];  
	    ob[1] = Digit[ib & 0X0F];  
	    String s = new String(ob);  
	    return s;  
	}  
}
