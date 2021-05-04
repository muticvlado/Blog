package com.vlado;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
	
	@RequestMapping("/loginPage")
	public String getLoginPage() {
		return "login-page";
	}
	
	@RequestMapping("/access-deneid")
	public String getAccessDeneid() {
		return "access-deneid";
	}
}
