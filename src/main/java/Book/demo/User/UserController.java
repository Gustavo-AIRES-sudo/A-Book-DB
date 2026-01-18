package Book.demo.User;

import Book.demo.Books.BooksDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to this aplication";
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> showAllUsers(){
        List<UserDTO> books = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).
                body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> showUserById(@PathVariable Long id){
        UserDTO user = userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(Long id){
        userService.deleteUser(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted");

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

    }

    @PutMapping("/alter/{id}")
    public ResponseEntity<UserDTO> alterUserInfo(@PathVariable Long id, @RequestBody UserDTO userDTO){
        UserDTO user = userService.alterUserInfo(id, userDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO){
        UserDTO user = userService.addUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User created with sucess.");
    }
}
