package com.durable.db;

import java.io.IOException;
import java.util.Scanner;

import com.collect.rent.Rent;
import com.collect.specific.Specific;
import com.collect.url.Collect;

public class Starter {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException{
		System.out.println("请输入起始地址:");
		Scanner scanner = new Scanner(System.in);
		String nextLine = scanner.nextLine();
//		String url = "http://nc.58.com/qingyunpu/zufang/?minprice=600_600&sourcetype=5";
		new Save().execute(new Specific().execute(new Rent().execute(new Collect().execute(nextLine))));
		System.exit(0);
	}
}
