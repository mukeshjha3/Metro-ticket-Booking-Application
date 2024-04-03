package com.metro.Metro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.metro.Metro.model.Role;
import com.metro.Metro.model.User;
import com.metro.Metro.payload.UserRequest;
import com.metro.Metro.service.UserService;
import com.metro.Metro.utils.JwtUtils;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor   
public class Usercontroller {

	private final UserService userService;
    private final JwtUtils utils;
	
	@PostMapping("/register_user")
	@ResponseStatus(code = HttpStatus.CREATED)
	public User saveNormalUser(@RequestBody UserRequest userRequest) {
		User user = userService.mapUserRequestToUser(userRequest);
		user.setRole(Role.ROLE_USER);
		User createUser = userService.createUser(user);
		return createUser;
	}

	/// This controller is for adding the admin
	/// in future it will be removed 
	@PostMapping("/register_admin")
	@ResponseStatus(code = HttpStatus.CREATED)
	public User saveAdminUser(@RequestBody UserRequest userRequest) {
		User user = userService.mapUserRequestToUser(userRequest);
		user.setRole(Role.ROLE_ADMIN);
		User createUser = userService.createUser(user);
		return createUser;
	}

	
	@PutMapping("/update_user")
	@ResponseStatus(code = HttpStatus.OK)
	public User updateUser(@RequestHeader("Authorization") String jwtToken, @RequestBody UserRequest userRequest) {
		String email = utils.extractEmailfromToken(jwtToken);
		User user =userService.mapUserRequestToUser(userRequest);
		User updateUser = userService.updateUser(email, user);
		return updateUser;

	}

	@DeleteMapping("/delete_user")
	@ResponseStatus(code = HttpStatus.OK)
	public String deleteUser(@RequestHeader("Authorization") String jwtToken) {
		String email = utils.extractEmailfromToken(jwtToken);
		userService.deleteUser(email);
		return "Account deleted successfully";
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/get_userdetails")
	public User getUserDetails(@RequestHeader("Authorization") String jwtToken) {
		String email = utils.extractEmailfromToken(jwtToken);
		System.out.println("username is :" + email);
		User user = userService.getUser(email);
		return user;

	}


	
}
