package challenge.api.controller;

import javax.validation.Valid;

import challenge.api.dto.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import challenge.api.dto.AddressRequest;
import challenge.api.dto.UserRequest;
import challenge.api.model.User;
import challenge.api.service.UserService;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> findUser(@PathVariable Long id) {

		User user = userService.findUser(id);	
		log.info("User found successfully: {}", user.getName());

		return ResponseEntity.ok(new UserResponse(user));
	}

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest userRequest, UriComponentsBuilder builder) {

		User user = userService.createUser(userRequest);
		URI uri = builder.path("/users/{id}").build(user.getId());
		log.info("User created successfully: {}", user.getName());

		return ResponseEntity.created(uri).build();
	}

	@PostMapping("/{idUser}/address")
	public ResponseEntity<?> registerAddress(@PathVariable Long idUser,
			@RequestBody @Valid AddressRequest addressRequest) {

		User user = userService.registerAddress(idUser, addressRequest);
		log.info("Address {}, successfully added to user: {}", addressRequest, idUser);

		return ResponseEntity.ok().build();
	}

}
