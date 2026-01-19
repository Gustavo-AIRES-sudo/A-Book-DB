package Book.demo.User;

import Book.demo.Books.BooksModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "username")
    private String userName;

    @Column (name = "age")
    private Integer userAge;

    @Column (name = "gmail", unique = true)
    private String userGmail;

    @OneToMany(mappedBy = "userModel")
    @JsonManagedReference
    private List<BooksModel> booksModels = new ArrayList<>();
}
