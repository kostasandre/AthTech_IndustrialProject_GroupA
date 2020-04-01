package com.letsstart.springbootrestapiproject.controller;

import java.util.List;


import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
	public Request createRequest(@Valid @RequestBody Request req) {
		return requestDAO.save(req);
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
		
		
		/*Transfer money to company*/
//		@PostMapping("/companies/transfer")
//		public ResponseEntity<Object> updateCompany(@RequestBody TransferDto transferDto){
//			
//			Optional<Company> optComp=companyDAO.findOne(Long.parseLong(transferDto.getUserId()));
//			List<Company> optComp1=companyDAO.findOneByUsername(transferDto.getTransferTo());
//			Company empFrom;
//			Company empTo;
//			try {
//				empFrom = optEmp.get();
//				if(optEmp1.isEmpty()) {
//					return ResponseEntity.notFound().build();
//				}
//				empTo = optEmp1.get(0);
//				Long amtFrom = empFrom.getAmount();
//				Long amtTo = empTo.getAmount();
//				Long transferDtoAmt = Long.parseLong(transferDto.getAmount());
//				if(amtFrom >= transferDtoAmt) {
//					amtFrom = amtFrom - transferDtoAmt;
//					empFrom.setAmount(amtFrom);
//					employeeDAO.save(empFrom);
//					amtTo = amtTo + transferDtoAmt;
//					empTo.setAmount(amtTo);
//					employeeDAO.save(empTo);
//				}else {
//					return ResponseEntity.unprocessableEntity().build();
//				}
//				
//					
//				
//			} catch (NoSuchElementException e) {
//				return ResponseEntity.notFound().build();
//				
//			}
//			
//			return ResponseEntity.ok().build();
//	}
//		
//}


}
//class TransferDto{
//	String userId;
//	String transferTo;
//	String amount;
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//	public String getTransferTo() {
//		return transferTo;
//	}
//	public void setTransferTo(String transferTo) {
//		this.transferTo = transferTo;
//	}
//	public String getAmount() {
//		return amount;
//	}
//	public void setAmount(String amount) {
//		this.amount = amount;
//	}
//}