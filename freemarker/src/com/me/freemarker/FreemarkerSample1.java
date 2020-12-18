package com.me.freemarker;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class FreemarkerSample1 {

	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		// 1.加载模版
		// 创建核心配置对象
		Configuration config = new Configuration(Configuration.VERSION_2_3_30);
		// 设置加载的目录,在这个类中加载，""表示当前包
		config.setClassForTemplateLoading(FreemarkerSample1.class, "");
		// 得到模版对象
		Template t = config.getTemplate("sample1.ftl");
		// 2.创建数据
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("site", "百度");
		data.put("url", "http://www.baidu.com");
		// 3.产生输出,先把字节流转换为字符流
		t.process(data, new OutputStreamWriter(System.out));
	}

}
