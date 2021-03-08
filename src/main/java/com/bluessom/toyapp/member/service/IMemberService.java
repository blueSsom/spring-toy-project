package com.bluessom.toyapp.member.service;

import com.bluessom.toyapp.member.Member;

public interface IMemberService {
	void memberJoin(Member member);
	Member memberSearch(Member member);
	Member memberModify(Member member);
	int memberRemove(Member member);
}
