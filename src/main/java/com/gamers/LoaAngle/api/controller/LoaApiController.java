package com.gamers.LoaAngle.api.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonParser;
import com.gamers.LoaAngle.api.LoaApiConst;

@Controller
public class LoaApiController {
	// 대표 캐릭터 이름을 입력해서 관련된 전체 캐릭터 불러오기
	@GetMapping("testt")
	public void characters(String _characterName) {
		try {
			String characterName = URLEncoder.encode("드미이잉", "UTF-8");
			URL url = new URL("https://developer-lostark.game.onstove.com/characters/"+characterName+"/siblings");
	        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
	        
	        httpURLConnection.setRequestMethod("GET");
	        httpURLConnection.setRequestProperty("authorization", "Bearer "+ LoaApiConst.TEST);
	        httpURLConnection.setRequestProperty("accept","application/json");
	        httpURLConnection.setRequestProperty("content-Type","application/json");
	        httpURLConnection.setDoOutput(true);
	        
	        int result = httpURLConnection.getResponseCode();
	        System.out.println("result : " + result);
	        System.out.println("httpURLConnection : " + httpURLConnection);
	        System.out.println("getResponseMessage : " + httpURLConnection.getResponseMessage());
	        System.out.println("getContentType : " + httpURLConnection.getContentType());
	        System.out.println("getResponseCode() : " + httpURLConnection.getResponseCode());
	        System.out.println("getURL() : " + httpURLConnection.getURL());
	        System.out.println();
	        
	        InputStream inputStream = null;
	        if(result == 200 ) {
	        	inputStream = httpURLConnection.getInputStream();
	        } else {
	        	inputStream = httpURLConnection.getErrorStream();
	        }
	        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

	        JSONParser parser = new JSONParser(inputStream);
	        JSONArray array = (JSONArray) parser.parse(inputStreamReader);
	        
	        System.out.println("parser : "+parser);
	        System.out.println("array : "+array);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
