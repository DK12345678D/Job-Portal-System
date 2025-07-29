package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Application;
import com.entity.User;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	List<Application> findByApplicant(User applicant);
}
