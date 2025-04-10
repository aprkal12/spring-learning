package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    회원 가입
    public Long join(Member member) {
        validateDuplicatedMember(member);
//        ifPresent는 람다식을 인자로 받아야만 사용가능 -> ifPresent가 값이 존재할 경우 람다식을 실행시킴
//        isPresent는 값이 존재하면 true 반환 아닐경우 false 반환 -> if문 사용가능
        
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        if(result.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 강의 버전
//    private void validateDuplicatedMember(Member member) {
//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
//    }
    //    전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}

