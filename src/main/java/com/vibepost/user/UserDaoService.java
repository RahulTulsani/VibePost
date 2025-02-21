package com.vibepost.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(1,"Rahul", LocalDate.now().minusYears(30)));
		users.add(new User(2,"Utkarsh", LocalDate.now().minusYears(25)));
		users.add(new User(3,"Muthu", LocalDate.now().minusYears(20)));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id) {
	    Predicate<User> predicate = user -> user.getId() == id;
	    return users.stream().filter(predicate).findFirst().orElse(null); // Returns null if user not found
	}

}
