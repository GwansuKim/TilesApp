package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.notice.command.NoticeAddControl;
import com.yedam.notice.command.NoticeDetailControl;
import com.yedam.notice.command.NoticeListControl;
import com.yedam.notice.command.NoticeAddFormControl;

public class FrontController extends HttpServlet{

	private Map<String, Command> map;
	private String charset;
	
	public FrontController() {
		map = new HashMap<>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		charset = config.getInitParameter("charset");
		
		map.put("/main.do", new MainControl());
		map.put("/second.do", new SecondControl());
		map.put("/noticeList.do", new NoticeListControl());
		map.put("/noticeDetail.do", new NoticeDetailControl());
		map.put("/NoticeAddForm.do", new NoticeAddFormControl());
		map.put("/noticeAdd.do", new NoticeAddControl());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);
		resp.setCharacterEncoding(charset);
		
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		String page = uri.substring(context.length());
		
		Command command = map.get(page);
		String viewPage = command.exec(req,  resp);
		
		if(viewPage.endsWith(".tiles")) {
			RequestDispatcher rd = req.getRequestDispatcher(viewPage);
			rd.forward(req, resp);
		} else if(viewPage.endsWith(".do")) {
			resp.sendRedirect(viewPage);
		}
	}
}
