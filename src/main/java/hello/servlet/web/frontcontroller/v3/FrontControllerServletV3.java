package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*") // v1 슬래시 하위에 어떤 것들이 들어와도 무조건 이 서블릿이 호출된다
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap=new HashMap<>(); // 매핑 정보

    public FrontControllerServletV3() {

        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3()); // 첫 번째 인자가 호출되면 두 번째 인자 실행되도록
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        String requestURI = request.getRequestURI();

        ControllerV3 controller=controllerMap.get(requestURI); // URI 에 맞는 컨트롤러 찾아짐

        if(controller==null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);

        ModelView mv=controller.process(paramMap);

        String viewName=mv.getViewName();//mv 로부터는 논리 이름 (ex. new-form)만 알 수 있다

        MyView view = viewResolver(viewName); //논리 이름을 가지고 물리 이름을 만들어준다

        view.render(mv.getModel(), request,response);


    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap=new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paraName->paramMap.put(paraName, request.getParameter(paraName)));
        return paramMap;
    }
}
