package com.SmoothStack.EurekaClient.IFeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SmoothStack.EurekaClient.Entity.Author;
import com.SmoothStack.EurekaClient.Entity.Book;
import com.SmoothStack.EurekaClient.Entity.BookCopy;
import com.SmoothStack.EurekaClient.Entity.BookLoan;
import com.SmoothStack.EurekaClient.Entity.Borrower;
import com.SmoothStack.EurekaClient.Entity.LibraryBranch;
import com.SmoothStack.EurekaClient.Entity.Publisher;

@FeignClient("EurekaBorrowerMS")
public interface IFeignBorrower{
	
	//Display
	@GetMapping("/lms/borrower/authors")
	public ResponseEntity<List<Author>> displayAuthor();
	
	@GetMapping("/lms/borrower/books")
	public ResponseEntity<List<Book>> displayBook();
	
	@GetMapping("/lms/borrower/bookCopies")
	public ResponseEntity<List<BookCopy>> displayBookCopy();
	
	@GetMapping("/lms/borrower/libraryBranches")
	public ResponseEntity<List<LibraryBranch>> displayLibraryBranch();
	
	@GetMapping("/lms/borrower/publishers")
	public ResponseEntity<List<Publisher>> displayPublisher();
	
	//-------------------------
	//Display by Id
	@GetMapping("/lms/borrower/author/{authorId}")
	public ResponseEntity<Author> displayAuthorById(@PathVariable(value = "authorId") int authorId);
	
	@GetMapping("/lms/borrower/book/{bookId}")
	public ResponseEntity<Book> displayBookById(@PathVariable(value = "bookId") int bookId);
	
	//QueryParam
	@GetMapping("/lms/borrower/bookCopy")
	public ResponseEntity<BookCopy> displayBookCopyById(@RequestParam(value = "bookId") int bookId,
			@RequestParam(value = "branchId") int libraryBranchId);
	
	//QueryParam
	@GetMapping("/lms/borrower/bookLoan")
	public ResponseEntity<BookLoan> displayBookLoanById(@RequestParam(value = "bookId") int bookId,
			@RequestParam(value = "branchId") int libraryBranchId,
			@RequestParam(value = "cardNo") int cardNo);
	
	@GetMapping("/lms/borrower/borrower/{cardNo}")
	public ResponseEntity<Borrower> displayBorrowerById(@PathVariable(value = "cardNo") int cardNo);
	
	@GetMapping("/lms/borrower/libraryBranch/{libraryBranchId}")
	public ResponseEntity<LibraryBranch> displayLibraryBranchById(@PathVariable(value = "libraryBranchId") int libraryBranchId);
	
	@GetMapping("/lms/borrower/publisher/{publisherId}")
	public ResponseEntity<Publisher> displayPublisherById(@PathVariable(value = "publisherId") int publisherId);
	
	//--------------------------------
	//Feature here
	//--------------------------------
	//Query Param for limit scope
	@PostMapping("/lms/borrower/bookLoan")
	public ResponseEntity<BookLoan> addBookLoan(@RequestParam(value = "bookId") int bookId,
			@RequestParam(value = "branchId") int libraryBranchId,
			@RequestParam(value = "cardNo") int cardNo);
	
	//Query Param for limit scope
	@PutMapping("/lms/borrower/returnBookLoan")
	public ResponseEntity<BookLoan> returnBookLoan(@RequestParam(value = "bookId") int bookId,
			@RequestParam(value = "branchId") int libraryBranchId,
			@RequestParam(value = "cardNo") int cardNo);
	
}

