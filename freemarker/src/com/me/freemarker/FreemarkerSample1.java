package com.me.freemarker;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
		// 1.����ģ��
		// �����������ö���
		Configuration config = new Configuration(Configuration.VERSION_2_3_30);
		// ���ü��ص�Ŀ¼,��������м��أ�""��ʾ��ǰ��
		config.setClassForTemplateLoading(FreemarkerSample1.class, "");
		// �õ�ģ�����
		Template t = config.getTemplate("sample2.ftl");
		// 2.��������
		Map<String, Object> data = new HashMap<String, Object>();
		List<Computer> computers = new ArrayList<>();
		computers.add(new Computer("1234567", "ThinkPad X1", 2, null, new Date(), 12999f, new HashMap()));
		computers.add(new Computer("1234568", "HP X1", 1, "����", new Date(), 7500f, new HashMap()));
		computers.add(new Computer("1234569", "DELL X1", 3, "����", new Date(), 8500f, new HashMap()));
		computers.add(new Computer("1234570", "ACER X1", 1, "����", new Date(), 6300f, new HashMap()));
		computers.add(new Computer("1234571", "MSI X1", 1, "����", new Date(), 9300f, new HashMap()));
		data.put("computers", computers);
		
		// LinkedHashMap���Ա�֤���ݰ��洢˳�������ȡ
		Map<String, Computer> computerMap = new LinkedHashMap<>();
		for (Computer c : computers) {
			computerMap.put(c.getSn(), c);
		}
		data.put("computer_map", computerMap);
		
//		data.put("site", "�ٶ�");
//		data.put("url", "http://www.baidu.com");
//		data.put("date", new Date());
//		data.put("number", 1290451230.124384598);
//		Map<String, String> info = new HashMap<String, String>();
//		info.put("cpu", "i5");
//		Computer c1 = new Computer("1234567", "ThinkPad", 2, null, new Date(), 12900f, info);
//		data.put("computer", c1);
		// 3.�������,�Ȱ��ֽ���ת��Ϊ�ַ���
		t.process(data, new OutputStreamWriter(System.out));
	}

}
