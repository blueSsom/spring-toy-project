package com.bluessom.toyapp.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluessom.toyapp.member.Member;
import com.bluessom.toyapp.member.dao.MemberDao;

@Service
public class MemberService implements IMemberService {
	
	@Autowired
	MemberDao dao;

	@Override
	public void memberJoin(Member member) {
		
		int result = dao.memberInsert(member);
		
		if (result == 0) System.out.println("join fail");
		else System.out.println("join success");
		
	}

	@Override
	public Member memberSearch(Member member) {
		
		Member selectedMem = dao.memberSelect(member);
		
		if(selectedMem == null) System.out.println("login fail");
		else System.out.println("login success");
		
		return selectedMem;
	}

}
