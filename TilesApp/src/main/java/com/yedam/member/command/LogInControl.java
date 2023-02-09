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

public class LogInControl implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 성공하면 mypage로 이동
		// 실패하면 다시 로그인화면으로 이동, "아이디, 패스워드 확인" 출력
		String id = req.getParameter("mid");
		String pw = req.getParameter("mpw");
		
		MemberVO vo = new MemberVO();
		vo.setMemberId(id);
		vo.setMemberPw(pw);
		
		MemberService service = new MemberServiceMybatis();
		MemberVO rvo = service.login(vo);
		
		if(rvo != null) {
			
			HttpSession session = req.getSession();
			
			session.setAttribute("vo", rvo);
			session.setAttribute("id", rvo.getMemberId());
			session.setAttribute("name", rvo.getMemberName());
			return "main/myPage.tiles";
		} else {
			req.setAttribute("result", "회원정보를 확인하세요!!");
			return "main/logIn.tiles";
		}
	}

}
