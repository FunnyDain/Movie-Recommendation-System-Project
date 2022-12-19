
package com.project.custommovie.controller;


import com.project.custommovie.dto.MemberDTO;
import com.project.custommovie.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@ComponentScan(basePackages = "com.project.custommovie")
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;



    //회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String home() {
        return "redirect:/member/register.html";

    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute @Valid MemberDTO memberDTO) {

        memberService.save(memberDTO);
        return "redirect:/member/login.html";
    }

//welcome
    @GetMapping("/")
    public String loginForm() {
        return "redirect:/member/login.html";
    }

    @PostMapping("/member/login")
    public String logincheck(@ModelAttribute MemberDTO memberDTO,HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null) {
            //로그인 성공
            session.setAttribute("loginsession", loginResult.getMemberid());
            return "redirect:/home.html";
        }else {
            //로그인 실패
            return "redirect:/member/login.html";
        }
    }

    // 로그아웃
    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/member/login.html";
    }
    //게시판 홈




}





