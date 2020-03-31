package com.letsstart.springbootrestapiproject.repository;

import com.letsstart.springbootrestapiproject.model.Request;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
	//List<Company> findByUsernameAndPassword(String username, String password);
	List<Request> findByUsername(String username);
	List<Request> findByEmailAndPassword(String email, String password);
}
