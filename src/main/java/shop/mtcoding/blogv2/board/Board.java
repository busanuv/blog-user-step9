package shop.mtcoding.blogv2.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blogv2.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    @Column(nullable = true)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    //@ManyToOne(cascade = CascadeType.ALL) // 영속성 전파
    //@ManyToOne
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }

}
