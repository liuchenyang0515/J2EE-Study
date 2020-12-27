package com.me.mgallery.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if (method.equals("list")) {
			this.list(request,response);
		} else if (method.equals("delete")) {
			
		} else if (method.equals("show_create")) {
			// http://localhost:8080/management?method=show_create
			this.showCreatePage(request, response);
		} else if (method.equals("create")) {
			this.create(request, response);
		}
	}
	
	// 显示油画的方法
	private void showCreatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		 * FileItemFactory 用于将前端表单的数据转换为一个个FileItem对象
		 * ServletFileUpload 则是FileUpload组件提供Java web的Http请求解析
		 */
		ServletFileUpload sf = new ServletFileUpload(factory);
		// 2.遍历所有FileItem
		try {
			List<FileItem> formData = sf.parseRequest(request);
			for (FileItem fi : formData) {
				if (fi.isFormField()) {
					// 判断是普通输入项还是文件上传框
					System.out.println("普通输入项" + fi.getFieldName() + ":" + fi.getString("utf-8"));
				} else {
					System.out.println("文件上传项" + fi.getFieldName());
					// 3.将客户端上传到服务器的文件保存到某个目录
					// getRealPath是tomcat运行环境下的物理地址(磁盘物理路径)
					String path = request.getServletContext().getRealPath("/upload");
					System.out.println("上传文件目录:" + path);
					//经测试，上传到了D:\\Tomcat9.0\\webapps\\mgallery\\upload
					//String fileName = "test.jpg"; // 已存在的名字是不能被覆盖的，会报错，必须加以区分，比如随机数时间戳
					String fileName = UUID.randomUUID().toString();//UUID和计算机本地属性相关，比如网卡、时间等等，会出现一个唯一字符串
					// fi.getName()得到原始文件名，截取扩展名，比如xx.jpg-->.jpg
					String suffix = fi.getName().substring(fi.getName().lastIndexOf("."));
					// 浏览器输入http://localhost/upload/文件名.扩展名  即可访问
					fi.write(new File(path, fileName + suffix));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 3.文件保存到服务器目录
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
