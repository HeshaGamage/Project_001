package org.movie.project_001.controllers;

import jakarta.servlet.http.HttpSession;
import org.movie.project_001.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Sign-In: Register a new user
    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String email, @RequestParam String password) throws IOException {
        userService.signIn(username, email, password);
        return "User registered successfully!";
    }

    // Log-In: Authenticate an existing user
    @PostMapping("/login")
    public String logIn(@RequestParam String username, @RequestParam String password, HttpSession session) throws IOException {
        System.out.println(username);
        System.out.println(password);
        boolean isAuthenticated = userService.logIn(username, password);
        if (isAuthenticated) {
            // Add user info to session to keep them logged in
            session.setAttribute("user", username);
            return "redirect:/";
        } else {
            // Set error message and return to login page
            return "redirect:/login?error=true";
        }

    }

    // Log-Out: Remove the user from session
    @PostMapping("/logout")
    public String logOut(HttpSession session) throws IOException {
        userService.logOut(session.getAttribute("user").toString());
        session.invalidate();
        return "redirect:/login";
    }


//    @DeleteMapping("/signout")
//    public String signOut(@RequestParam String username, @RequestParam String password) throws IOException {
//        userService.signOut(username, password);
//        return "User removed successfully!";
//    }

}
