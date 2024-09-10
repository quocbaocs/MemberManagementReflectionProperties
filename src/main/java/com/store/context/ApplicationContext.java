package com.store.context;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class ApplicationContext {
	//application-context.properties Đọc tệp và tạo Hashtable để chứa giá trị key và value
	//key : jndi.dataSource, memberDAO, /auth/login.html ....
	//value : java:comp/env/jdbc/studydb, spms.dao.MemberDAO, com.store.controls.LoginController...
	Hashtable<String, Object> objTable = new Hashtable<String, Object>();
	
	//key Phương thức trả về một đối tượng tương ứng với một giá trị
	public Object getBean(String key) {
		return objTable.get(key);
	}
	
	//Hàm tạo đọc đường dẫn tệp (application-context.properties) và tạo các thuộc tính.
	public ApplicationContext(String propertiesPath) throws Exception {
		Properties props = new Properties();
		props.load(new FileReader(propertiesPath));
		
		//Phương thức tạo đối tượng bằng cách đọc tệp thuộc tính
		prepareObject(props); 
		// Phương pháp tiêm phụ thuộc
		injectDependency();
	}
	
	public void prepareObject(Properties props) throws Exception {
		Context ctx = new InitialContext();
		String key =null;
		String value = null;
		
		//props.keySet : jndi.dataSource, memberDAO, /auth/login.do ....
		for(Object item : props.keySet()) {
			key = (String)item;
			//props.getProperty : java:comp/env/jdbc/studydb, spms.dao.MemberDAO, com.store.controls.LoginController...
			value = props.getProperty(key);
			if(key.startsWith("jndi.")) {
				//tìm key dataSource lookup
				objTable.put(key, ctx.lookup(value));
			} else {
				//dataSource Ngoài ra, các đối tượng được tạo trực tiếp
				objTable.put(key, Class.forName(value).newInstance());
			}
		}
	}
	
	public void injectDependency() throws Exception {
		for(String key : objTable.keySet()) {
			//Nếu đó không phải là dataSource, hãy tìm phương thức setter và chèn đối tượng DAO.
			if(!key.startsWith("jndi.")) {
				// Gửi đối tượng tương ứng với giá trị khóa làm tham số
				callSetter(objTable.get(key));
			}
		}
	}
	
	//Tìm và thực thi phương thức setter
	private void callSetter(Object obj) throws Exception {
		Object dependency = null;
		// Gọi tất cả các phương thức trong đối tượng và tìm phương thức setter trong số đó
		for(Method m : obj.getClass().getMethods()) {
			if(m.getName().startsWith("set")) {
				
				//Tìm phương thức setter và tìm đối tượng có kiểu tham số của phương thức setter.
				//dependency = MySqlMemberDAO
				dependency = findObjectByType(m.getParameterTypes()[0]);
				if(dependency != null) {
					m.invoke(obj, dependency);
				}
			}
		}
	}
	
	private Object findObjectByType(Class<?> type) {
		for(Object obj : objTable.values()) {
			if(type.isInstance(obj)) {
				return obj;
			}
		}
		return null;
	}
}