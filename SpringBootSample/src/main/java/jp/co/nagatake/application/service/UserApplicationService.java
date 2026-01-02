package jp.co.nagatake.application.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

	/**
	 * 性別を登録する
	 */
	public Map<String, Integer> getGenderMap() {
		Map<String, Integer> genderMap = new LinkedHashMap<String, Integer>();
		genderMap.put("男性", 1);
		genderMap.put("女性", 2);
		return genderMap;
	}
}
