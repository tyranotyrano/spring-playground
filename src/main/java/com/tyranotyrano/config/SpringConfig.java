package com.tyranotyrano.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tyranotyrano.repository.MemberRepository;
import com.tyranotyrano.repository.MemoryMemberRepository;
import com.tyranotyrano.service.MemberService;

@Configuration
public class SpringConfig {

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
