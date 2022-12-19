package us.group14.backend.user;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.group14.backend.annotations.ApiMapping;
import us.group14.backend.views.AllUserInfoView;
import us.group14.backend.views.ContactsView;
import us.group14.backend.views.UserAndAccountView;

import java.util.List;
import java.util.Set;

@RestController
@ApiMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/all")
    @JsonView(AllUserInfoView.class)
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping
    @JsonView(UserAndAccountView.class)
    public ResponseEntity<User> getUser(AuthUser authUser) {
        return ResponseEntity.ok(authUser.get());
    }

    @PostMapping("/contacts/{username}")
    public ResponseEntity<String> addContact(AuthUser authUser, @PathVariable("username") String username) {
        return userService.addContact(authUser.get(), username);
    }

    @GetMapping("/contacts")
    @JsonView(ContactsView.class)
    public ResponseEntity<Set<Contact>> getContacts(AuthUser authUser){
        return userService.getContacts(authUser.get());
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<String> deleteContact(AuthUser authUser, @PathVariable("id") Long id) {
        return userService.deleteContact(authUser.get(), id);
    }



}
