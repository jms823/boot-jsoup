package com.ict.soup.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ict.soup.collection.DaumIssue;
import com.ict.soup.collection.NaverITNews;
import com.ict.soup.repository.DaumIssueRepository;
import com.ict.soup.repository.NaverITNewsRepository;
import com.ict.soup.service.CrawlService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CrawlServiceImpl implements CrawlService{
	
	@Value("${ex.target.url}")
	private String targetUrl;
	
	@Value("${ex.parent.selector}")
	private String pSelector;
	
	@Value("${ex.dt.selector}")
	private String dtSelector;
	
	@Autowired
	private NaverITNewsRepository nitnRepo;
	
	@Autowired
	private DaumIssueRepository diRepo;

	@Override
	public List<NaverITNews> getNaverITList() throws IOException {
		log.info("targetUrl=>{}", targetUrl);
		Document doc = Jsoup.connect(targetUrl).get();
		Elements ulElements = doc.select(pSelector);
		Elements aElements = ulElements.select(dtSelector);
		List<NaverITNews> nitnList = new ArrayList<NaverITNews>();
		
		for(Element aElement : aElements) {
			String uri = aElement.attr("href");
			int sIdx = uri.indexOf("aid=");
			String aid = uri.substring(sIdx);
			if(aid.indexOf("&") != -1) {
				aid = aid.substring(0,aid.indexOf("&"));
			}
			aid = aid.replace("aid=", "");
			if(nitnRepo.findByAId(aid).size()!=0) {
				continue;
			}
			
			log.info("aid? => {}", nitnRepo.findByAId(aid).size());
			
			String title = aElement.text();
			NaverITNews nitn = new NaverITNews();
			nitn.setUri(uri);
			nitn.setAid(aid);
			nitn.setTitle(title);
			nitnList.add(nitn);
		}
		
			//nitnRepo.saveAll(nitnList);
		
		return nitnRepo.saveAll(nitnList);
	}

	@Override
	public List<DaumIssue> getDaumIssue() throws IOException {
		Document doc = null;
		try {
			doc = Jsoup.connect(targetUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements els = doc.select(pSelector);
		Elements aEls = els.select(dtSelector);
		
		List<DaumIssue> diList = new ArrayList<>();
		
		for(Element e : aEls) {
			DaumIssue daumIssue = new DaumIssue();
			daumIssue.setUri(e.attr("href"));
			daumIssue.setText(e.text());
			diList.add(daumIssue);
		}
		return diRepo.saveAll(diList);
	}
	
	
}
