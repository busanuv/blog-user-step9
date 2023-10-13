package shop.mtcoding.blogv2.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blogv2._core.error.ex.Exception401;
import shop.mtcoding.blogv2._core.error.ex.Exception500;

@Slf4j
@RequiredArgsConstructor
@Service // IoC 등록 (컴퍼넌트 스캔)
public class UserService {

    // DI
    private final UserRepository userRepository;

    @Transactional // 트랜잭션 관리 (insert, delete, update)
    public void 회원가입(UserRequest.JoinDTO requestDTO){
        try {
            userRepository.save(requestDTO.toEntity());
        }catch (Exception e){
            log.error("DB error : "+e.getMessage());
            throw new Exception500("server error");
        }
    }

    public User 로그인(UserRequest.LoginDTO requestDTO){
        User userPS = userRepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword());

        // else 사용 자제 (if를 필터링 방식으로 사용)
        if(userPS == null){
            throw new Exception401("아이디 혹은 패스워드가 틀렸습니다");
        }
        return userPS;
    }
}
