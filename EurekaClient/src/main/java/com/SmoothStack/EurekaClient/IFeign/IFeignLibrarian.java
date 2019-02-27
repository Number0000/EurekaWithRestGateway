package com.SmoothStack.EurekaClient.IFeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.SmoothStack.EurekaClient.Entity.Author;
import com.SmoothStack.EurekaClient.Entity.Book;
import com.SmoothStack.EurekaClient.Entity.BookCopy;
import com.SmoothStack.EurekaClient.Entity.BookLoan;
import com.SmoothStack.EurekaClient.Entity.LibraryBranch;
import com.SmoothStack.EurekaClient.Entity.Publisher;

@FeignClient("EurekaLibrarianMS")
public interface IFeignLibrarian{
	
	//Display
	@GetMapping("/lms/librarian/authors")
	public ResponseEntity<List<Author>> displayAuthor();
	
	@GetMapping("/lms/librarian/books")
	public ResponseEntity<List<Book>> displayBook();
	
	@GetMapping("/lms/librarian/bookCopies")
	public ResponseEntity<List<BookCopy>> displayBookCopy();
	
	@GetMapping("/lms/librarian/bookLoans")
	public ResponseEntity<List<BookLoan>> displayBookLoan();
	
	@GetMapping("/lms/librarian/libraryBranches")
	public ResponseEntity<List<LibraryBranch>> displayLibraryBranch();
	
	@GetMapping("/lms/librarian/publishers")
	public ResponseEntity<List<Publisher>> displayPublisher();
	
	//Display by Id
	@GetMapping("/lms/librarian/author/{authorId}")
	public ResponseEntity<Author> displayAuthorById(@PathVariable(value = "authorId") int authorId);
	
	@GetMapping("/lms/librarian/book/{bookId}")
	public ResponseEntity<Book> displayBookById(@PathVariable(value = "bookId") int bookId);
	
	//QueryParam
	@GetMapping("/lms/librarian/bookCopy")
	public ResponseEntity<BookCopy> displayBookCopyById(@RequestParam(value = "bookId") int bookId,
			@RequestParam(value = "branchId") int libraryBranchId);
	
	//QueryParam
	@GetMapping("/lms/librarian/bookLoan")
	public ResponseEntity<BookLoan> displayBookLoanById(@RequestParam(value = "bookId") int bookId,
			@RequestParam(value = "branchId") int libraryBranchId,
			@RequestParam(value = "cardNo") int cardNo);
	
	@GetMapping("/lms/librarian/libraryBranch/{libraryBranchId}")
	public ResponseEntity<LibraryBranch> displayLibraryBranchById(@PathVariable(value = "libraryBranchId") int libraryBranchId);
	
	@GetMapping("/lms/librarian/publisher/{publisherId}")
	public ResponseEntity<Publisher> displayPublisherById(@PathVariable(value = "publisherId") int publisherId);
	
	
	//--------------------------------
	//Feature here
	//--------------------------------
	//Query Param for limit scope
	@PostMapping("/lms/librarian/bookCopy")
	public ResponseEntity<BookCopy> addBookCopy(@RequestBody BookCopy bookCopyDetails,
			@RequestParam(value = "bookId") int bookId,
			@RequestParam(value = "branchId") int libraryBranchId);
	
	//Query Param for limit scope
	@PutMapping("/lms/librarian/bookCopy")
	public ResponseEntity<BookCopy> updateBookCopy(@RequestBody BookCopy bookCopyDetails,
			@RequestParam(value = "bookId") int bookId,
			@RequestParam(value = "branchId") int libraryBranchId);
	
	@PutMapping("/lms/librarian/libraryBranch/{libraryBranchId}")
	public ResponseEntity<LibraryBranch> updateLibraryBranch(@RequestBody LibraryBranch libraryBranchDetails,
			@PathVariable(value = "libraryBranchId") int libraryBranchId);
	
}

