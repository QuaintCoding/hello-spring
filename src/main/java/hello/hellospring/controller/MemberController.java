package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    // Controller 어노테이션이 있으면 MemberController객체를 생성하고 spring container에서 관리함
    private final MemberService memberService;

    // 생성자를 호출할 때 Autowired 있으면 MemberService를 스프링 컨테이너에 있는 MemberService와 연결시킴
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String creatFrom() {
        return "members/createMemberForm";
    }
}
