package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    // 우선순위가 있음
    // 스프링 컨테이너에서 요청에 대한 mapping 을 찾음
    // 그러므로 index.html은 무시됨
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
