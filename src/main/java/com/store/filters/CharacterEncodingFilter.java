package com.store.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
//Phương thức chú thích
@WebFilter(
		urlPatterns = "/*",
		initParams = {
				@WebInitParam(name = "encoding", value = "UTF-8")
		}
)
public class CharacterEncodingFilter implements Filter {

	FilterConfig config;

	@Override
	public void destroy() {

	}

	@Override // Gọi doFilter() mỗi khi có yêu cầu
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Tác vụ xử lý bộ lọc được ghi trước khi gửi tới bộ lọc tiếp theo
		String encoding = this.config.getInitParameter("encoding");
		request.setCharacterEncoding(encoding);

		System.out.println("CharacterEncodingFilter - " + encoding);

		// Chuyển sang bộ lọc tiếp theo, nếu không có bộ lọc tiếp theo, hãy gọi dịch vụ
		// của servlet().
		// Phương thức gửi tới bộ lọc tiếp theo
		// Nếu bộ lọc sau không tồn tại, hãy gọi phương thức service() của servlet
		FilterChain nextFilter = chain;
		nextFilter.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.config = arg0;

	}

}
