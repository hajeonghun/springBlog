package com.example.spring02.model.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.member.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public boolean loginCheck(MemberDTO dto) {
		String name = sqlSession.selectOne("member.login_check", dto);
		return (name == null) ? false : true;
	}
	
	//이렇게 안하고 loginCheck에서 이름을 받아와도 됨.
	@Override
	public MemberDTO viewMember(String userid) {
		return sqlSession.selectOne("member.viewMember", userid);		
	}
}
