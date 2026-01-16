package Book.demo.Books;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

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
        return ResponseEntity.ok(findAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull BooksDTO> showById (@PathVariable Long id){
        BooksDTO dtoBook = booksService.findTitleById(id);
        return ResponseEntity.ok(dtoBook);
    }

    @PostMapping("/add")
    public ResponseEntity<@NonNull BooksDTO> addBook (@RequestBody BooksDTO booksDTO){//DTO DE ENTRADA
        BooksDTO newBook = booksService.addTitle(booksDTO);//CRIA DTO DE SAÍDA EM UMA NOVA VARIÁVEL
        return ResponseEntity.ok(newBook);//RETORNA A SAÍDA DO DTO
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
    public ResponseEntity<@NonNull BooksDTO> alterBook (@PathVariable Long id, @RequestBody BooksDTO booksdto){
        booksService.alterTitle(id, booksdto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
