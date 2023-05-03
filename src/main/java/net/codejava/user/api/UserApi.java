package net.codejava.user.api;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.user.User;

@RestController
public class UserApi {

	@Autowired private UserService service;
	
	@PutMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody @Valid User user, @RequestParam Integer id) {
		User createdUser = service.save(user,id);
		URI uri = URI.create("/users/" + createdUser.getId());
		
		UserDTO userDto = new UserDTO(createdUser.getId(), createdUser.getEmail());
		
		return ResponseEntity.created(uri).body(userDto);
	}
}
