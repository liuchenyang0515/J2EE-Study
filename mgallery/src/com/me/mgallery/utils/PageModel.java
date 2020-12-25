package com.me.mgallery.utils;

import java.util.ArrayList;
import java.util.List;

// ��ҳģ�Ͷ���
public class PageModel {
	private int page;		// ҳ��
	private int totalPages;	// ��ҳ��
	private int rows;		// ÿҳ��¼��
	private int totalRows;	// �ܼ�¼��
	private int pageStartRow; // ��ǰҳ��ӵ�n�п�ʼ
	private int pageEndRow;	// ��ǰҳ��n�н���
	private boolean hasNextPage;  // �Ƿ������һҳ
	private boolean hasPreviousPage;  // �Ƿ������һҳ
	private List pageData;	// ��ǰҳ������
	
	
	public PageModel() {

	}

	/**
	 * 	��ʼ��PageModel���󣬼����ҳ����
	 * @param data	ԭʼ���ݼ���
	 * @param page	ҳ��
	 * @param rows	ÿҳ��¼��
	 */
	public PageModel(List data, int page, int rows) {
		this.page = page;
		this.rows = rows;
		totalRows = data.size();
		// ��ҳ���������������/ÿҳ��¼����������ҳȡ����������������ȡ��
		// ���磺18/6=3 | 20/6��3.33 ����ȡ��4
		totalPages = new Double(Math.ceil(totalRows*1.0/rows)).intValue();
		
		pageStartRow = (page - 1) * rows; // ȡ��ҳ������ʼ��
		pageEndRow = page * rows;	// ȡ��ҳ���ݽ�����
		// totalRows:20 | totalPage:4 | rows:6
		// pageEndRow=4*6=24>20 ִ��subList()�׳��±�Խ���쳣
		if(pageEndRow > totalRows) {
			pageEndRow = totalRows;
		}
		
		pageData = data.subList(pageStartRow, pageEndRow); // �õ���ҳ����
		if (page > 1) { // �ж��Ƿ������һҳ
			hasPreviousPage = true;
		} else {
			hasPreviousPage = false;
		}
		if (page < totalPages) { // �ж��Ƿ������һҳ
			hasNextPage = true;
		} else {
			hasNextPage = false;
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageStartRow() {
		return pageStartRow;
	}

	public void setPageStartRow(int pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	public int getPageEndRow() {
		return pageEndRow;
	}

	public void setPageEndRow(int pageEndRow) {
		this.pageEndRow = pageEndRow;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public List getPageData() {
		return pageData;
	}

	public void setPageData(List pageData) {
		this.pageData = pageData;
	}
	
	public static void main(String[] args) {
		List sample = new ArrayList();
		for (int i = 1; i <= 100; ++i) {
			sample.add(i);
		}
		PageModel pageModel = new PageModel(sample, 6, 8);
		System.out.println(pageModel.getPageData());
		System.out.println(pageModel.getTotalPages());
		System.out.println(pageModel.getPageStartRow() + ":" + pageModel.getPageEndRow());
	}
	
}
