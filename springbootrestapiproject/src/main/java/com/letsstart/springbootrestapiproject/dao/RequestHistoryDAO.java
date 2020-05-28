package com.letsstart.springbootrestapiproject.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.letsstart.springbootrestapiproject.model.RequestHistory;
import com.letsstart.springbootrestapiproject.repository.RequestHistoryRepository;



@Service
public class RequestHistoryDAO {
	
	@Autowired
	RequestHistoryRepository requestHistoryRepository;
	
	/*to save a request*/
	
	public RequestHistory save(RequestHistory req) {
		return requestHistoryRepository.save(req);
	}
	
	/* search all requests*/
	
	public List<RequestHistory> findAll(){
		return requestHistoryRepository.findAll();
	}
	
	/*get a request by id*/
	public Optional<RequestHistory> findOne(Long empId) {
		return requestHistoryRepository.findById(empId);
	}
	
	/*delete a request*/
	
	public void delete(RequestHistory req) {
		requestHistoryRepository.delete(req);
	}	

}
