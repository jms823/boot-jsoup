package com.ict.soup.collection;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("naverit")
public class NaverITNews {
	private String uri;
	private String title;
	private String aid;

}
