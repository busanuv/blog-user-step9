package shop.mtcoding.blogv2.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.blogv2._core.util.Script;

import javax.validation.Valid;

@Controller
public class UserController {

    @ResponseBody
    @PostMapping("/user/update")
    public String update(@Valid UserRequest.PasswordUpdateDTO requestDTO, Errors errors) {
        System.out.println(requestDTO);

        if (errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                System.out.println(error.getDefaultMessage()+" : "+error.getField());
                return Script.back(error.getDefaultMessage()+" : "+error.getField());
            }
        }

        return Script.href("/");
    }

    @ResponseBody
    @PostMapping("/join")
    public String join(@Valid UserRequest.JoinDTO requestDTO, Errors errors){
        System.out.println(requestDTO);

        if (errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                System.out.println(error.getDefaultMessage()+" : "+error.getField());
                return Script.back(error.getDefaultMessage()+" : "+error.getField());
                //return Script.href("/", error.getDefaultMessage()+" : "+error.getField());
            }
        }

        return Script.href("/loginForm");
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@Valid UserRequest.LoginDTO requestDTO, Errors errors){
        System.out.println(requestDTO);

        if (errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                System.out.println(error.getDefaultMessage()+" : "+error.getField());
                return Script.back(error.getDefaultMessage()+" : "+error.getField());
            }
        }

        return Script.href("/");
    }

    @GetMapping("/logout")
    public String logout() {
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
