package com.tyranotyrano.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tyranotyrano.repository.JdbcMemberRepository;
import com.tyranotyrano.repository.MemberRepository;
import com.tyranotyrano.service.MemberService;

@Configuration
public class SpringConfig {

	private final DataSource dataSource; // 스프링이 application.yml 에 설정한 내용을 보고 자체적으로 DataSource 빈을 생성한다.

	@Autowired
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		// 1. Memory DB 로 사용하던 Repository
		// return new MemoryMemberRepository();

		return new JdbcMemberRepository(dataSource);
	}
}
