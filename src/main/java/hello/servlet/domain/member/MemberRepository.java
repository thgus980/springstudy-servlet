package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store=new HashMap<>(); //static 으로 했기 때문에 MemberRepository 가 new 로 아무리 많아도 하나만 생성이 된다
    private static long sequence= 0L;

    private static final MemberRepository instance=new MemberRepository(); //싱글톤

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {

    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store 에 있는 모든 값 꺼내서 ArrayList 에 담아 반환
    }

    public void clearStore() {
        store.clear();
    }

}
