package shop.mtcoding.blogv2.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    // HttpSession은 너무 자주 사용해서 IoC 컨테이너에 등록되어 있음
    private final HttpSession session;

    @PostMapping("/login")
    public String login(@Valid UserRequest.LoginDTO requestDTO, Errors errors) {
        User sessionUser = userService.로그인(requestDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(@Valid UserRequest.JoinDTO requestDTO, Errors errors) {
        userService.회원가입(requestDTO);
        return "redirect:/loginForm";
    }

    @PostMapping("/user/update")
    public String update(@Valid UserRequest.PasswordUpdateDTO requestDTO, Errors errors) {
        return "redirect:/";
    }


    @GetMapping("/logout")
    public String logout() {
        session.invalidate(); // 세션에 저장된 모든 값을 삭제합니다.
        return "redirect:/";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }
}
