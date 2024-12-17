package com.example.commu.Controller;

import com.example.commu.Domain.Member;
import com.example.commu.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/join")
    public String join(){
        return "join";
    }

    @GetMapping("/member/check-id") //id 중복확인
    public  ResponseEntity<Boolean> checkId(@RequestParam String id){
        boolean ok = memberService.checkId(id);
        return ResponseEntity.ok(ok);
    }

    @PostMapping("/member/join")
    public String join(@ModelAttribute Member member){
        memberService.join(member);
        return "redirect:/";
    }


    @GetMapping("/member/login")
    public String login(){
        return "login";
    }

    @GetMapping("/member/accessLogin")
    public ResponseEntity<Boolean> accessLogin(@RequestParam String id, @RequestParam String password,
                                               HttpSession session){
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        boolean ok = memberService.accessLogin(member);

        if(ok){
            session.setAttribute("login", true);
            session.setAttribute("userId", id);
        }
        return ResponseEntity.ok(ok);
    }

    @GetMapping("/member/isLogin")
    public ResponseEntity<Map<String, Object>> isLogin(HttpSession session){
        Boolean status = (Boolean) session.getAttribute("login");
        String id =  (String) session.getAttribute("userId");
        Map<String, Object> response = new HashMap<>();
        response.put("login", status);
        response.put("userId", id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.removeAttribute("login");
        session.removeAttribute("userId");
        session.invalidate();
        return "redirect:/";
    }


}
