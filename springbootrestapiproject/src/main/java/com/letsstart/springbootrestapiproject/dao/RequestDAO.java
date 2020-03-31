package com.letsstart.springbootrestapiproject.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.letsstart.springbootrestapiproject.model.Request;
import com.letsstart.springbootrestapiproject.repository.RequestRepository;



@Service
public class RequestDAO {
	
	@Autowired
	RequestRepository requestRepository;
	
	/*to save a request*/
	
	public Request save(Request comp) {
		return requestRepository.save(comp);
	}
	
	
	/* search all requests*/
	
	public List<Request> findAll(){
		return requestRepository.findAll();
	}
	
//	public List<Request> login(String username, String password){
//		return requestRepository.findByUsernameAndPassword(username, password);
//	}
	
	public List<Request> loginByEmail(String email, String password) {
		return requestRepository.findByEmailAndPassword(email, password);
	}
	
	/*get a request by id*/
	public Optional<Request> findOne(Long empId) {
		return requestRepository.findById(empId);
	}
	
//	public List<Company> findOneByUsername(String username) {
//		return requestRepository.findByUsername(username);
//	}
	
	
	/*delete a request*/
	
	public void delete(Request comp) {
		requestRepository.delete(comp);
	}
	

}
