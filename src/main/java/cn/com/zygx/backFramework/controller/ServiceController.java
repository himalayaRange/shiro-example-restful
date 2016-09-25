package cn.com.zygx.backFramework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

	@RequestMapping("/hello")
    public String hello1(String[] param1, String param2) {
        return "您好！欢迎访问REST,result: " + param1[0] + param1[1] + param2;
    }
	
	
}
