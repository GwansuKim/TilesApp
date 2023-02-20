package com.yedam.member.service;

import java.util.List;

import com.yedam.member.vo.MemberVO;
import com.yedam.notice.vo.PagingUtil;

public interface MemberService {
	public MemberVO login(MemberVO member);
	public int addMember(MemberVO member);
	public List<MemberVO> memberList();
	public MemberVO getMember(String id);
	public int modMember(MemberVO member);
	public int removeMember(String mid);
	public PagingUtil getTotal();
}
