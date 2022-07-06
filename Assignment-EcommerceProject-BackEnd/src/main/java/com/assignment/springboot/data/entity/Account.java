package com.assignment.springboot.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "password")
	@Size(min = 6,max = 100)
	@JsonIgnore
	private String passWord;
	private String gmail;
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "account_roles",
			joinColumns = @JoinColumn(name = "account_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles=new HashSet<>();
	
	public Account(int id, String passWord, String gmail, Employee employee, Customer customer) {
		this.id = id;
		this.passWord = passWord;
		this.gmail = gmail;
		this.employee = employee;
		this.customer = customer;
	}
}