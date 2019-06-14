package com.imooc.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

@Controller
public class HelloController {
	
	@GetMapping("say")
	@ResponseBody
	public Map<String,Object> say(HttpServletRequest request) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("wang", "keqi");
		map.put("li", "hello~~~");
		return map;
	}
	
	
}
