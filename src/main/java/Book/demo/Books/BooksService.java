package Book.demo.Books;

import Book.demo.User.UserModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.events.Event;

import javax.imageio.plugins.tiff.ExifInteroperabilityTagSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class BooksService {

    @Autowired
    private final BooksRepository booksRepository;
    private final BooksMapper booksMapper;

    public BooksService(BooksRepository booksRepository, BooksMapper booksMapper) {
        this.booksRepository = booksRepository;
        this.booksMapper = booksMapper;
    }

    public List<BooksDTO> titles(){
        List<BooksModel> allBooks = booksRepository.findAll();
        return allBooks.stream()
                .map(booksMapper::map)
                .collect(Collectors.toList());
    }

    public BooksDTO findTitleById(Long id){
        Optional<BooksModel> book = booksRepository.findById(id);
        return book.map(booksMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public boolean idVerify(Long id){
        return booksRepository.existsById(id);
    }

    public BooksDTO addTitle(BooksDTO booksDTO){
        BooksModel books = booksMapper.map(booksDTO);
        books = booksRepository.save(books);
        return booksMapper.map(books);
    }

    public void deleteTitle(Long id){
        booksRepository.deleteById(id);
    }

    public BooksDTO alterBook(Long id, BooksDTO booksDTO){
        Optional<BooksModel> book = booksRepository.findById(id);

        if (book.isPresent()){
            BooksModel bookToUpdate = book.get();

            booksMapper.updateBookFromDTO(booksDTO, bookToUpdate);

            BooksModel newBook = booksRepository.save(bookToUpdate);
            return booksMapper.map(newBook);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id of book not found");
    }


}
