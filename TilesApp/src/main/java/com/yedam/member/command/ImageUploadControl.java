package com.yedam.member.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class ImageUploadControl implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String savePath = req.getServletContext().getRealPath("/images");
		int maxSize = (1024 * 1024 * 10);
		String encoding = "utf-8";
		
		MultipartRequest multi = new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());

		String id = multi.getParameter("id");
		String fileName = "";
		
		Enumeration<?> files = multi.getFileNames();
		while(files.hasMoreElements()) {
			String file = (String) files.nextElement();
			fileName = multi.getFilesystemName(file);
		}
		
		MemberVO vo = new MemberVO();
		vo.setMemberId(id);
		vo.setImage(fileName);
		
		MemberService service = new MemberServiceMybatis();
		service.modMember(vo);
		
		HttpSession session = req.getSession();
		
		session.setAttribute("img", fileName);
		
		// {"retCode":Success"}
		
		return "{\"retCode\":Success\"}";
	}

}
