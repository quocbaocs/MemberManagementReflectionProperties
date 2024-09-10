package com.store.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.store.context.ApplicationContext;

/* JNDI
 * WAS(Web Application Server) Xác định tên duy nhất cho một tài nguyên trong
 * Quy tắc đặt tên được sử dụng khi truy cập tài nguyên máy chủ trong ứng dụng
 * 1) java:comp/env				- Các mục môi trường ứng dụng
 * 2) java:comp/env/jdbc		- JDBC nguồn dữ liệu
 * 3) java:comp/ejb				- EJB thành phần
 * 4) java:comp/UserTransaction - UserTransaction 
 * 5) java:comp/env/mail		- JavaMail đối tượng kết nối
 * 6) java:comp/env/url			- URL 
 * 7) java:comp/env/jms			- JMS 
 * */

public class ContextLoaderListener implements ServletContextListener {
	//datasource Ưu điểm của việc này là Tomcat hỗ trợ nhóm kết nối.
	//Các nhà phát triển không cần phải tự tạo đối tượng nhóm kết nối.
	//BasicDataSource ds;
	static ApplicationContext applicationContext;
	//phuog thuc tĩnh (tiện lợi) để có thể sử dụng trực tiếp từ bên ngoài
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//Khi ứng dụng web được thực thi, kết nối DB sẽ tự động được tạo và đối tượng MemeberDAO được tạo.
		try {
			System.out.println("contextInitialized");
			ServletContext sc = sce.getServletContext();
			
			String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
			// propertiesPath : đường dẫn tập tin .properties
						// Bằng cách đặt nó vào hàm tạo, ApplicationContext sẽ tạo các đối tượng đã đăng ký trong tệp.
			applicationContext = new ApplicationContext(propertiesPath);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			System.out.println("contextDestroyed");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}