package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.JavaUserCreateRequest;
import com.group.libraryapp.dto.user.request.JavaUserUpdateRequest;
import com.group.libraryapp.dto.user.response.JavaUserResponse;
import com.group.libraryapp.service.user.JavaUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JavaUserController {

  private final JavaUserService userService;

  public JavaUserController(JavaUserService userService) {
    this.userService = userService;
  }

//  @PostMapping("/user")
//  public void saveUser(@RequestBody JavaUserCreateRequest request) {
//    userService.saveUser(request);
//  }
//
//  @GetMapping("/user")
//  public List<JavaUserResponse> getUsers() {
//    return userService.getUsers();
//  }
//
//  @PutMapping("/user")
//  public void updateUserName(@RequestBody JavaUserUpdateRequest request) {
//    userService.updateUserName(request);
//  }
//
//  @DeleteMapping("/user")
//  public void deleteUser(@RequestParam String name) {
//    userService.deleteUser(name);
//  }

}
