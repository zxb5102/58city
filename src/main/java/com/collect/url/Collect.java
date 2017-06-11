package com.collect.url;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Collect {

	public ArrayList<String> execute(String url) throws IOException {

		ArrayList<String> list = new ArrayList<>();

		Document doc = Jsoup.connect(url).get();
		Elements ele = doc.select("#bottom_ad_li").select(".pager");
		Elements el = ele.select("a[class!=next]").select("a[class!=prv]");
		if (el.size() > 0) {
			Iterator<Element> iterator = el.iterator();
			while (iterator.hasNext()) {
				Element next = iterator.next();
				String attr = next.attr("href");
				list.add(attr);
			}
			// 判断是否存在下一页
			Elements elements = ele.select("a[class=next]");
			if (elements.size() > 0) {

				String turl = list.get(list.size() - 1);
				ArrayList<String> tlist = execute(turl);
				list.addAll(tlist);
			}
		}
		
		//添加当前的URL到list 
		list.add(url);
		
		//进行过滤去除重复
		Stream<String> stream = list.stream();
		Stream<String> distinct = stream.distinct();
		Object[] array = distinct.toArray();
		ArrayList<String> list2 = new ArrayList<>();
		for(int i=0;i<array.length;i++){
			String str = (String) array[i];
			list2.add(str);
		}
		
		return list2;
	}
}
