package com.gamers.LoaAngle.api.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.gamers.LoaAngle.api.LoaApiConst;

public class APIController {
	public String callInfoAPI(String _category, String _charName, String _cateDetail) {
		StringBuilder response = new StringBuilder();
		try {
			// 카테고리 인코딩
			String category = URLEncoder.encode(_category, "UTF-8");
			// 캐릭터 검색한 것 인코딩
			String charName = URLEncoder.encode(_charName, "UTF-8");
			// URL 뒤에 오는 상세 카테고리
			String cateDetail = _cateDetail;
			
			System.out.println("cateDetail : " + cateDetail);
			if(cateDetail != null) { // null이 아닐경우 인코딩
				cateDetail = URLEncoder.encode(_cateDetail, "UTF-8");
				cateDetail = "/"+cateDetail;
			}
			// URL API 설정
			URL url = new URL(LoaApiConst.LOA_API_URL+category+"/"+charName+cateDetail);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
	        httpURLConnection.setRequestMethod("GET");
	        httpURLConnection.setRequestProperty("authorization", "Bearer " + LoaApiConst.LOA_API_KEY);
	        httpURLConnection.setRequestProperty("accept", "application/json");
	        httpURLConnection.setRequestProperty("content-Type", "application/json");
	        httpURLConnection.setDoOutput(true);
	        
	     // 응답 코드 확인
	        int result = httpURLConnection.getResponseCode();
	        
	        InputStream inputStream;
	        
	        if (result == 200) {
	        	System.out.println("200 호출 성공");
	            inputStream = httpURLConnection.getInputStream();
	        } else {
	            inputStream = httpURLConnection.getErrorStream();
	        }
	        
	        // InputStreamReader를 이용해 데이터를 읽어들임
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	        String line;

	        // 데이터를 한 줄씩 읽어 StringBuilder에 저장
	        while ((line = reader.readLine()) != null) {
	            response.append(line);
	        }
	        
		} catch (Exception e) {
	        throw new RuntimeException(e);
		}
		
		return response.toString();
	}
}
