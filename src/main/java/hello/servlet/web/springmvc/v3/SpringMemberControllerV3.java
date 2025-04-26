package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members") //중복되는 부분 이렇게 묶어주기도 가능
public class SpringMemberControllerV3 { // 하나의 컨트롤러에 @RequestMapping 붙은 메소드들을 다 넣을수 있다

    private MemberRepository memberRepository=MemberRepository.getInstance();

    @GetMapping //@RequestMapping(value = "/new-form", method = RequestMethod.GET) // http method 가 get 방식인 경우에만 호출됨 -> @GetMapping 과 똑같음
    public String newForm() {
        return "new-form"; // 문자로 반환해도 됨
    }


    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members=memberRepository.findAll();

        model.addAttribute("members",members);

        return "members";
    }

    // 아래와 같이 파라미터를 간결하게 받을 수 있음 (파싱하는 것도 알아서 지원해줌)
    //@RequestMapping(value = "/save", method = RequestMethod.POST) // 단순 조회는 get, 데이터 변경은 post
    @PostMapping
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {


        Member member=new Member(username,age);
        memberRepository.save(member);

        model.addAttribute("member",member);
        return "save-result"; // 그냥 스트링으로 뷰의 이름 반환
    }


}
