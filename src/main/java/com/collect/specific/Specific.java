package com.collect.specific;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.collect.specific.bean.EstateDesc;
import com.collect.specific.bean.HouseBase;
import com.collect.specific.bean.HouseDesc;
import com.collect.specific.bean.HouseMsg;

public class Specific {
	
	public ArrayList<HouseMsg> execute( ArrayList<String> list) throws IOException{
		
		ArrayList<HouseMsg> list_r = new ArrayList<HouseMsg>();
		
		
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			String next = iterator.next();
			
			HouseMsg houseMsg = new HouseMsg();
			HouseBase base = houseMsg.getHousebase();
			EstateDesc estateDesc = houseMsg.getEstatedesc();
			HouseDesc houseDesc = houseMsg.getHousedesc();
			
			Document doc = Jsoup.connect(next).get();
			
			// set house link
			houseMsg.setLink(next);
			// set house name
			String query = "body > div.main-wrap > div.house-title > h1";
			Elements se = doc.select(query);
			Iterator<Element> iterator2 = se.iterator();
			while(iterator2.hasNext()){
				Element next2 = iterator2.next();
				String text = next2.text();
				houseMsg.setName(text);
			}
			//set update
			query = "body > div.main-wrap > div.house-title > p";
			se = doc.select(query);
			iterator2 = se.iterator();
			while(iterator2.hasNext()){
				Element next2 = iterator2.next();
				String text = next2.text();
				Date extract = extractUpdate(text);
				houseMsg.setUpdate(extract);
			}
			//set rentStyle  houseStyle  
			query = "body > div.main-wrap > div.house-basic-info > div.house-basic-right.fr > div.house-basic-desc > div.house-desc-item.fl.c_333 > ul > li > span:nth-child(2)";
			se = doc.select(query);
			for(int i=0;i<se.size();i++){
				Element e = se.get(i);
				String text = e.text();
				if(i==0){
					//set rentStyle
					base.setRentStyle(text);
				}else
				if(i==1){
					//set houseStyle
					setHouseStyle(text, base);
				}else
				if(i==2){
					//set orien
					setOrien(text, base);	
				}else
				if(i==3){
					//set estate
					base.setEstate(text);
				}else if(i==4){
					//set area
					base.setArea(text);
				}else if(i==5){
					//set location
					base.setLocation(text);
				}
			}
			//set houseDesc
			query = "body > div.main-wrap > div.house-basic-info > div.house-basic-right.fr > div.house-basic-desc > div.house-desc-item.fl.c_333 > div";
			se = doc.select(query);
			iterator2 = se.iterator();
			while(iterator2.hasNext()){
				Element next2 = iterator2.next();
				String text = next2.text();
				setBase(text, base);
			}
			//set configure
			query = "body > div.main-wrap > div.house-detail-desc > div.main-detail-info.fl > ul.house-disposal";
			se = doc.select(query);
			iterator2 = se.iterator();
			while(iterator2.hasNext()){
				Element next2 = iterator2.next();
				String text = next2.text();
				houseDesc.setConfigure(text);
			}
			//set more desc
			query = "body > div.main-wrap > div.house-detail-desc > div.main-detail-info.fl > div.house-word-introduce.f16.c_555 > ul > li > span.a1";
			Elements select = doc.select(query);
			Object[] key = select.toArray();
			query = "body > div.main-wrap > div.house-detail-desc > div.main-detail-info.fl > div.house-word-introduce.f16.c_555 > ul > li > span.a2";
			Elements select2 = doc.select(query);
			Object[] value = select2.toArray();
			setMore(key, value, houseDesc);
			//set EstateDesc

			query = "body > div.main-wrap > div.house-detail-desc > div.main-detail-info.fl > ul.district-info-list.c_333.f14.lh28 > li > span:nth-child(2)";
			String query2 = "body > div.main-wrap > div.house-detail-desc > div.main-detail-info.fl > ul.district-info-list.c_333.f14.lh28 > li > span:nth-child(1)";
			se = doc.select(query);
			Elements se2 = doc.select(query2);
			for(int i=0;i<se2.size();i++){
				Element element = se.get(i);
				String text = element.text();
				
				Element el2 = se2.get(i);
				String text2 = el2.text().trim();
				
				if(text2.equals("建筑年代：")){
					estateDesc.setYear(extractDateYear(text));
				}else if(text2.equals("建筑类型：")){
					estateDesc.setStyle(text);
				}else if(text2.equals("物业公司：")){
					estateDesc.setCompany(text);
				}else if(text2.equals("物业费用：")){
					
					Pattern compile = Pattern.compile("[\\d.]+");
					Matcher matcher = compile.matcher(text);
					if(matcher.find()){
						String group = matcher.group();
						estateDesc.setPrice(Double.parseDouble(group));
					}
					
				}else if(text2.equals("所属商圈：")){
					estateDesc.setArea(text);
				}
			}
			list_r.add(houseMsg);
		}
		
