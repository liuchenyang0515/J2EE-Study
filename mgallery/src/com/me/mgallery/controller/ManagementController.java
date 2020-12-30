package com.me.mgallery.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.json.JSONParser;

import com.alibaba.fastjson.JSON;
import com.me.mgallery.entity.Painting;
import com.me.mgallery.service.PaintingService;
import com.me.mgallery.utils.PageModel;

/**
 * Servlet implementation class ManagementController
 */
@WebServlet("/management")
public class ManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PaintingService paintingService = new PaintingService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagementController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if (method.equals("list")) {
			// http://localhost:8080/management?method=list后台页面
			this.list(request, response);
		} else if (method.equals("delete")) {
			// 删除要带上id，是在list页面列表项中点击删除触发的
			this.delete(request, response);
		} else if (method.equals("show_create")) { // 显示新增页面
			// http://localhost:8080/management?method=show_create
			this.showCreatePage(request, response);
		} else if (method.equals("create")) {
			// 新增后提交表单触发的
			this.create(request, response);
		} else if (method.equals("show_update")) {
			// 点击修改触发的
			this.showUpdatePage(request, response);
		} else if (method.equals("update")) {
			// 点击提交修改触发的
			this.update(request, response);
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// 1.初始化FileLoad组件
		FileItemFactory factory = new DiskFileItemFactory();
		/**
		 * FileItemFactory 用于将前端表单的数据转换为一个个FileItem对象 ServletFileUpload
		 * 则是FileUpload组件提供Java web的Http请求解析
		 */
		ServletFileUpload sf = new ServletFileUpload(factory);
		String isPreviewModified = "";
		// 2.遍历所有FileItem
		try {
			List<FileItem> formData = sf.parseRequest(request);
			Painting painting = new Painting();
			for (FileItem fi : formData) {
				if (fi.isFormField()) {
					// 判断是普通输入项还是文件上传框
					System.out.println("update中普通输入项" + fi.getFieldName() + ":" + fi.getString("utf-8"));
					switch (fi.getFieldName()) {
					case "pname":
						painting.setPname(fi.getString("utf-8"));
						break;
					case "category":
						painting.setCategory(Integer.parseInt(fi.getString("utf-8")));
						break;
					case "price":
						painting.setPrice(Integer.parseInt(fi.getString("utf-8")));
						break;
					case "description":
						painting.setDescription(fi.getString("utf-8"));
						break;
					case "id":// 更新后带上id才知道对应哪个painting对象，接收隐藏域的数值
						painting.setId(Integer.parseInt(fi.getString("UTF-8")));
						break;
					case "isPreviewModified":
						isPreviewModified = fi.getString("utf-8");
						break;
					default:
						break;
					}
				} else if(isPreviewModified.equals("1")) {// 1表示重新上传了新图片
					System.out.println("update中文件上传项" + fi.getFieldName());
					// 3.将客户端上传到服务器的文件保存到某个目录
					// getRealPath是tomcat运行环境下的物理地址(磁盘物理路径)
					String path = request.getServletContext().getRealPath("/upload");
					System.out.println("上传文件目录:" + path);
					// 经测试，上传到了D:\\Tomcat9.0\\webapps\\mgallery\\upload
					// String fileName = "test.jpg"; // 已存在的名字是不能被覆盖的，会报错，必须加以区分，比如随机数时间戳
					String fileName = UUID.randomUUID().toString();// UUID和计算机本地属性相关，比如网卡、时间等等，会出现一个唯一字符串
					// fi.getName()得到原始文件名，截取扩展名，比如xx.jpg-->.jpg
					String suffix = fi.getName().substring(fi.getName().lastIndexOf("."));
					// 浏览器输入http://localhost/upload/文件名.扩展名 即可访问
					fi.write(new File(path, fileName + suffix));
					painting.setPreview("/upload/" + fileName + suffix);
				}
			}
			paintingService.update(painting, Integer.parseInt(isPreviewModified)); // 新增功能
			response.sendRedirect("management?method=list"); // 返回列表页
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// 展示修改页面
	private void showUpdatePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id"); // 点击修改的时候href="/management?method=show_update&id=${painting.id }"
		Painting painting = paintingService.findById(Integer.parseInt(id));
		request.setAttribute("painting", painting);
		request.getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(request, response);
	}

	// 显示油画的方法
	private void showCreatePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(request, response);
	}

	// 新增油画的方法
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 因为设置了enctype="multipart/form-data"，图片就不会以字符串方式上传，而是二进制的方式
		// 如果不用FileUpload，接收不到二进制上传的文件数据
