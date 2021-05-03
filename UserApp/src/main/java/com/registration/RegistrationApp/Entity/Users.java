package com.registration.RegistrationApp.Entity;

import java.io.Serializable;
import java.sql.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Users")
public class Users implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="UserId")
	private String userId;
	
	@Column(name="Password",nullable = false)
	private String password;
	
	@Column(name="FirstName",nullable = false)
	private String name;
	
	@Column(name="Surname",nullable = false)
	private String surname ;

	@Column(name="Email")
	private String email ;
	
	@Column(name="DOB")
	private Date dob;
	
	@Column(name="City")
	private String city;
	
	@Column(name="Role")
	private String role;
	
	@Column(name="ContactNumber")
	private String contactNumber;
	
	@Column(name="Pincode")
	private Integer pinCode;
	
	@Column(name="Designation")
	private String designation;
	
	@Column(name="JoiningDate")
	private Date joiningDate;
}