		return list_r;
	}
	
	private Date extractUpdate(String str){
		//2017-05-01 00:00:05更新    0 次浏览
		String[] split = str.split("更新");
		String[] split2 = split[0].split("\\s+");
		Calendar instance = Calendar.getInstance();
		String[] split3 = split2[0].split("-");
		String[] split4 = split2[1].split(":");
		instance.set(Integer.parseInt(split3[0]),Integer.parseInt(split3[1]) - 1,Integer.parseInt(split3[2]), Integer.parseInt(split4[0]), Integer.parseInt(split4[1]),Integer.parseInt(split4[2]));
		return instance.getTime();
	}
	
	private void setHouseStyle(String str,HouseBase base){
		//1室1厅1卫   50 平  豪华装修
		//housestyle  size  decorateStyle
		String[] split = str.split("[\\s\\p{Zs}]+");
		for(int i=0;i<split.length;i++){
			String string = split[i];
			if( string.matches(".*室.*")){
				base.setHouseStyle(string);
			}else if(string.matches("\\d+")){
//				String replace = string.replace("平", "");
//				System.out.println(string);
				base.setSize(Integer.parseInt(string));
			}else if(string.matches(".*修.*")){
				base.setDecorateStyle(string);
			}
		}
	}
	private void setOrien(String str,HouseBase base){
		//东西  22层/共27层
		//orien  totalFloor  currFloor
		String[] split = str.split("[\\s\\p{Zs}]+");
		for(int i=0;i<split.length;i++){
			if( split[i].matches(".*层.*")){
				String[] split2 = split[i].split("/");
				Pattern compile = Pattern.compile("\\d+");
				Matcher matcher = compile.matcher(split2[0]);
				if(matcher.find()){
					String group = matcher.group();
					base.setCurrFloor(Integer.parseInt(group));
				}
				
				matcher = compile.matcher(split2[1]);
				if(matcher.find()){
					String group = matcher.group();
					base.setTotalFloor(Integer.parseInt(group));
				}
				
			}else{
				base.setOrien(split[i]);
			}
		}
	}
	private void setBase(String str,HouseBase base){
		//1300 元/月    面议
		//price  payStyle
		String[] split = str.split("[\\s\\p{Zs}]+");
		for(int i=0;i<split.length;i++){
			if( split[i].matches("\\d+")){
				base.setPrice(Integer.parseInt(split[i]));
			}else if( !split[i].matches("/") ){
				base.setPayStyle(split[i]);
			}
		}
		
	}
	
	private void setMore(Object[] key,Object[] value,HouseDesc desc){
		/**
		 * 	房屋亮点 advantage		
			出租要求	require
			房源描述 description
		 */
		
		for(int i=0;i<key.length;i++){
			Element ele = (Element) key[i];
			String  str = ele.text(); 
			str = str.trim();
			
			Element ele2 = (Element) value[i];
			String value2 = ele2.text();
			
			if(str.equals("房屋亮点")){
				desc.setAdvantage(value2);
			}else if(str.equals("出租要求")){
				desc.setRequire(value2);
			}else if(str.equals("房源描述")){
				desc.setDescription(value2);
			}
		}
	}
	private Date  extractDateYear(String str){
		//2012转化为一个Date类型
		String trim = str.trim();
		String[] split = trim.split("-");
		int parseInt = Integer.parseInt(split[0]);
		Date date = new Date();
		date.setYear(parseInt - 1900);
		return date;
	}
}
