package com.ict.soup.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ict.soup.collection.DaumIssue;
import com.ict.soup.collection.NaverITNews;
import com.ict.soup.service.CrawlService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ExecuteController {
	
	@Autowired
	private CrawlService cs;
	
	@PostMapping(value="/execute")
	@ResponseBody
	public List<NaverITNews> naverTest(@RequestParam Map<String,String> auth) throws Exception{
		
		if(!"test".equals(auth.get("id"))){
			throw new Exception("응 안돼");
		}
		
		if(!"test".equals(auth.get("pwd"))) {
			throw new Exception("응 안 돼");
		}
		log.info("auth => {}",auth);
		return cs.getNaverITList();
	}
	
	@PostMapping(value="/execute2")
	@ResponseBody
	public List<DaumIssue> daumTest(@RequestParam Map<String,String> auth) throws Exception{
		
		if(!"test".equals(auth.get("id"))){
			throw new Exception("응 안돼");
		}
		
		if(!"test".equals(auth.get("pwd"))) {
			throw new Exception("응 안 돼");
		}
		log.info("auth => {}",auth);
		return cs.getDaumIssue();
	}
}
