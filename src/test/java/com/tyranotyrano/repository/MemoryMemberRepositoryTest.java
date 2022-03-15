package com.tyranotyrano.repository;

import com.tyranotyrano.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        repository.save(member);

        // then
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring2");

        // when
        repository.save(member1);
        repository.save(member2);

        // then
        Member result = repository.findByName(member1.getName()).get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring2");

        // when
        repository.save(member1);
        repository.save(member2);

        // then
        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}