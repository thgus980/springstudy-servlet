package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository=MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) { // Map 에다가 request 정보를 모두 다 넣어서 보내주면 여기서는 단순히 꺼내서 쓰기만 하면 됨
        String username=paramMap.get("username");
        int age=Integer.parseInt(paramMap.get("age"));

        Member member=new Member(username,age);

        memberRepository.save(member);

        ModelView mv=new ModelView("save-result"); // 논리적 이름
        mv.getModel().put("member", member);
        return mv;
    }
}
