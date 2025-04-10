package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 기본 CRUD 등 간단한 쿼리관련한 내용은 다 spring data jpa로 해결가능
// 복잡한 쿼리는 Querydsl 사용
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
