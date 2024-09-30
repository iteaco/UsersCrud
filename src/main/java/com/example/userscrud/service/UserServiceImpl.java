package com.example.userscrud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.userscrud.entity.User;
import com.example.userscrud.exception.UserNotFoundException;
import com.example.userscrud.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
//@AllArgsConstructor
//@NoArgsConstructor
public class UserServiceImpl implements UserService {
	
	
	private UserRepository userRepository;
	
	
	public UserServiceImpl(UserRepository repository) {
		this.userRepository=repository;
	}
	
	
	@Override
	public List<User> getAllUsers() {
		//System.out.println("UserServiceImpl > getAllUsers" + userRepository.count());
		//List<User> users = userRepository.findAll(); 
		//System.out.println("size " + users.size());
		//return users;
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(String email) {
		User user = userRepository.findByEmailAddress(email);
		if(user == null) {
			throw new UserNotFoundException("User with email : "+email+" doesn't exist.");
		}
		
		return user;
	}

	@Override
	public void deleteUser(String email) {
		User user = userRepository.findByEmailAddress(email);
		userRepository.delete(user);
	}

	//========================
	
	@Override
	public void deleteUserByName(String name) {
		User user = userRepository.findByName(name);
		userRepository.delete(user);		
	}


	@Override
	public List<User> findAllByName(String name) {
		return userRepository.findAllByName(name);
	}

}
