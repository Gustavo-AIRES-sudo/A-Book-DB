package Book.demo.Books;

import Book.demo.User.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BooksDTO {

    private Long id;

    private String title;

    private String book_url;

    private String synopsis;

    private String author;

    private UserModel userModel;

}
