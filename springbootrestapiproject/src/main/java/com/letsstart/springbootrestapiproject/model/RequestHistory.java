package com.letsstart.springbootrestapiproject.model;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name="requeststatushistory")
@EntityListeners(AuditingEntityListener.class)

public class RequestHistory { 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, unique = true)
	private Long id;
	private String supervisor;
	private String description;
	private Status status;
	private Status changedStatus;
	private Date requestExpirationDate;
	private Date changedRequestExpirationDate;
	private Date requestDate;
	private Date changedAt;
	
	
	@Column(name = "company_id")
    private Long company_id;
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
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
	
	public Date getRequestDate() {
		return requestDate;
	}
	
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
	public Date getChangedAt() {
		return changedAt;
	}
	
	public void setChangedAt(Date changedAt) {
		this.changedAt = changedAt;
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
	public Status getChangedStatus() {
		return changedStatus;
	}
	public void setChangedStatus(Status changedStatus) {
		this.changedStatus = changedStatus;
	}
	public Date getRequestExpirationDate() {
		return requestExpirationDate;
	}
	public void setRequestExpirationDate(Date requestExpirationDate) {
		this.requestExpirationDate = requestExpirationDate;
	}
	
	public Date getChangedRequestExpirationDate() {
		return changedRequestExpirationDate;
	}
	public void setChangedRequestExpirationDate(Date changedRequestExpirationDate) {
		this.changedRequestExpirationDate = changedRequestExpirationDate;
	}
	
}