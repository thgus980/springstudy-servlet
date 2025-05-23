package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberRepositoryTest {

    MemberRepository memberRepository=MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore(); // 테스트가 끝나면 깨끗하게 초기화하기 위함
    }

    @Test
    void save() {
        //given
        Member member=new Member("hello", 20);

        //when
        Member savedMember=memberRepository.save(member);

        //then
        Member findMember=memberRepository.findById(savedMember.getId());
        Assertions.assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        //given
        Member member1=new Member("member1",20);
        Member member2=new Member("member2",30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result=memberRepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(member1, member2);

    }

}
