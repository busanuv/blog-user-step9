package shop.mtcoding.blogv2._core.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.blogv2._core.error.ex.Exception400;
import shop.mtcoding.blogv2._core.error.ex.Exception401;
import shop.mtcoding.blogv2._core.error.ex.Exception500;
import shop.mtcoding.blogv2._core.util.Script;

@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception400.class)
    public String ex400(Exception400 e){
        return Script.back(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception500.class)
    public String ex500(Exception500 e){
        return Script.back(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception401.class)
    public String ex401(Exception401 e){
        // Exception 종류는 나누는 이유
        // ex) 5번 실패하면, 로그인시도 못하게 막는 코드 필요
        // 상태코드마다 다른 로직이 필요할 수 있다.
        return Script.back(e.getMessage());
    }
}
