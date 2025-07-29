package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Application {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Job job;

	@ManyToOne
	private User applicant;

	private String status; // APPLIED, REJECTED, ACCEPTED
}
