package jp.co.nagatake.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.nagatake.application.service.UserApplicationService;
import jp.co.nagatake.form.SignupForm;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {

	@Autowired
	private UserApplicationService userApplicationService;
	
	/** ユーザ登録画面表示 */
	@GetMapping("/signup")
	public String getSignup(Model model, Locale locale, @ModelAttribute SignupForm form) {
		/** 性別を取得 */
		Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
		model.addAttribute("genderMap", genderMap);
		// ユーザ登録画面へ遷移
		return "user/signup";
	}
	
	/** ユーザ登録処理 */
	@PostMapping("/signup")
	public String postSignup(Model model, Locale locale, @ModelAttribute SignupForm form, BindingResult bindingResult) {
		
		// 入力チェック結果
		if(bindingResult.hasErrors()) {
			//ユーザー画面へ遷移
			return getSignup(model, locale, form);
		}
		
		log.info(form.toString());
		// ログイン画面へリダイレクト
		return "redirect:/login";
	}
	
}
