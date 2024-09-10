package com.store.controls;

import java.util.Map;
/*
	1) Để gọi “Page Controller” từ ‘Front Controller’ theo cách tương tự
	giao diện được xác định
	2) Tất cả "Page Controller" đều kế thừa giao diện này
	3) Loại bỏ sự phụ thuộc của Servlet của “Page Controller” và tạo nó trong phương thức POJO.
*/
public interface Controller {
	
	public String execute(Map<String, Object> model) throws Exception;

}
