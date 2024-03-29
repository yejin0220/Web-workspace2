package com.kh.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter({"/insert.bo","/update.bo","/detail.bo", "/delete.bo"})
//@WebFilter({"/board/insert", "/board/delete/"}) or({"/board/*", "/member/*"}) 처럼 작성할 수도 있음.
public class LoginCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
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
		
		//로그인했을때만 이용할 수 있도록
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		if(session == null || session.getAttribute("loginUser") == null) {
			
			request.setAttribute("errorMsg", "로그인 후 이용하실 수 있습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
		}else {

			chain.doFilter(request, response); //특정데이터가 담겨있는 경우 -> 로그인을 했다.
			
		}
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