//		String pname = request.getParameter("pname");
//		System.out.println(pname);
//		request.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(request, response);
		// 1.初始化FileLoad组件
		FileItemFactory factory = new DiskFileItemFactory();
		/**
		 * FileItemFactory 用于将前端表单的数据转换为一个个FileItem对象 ServletFileUpload
		 * 则是FileUpload组件提供Java web的Http请求解析
		 */
		ServletFileUpload sf = new ServletFileUpload(factory);
		// 2.遍历所有FileItem
		try {
			List<FileItem> formData = sf.parseRequest(request);
			Painting painting = new Painting();
			for (FileItem fi : formData) {
				if (fi.isFormField()) {
					// 判断是普通输入项还是文件上传框
					System.out.println("普通输入项" + fi.getFieldName() + ":" + fi.getString("utf-8"));
					switch (fi.getFieldName()) {
					case "pname":
						painting.setPname(fi.getString("utf-8"));
						break;
					case "category":
						painting.setCategory(Integer.parseInt(fi.getString("utf-8")));
						break;
					case "price":
						painting.setPrice(Integer.parseInt(fi.getString("utf-8")));
						break;
					case "description":
						painting.setDescription(fi.getString("utf-8"));
						break;
					default:
						break;
					}
				} else {
					System.out.println("文件上传项" + fi.getFieldName());
					// 3.将客户端上传到服务器的文件保存到某个目录
					// getRealPath是tomcat运行环境下的物理地址(磁盘物理路径)
					String path = request.getServletContext().getRealPath("/upload");
					System.out.println("上传文件目录:" + path);
					// 经测试，上传到了D:\\Tomcat9.0\\webapps\\mgallery\\upload
					// String fileName = "test.jpg"; // 已存在的名字是不能被覆盖的，会报错，必须加以区分，比如随机数时间戳
					String fileName = UUID.randomUUID().toString();// UUID和计算机本地属性相关，比如网卡、时间等等，会出现一个唯一字符串
					// fi.getName()得到原始文件名，截取扩展名，比如xx.jpg-->.jpg
					String suffix = fi.getName().substring(fi.getName().lastIndexOf("."));
					// 浏览器输入http://localhost/upload/文件名.扩展名 即可访问
					fi.write(new File(path, fileName + suffix));
					painting.setPreview("/upload/" + fileName + suffix);
				}
			}
			paintingService.create(painting); // 新增功能
			response.sendRedirect("management?method=list"); // 返回列表页
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 3.文件保存到服务器目录
	}
	
	/**
	 * 	客户端采用Ajax方式提交Http请求
	 * 	Controller方法处理后不再跳转任何jsp，而是通过响应输出JSON格式字符串
	 * 	Tips：作为Ajax与服务器交互后，得到的不是整页HTML，而是服务器处理后的数据
	 * @param request
	 * @param response
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			paintingService.delete(Integer.parseInt(id));
			printWriter.println(JSON.parseObject("{result:\"ok\"}"));
		} catch (Exception e) {
			e.printStackTrace();
			printWriter.println(JSON.parseObject("{result:\"" + e.getMessage() + "\"}"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p = request.getParameter("p");
		String r = request.getParameter("r");
		if (p == null) {
			p = "1";
		}
		if (r == null) {
			r = "6";
		}
		PageModel pageModel = paintingService.pagination(Integer.parseInt(p), Integer.parseInt(r));
		request.setAttribute("pageModel", pageModel);
		request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
	}

}
