package com.yedam.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.member.mapper.MemberMapper;
import com.yedam.member.vo.MemberVO;
import com.yedam.notice.vo.PagingUtil;

public class MemberServiceMybatis implements MemberService {
	SqlSession session = DataSource.getInstance().openSession(true);
	MemberMapper mapper = session.getMapper(MemberMapper.class);

	@Override
	public MemberVO login(MemberVO member) {
		return mapper.login(member); // session.selectOne("네임스페이스.");
	}

	@Override
	public int addMember(MemberVO member) {
		return mapper.addMember(member);
	}

	@Override
	public List<MemberVO> memberList() {
		return mapper.memberList();
	}

	@Override
	public MemberVO getMember(String id) {
		return mapper.getMember(id);
	}

	@Override
	public int modMember(MemberVO member) {
		return mapper.updMember(member);
	}

	@Override
	public int removeMember(String mid) {
		return mapper.deleteMember(mid);
	}

	@Override
	public PagingUtil getTotal() {
		return mapper.getTotal();
	}
	
}
