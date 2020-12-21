package com.me.mgallery.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class XmlDataSource {
	// 通过static静态关键字保证数据全局唯一
	private static List data = new ArrayList();
	private static String dataFile;
	static {
		// 得到painting.xml文件完整物理路径
		// 如果路径有特殊字符，getPath()会进行BASE64转换，比如空格会转化为%20
		dataFile = XmlDataSource.class.getResource("/painting.xml").getPath();
		System.out.println(dataFile);
		URLDecoder decoder = new URLDecoder();
		try {
			dataFile = decoder.decode(dataFile,"UTF-8");
			System.out.println(dataFile);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new XmlDataSource();
	}
}
