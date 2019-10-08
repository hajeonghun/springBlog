package com.example.spring02.service.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring02.model.member.dao.MemberDAO;
import com.example.spring02.model.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO memberDao;

	@Override
	public boolean loginCheck(MemberDTO dto, HttpSession session) {
		boolean result = memberDao.loginCheck(dto);
		if (result) {
			MemberDTO dto2 = viewMember(dto.getUserid());
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", dto2.getName());	//dto.getName()은 이름을 가져오지 못한다. 입력창에서 id,pw만 적기 때문에		
		}
		return result;
	}

	@Override
	public MemberDTO viewMember(String userid) {
		return memberDao.viewMember(userid);		
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();

	}
}
