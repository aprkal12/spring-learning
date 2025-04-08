package hello.hello_spring;

import hello.hello_spring.controller.MemberController;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

// 자바 코드로 직접하는 스프링 빈 설정
// 각각 컴포넌트마다 service, repository 추가로 Autowired 등등 어노테이션 달아줘도 됨
// 컨트롤러는 빼고 -> 원래대로 어노테이션
// 설정 파일을 사용하면 나중에 구현 클래스를 변경할 때 여기 설정 파일면 변경하면 된다. -> 매우 편리함
// 스프링 빈에 컴포넌트가 등록이 되어 있어야 Autowired도 적용이 됨 어노테이션이던 설정파일이건 스프링 빈에 컴포넌트를 등록 해야함
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
