package com.me.mgallery.service;

import java.util.List;

import com.me.mgallery.dao.PaintingDao;
import com.me.mgallery.entity.Painting;
import com.me.mgallery.utils.PageModel;
import com.me.mgallery.utils.XmlDataSource;

public class PaintingService {
	private PaintingDao paintingDao = new PaintingDao();
	
	public PageModel pagination(int page, int rows, String...category) {
		if (rows == 0) {
			throw new RuntimeException("无效的rows参数");
		}
		// 传入category就调用三个参数的pagination方法
		if (category.length == 0 || category[0] == null) {
			return paintingDao.pagination(page, rows);
		} else {
			return paintingDao.pagination(Integer.parseInt(category[0]), page, rows);
		}
	}
	/**
	 * 	新增油画
	 * @param painting 准备新增Painting的数据
	 */
	public void create(Painting painting) {
		paintingDao.create(painting);
	}
	
	/**
	 * 	按编号查询油画
	 * @param id 油画编号
	 * @return	油画对象
	 */
	public Painting findById(Integer id) {
		Painting p = paintingDao.findById(id);
		if (p == null) {
			throw new RuntimeException("[id=" + id + "]油画不存在");
		}
		return p;
	}
	
	public static void main(String[] args) {
		PaintingService paintingService = new PaintingService();
		PageModel pageModel = paintingService.pagination(2, 6);
		List<Painting> paintingList = pageModel.getPageData();
		for (Painting painting : paintingList) {
			System.out.println(painting.getPname());
		}
		System.out.println(pageModel.getPageStartRow() + ":" + pageModel.getPageEndRow());
	}
}
