package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper=new ObjectMapper(); //JSON 형식을 객체로 변환하여 사용할 수 있도록 스프링 부트에서 지원

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream InputStream=request.getInputStream();
        String messageBody=StreamUtils.copyToString(InputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);
        HelloData hellodata=objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData.username = " + hellodata.getUsername());
        System.out.println("helloData.age = "+hellodata.getAge());

        response.getWriter().write("ok");

    }
}
