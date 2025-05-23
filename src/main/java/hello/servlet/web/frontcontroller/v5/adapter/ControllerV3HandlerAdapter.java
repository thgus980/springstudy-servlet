package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) { // 지원할 수 있나?
        return (handler instanceof ControllerV3); //ControllerV3의 인스턴스면 참을 반환
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException { // 실제 돌리는 것
        ControllerV3 controller=(ControllerV3) handler; //Type 변환. 캐스팅.

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        return mv;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap=new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paraName->paramMap.put(paraName, request.getParameter(paraName)));
        return paramMap;
    }

}
