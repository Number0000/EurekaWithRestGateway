package com.SmoothStack.EurekaAdmin.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SmoothStack.EurekaAdmin.Entity.BookCopy;

@Repository
@Transactional
public interface BookCopyRepository extends JpaRepository<BookCopy, Integer>{
	
	Page<BookCopy> findAll(Pageable pageRequest);
	
	@Query("select b from tbl_book_copies b  where b.bookCopyId.book.bookId=?1 and b.bookCopyId.libraryBranch.libraryBranchId=?2")
	BookCopy getByBookIdAndBranchId(int bookId, int branchId);
	
}

