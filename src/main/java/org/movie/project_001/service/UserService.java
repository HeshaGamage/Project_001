package org.movie.project_001.service;

import org.movie.project_001.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.movie.project_001.utils.UserRoles;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class UserService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String USERS_FILE = "src/main/resources/data/users.json";
    private final Map<UUID, String> loggedInUsers = new HashMap<>();

    private User getUserByUsernameAndPassword(String username, String password) throws IOException {
        return getAllUsers().stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public User getUserByUserName(String username) throws IOException {
        return getAllUsers().stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    //Sign in
    public void signIn(String username, String email, String password) throws IOException {
        // Load existing users
        List<User> users = getAllUsers();

        // Check if the username or email already exists
        boolean exists = users.stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username) || user.getEmail().equalsIgnoreCase(email));
        if (exists) {
            throw new IllegalArgumentException("Username or email already exists!");
        }

        // Add new user
        User newUser = new User(username, email, UserRoles.USER, password);
        addUser(newUser);
    }

    //log in
    public boolean logIn(String username, String password) throws IOException {
        // Load users
        List<User> users = getAllUsers();

        // Validate credentials
        Optional<User> userOpt = users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password))
                .findFirst();

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            loggedInUsers.put(user.getUserId(), username);
            return true; // Successful login
        } else {
            return false; // Invalid credentials
        }
    }

    //Logout
    public void logOut(String username) throws IOException {
        // Validate the user's credentials
        User user = getUserByUserName(username);
        if (user == null) {
            throw new IllegalArgumentException("No user found with username");
        }

        // Remove the user from session
        if (!loggedInUsers.containsKey(user.getUserId())) {
            throw new IllegalArgumentException("User is not logged in!");
        }
        loggedInUsers.remove(user.getUserId());
    }

//    //signout
//    public void signOut(String username, String password) throws IOException {
//        // Validate the user's credentials
//        User user = getUserByUsernameAndPassword(username, password);
//        if (user == null) {
//            throw new IllegalArgumentException("Invalid username or password!");
//        }
//
//        // Remove the user from the database
//        deleteUser(user.getUserId());
//
//        // Remove the user from session if they are logged in
//        loggedInUsers.remove(user.getUserId());
//    }

    //checkin if loogedin
    public boolean isLoggedIn(UUID userId) {
        return loggedInUsers.containsKey(userId);
    }



    public List<User> getAllUsers() throws IOException {
        File file = new File(USERS_FILE);
        return Arrays.asList(mapper.readValue(file, User[].class));
    }

    public User getUserById(UUID id) throws IOException {
        if (id == null) {
            return null; // Handle null input
        }
        return getAllUsers().stream()
                .filter(user -> user.getUserId().equals(id)) // Use == for long vs Long
                .findFirst()
                .orElse(null);
    }

    public void addUser(User user) throws IOException {
        List<User> users = new ArrayList<>(getAllUsers());
        users.add(user);
        mapper.writeValue(new File(USERS_FILE), users);
    }

    public void updateUser(UUID id, User updatedUser) throws IOException {
        if (id == null) {
            return;
        }
        List<User> users = new ArrayList<>(getAllUsers());
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(id)) {
                users.set(i, updatedUser);
                break;
            }
        }
        mapper.writeValue(new File(USERS_FILE), users);
    }

    public void deleteUser(UUID id) throws IOException {
        if (id == null) {
            return;
        }
        List<User> users = new ArrayList<>(getAllUsers());
        users.removeIf(user -> user.getUserId().equals(id));
        mapper.writeValue(new File(USERS_FILE), users);
    }
}