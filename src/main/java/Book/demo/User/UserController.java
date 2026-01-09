package Book.demo.User;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to this aplication";
    }

    @DeleteMapping
    public String deleteUser(){
        return "User deleted...";
    }

    @PutMapping
    public String alterUserInfo(){
        return "Alter user Info";
    }

    @PostMapping
    public String addUser(){
        return "user added";
    }
}
