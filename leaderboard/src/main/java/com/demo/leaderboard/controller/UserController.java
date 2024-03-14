// package com.demo.leaderboard.controller;

// import org.springframework.web.bind.annotation.RestController;

// import com.demo.leaderboard.dto.UserDto;
// import com.demo.leaderboard.service.UserService;

// import lombok.AllArgsConstructor;
// import org.springframework.web.bind.annotation.RequestMapping;
// import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;




// @CrossOrigin("*")
// @AllArgsConstructor
// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//     @Autowired
//     private UserService userService;

//     @PostMapping
//     public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
//         UserDto savedUser=userService.createUser(userDto);
//         return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//     }
    
//     @GetMapping("{id}")
//     public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
//         UserDto userDto=userService.getUserById(userId);
//         return ResponseEntity.ok(userDto);
//     }

//     @GetMapping
//     public ResponseEntity<List<UserDto>> getAllUsers(){
//         List<UserDto> users=userService.getAllUsers();
//         return ResponseEntity.ok(users);

//     }

//     @PutMapping("{id}")
//     public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto updatedUser){
//         UserDto userDto=userService.updateUser(userId, updatedUser);
//         return ResponseEntity.ok(userDto);
//     }

//     @DeleteMapping("{id}")
//     public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
//         userService.deleteUser(userId);
//         return ResponseEntity.ok("Delete Successful");
//     }
// }
