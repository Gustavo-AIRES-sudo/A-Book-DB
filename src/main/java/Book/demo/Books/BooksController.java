package Book.demo.Books;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private final BooksService booksService;
    private final BooksMapper booksMapper;

    public BooksController(BooksService booksService, BooksMapper booksMapper) {
        this.booksService = booksService;
        this.booksMapper = booksMapper;
    }

    @GetMapping("/welcome")
    public String welcome (){
        return "Welcome to the your library";
    }

    @GetMapping("/titles")
    public ResponseEntity<@NonNull List<BooksDTO>> showAllTitles (){
        List<BooksDTO> findAll = booksService.titles();
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(findAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull BooksDTO> showById (@PathVariable Long id){
        BooksDTO dtoBook = booksService.findTitleById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(dtoBook);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook (@RequestBody BooksDTO booksDTO){//DTO DE ENTRADA
        BooksDTO newBook = booksService.addTitle(booksDTO);//CRIA DTO DE SAÍDA EM UMA NOVA VARIÁVEL
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("New book added. Infos: " + newBook);//RETORNA A SAÍDA DO DTO
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteBook (@PathVariable Long id){
        booksService.deleteTitle(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Book deleted successfully");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/alter/{id}")
    public ResponseEntity<String> alterBook (@PathVariable Long id, @RequestBody BooksDTO booksdto){
        BooksDTO savedBook = booksService.alterBook(id, booksdto);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Altered info of the book " + savedBook.getTitle());//envia o body ao usuário
    }
}