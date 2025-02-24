package com.vibepost.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	 @GetMapping("/users/{id}")
	 public User retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user == null)
			throw new UserNotFoundException("id:"+id);
		return user;
	 }
	 
	 @PostMapping("/users")
	 public ResponseEntity<Object> createuser(@RequestBody User user) {
		 User currentUser = service.save(user);
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(currentUser.getId())
				 .toUri();
		 return ResponseEntity.created(location).build();
	 }
}
