package Book.demo.User;

import Book.demo.Books.BooksModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "username")
    private String user_name;

    @Column (name = "age")
    private Integer user_age;

    @Column (name = "gmail", unique = true)
    private String user_gmail;

    @OneToMany(mappedBy = "userModel")
    @JsonIgnore
    private List<BooksModel> booksModels;
}
