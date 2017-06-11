package com.collect.rent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Rent {

	public ArrayList<String> execute(ArrayList<String> list) throws IOException{
		ArrayList<String> list_r = new ArrayList<>();
		
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			String next = iterator.next();
			
			Document document = Jsoup.connect(next).get();
			Elements select = document.select("body > div.mainbox > div.main > div.content > div.listBox > ul").select("li[id!=bottom_ad_li]").select("li[class!=noresult]");
			Elements select2 = select.select("div[class=img_list]").select("a");
			Iterator<Element> iterator2 = select2.iterator();
			while(iterator2.hasNext()){
				Element next2 = iterator2.next();
				String attr = next2.attr("href");
				list_r.add(attr);
			}
		}
		
		return list_r;
	}
}
