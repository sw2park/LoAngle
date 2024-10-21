package com.gamers.LoaAngle.api.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoaApiController extends APIController{
	// 대표 캐릭터 이름을 입력해서 관련된 원정대 전체 캐릭터 불러오기
	@GetMapping("testt")
	public void siblings(String _charName) throws ParseException {
		
		String charName = "하린슬레";
		// 테스트 끝나면 아래로 원상복구
		// String charName = _charName;
		String category = "characters";
		String cateDetail = "siblings";
		
		String searchData = callInfoAPI(category, charName, cateDetail);
		
		System.out.println(searchData);
		JSONObject objData = (JSONObject)new JSONParser().parse(searchData);
		
//		JSONObject infoListResult = (JSONObject).get
		
	}
}
