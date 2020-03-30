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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.letsstart.springbootrestapiproject.dao.CompanyDAO;

import com.letsstart.springbootrestapiproject.model.Company;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	CompanyDAO companyDAO;
	
	/* to save an companies*/
	@PostMapping("/companies")
	public Company createEmployee(@Valid @RequestBody Company comp) {
		return companyDAO.save(comp);
	}
	
	/*get all companies*/
	@GetMapping("/companies")
	public List<Company> getAllEmployees(){
		return companyDAO.findAll();
	}
	
	/*get company by compid*/
//	@GetMapping("/companies/{id}")
//	public ResponseEntity<Company> getEmployeeById(@PathVariable(value="id") Long compid){
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
	
	// Employee login
	@RequestMapping("/employees/login")
	public Object findByEmailAndPassword(@RequestBody Body body) {
		List<Company> employees = companyDAO.login(body.getUsername(), body.getPassword());
		if(employees.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return employees.get(0);
	}
	
	
	/*update an employee by compid*/
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
//		Company updateEmployee=companyDAO.save(comp);
//		return ResponseEntity.ok().body(updateEmployee);
//	}
	
	/*Delete a company*/
//	@DeleteMapping("/companies/{id}")
//	public ResponseEntity<Company> deleteCompany(@PathVariable(value="id") Long compid){
//		
//		Optional<Company> optEmp=companyDAO.findOne(compid.longValue());
//		Company comp;
//		try {
//			comp = optComp.get();
//		} catch (NoSuchElementException e) {
//			comp = null;
//		}
//		if(comp==null) {
//			return ResponseEntity.notFound().build();
//		}
//		companyDAO.delete(comp);
//		
//		return ResponseEntity.ok().build();
//	}
		
		
		/*Transfer money to company*/
//		@PostMapping("/companies/transfer")
//		public ResponseEntity<Object> updateEmployee(@RequestBody TransferDto transferDto){
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

class Body{
	String username;
	String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
  }
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