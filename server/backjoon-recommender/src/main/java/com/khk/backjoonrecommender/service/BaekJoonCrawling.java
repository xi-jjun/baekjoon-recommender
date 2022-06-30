package com.khk.backjoonrecommender.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BaekJoonCrawling {
	private final String CRAWLING_URL = "https://www.acmicpc.net/problemset";

	public void getProblem() throws IOException {
		Document document = Jsoup.connect(CRAWLING_URL).get();
//		System.out.println(document.html());

		Elements list = document.getElementsByClass("list_problem_id");
		System.out.println(list.html());
	}
}
