package shop.mtcoding.blogv2.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp(){
        // auto increment 초기화
        em.createNativeQuery("ALTER TABLE user_tb ALTER COLUMN id RESTART WITH 1").executeUpdate();
    }

    @Test
    void save_test(){
        // given
        User user = User.builder().username("meta").password("1234").email("meta@nate.com").build();

        // when
        User userPS = userRepository.save(user);

        // then
        System.out.println("테스트 -> id : "+userPS.getId());
        Assertions.assertThat(userPS.getUsername()).isEqualTo("meta");
    }

    @Test
    void findById_test(){
        // given
        int id = 1;
        User user = User.builder().username("meta").password("1234").email("meta@nate.com").build();
        userRepository.save(user);
        em.clear(); // 영속성 컨텍스트 비우기

        // when
        // Java Optional 객체 공부하기
        User userPS = userRepository.findById(id).get();

        // then
        System.out.println("테스트 -> id : "+userPS.getId());
        Assertions.assertThat(userPS.getUsername()).isEqualTo("meta");
    }

    @Test
    void findAll_test(){
        // given
        User user1 = User.builder().username("meta").password("1234").email("meta@nate.com").build();
        User user2 = User.builder().username("love").password("1234").email("love@nate.com").build();
        userRepository.save(user1);
        userRepository.save(user2);
        em.clear();

        // when
        List<User> userListPS = userRepository.findAll();

        // then
        Assertions.assertThat(userListPS.get(1).getUsername()).isEqualTo("love");
    }

    @Test
    void deleteById_test(){
        // given
        int id = 1; // 2로 변경 해서 오류 확인하기
        User user1 = User.builder().username("meta").password("1234").email("meta@nate.com").build();
        userRepository.save(user1);
        em.clear();

        // when
        userRepository.deleteById(id); // 트랜잭션 종료시(서비스 레이어 종료)에 delete 쿼리가 발동한다.

        // then
    }

    @Test
    void update_test(){
        // given
        String password = "5678";
        User user1 = User.builder().username("meta").password("1234").email("meta@nate.com").build();
        userRepository.save(user1);
        em.clear();

        // when
        User userPS = userRepository.findById(1).get();
        userPS.updatePassword(password);
        em.flush();  // 트랜잭션 종료시(서비스 레이어 종료)에 자동 발동한다 - (영속화된 객체 변화 감지 - 더티체킹)

        // then
        Assertions.assertThat(userPS.getPassword()).isEqualTo("5678");
    }
}
