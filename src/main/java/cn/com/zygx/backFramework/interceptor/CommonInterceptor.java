package cn.com.zygx.backFramework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.zygx.backFramework.common.context.Constant;
import cn.com.zygx.backFramework.model.Admin;

public class CommonInterceptor extends HandlerInterceptorAdapter {

	private static final String[] IGNORE_URI = { "/restful" };

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception exception)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView modelAndView)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		boolean flag = false;
		String request_url = request.getRequestURL().toString();
		for (String url : IGNORE_URI) {
			if (request_url.contains(url)) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			Admin cust = (Admin) request.getSession().getAttribute(
					Constant.SESSION_ADMIN);
			if (cust == null) {
				response.sendRedirect(request.getContextPath()
						+ "/base/login");
				return false;
			}
			return true;
		}
		return flag;
	}
}
