package com.store.bind;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Set;

import javax.servlet.ServletRequest;

public class ServletRequestDataBinders {
	/* request : Trích xuất tham số
	 * dataType : Tạo đối tượng với loại lớp
	 * dataName : Tên tham số
	 * */
	public static Object bind(ServletRequest request,
							 Class<?> dataType,
							 String dataName) throws Exception {
		// Khi đối tượng được tạo là PrimitiveType
		if(isPrimitiveType(dataType)) {
			return createValueObject(dataType, 
						request.getParameter(dataName));
		}
		// Trường hợp đối tượng model chung
		else {
			// Tên của các tham số do trình duyệt gửi được lưu trữ trong Set.
			Set<String> paramNames = request.getParameterMap().keySet();
			// Tạo đối tượng theo loại lớp
			Object dataObject = dataType.newInstance();
			// Tìm và lưu trữ các tham số được trình duyệt gửi vào các trường của đối tượng.
			Method m = null;	// Tìm và set giá trị vào Setter của Model
			// Truy cập từng tham số được gửi bởi trình duyệt
			for(String paramName : paramNames) {
				// Lấy Setter tương ứng với tham số
				/* ex) no Tham số -> m đề cập đến setNo
				 *     email -> m đề cập đến setEmail
				 *     password -> m đề cập đến setPassword
				 * */
				m = findSetter(dataType, paramName);
				if(m != null) {
					// Gọi phương thức m của đối tượng dataObject.
					// Thay thế giá trị tham số của khách hàng vào tham số đầu tiên.
					m.invoke(dataObject, 
							createValueObject(m.getParameterTypes()[0],
							request.getParameter(paramName)));
				}
			}
			// Ngay cả các giá trị biến do trình duyệt gửi cũng được lưu trữ trong một đối tượng và đối tượng đã tạo sẽ được trả về.
			return dataObject;
		}		
	}
	
	private static Method findSetter(Class<?> type, String name) {
		// Trích xuất tất cả các phương thức của loại lớp
		Method[] methods = type.getMethods();
		
		String propName = null;
		for(Method m : methods) {
			// Trả về nếu tên bắt đầu của phương thức không được đặt.
			if(!m.getName().startsWith("set"))
				continue;
			
			// Tên không bao gồm thuộc tính set ==>
			propName = m.getName().substring(3);
			propName = propName.toLowerCase();	// bằng chữ thường
			// Nếu tên giống với tên được truyền vào, => đã tìm thấy trình thiết lập cho thuộc tính.
			if(propName.equals(name.toLowerCase())) {
				return m;
			}
		}
		return null;
	}
	
	private static boolean isPrimitiveType(Class<?> type) {
		if(type.getName().equals("int") || type==Integer.class ||
			type.getName().equals("long") || type==Long.class ||
			type.getName().equals("float") || type==Float.class ||
			type.getName().equals("double") || type==Double.class ||
			type.getName().equals("boolean") || type==Boolean.class ||
			type==Date.class || type==String.class) {
			return true;
		}
		return false;
	}
	
	private static Object createValueObject(Class<?> type, String value) {
		if(type.getName().equals("int") || type==Integer.class)
			return new Integer(value);
		else if(type.getName().equals("float") || type==Float.class)
			return new Float(value);
		else if(type.getName().equals("double") || type==Double.class)
			return new Double(value);
		else if(type.getName().equals("long") || type==Long.class)
			return new Long(value);
		else if(type.getName().equals("boolean") || type==Boolean.class)
			return new Boolean(value);
		else if(type==Date.class)
			return java.sql.Date.valueOf(value);
		else
			return value;
	}
}