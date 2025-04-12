package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream=request.getInputStream(); // html 의 body 내용을 바이트 코드로 얻어올 수 있음

        //바이트 코드를 스트링으로 바꾸기
        String messageBody=StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = "+messageBody);

        response.getWriter().write("RequestBodyStringServlet - ok");
    }
}
