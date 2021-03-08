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

	@Override
	public Member memberModify(Member member) {
		
		//Member modifiedMem = dao.memberUpdate(member);
		int result = dao.memberUpdate(member);
		
		if(result == 0) {
			System.out.println("Modify fail");
			return null;
		} else System.out.println("Modify success");
		
		return member; 
	}
	
	@Override
	public int memberRemove(Member member) {
		
		int result = dao.memberDelete(member);
		
		if(result == 0) {
			System.out.println("Remove fail");
		} else System.out.println("Remove success");
		
		return result;
	}
}
