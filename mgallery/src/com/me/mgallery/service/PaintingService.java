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

	public void update(Painting newPainting, int isPreviewModified) {
		boolean isModified = false;
		Painting oldPainting = paintingDao.findById(newPainting.getId()); // id的作用是拿到对应的旧的painting对象，而不是去更新它
		if (isPreviewModified == 1) { // 1表示原始painting对象需要更新preview图片地址
			oldPainting.setPreview(newPainting.getPreview());
			isModified = true;
		}
		if (!oldPainting.getPname().equals(newPainting.getPname())) {
			oldPainting.setPname(newPainting.getPname());
			isModified = true;
		}
		if (!oldPainting.getCategory().equals(newPainting.getCategory())) {
			oldPainting.setCategory(newPainting.getCategory());
			isModified = true;
		}
		if (!oldPainting.getPrice().equals(newPainting.getPrice())) {
			oldPainting.setPrice(newPainting.getPrice());
			isModified = true;
		}
		if (!oldPainting.getDescription().equals(newPainting.getDescription())) {
			oldPainting.setDescription(newPainting.getDescription());
			isModified = true;
		}
		if (isModified) { // 只有任意一项修改之后，才去进行更新操作
			paintingDao.update(oldPainting);
		} else {
			System.out.println("没有修改，不作处理");
		}
//		oldPainting.setId(newPainting.getId()); // id没变，不用设置
	}
	
	/**
	 * 	删除油画
	 * @param id
	 */
	public void delete(Integer id) {
		paintingDao.delete(id);
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
