package shop.mtcoding.blogv2.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.util.List;

@Import(UserQueryRepository.class)
@DataJpaTest
public class UserQueryRepositoryTest {

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Autowired
    private EntityManager em;

    @Test
    void save_test(){
        // given
        User user = User.builder().username("meta").password("1234").email("meta@nate.com").build();

        // when
        User userPS = userQueryRepository.save(user);

        // then
        System.out.println("테스트 -> id : "+userPS.getId());
        System.out.println("테스트 -> createdAt : "+userPS.getCreatedAt());
        Assertions.assertThat(userPS.getUsername()).isEqualTo("meta");
    } // 테스트 메서드 종료시 rollback

    @Test
    void findById_v1_test(){
        // given
        int id = 1;
        User user = User.builder().username("meta").password("1234").email("meta@nate.com").build();
        userQueryRepository.save(user);

        // when
        User userPS = userQueryRepository.findById(id); // 1차 캐시

        // then
        System.out.println("테스트 -> id : "+userPS.getId());
        Assertions.assertThat(userPS.getUsername()).isEqualTo("meta");
    }

    @Test
    void findById_v2_test(){
        // given
        int id = 1;
        User user = User.builder().username("meta").password("1234").email("meta@nate.com").build();
        userQueryRepository.save(user);
        em.clear(); // 영속성 컨텍스트 비우기

        // when
        User userPS = userQueryRepository.findById(id);

        // then
        System.out.println("테스트 -> id : "+userPS.getId());
        Assertions.assertThat(userPS.getUsername()).isEqualTo("meta");
    }

    @Test
    void findAll_test(){
        // given
        User user1 = User.builder().username("meta").password("1234").email("meta@nate.com").build();
        User user2 = User.builder().username("love").password("1234").email("love@nate.com").build();
        userQueryRepository.save(user1);
        userQueryRepository.save(user2);
        em.clear();

        // when
        List<User> userListPS = userQueryRepository.findAll();

        // then
        Assertions.assertThat(userListPS.get(1).getUsername()).isEqualTo("love");
    }

    @Test
    void deleteById_v1_test(){
        // given
        int id = 1; // 2로 변경 해서 오류 확인하기
        User user1 = User.builder().username("meta").password("1234").email("meta@nate.com").build();
        userQueryRepository.save(user1);
        em.clear();

        // when
        userQueryRepository.deleteById(id); // 트랜잭션 종료시(서비스 레이어 종료)에 delete 쿼리가 발동한다.

        // then
    }

    @Test
    void deleteById_v2_test(){
        // given
        int id = 1; // 2로 변경 해서 오류 확인하기
        User user1 = User.builder().username("meta").password("1234").email("meta@nate.com").build();
        userQueryRepository.save(user1);
        em.clear();

        // when
        userQueryRepository.deleteById(id);
        em.flush();  // 강제로 영속성 컨텍스트에 있는 객체를 DB에 전송한다.

        // then
    }

    @Test
    void update_test(){
        // given
        String password = "5678";
        User user1 = User.builder().username("meta").password("1234").email("meta@nate.com").build();
        userQueryRepository.save(user1);
        em.clear();

        // when
        User userPS = userQueryRepository.findById(1);
        userPS.updatePassword(password);
        em.flush();  // 트랜잭션 종료시(서비스 레이어 종료)에 자동 발동한다 - (영속화된 객체 변화 감지 - 더티체킹)

        // then
        Assertions.assertThat(userPS.getPassword()).isEqualTo("5678");
    }
}
