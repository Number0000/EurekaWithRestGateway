package com.SmoothStack.EurekaAdmin.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SmoothStack.EurekaAdmin.Entity.Publisher;

@Repository
@Transactional
public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
	
	Page<Publisher> findAll(Pageable pageRequest);

}

