package com.ict.soup.service;

import java.io.IOException;
import java.util.List;

import com.ict.soup.collection.DaumIssue;

public interface CrawlService {
	public List<DaumIssue> getDaumIssue() throws IOException;

}
