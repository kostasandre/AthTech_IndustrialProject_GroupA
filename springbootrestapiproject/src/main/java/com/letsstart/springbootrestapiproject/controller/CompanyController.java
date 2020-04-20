package com.letsstart.springbootrestapiproject.controller;

import java.util.ArrayList;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.letsstart.springbootrestapiproject.dao.CompanyDAO;
import com.letsstart.springbootrestapiproject.dao.RequestDAO;
import com.letsstart.springbootrestapiproject.model.Company;
import com.letsstart.springbootrestapiproject.model.Login;
import com.letsstart.springbootrestapiproject.model.Request;

@CrossOrigin(origins = "http://localhost:4078")
@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	CompanyDAO companyDAO;
	RequestDAO requestDAO;
	
	/* to save a company*/
	@PostMapping("/register")
	public Company createCompany(@Valid @RequestBody Company comp) {
		return companyDAO.save(comp);
	}
	
	@PostMapping("/request")
	public Company createRequest(@Valid @RequestBody Request req) {
		List<Request> request = new ArrayList<Request>();
		
		Company comp = companyDAO.findOne(req.getCompany().getId()).get();
		//request = comp.getGetRequests();
		Request req1 = new Request();
		req1.setSupervisor(req.getSupervisor());
		request.add(req1);
		return companyDAO.save(comp);
	}
	
	/*get all companies*/
	@GetMapping("/companies")
	public List<Company> getAllCompanies(){
		return companyDAO.findAll();
	}
	
	
	
	/*get company by id*/
//	@GetMapping("/companies/{id}")
//	public ResponseEntity<Company> getCompanyById(@PathVariable(value="id") Long compid){
//		
//		Optional<Company> optEmp=companyDAO.findOne(compid);
//		Company comp;
//		try {
//			comp = optComp.get();
//		} catch (NoSuchElementException e) {
//			comp = null;
//		}
//		
//		if(comp==null) {
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok().body(comp);
//		
//	}
	
	// Company login
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public Object findByEmailAndPassword(@RequestBody Body body) {
//		List<Company> companies = companyDAO.loginByEmail(body.getEmail(), body.getPassword());
//		if(companies.isEmpty()) {
//			return ResponseEntity.notFound().build();
//		}
//		return companies.get(0);
//	}
	
	@PostMapping("/login")
	public Object createLogin(@Valid @RequestBody Login log) {
		List<Company> companies = companyDAO.loginByEmail(log.getEmail(), log.getPassword());
		if(companies.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return companies.get(0);
	}
	
	
	/*update an company by compid*/
//	@PutMapping("/companies/{id}")
//	public ResponseEntity<Company> updateCompany(@PathVariable(value="id") Long compid,@Valid @RequestBody Company compDetails){
//		
//		Optional<Company> optEmp=companyDAO.findOne(compid);
//		Company comp;
//		try {
//			comp = optComp.get();
//		} catch (NoSuchElementException e) {
//			comp = null;
//		}
//		
//		if(comp==null) {
//			return ResponseEntity.notFound().build();
//		}
//		comp.setUsername(compDetails.getUsername());
//		comp.setPassword(compDetails.getPassword());
//		comp.setFirstname(compDetails.getFirstname());
//		comp.setLastname(compDetails.getLastname());
//		comp.setAmount(compDetails.getAmount());
//		
//		Company updateCompany=companyDAO.save(comp);
//		return ResponseEntity.ok().body(updateCompany);
//	}
	
	/*Delete a request*/
//	@DeleteMapping("/requests/{id}")
//	public ResponseEntity<Request> deleteRequest(@PathVariable(value="id") Long reqid){
//		
//		Optional<Request> optEmp=requestDAO.findOne(reqid.longValue());
//		Company comp;
//		try {
//			reqid = optEmp.get();
//		} catch (NoSuchElementException e) {
//			reqid = null;
//		}
//		if(reqid==null) {
//			return ResponseEntity.notFound().build();
//		}
//			requestDAO.delete(reqid);
//		
//		return ResponseEntity.ok().build();
//	}
		

}
