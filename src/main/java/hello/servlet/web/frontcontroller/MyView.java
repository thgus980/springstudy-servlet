package hello.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MyView {
    private String ViewPath;

    public MyView(String viewPath) {
        this.ViewPath = viewPath;
    }

    // 서블릿에서 특정 뷰(JSP 등)로 요청을 포워딩
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath); //dispatcher: 해당 뷰(jsp) 로 요청을 전달(포워딩)하는 역할
        dispatcher.forward(request, response); //현재의 요청과 응답 객체를 ViewPath 로 전달(포워딩), jsp 로 화면 출력 시
    }
}
