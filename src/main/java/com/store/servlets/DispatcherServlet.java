package com.store.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.bind.DataBinding;
import com.store.bind.ServletRequestDataBinders;
import com.store.context.ApplicationContext;
import com.store.controls.Controller;
import com.store.listeners.ContextLoaderListener;

@WebServlet("*.html")
@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		// pageControllerNhận 'đường dẫn servlet' để chuyển chi nhánh tới
		String servletPath = request.getServletPath();

		try {
			/* ServletContext sc = this.getServletContext(); */
			ApplicationContext ctx = ContextLoaderListener.getApplicationContext();
			// Đối tượng khớp với 'đường dẫn servlet' được lấy ra khỏi không gian chia sẻ 'ngữ cảnh servlet'.
			// Controller pageController = (Controller) sc.getAttribute(servletPath);
			Controller pageController = (Controller) ctx.getBean(servletPath);

			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("session", request.getSession());

			// Nếu đối tượng pageController kế thừa DataBinding
			// => => Nếu đối tượng được tạo là pageController
			// Đây có phải là loại ràng buộc dữ liệu không? Hay nó là một đối tượng kế thừa ràng buộc dữ liệu?
			if (pageController instanceof DataBinding) {

				// pageController Bằng cách tạo một đối tượng để chuyển tới
				// Tự động đưa các tham số được trình duyệt gửi vào đối tượng
				// model lưu giá trị
				// =>Chứa dữ liệu được trình duyệt yêu cầu và lưu trữ trong model.
				preparedRequestData(request, model, (DataBinding) pageController);
			}

			String viewUrl = "";
			// POJO Page Controller
			if (pageController != null) {
				viewUrl = pageController.execute(model);
				// ex) update Trong trường hợp , đối tượng thành viên được cập nhật được đặt trong
				//không gian yêu cầu để có thể lấy và sử dụng thông tin.
				for (String key : model.keySet()) {
					request.setAttribute(key, model.get(key));
				}

			} else {
				System.out.println("Địa chỉ Controller không tồn tại!");
			}

			// Nếu đường dẫn bắt đầu bằng 'redirect:', hãy đi thẳng
			if (viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}

	private void preparedRequestData(HttpServletRequest request, HashMap<String, Object> model, DataBinding dataBinding)
			throws Exception {
		// pageController Mỗi lần nó trả về một đối tượng mà nó đã tạo.
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null; // key 
		Class<?> dataType = null; // Thông tin lớp của đối tượng được tạo
		Object dataObj = null; // đối tượng thô

		// DataBinding được truyền có chứa đối tượng pageController và vì cả hai là một cặp nên i+2
		for (int i = 0; i < dataBinders.length; i += 2) {
			dataName = (String) dataBinders[i];
			dataType = (Class<?>) dataBinders[i + 1];

			/*
			 * yêu cầu: Cần thiết để trích xuất tham số dataType: Loại lớp để tạo đối tượng dataNmae: Tên tham số
			 */

			// Tạo một đối tượng
			// Nó được đưa ra dưới dạng tham số vì thông tin phải được truy xuất thông qua getparameter của yêu cầu.
			dataObj = ServletRequestDataBinders.bind(request, dataType, dataName);
			// Lưu đối tượng đã tạo vào mô hình
			model.put(dataName, dataObj);
		}
	}
}