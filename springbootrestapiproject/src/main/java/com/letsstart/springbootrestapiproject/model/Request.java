package com.letsstart.springbootrestapiproject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import enums.Status;

@Entity
@Table(name="requests")
@EntityListeners(AuditingEntityListener.class)

public class Request { 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String supervisor;
	private String description;
	private Status status;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date requestDate;
	
	public Date getRequestDate() {
		return requestDate;
	}
	
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}