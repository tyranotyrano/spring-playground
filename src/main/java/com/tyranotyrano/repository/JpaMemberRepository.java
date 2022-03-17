package com.tyranotyrano.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.tyranotyrano.domain.Member;

public class JpaMemberRepository implements MemberRepository {

	private final EntityManager em;

	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		// JPQL 문법
		List<Member> results = em.createQuery("select m from Member m where m.name = :name", Member.class)
								 .setParameter("name", name)
								 .getResultList();
		return results.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		// JPQL 문법
		return em.createQuery("select m from Member m", Member.class)
				 .getResultList();
	}
}
