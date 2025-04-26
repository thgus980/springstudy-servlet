package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members") //중복되는 부분 이렇게 묶어주기도 가능
public class SpringMemberControllerV2 { // 하나의 컨트롤러에 @RequestMapping 붙은 메소드들을 다 넣을수 있다

    private MemberRepository memberRepository=MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }


    @RequestMapping
    public ModelAndView members() {
        List<Member> members=memberRepository.findAll();
        ModelAndView mv= new ModelAndView("members");
        mv.addObject("members",members);

        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username=request.getParameter("username");
        int age=Integer.parseInt(request.getParameter("age"));

        Member member=new Member(username,age);
        memberRepository.save(member);

        ModelAndView mv=new ModelAndView("save-result");
        mv.addObject("member",member);
        return mv;
    }


}
