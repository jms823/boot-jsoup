package com.ict.soup.collection;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("daumissue")
public class DaumIssue {
	
	private String uri;
	private String text;

}
