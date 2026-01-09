package Book.demo.Books;

import Book.demo.User.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "tb_books")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class BooksModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "bookURL", unique = true)
    private String book_url;

    @ManyToOne
    @JoinColumn(name = "foreignkeyUserId")
    private UserModel userModel;

}
