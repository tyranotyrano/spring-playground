package com.tyranotyrano.config;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tyranotyrano.repository.JdbcTemplateMemberRepository;
import com.tyranotyrano.repository.JpaMemberRepository;
import com.tyranotyrano.repository.MemberRepository;
import com.tyranotyrano.service.MemberService;

@Configuration
public class SpringConfig {

	// 4. Jpa Repository
	private final EntityManager em;

	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}

	/*
	// 2. H2 DB + 순수 Jdbc Repository
	private final DataSource dataSource;

	@Autowired
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	*/

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		// 1. Memory DB 로 사용하던 Repository
		// return new MemoryMemberRepository();

		// 2. H2 DB + 순수 Jdbc Repository
		// return new JdbcMemberRepository(dataSource);

		// 3. H2 DB + JdbcTemplate Repository
		// return new JdbcTemplateMemberRepository(dataSource);

		// 4. Jpa Repository
		return new JpaMemberRepository(em);
	}
}
