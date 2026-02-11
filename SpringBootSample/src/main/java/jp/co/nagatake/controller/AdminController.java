package jp.co.nagatake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {
	
	/* アドミン権限専用画面へ遷移 */
	@GetMapping("/admin")
	public String getAdmin() {
		return "admin/admin";
	}
}
