package shop.mtcoding.blogv2.user;

import lombok.Data;

public class UserRequest {

    @Data // getter, setter, toString
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @Data
    public static class PasswordUpdateDTO {
        private String password;
    }
}
