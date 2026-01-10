package Book.demo.Books;

import org.springframework.stereotype.Component;

@Component
public class BooksMapper {

    public BooksModel toModel(BooksDTO booksDTO){
        BooksModel booksModel = new BooksModel();

        booksModel.setId(booksDTO.getId());
        booksModel.setBook_url(booksDTO.getBook_url());
        booksModel.setTitle(booksDTO.getTitle());
        booksModel.setAuthor(booksDTO.getAuthor());
        booksModel.setSynopsis(booksDTO.getSynopsis());
        booksModel.setUserModel(booksDTO.getUserModel());

        return booksModel;

    }

    public BooksDTO toDTO(BooksModel booksModel){
        BooksDTO booksDTO = new BooksDTO();

        booksDTO.setAuthor(booksModel.getAuthor());
        booksDTO.setSynopsis(booksModel.getSynopsis());
        booksDTO.setBook_url(booksModel.getBook_url());
        booksDTO.setId(booksModel.getId());
        booksDTO.setTitle(booksModel.getTitle());
        booksDTO.setUserModel(booksModel.getUserModel());

        return booksDTO;
    }

}
