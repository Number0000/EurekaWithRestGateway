package com.SmoothStack.EurekaBorrower.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SmoothStack.EurekaBorrower.Entity.Borrower;

@Repository
@Transactional
public interface BorrowerRepository extends JpaRepository<Borrower, Integer>{
	
	Page<Borrower> findAll(Pageable pageRequest);

}

