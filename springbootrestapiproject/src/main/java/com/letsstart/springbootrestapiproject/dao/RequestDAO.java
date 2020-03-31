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
	
	public Request save(Request req) {
		return requestRepository.save(req);
	}
	
	/* search all requests*/
	
	public List<Request> findAll(){
		return requestRepository.findAll();
	}
	
	/*get a request by id*/
	public Optional<Request> findOne(Long empId) {
		return requestRepository.findById(empId);
	}
	
	/*delete a request*/
	
	public void delete(Request req) {
		requestRepository.delete(req);
	}	

}
