package com.letsstart.springbootrestapiproject.repository;

import com.letsstart.springbootrestapiproject.model.Company;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	//List<Company> findByUsernameAndPassword(String username, String password);
	List<Company> findByUsername(String username);
	List<Company> findByEmailAndPassword(String email, String password);
}
