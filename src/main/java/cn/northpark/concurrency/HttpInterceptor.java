package cn.northpark.concurrency;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.northpark.concurrency.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jeyy
 * 创建拦截器Interceptor，在拦截器中删除刚才添加的值
 */
@Slf4j
public class HttpInterceptor extends  HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("HttpInterceptor-->preHandle");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("HttpInterceptor-->afterCompletion");
		RequestHolder.remove();
		return;
	}

}

