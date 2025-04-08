package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // static 파일보다 controller가 우선임
    // 그래서 static에 만든 index.html 대신 여기서 매핑한 home.html이 호출됨
    @GetMapping("/") 
    public String home(){
        return "home";
    }
}
