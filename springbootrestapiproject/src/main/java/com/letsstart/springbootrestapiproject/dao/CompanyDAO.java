package com.letsstart.springbootrestapiproject.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.letsstart.springbootrestapiproject.model.Company;
import com.letsstart.springbootrestapiproject.repository.CompanyRepository;


@Service
public class CompanyDAO {
	
	@Autowired
	CompanyRepository companyRepository;
	
	/*to save an company*/
	
	public Company save(Company comp) {
		return companyRepository.save(comp);
	}
	
	
	/* search all companies*/
	
	public List<Company> findAll(){
		return companyRepository.findAll();
	}
	
	public List<Company> login(String username, String password){
		return companyRepository.findByUsernameAndPassword(username, password);
	}
	
	public List<Company> loginByEmail(String email, String password) {
		return companyRepository.findByEmailAndPassword(email, password);
	}
	
	/*get an employee by id*/
	public Optional<Company> findOne(Long empId) {
		return companyRepository.findById(empId);
	}
	
	public List<Company> findOneByUsername(String username) {
		return companyRepository.findByUsername(username);
	}
	
	
	/*delete an employee*/
	
	public void delete(Company comp) {
		companyRepository.delete(comp);
	}
	

}
