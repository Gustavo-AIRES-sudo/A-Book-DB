package Book.demo.User;

import Book.demo.Books.BooksModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private Long id;

    private String userName;

    private Integer userAge;

    private String userGmail;

    private List<BooksModel> booksModels;
}
