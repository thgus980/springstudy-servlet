package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MyHandlerAdapter {

    boolean supports(Object handler); // 컨트롤러(핸들러)가 넘어왔을 때 내가 이 컨트롤러를 지원할 수 있어?

    ModelView handler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
