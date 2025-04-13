package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*") // v1 슬래시 하위에 어떤 것들이 들어와도 무조건 이 서블릿이 호출된다
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap=new HashMap<>(); // 매핑 정보

    public FrontControllerServletV1() {

        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1()); // 첫 번째 인자가 호출되면 두 번째 인자 실행되도록
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");
    }
}
