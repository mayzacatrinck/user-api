package challenge.api.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import challenge.api.dto.AddressDto;
import challenge.api.dto.UserDto;
import challenge.api.model.User;
import challenge.api.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<User> findUser(Long id) {
		User user = userService.findUser(id);	
		log.info("User found successfully: {}", user);
		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody @Valid UserDto userDto) {
		User user = userService.createUser(userDto);
		log.info("User created successfully: {}", user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PostMapping("/{idUser}/address")
	public ResponseEntity<User> registerAddress(@PathVariable Long idUser,
			@RequestBody @Valid AddressDto addressDto) {
		User user = userService.registerAddress(idUser, addressDto);
		log.info("Address {}, successfully added to user: {}", addressDto, idUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

}
