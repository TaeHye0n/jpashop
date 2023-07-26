package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional //@Transactional 어노테이션은 테스트에서는 기본적으로 RollBack을 하기 때문에 영속성 컨텍스트의 플러시가 일어나지 않음
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
//    @Rollback(false)
    void 횐원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);
        Member findMember = memberRepository.findById(savedId).get();

        //then
//        em.flush();
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void 중복_회원_예외V1() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        try{
            memberService.join(member2); //예외가 발생해야 한다!!!
        } catch (IllegalStateException e) {
            return;
        }

        //then
        Assertions.fail("예외가 발생해야 한다.");
    }

    @Test
    void 중복_회원_예외V2() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

}