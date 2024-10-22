package com.gamers.LoaAngle.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gamers.LoaAngle.userInfo.siblings.dto.SiblingsDTO;

@Controller
public class LostarkApi extends APIController{
	// 대표 캐릭터 이름을 입력해서 관련된 원정대 전체 캐릭터 불러오기
	@GetMapping("testt")
	public List<SiblingsDTO> siblings(String _charName) throws ParseException {
		// 값전달용 리스트 선언
		List<SiblingsDTO> siblingsList = new ArrayList<>();
		 String charName = _charName;
		String category = "characters";
		String cateDetail = "siblings";
		
		// api 호출
		String searchData = callInfoAPI(category, charName, cateDetail);
		
		if(!searchData.contentEquals("[]")) { // API 호출 후 검색결과가 있는 경우
			// JSONArray로 변환
			JSONArray searchResult = (JSONArray)new JSONParser().parse(searchData);
			
			for(int i=0; i<searchResult.size(); i++) {
				// JSONArray를 JSONObject로 변환 
				JSONObject searchResultObj = (JSONObject)searchResult.get(i);
				
				SiblingsDTO siblingsDTO = new SiblingsDTO();
				
				// DTO 값 세팅
				siblingsDTO.setServerName(searchResultObj.get("ServerName").toString());
				siblingsDTO.setCharacterName(searchResultObj.get("CharacterName").toString());
				siblingsDTO.setCharacterLevel(Integer.parseInt(searchResultObj.get("CharacterLevel").toString()));
				siblingsDTO.setCharacterClassName(searchResultObj.get("CharacterClassName").toString());
				siblingsDTO.setItemMaxLevel(searchResultObj.get("ItemAvgLevel").toString());
				siblingsDTO.setItemAvgLevel(searchResultObj.get("ItemMaxLevel").toString());
				
				siblingsList.add(siblingsDTO);
			}
		} else { // API 호출 후 검색 결과가 없는 경우
			System.out.println("검색결과가 없습니다.");
			return null;
		}
		return siblingsList;
	}
}