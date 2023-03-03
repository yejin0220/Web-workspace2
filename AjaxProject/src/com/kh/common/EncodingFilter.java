package com.kh.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*") //모든 요청에 대해 Filter가 먼저 작업할 수 있도록 설정
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//서블릿이 호출되기 전 , 전처리 작업할 코드 작성
		//서블릿이 실행되기전 request.setCharacterEncoding("UTF-8");작업이 항상 먼저 이뤄짐
		
		request.setCharacterEncoding("UTF-8");
		System.out.println("인코딩 실행");
	
		//doFilter함수 호출시 url가지고 더 작업할 서블릿, 필터를 포함해서 어떤 작업이든 있는지 검사
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
