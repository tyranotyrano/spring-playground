package com.tyranotyrano.service;

import java.util.List;
import java.util.Optional;

import com.tyranotyrano.domain.Member;
import com.tyranotyrano.repository.MemberRepository;
import com.tyranotyrano.repository.MemoryMemberRepository;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(Member member) {
        validateDuplicateNameMember(member);

        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }

    private void validateDuplicateNameMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                        });
    }
}