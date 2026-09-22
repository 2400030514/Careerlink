package com.careerlink.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerlink.application.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}