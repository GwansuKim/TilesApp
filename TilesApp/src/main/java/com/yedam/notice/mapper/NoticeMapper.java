package com.yedam.notice.mapper;

import java.util.List;

import com.yedam.notice.vo.NoticeVO;

public interface NoticeMapper {
	public List<NoticeVO> selectList();			//전체 목록 조회
	public NoticeVO searchOne(int nid);			//단건 조회
	public int insertNotice(NoticeVO notice);	//등록
	public int updateNotice(NoticeVO notice);	//수정
	public int deleteNotice(int nid);			//삭제
	public int increaseCnt(int nid);			//조회수 상승
	//댓글 등록
	//댓글 목록	
}
