package com.yedam.member.mapper;

import java.util.List;

import com.yedam.member.vo.MemberVO;

public interface MemberMapper {
	public MemberVO login(MemberVO member);
	public int addMember(MemberVO member);
	public List<MemberVO> memberList();
	public MemberVO getMember(String id);
	public int updMember(MemberVO member);
	public int deleteMember(String mid);
}
