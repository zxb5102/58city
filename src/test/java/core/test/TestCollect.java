package core.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.collect.rent.Rent;
import com.collect.specific.Specific;
import com.collect.specific.bean.HouseMsg;
import com.collect.url.Collect;

public class TestCollect {

	@Test
	public void f10() throws IOException{
		Collect collect = new Collect();
		ArrayList<String> execute2 = collect.execute("http://nc.58.com/qingyunpu/zufang/?minprice=600_600&sourcetype=5");
		
		Rent rent = new Rent();
		ArrayList<String> execute3 = rent.execute(execute2);
		System.out.println(execute3.size());
	}
	
	@Test
	public void f9(){
		String xm = "fdfs";
		String[] split = xm.split("/");
		System.out.println(split[0]);
//		for(int i=0;i<split.length;i++){
//			System.out.println(split[i]);
//		}
	}
	
	@Test
	public void f8(){
		String str = "平  精装修";
		String[] split = str.split("[\\s\\p{Zs}]+");
		for( int i = 0;i<split.length;i++){
			System.out.println(split[i]);
		}
		//		if(str.matches(".*平.*")){
//			System.out.println(treu);
//		}else{
//			
//		}
		
	}
	
	
	@Test
	public void f7() throws IOException{
		
		Collect collect = new Collect();
		ArrayList<String> execute2 = collect.execute("http://nc.58.com/qingyunpu/zufang/?minprice=600_600&sourcetype=5");
		
		Rent rent = new Rent();
		ArrayList<String> execute3 = rent.execute(execute2);
		
		
//		ArrayList<String> list = new ArrayList<String>();
//		list.add("http://nc.58.com/zufang/30345115768760x.shtml?iuType=p_0&PGTID=0d300008-002a-0b5f-38c9-23aacedb6ba8&ClickID=13");
		Specific specific = new Specific();
		ArrayList<HouseMsg> execute = specific.execute(execute3);
//		ArrayList<HouseMsg> execute = specific.execute(list);
		System.out.println(execute);
	}
	
	@Test
	public  void f6() throws IOException{
//		String url2 = "http://nc.58.com/qingyunpu/zufang/pn2/?minprice=600_600&sourcetype=5&PGTID=0d300008-002a-01c1-1dbd-cd6b6cb542e5&ClickID=13"; 
		String url = "http://jst1.58.com/counter?infoid=30290209918288&uname=&userid=&totalControl=&listControl=&sid=0&lid=0&px=0&cfpath=&_=";
		
//		Map<String, String> cookies = Jsoup.connect(url2).execute().cookies();
//		System.out.println(cookies);
		Date date = new Date();
		long time = date.getTime();
		url += time; 
//		System.out.println(url);
		Document document = Jsoup
								.connect(url)
								.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
								.header("X-Requested-With","XMLHttpRequest")
								.get();
		
		System.out.println(document.text());
		
	}
	
	
	@Test
	public void f5() throws IOException{
		String url = "http://nc.58.com/zufang/28412191770164x.shtml?iuType=j_2&PGTID=0d300008-002a-03db-2d85-3978ac736f60&ClickID=3";
		Document doc = Jsoup.connect(url).get();

		//获取房屋的名字
		String query = "body > div.main-wrap > div.house-title > h1";
		
		//获取热度

		
		//获取更新时间   	分割 更新之前的数据   将字符转化 为 Date 类型
		
		query = "body > div.main-wrap > div.house-title > p";
//		Elements se = doc.select(query);
		
		//获取基本信息
//		Elements se = doc.select("body > div.main-wrap > div.house-basic-info > div.house-basic-right.fr > div.house-basic-desc > div.house-desc-item.fl.c_333 > ul > li > span:nth-child(2)");
		
		//获取价格信息 和 租赁方式
//		Elements se = doc.select(		"body > div.main-wrap > div.house-basic-info > div.house-basic-right.fr > div.house-basic-desc > div.house-desc-item.fl.c_333 > div");

		
		
		//获取房屋的配置
//		Elements se = doc.select("body > div.main-wrap > div.house-detail-desc > div.main-detail-info.fl > ul.house-disposal");
		
		//获取增加的描述
//		Elements se = doc.select("body > div.main-wrap > div.house-detail-desc > div.main-detail-info.fl > div.house-word-introduce.f16.c_555 > ul > li > span.a2");

		
		//获取增加描述的条目名称
//		Elements se = doc.select("body > div.main-wrap > div.house-detail-desc > div.main-detail-info.fl > div.house-word-introduce.f16.c_555 > ul > li > span.a1");		
		
		//获取房屋图片的数量 和地址 
		//对图片的 地址 通过  ？ 进行分割 去除后面的 参数
//		Elements se = doc.select("#housePicList > li[class~=pic] > img");		
		
		//获取小区简介
		/**
		 *  建筑年代：1996
			建筑类型：多层|小高层
			物业费用：0.00
			所属商圈：青云谱 / 洪都
		 */
		Elements se = doc.select("body > div.main-wrap > div.house-detail-desc > div.main-detail-info.fl > ul.district-info-list.c_333.f14.lh28 > li > span:nth-child(2)");		
		
		
		
		
		Iterator<Element> iterator = se.iterator();
		while(iterator.hasNext()){
			Element next = iterator.next();
			String text = next.text();
			System.out.println(text);
		}
		System.out.println("计数		"+se.size());
	}
	
	
	@Test
	public void f4() throws IOException{
		String url = "http://nc.58.com/qingyunpu/zufang/?minprice=600_600&PGTID=0d300008-002a-0302-15e0-4a7016f2a9eb&ClickID=5";
		Collect collect = new Collect();
		ArrayList<String> execute = collect.execute(url);
		Rent rent = new Rent();
		ArrayList<String> execute2 = rent.execute(execute);
		System.out.println(execute2.size());
		System.out.println(execute2);
	}
	
