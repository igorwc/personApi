package one.digitalinnovation.personapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.personapi.dto.UserDTO;
import one.digitalinnovation.personapi.entity.ApiUser;
import one.digitalinnovation.personapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    

    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserDTO userDTO) {
        System.out.println(bCryptPasswordEncoder.encode(userDTO.getPassword()));
    	userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userService.createUser(userDTO);
    }
}