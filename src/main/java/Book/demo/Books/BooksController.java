package Book.demo.Books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/welcome")
    public String welcome (){
        return "Welcome to the your library";
    }

    @GetMapping("/titles")
    public List<BooksModel> showAllTitles (){
        return booksService.titles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BooksModel> showById (@PathVariable Long id){
        BooksModel booksModel = booksService.findTitleById(id);
        return ResponseEntity.ok(booksModel);
    }

    @PostMapping("/add")
    public ResponseEntity<BooksModel> addBook (@RequestBody BooksModel booksModel){
        BooksModel newBook = booksService.addTitle(booksModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook (@PathVariable Long id){
        if (!booksService.idVerify(id)){
            return ResponseEntity.notFound().build();
        }

        booksService.deleteTitle(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/alter/{id}")
    public ResponseEntity<BooksModel> alterBook (@PathVariable Long id, @RequestBody BooksModel booksModel){
        booksService.alterTitle(booksModel, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
