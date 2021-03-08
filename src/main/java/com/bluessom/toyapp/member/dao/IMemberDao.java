package com.bluessom.toyapp.member.dao;

import com.bluessom.toyapp.member.Member;

public interface IMemberDao {
	int memberInsert(Member member);
	Member memberSelect(Member member);
	int memberUpdate(Member member);
	int memberDelete(Member member);
}
