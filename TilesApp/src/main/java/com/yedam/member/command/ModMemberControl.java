package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class ModMemberControl implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mid = req.getParameter("mid");
		String mpw = req.getParameter("mpw");
		String mName = req.getParameter("mname");
		String mPhone = req.getParameter("mphone");
		String mAddr = req.getParameter("maddr");
		
		MemberVO vo = new MemberVO();
		vo.setMemberId(mid);
		vo.setMemberPw(mpw);
		vo.setMemberName(mName);
		vo.setMemberPhone(mPhone);
		vo.setMemberAddr(mAddr);
		
		MemberService service = new MemberServiceMybatis();
		if(service.modMember(vo) > 0) {
			req.setAttribute("result", "정보 수정 완료");
		} else {
			req.setAttribute("result", "정보 수정 중 오류 발생");
			return "main/myPage.tiles";
		}
		
		HttpSession session = req.getSession();
		
		session.setAttribute("vo", vo);
		session.setAttribute("id", vo.getMemberId());
		session.setAttribute("name", vo.getMemberName());
		
		return "main.do";
	}

}
