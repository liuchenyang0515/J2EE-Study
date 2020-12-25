package com.me.mgallery.dao;

import java.util.List;

import com.me.mgallery.entity.Painting;
import com.me.mgallery.utils.PageModel;
import com.me.mgallery.utils.XmlDataSource;
/**
 * 油画数据访问对象
 * @author Administrator
 *
 */
// 获取最原始的XML数据，对其进行分页
public class PaintingDao {
	/**
	 * 	分页查询油画数据
	 * @param page 页号
	 * @param rows 每页记录数
	 * @return	PageModel分页对象
	 */
	public PageModel pagination(int page, int rows) {
		// Painting油画对象集合
		List<Painting> list = XmlDataSource.getRawData();
		// PageModel分页处理得到分页数据及分页附加
		PageModel pageModel = new PageModel(list, page, rows);
		return pageModel;
	}
}
