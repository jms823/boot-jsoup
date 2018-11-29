package com.ict.soup.service;

import java.io.IOException;
import java.util.List;

import com.ict.soup.collection.DaumIssue;
import com.ict.soup.collection.NaverITNews;

public interface CrawlService {
	public List<NaverITNews> getNaverITList() throws IOException;
	public List<DaumIssue> getDaumIssue() throws IOException;

}
