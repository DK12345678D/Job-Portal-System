package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Job {
	@Id
	@GeneratedValue
	private Long id;

	private String title;
	private String description;

	@ManyToOne
	private User employer;
}
