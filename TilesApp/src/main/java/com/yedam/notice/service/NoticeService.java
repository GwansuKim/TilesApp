package com.yedam.notice.service;

import java.util.List;

import com.yedam.member.vo.MemberVO;
import com.yedam.notice.vo.NoticeVO;
import com.yedam.notice.vo.PagingUtil;
import com.yedam.notice.vo.ReplyVO;

public interface NoticeService {
	public List<NoticeVO> noticeList();		//전체 목록 조회
	public NoticeVO getNotice(int nid);		//단건 조회
	public int addNotice(NoticeVO notice);	//등록
	public int modNotice(NoticeVO notice);	//수정
	public int remNotice(int nid);			//삭제
	//댓글 등록
	public int addReply(ReplyVO reply);
	//댓글 목록
	public List<ReplyVO> replyList(int nid);
	//댓글 삭제
	public int remReply(int rid);
	
	
	public PagingUtil getTotal();
	public List<MemberVO> selectBoardList(NoticeVO notice);
}
