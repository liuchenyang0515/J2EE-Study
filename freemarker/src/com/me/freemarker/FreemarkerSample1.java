package com.me.freemarker;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.me.freemarker.entity.Computer;

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
		data.put("date", new Date());
		data.put("number", 1290451230.124384598);
		Map<String, String> info = new HashMap<String, String>();
		info.put("cpu", "i5");
		Computer c1 = new Computer("1234567", "ThinkPad", 2, null, new Date(), 12900f, info);
		data.put("computer", c1);
		// 3.产生输出,先把字节流转换为字符流
		t.process(data, new OutputStreamWriter(System.out));
	}

}
