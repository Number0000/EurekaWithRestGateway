package com.SmoothStack.EurekaLibrarian.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SmoothStack.EurekaLibrarian.Entity.LibraryBranch;

@Repository
@Transactional
public interface LibraryBranchRepository extends JpaRepository<LibraryBranch, Integer>{
	
	Page<LibraryBranch> findAll(Pageable pageRequest);

}

