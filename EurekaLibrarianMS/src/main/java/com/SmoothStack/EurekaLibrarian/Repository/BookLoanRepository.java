package com.SmoothStack.EurekaLibrarian.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SmoothStack.EurekaLibrarian.Entity.BookLoan;

@Repository
@Transactional
public interface BookLoanRepository extends JpaRepository<BookLoan, Integer>{
	
	Page<BookLoan> findAll(Pageable pageRequest);
	
	@Query("SELECT b FROM tbl_book_loans b  WHERE b.bookLoanId.book.bookId=?1 and b.bookLoanId.libraryBranch.libraryBranchId=?2 and b.bookLoanId.borrower.cardNo=?3")
	BookLoan getByBookIdAndBranchIdAndCardNo(int bookId, int branchId, int cardNo);

}

