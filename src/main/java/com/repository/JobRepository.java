package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Job;
import com.entity.User;

public interface JobRepository extends JpaRepository<Job, Long> {

	List<Job> findByTitleContaining(String keyword);

	List<Job> findByEmployer(User employer);

	List<Job> findByTitleContainingOrDescriptionContaining(String title, String desc);
}