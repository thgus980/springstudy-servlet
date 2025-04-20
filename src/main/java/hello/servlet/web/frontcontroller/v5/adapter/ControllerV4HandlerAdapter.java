package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller=(ControllerV4) handler; //Type 변환. 캐스팅.

        Map<String, String> paramMap = createParamMap(request);
        HashMap<String, Object> model=new HashMap<>();

        String viewName = controller.process(paramMap, model); //컨트롤러는 뷰의 이름 반환

        ModelView mv=new ModelView(viewName); // 어댑터 변환 - 중요한 부분 ( 어댑터는 ModelView 를 만들어서 형식을 맞추어 변환 )
        mv.setModel(model);

        return mv;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap=new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paraName->paramMap.put(paraName, request.getParameter(paraName)));
        return paramMap;
    }

}
