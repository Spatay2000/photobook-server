package kz.masa.photobook.photobookserver.controller;

import kz.masa.photobook.photobookserver.util.exception.ResourceNotFoundException;
import kz.masa.photobook.photobookserver.model.User;
import kz.masa.photobook.photobookserver.repository.UserRepository;
import kz.masa.photobook.photobookserver.security.CurrentUser;
import kz.masa.photobook.photobookserver.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
//    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findByEmail(userPrincipal.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userPrincipal.getEmail()));
    }
}
