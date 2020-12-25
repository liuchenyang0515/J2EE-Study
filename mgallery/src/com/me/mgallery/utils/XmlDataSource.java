package com.me.mgallery.utils;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.me.mgallery.entity.Painting;

public class XmlDataSource {
	// 通过static静态关键字保证数据全局唯一
	private static List<Painting> data = new ArrayList<>();
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
			// 利用DOM4j对XML进行解析
			SAXReader reader = new SAXReader();
			// 1.获取Document文档对象
			Document document = reader.read(dataFile);
			// 2.Xpath得到XML节点集合
			List<Node> nodes = document.selectNodes("/root/painting");
			
			for (Node node : nodes) {
				Element element = (Element)node;
				String id = element.attributeValue("id");
				String pname = element.elementText("pname"); // 子节点文本
				Painting painting = new Painting();
				painting.setId(Integer.parseInt(id));
				painting.setPname(pname);
				painting.setCategory(Integer.parseInt(element.elementText("category")));
				painting.setPrice(Integer.parseInt(element.elementText("price")));
				painting.setPreview(element.elementText("preview"));
				painting.setDescription(element.elementText("description"));
				data.add(painting);
//				System.out.println(id + ":" + pname);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 	获取所有油画Painting对象
	 * @return	Painting List
	 */
	public static List<Painting> getRawData() {
		return data;
	}
	
	public static void main(String[] args) {
//		new XmlDataSource();
		List<Painting> ps = XmlDataSource.getRawData();
		System.out.println(ps);
	}
}
