package shop.mtcoding.blogv2.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.mtcoding.blogv2.user.User;
import shop.mtcoding.blogv2.user.UserRepository;

import javax.persistence.EntityManager;

@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Test
    void findById_cascade_test(){ // boardEntity에 cascade 옵션달고 실행
        User u1 = User.builder().username("meta").password("1234").email("meta@nate.com").build(); // 비영속 객체
        Board b1 = Board.builder().title("title1").content("content1").user(u1).build();
        boardRepository.save(b1); // board를 insert하면 user가 함께 insert 된다.
    }

    @Test
    void findById_no_cascade_test(){ // boardEntity에 cascade 옵션없이 실행
        User u1 = User.builder().username("meta").password("1234").email("meta@nate.com").build();
        Board b1 = Board.builder().title("title1").content("content1").user(u1).build();
        boardRepository.save(b1); // board를 insert하면 user가 함께 insert 된다.
    }

    @Test
    void findById_eager_test(){ // eager 전략
        // given
        int id = 1;
        User u1 = User.builder().username("meta").password("1234").email("meta@nate.com").build();
        userRepository.save(u1);
        Board b1 = Board.builder().title("title1").content("content1").user(u1).build();
        boardRepository.save(b1); // board를 insert하면 user가 함께 insert 된다.
        em.clear();

        // when
        boardRepository.findById(id);
    }

    @Test
    void findById_lazy_test(){ // lazy 전략
        // given
        int id = 1;
        User u1 = User.builder().username("meta").password("1234").email("meta@nate.com").build();
        userRepository.save(u1);
        Board b1 = Board.builder().title("title1").content("content1").user(u1).build();
        boardRepository.save(b1); // board를 insert하면 user가 함께 insert 된다.
        em.clear();

        // when
        Board board = boardRepository.findById(id).get();

        // lazy loading
        System.out.println("테스트 -> lazy loading step 1");
        int userId = board.getUser().getId(); // lazy loading 발동안함 - id는 이미 board 테이블에 fk로 있음.
        System.out.println("테스트 -> userId : "+userId);

        System.out.println("테스트 -> lazy loading step 2");
        String username = board.getUser().getUsername();
        System.out.println("테스트 -> username : "+username);

    }
}
