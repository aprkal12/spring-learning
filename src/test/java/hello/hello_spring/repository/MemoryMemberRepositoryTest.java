package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; // 이렇게 하면 메소드 바로 사용가능, 스태틱으로 가져오기

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 매 테스트마다 특정 기능을 반복하는 일종의 콜백
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("testmem");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(member, result); // 출력은 없음
//         Assertions.assertEquals(member, null); // 이러면 테스트 통과 x
        assertThat(result).isEqualTo(member); // 이 버전의 assertion도 있음
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("testmem1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("testmem2");
        repository.save(member2);

        Member result = repository.findByName("testmem1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("testmem1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("testmem2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
