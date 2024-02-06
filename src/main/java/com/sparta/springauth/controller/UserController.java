package com.sparta.springauth.controller;

import com.sparta.springauth.dto.LoginRequestDto;
import com.sparta.springauth.dto.SignupRequestDto;
import com.sparta.springauth.entity.User;
import com.sparta.springauth.repository.UserRepository;
import com.sparta.springauth.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class UserController {

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signup(SignupRequestDto requestDto){
    userService.signup(requestDto);
            return "redirect:/api/user/login-page";
    }


    @PostMapping("/user/login")
        public String login(LoginRequestDto requestDto, HttpServletResponse res){
            try {
                userService.login(requestDto,res);
            } catch (Exception e) {
               return "redirect:/api/user/login-page?error";
            }

            return "redirect:/api/products";
        }
User user = new User();
    @GetMapping("/products")
    public String getProducts(HttpServletRequest req) {

        user = (User) req.getAttribute("user");


        return "redirect:/";
    }

@PutMapping("/update")
    public void update(@RequestBody User user) {
     Optional<User> UserToEdit = userRepository.findByUsername(user.getUsername());
           if(UserToEdit.isPresent()){
               User NewUser = UserToEdit.get();
               // 변경할 내용
                userRepository.save(user);//저장

           }

    }


}