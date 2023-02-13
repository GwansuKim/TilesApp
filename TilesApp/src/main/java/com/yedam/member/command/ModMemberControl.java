package com.yedam.member.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
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
		String rsps = req.getParameter("rsps");
		String status = req.getParameter("from");
		
		if(status == null) {
			status = "";
		}
		
		if(mid == null && mpw == null && mName == null && mPhone == null && mAddr == null && rsps == null) {
			String savePath = req.getServletContext().getRealPath("/images");
			int maxSize = (1024 * 1024 * 10);
			String encoding = "utf-8";
			
			MultipartRequest multi = new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());
			
			mid = multi.getParameter("mid");
			mpw = multi.getParameter("mpw");
			mName = multi.getParameter("mname");
			mPhone = multi.getParameter("mphone");
			mAddr = multi.getParameter("maddr");
			rsps = multi.getParameter("rsps");
			status = multi.getParameter("from");
		}
		
		
		MemberVO vo = new MemberVO();
		vo.setMemberId(mid);
		vo.setMemberPw(mpw);
		vo.setMemberName(mName);
		vo.setMemberPhone(mPhone);
		vo.setMemberAddr(mAddr);
		vo.setResponsibility(rsps);
		
		MemberService service = new MemberServiceMybatis();
		if(status.equals("manager")) {
			Gson gson = new GsonBuilder().create();
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("member", vo);
			if(service.modMember(vo) > 0) {
				resultMap.put("retCode", "Success");
			} else {
				resultMap.put("retCode", "Fail");
			}
			return gson.toJson(resultMap) + ".json";
		} else {			
			if(service.modMember(vo) > 0) {
				HttpSession session = req.getSession();
				
				session.setAttribute("vo", vo);
				session.setAttribute("id", vo.getMemberId());
				req.setAttribute("result", "정보 수정 완료");
				return "main.do";
			} else {
				req.setAttribute("result", "정보 수정 중 오류 발생");
			}
			return "main/myPage.tiles";
		}
	}

}
