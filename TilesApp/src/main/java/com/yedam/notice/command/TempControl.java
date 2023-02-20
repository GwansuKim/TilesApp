package com.yedam.notice.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Command;
import com.yedam.member.vo.MemberVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;
import com.yedam.notice.vo.PagingUtil;

public class TempControl implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNo = req.getParameter("pageNo");
		String countPerPage = req.getParameter("countPerPage");
		
		NoticeService service = new NoticeServiceImpl();
		int listCnt = service.getTotal().getListCnt();
		
		PagingUtil pagingInfo = new PagingUtil(listCnt, Integer.parseInt(pageNo));
		
		if( countPerPage != null) {
			pagingInfo.setPageSize(Integer.parseInt(countPerPage));
		}
		
		NoticeVO notice = new NoticeVO();
		
		notice.setStartIndex(pagingInfo.getStartIndex());
		notice.setPageSize(pagingInfo.getPageSize());
		notice.setLastIndex(pagingInfo.getStartIndex(), pagingInfo.getPageSize());
		
		List<MemberVO> list = service.selectBoardList(notice);
		
		req.setAttribute("result", list);
		req.setAttribute("pagingInfo", pagingInfo);
		
		return "notice/noticePaging.tiles";
	}

}
