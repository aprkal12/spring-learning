package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTraceAop {


//    @Around("execution(* hello.hello_spring.service..*(..))") -> 이렇게 하면 서비스 패키지 하위만 함
//    이런식으로 범위 지정 가능
//    joinPoint도 여러 메소드들 많아서 단순 시간 측정 말고도 다른 동작들 가능
    @Around("execution(* hello.hello_spring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("START: " + joinPoint.toString());

        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() +" "+ timeMs + " ms");
        }
    }
}
