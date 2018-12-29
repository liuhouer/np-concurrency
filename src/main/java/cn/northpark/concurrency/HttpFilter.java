package cn.northpark.concurrency;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.northpark.concurrency.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpFilter implements  Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest my_request = (HttpServletRequest) request;
		//打印当前线程的ID与请求路径
        log.info("do filter, {}, {}", Thread.currentThread().getId(), my_request.getServletPath());
        //将当前线程ID添加到ThreadLocal中
        RequestHolder.add(Thread.currentThread().getId());
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}



}

