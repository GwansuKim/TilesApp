package com.yedam.member.command;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class SignOnControl implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// form:multipart/form-data => 처리(MultipartRequest)
		// 생성자매개값: 1) 요청정보, 2) 저장경로, 3)최대 파일 사이즈 지정, 4)인코딩, 5)리네임정책 
		String savePath = req.getServletContext().getRealPath("/images");
		int maxSize = (1024 * 1024 * 10);
		String encoding = "utf-8";
		
		
		try {
			//파일 업로드 서블릿
			MultipartRequest multi = new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());
			
			String id = multi.getParameter("member_id");
			String pw = "";
			if(multi.getParameter("member_pw") == null) {
				pw = id;
			} else {
				pw = multi.getParameter("member_pw");
			}
			String nm = multi.getParameter("member_name");
			String ph = multi.getParameter("member_phone");
			String ad = multi.getParameter("member_addr");
			String from = multi.getParameter("from");
			String fileName = "";
			
			Enumeration<?> files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String file = (String) files.nextElement();
				fileName = multi.getFilesystemName(file);
			}
			
			MemberVO member = new MemberVO();
			member.setMemberId(id);
			member.setMemberPw(pw);
			member.setMemberName(nm);
			member.setMemberPhone(ph);
			member.setMemberAddr(ad);
			member.setImage(fileName);
			member.setResponsibility("user");
			
			MemberService service = new MemberServiceMybatis();
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("member", member);
			Gson gson = new GsonBuilder().create();
			
			if(from.equals("manager")) {
				if (service.addMember(member) > 0) {
					resultMap.put("retCode", "Success");
					// return "{\"retCode\": \"Success\"}.json";}
				} else {
					resultMap.put("retCode", "Fail");
					// return "{\"retCode\": \"Fail\"}.json";
				}
				return gson.toJson(resultMap) + ".json";
			} else {
				if(service.addMember(member)>0) {
					return "main/logIn.tiles";
				} else {
					req.setAttribute("result", "처리중 오류 발생!");
					return "main/signon.tiles";
				}	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		req.setAttribute("result", "처리중 오류 발생!");
		return "main/signon.tiles";
	}

}
