package com.SmoothStack.EurekaLibrarian.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SmoothStack.EurekaLibrarian.Entity.Borrower;

@Repository
@Transactional
public interface BorrowerRepository extends JpaRepository<Borrower, Integer>{
	
	Page<Borrower> findAll(Pageable pageRequest);

}

