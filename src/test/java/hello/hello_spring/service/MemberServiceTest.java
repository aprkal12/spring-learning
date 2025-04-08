package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);
    }

    @AfterEach // 매 테스트마다 특정 기능을 반복하는 일종의 콜백
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("test");
        // when
        Long saveId = memberService.join(member);

        // then
        Member result = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    void 중복회원검증(){
        // given
        Member member = new Member();
        member.setName("test");

        Member member2 = new Member();
        member2.setName("test");
        // when
        memberService.join(member);
        // try-catch 안쓰고 람다로 하는 버전
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try{
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}