	@Test
	public void f3() throws IOException{
		String url = "http://nc.58.com/zufang/30290209918288x.shtml?iuType=p_1&PGTID=0d300008-002a-021b-dc2c-66680c539505&ClickID=5";
		Document document = Jsoup.connect(url).get();
		Elements select = document.select("body > div.mainbox > div.main > div.content > div.listBox > ul").select("li[id!=bottom_ad_li]").select("li[class!=noresult]");
		Elements select2 = select.select("div[class=img_list]").select("a");
		Iterator<Element> iterator = select2.iterator();
		while(iterator.hasNext()){
			Element next = iterator.next();
			System.out.println(next.attr("href"));
		}
		System.out.println("计数		"+select.size());
	}
	
	
	@Test
	public void f2(){
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("xm");
		arrayList.add("xm");
		arrayList.add("xm2");
		Stream<String> stream = arrayList.stream();
		Stream<String> distinct = stream.distinct();
		Object[] array = distinct.toArray();
		String string = Arrays.toString(array);
		System.out.println(string);
		
	}
	
	
	@Test
	public void g() throws IOException {
		String url = "http://nc.58.com/qingyunpu/zufang/?minprice=600_600&sourcetype=5";
		Collect collect = new Collect();
		ArrayList<String> execute = collect.execute(url);
		Iterator<String> iterator = execute.iterator();
		while(iterator.hasNext()){
			String next = iterator.next();
			System.out.println(next);
		}
//		Stream<String> stream = execute.stream();
//		Stream<String> distinct = stream.distinct();
//		Object[] array = distinct.toArray();
//		String string = Arrays.toString(array);
//		System.out.println(string);
	}

	// public void collect() throws IOException{
	// String url = "http://nc.58.com/zufang/";
	// Collect collect = new Collect(url);
	// collect.execute();
	// }
	// @Test
	public void f() {

		String url = "http://nc.58.com/qingyunpu/zufang/0/pn645/?minprice=600_600&sourcetype=5";
		Pattern compile = Pattern.compile("pn\\d+");
		Matcher matcher = compile.matcher(url);
		if (matcher.find()) {
			String group = matcher.group();
			System.out.println(group);
		}

	}

}
