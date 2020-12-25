package com.me.mgallery.service;

import java.util.List;

import com.me.mgallery.dao.PaintingDao;
import com.me.mgallery.entity.Painting;
import com.me.mgallery.utils.PageModel;

public class PaintingService {
	private PaintingDao paintingDao = new PaintingDao();
	public PageModel pagination(int page, int rows) {
		if (rows == 0) {
			throw new RuntimeException("无效的rows参数");
		}
		return paintingDao.pagination(page, rows);
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
