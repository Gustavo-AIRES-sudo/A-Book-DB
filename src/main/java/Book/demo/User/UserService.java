package Book.demo.User;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers(){
        List<UserModel> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(Long id){
        Optional<UserModel> user = userRepository.findById(id);
        return user.map(userMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
    }

    public UserDTO alterUserInfo(Long id, UserDTO userDTO){
        Optional<UserModel> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        UserModel updatingUser = user.get();
        userMapper.updateUserFromDTO(userDTO, updatingUser);
        UserModel updatedUser = userRepository.save(updatingUser);
        return userMapper.map(updatedUser);
    }

    public UserDTO addUser(UserDTO userDTO){
        UserModel newUser = userMapper.map(userDTO);
        newUser = userRepository.save(newUser);
        return userMapper.map(newUser);
    }


}