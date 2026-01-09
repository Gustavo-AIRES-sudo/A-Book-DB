package Book.demo.Books;

import Book.demo.User.UserModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.events.Event;

import javax.imageio.plugins.tiff.ExifInteroperabilityTagSet;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    @Autowired
    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<BooksModel> titles(){
        return booksRepository.findAll();
    }

    public BooksModel findTitleById(Long id){
        return booksRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Page not founded."));
    }

    public boolean idVerify(Long id){
        return booksRepository.existsById(id);
    }

    public BooksModel addTitle(BooksModel booksModel){
        booksModel.setId(null);
        return booksRepository.save(booksModel);
    }

    public void deleteTitle(Long id){
        booksRepository.deleteById(id);
    }

   public BooksModel alterTitle(BooksModel booksModel, Long id){
        BooksModel existingTitle = booksRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found"));

        existingTitle.setId(id);
        existingTitle.setTitle(booksModel.getTitle());
        existingTitle.setBook_url(booksModel.getBook_url());

        booksRepository.save(existingTitle);
        return existingTitle;
   }

    public BooksModel alterTitle(Long id, BooksModel booksModel){
        BooksModel existingTitle = booksRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found."));

        existingTitle.setId(id);
        existingTitle.setTitle(booksModel.getTitle());
        existingTitle.setBook_url(booksModel.getBook_url());
        existingTitle.setUserModel(booksModel.getUserModel());

        booksRepository.save(existingTitle);
        return existingTitle;

    }


}
