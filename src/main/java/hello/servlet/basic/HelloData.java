package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HelloData { // 보통 JSON 으로 받은 데이터를 바로 사용하지 않고 객체로 변환하여 사용함. 객체로 변환할 때 필요한 클래스.

    private String username;
    private int age;



}

