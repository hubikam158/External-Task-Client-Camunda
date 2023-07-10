package pl.altkomsoftware.eventsdemo.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.altkomsoftware.eventsdemo.utils.Status;
import pl.altkomsoftware.eventsdemo.domain.User;
import pl.altkomsoftware.eventsdemo.domain.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
@Api(tags = "Users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String status,
                                         @RequestParam(required = false) String userEmail) {
        List<User> result;
        String message;

        if (userEmail == null && status == null) {
            result = userRepository.findAll();
            message = "Did not find any user";
        } else if (userEmail == null) {
            result = userRepository.findByStatus(Status.valueOf(status));
            message = "Did not find any user with status - " + status;
        } else if (status == null) {
            result = userRepository.findFirstByUserEmail(userEmail);
            message = "Did not find user with email - " + userEmail;
        } else {
            result = userRepository.findFirstByUserEmailAndStatus(userEmail, Status.valueOf(status));
            message = "Did not find user with email - " + userEmail + " with status - " + status;
        }

        return result != null ?
                ResponseEntity.ok(result) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @GetMapping("/isActive")
    public Boolean isUserActive(@RequestParam String userEmail) {
        return userRepository.findFirstByUserEmailAndStatus(userEmail, Status.ACTIVE) != null;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {

        Optional<User> result = userRepository.findById(userId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Did not find user id - " + userId);
        }
    }
    
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    
    @PutMapping
    public User updateUser(@RequestBody User user) {

        return userRepository.findById(user.getId())
                .map(userToUpdate -> {
                    userToUpdate.setStatus(user.getStatus());
                    userToUpdate.setFirstName(user.getFirstName());
                    userToUpdate.setLastName(user.getLastName());
                    userToUpdate.setUserEmail(user.getUserEmail());
                    return userRepository.save(userToUpdate);
                }).orElseGet(() -> addUser(user));
    }
    
    @DeleteMapping("/{userId}")
    public Status deleteUser(@PathVariable Long userId) {

        Optional<User> result = userRepository.findById(userId);
        if (result.isPresent()) {
            result.get().setStatus(Status.DELETED);
            userRepository.save(result.get());
            return result.get().getStatus();
        } else {
            throw new RuntimeException("Did not find user id - " + userId);
        }
    }
}
