package com.tyranotyrano.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tyranotyrano.domain.Member;
import com.tyranotyrano.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
	@Autowired
	MemberRepository memberRepository;

	@Autowired
	MemberService memberService;

	@Test
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("spring");

		// when
		Long savedId = memberService.join(member);

		// then
		Member findMember = memberService.findOne(savedId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	void 중복_이름_회원_예외() {
		// given
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");

		// when
		memberService.join(member1);
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> memberService.join(member2));

		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	}
}