package com.yedam.common;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.member.command.*;
import com.yedam.notice.command.*;

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
		map.put("/noticeListWithTables.do", new NoticeListWithTablesControl());
		map.put("/noticeDetail.do", new NoticeDetailControl());
		map.put("/noticeAddForm.do", new NoticeAddFormControl());
		map.put("/noticeAdd.do", new NoticeAddControl());
		map.put("/noticeAddJson.do", new NoticeAddJson());
		map.put("/noticeListJson.do", new NoticeListJson());
		map.put("/noticeListAjax.do", new NoticeAjax());
		
		// 댓글
		map.put("/replyList.do", new ReplyListControl());
		map.put("/removeReply.do", new RemoveReplyControl());
		map.put("/addReply.do", new AddReplyControl());
		
		// 회원관련
		map.put("/logInForm.do", new LogInFormControl());
		map.put("/logIn.do", new LogInControl());
		map.put("/logOut.do", new LogOutControl());
		map.put("/signOnForm.do", new SignOnFormControl());
		map.put("/signOn.do", new SignOnControl());
		map.put("/myPage.do", new MyPageControl());
		map.put("/modMember.do", new ModMemberControl());
		map.put("/imageUpload.do", new ImageUploadControl());
		
		// 관리자 회원 관리
		map.put("/memberManagerForm.do", new MemberManagerFormControl());
		map.put("/memberList.do", new MemberListControl());
		map.put("/removeMember.do", new RemoveMemberControl());
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
		} else if(viewPage.endsWith(".json")) {
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().print(viewPage.substring(0, viewPage.length() - 5));
		}
	}
}
