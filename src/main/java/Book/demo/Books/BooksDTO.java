package Book.demo.Books;

import Book.demo.User.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BooksDTO {

    private Long id;

    private String title;

    private String bookUrl;

    private String synopsis;

    private String author;

    private UserModel userModel;

}
