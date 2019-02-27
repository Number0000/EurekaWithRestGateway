package com.SmoothStack.EurekaClient.IFeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.SmoothStack.EurekaClient.Entity.Borrower;
import com.SmoothStack.EurekaClient.Entity.LibraryBranch;
import com.SmoothStack.EurekaClient.Entity.Publisher;

@FeignClient("EurekaAdminMS")
public interface IFeignAdmin{
	
	//Display
	@GetMapping("/lms/admin/authors")
	public ResponseEntity<List<Author>> displayAuthor();
	
	@GetMapping("/lms/admin/books")
	public ResponseEntity<List<Book>> displayBook();
	
	@GetMapping("/lms/admin/bookCopies")
	public ResponseEntity<List<BookCopy>> displayBookCopy();
	
	@GetMapping("/lms/admin/bookLoans")
	public ResponseEntity<List<BookLoan>> displayBookLoan();
	
	@GetMapping("/lms/admin/borrowers")
	public ResponseEntity<List<Borrower>> displayBorrower();
	
	@GetMapping("/lms/admin/libraryBranches")
	public ResponseEntity<List<LibraryBranch>> displayLibraryBranch();
	
	@GetMapping("/lms/admin/publishers")
	public ResponseEntity<List<Publisher>> displayPublisher();
	
	//----------------------------------
	//Display by Id
	@GetMapping("/lms/admin/author/{authorId}")
	public ResponseEntity<Author> displayAuthorById(@PathVariable(value = "authorId") int authorId);
	
	@GetMapping("/lms/admin/book/{bookId}")
	public ResponseEntity<Book> displayBookById(@PathVariable(value = "bookId") int bookId);
	
	//QueryParam
	@GetMapping("/lms/admin/bookCopy")
	public ResponseEntity<BookCopy> displayBookCopyById(@RequestParam(value = "bookId") int bookId,
			@RequestParam(value = "libraryBranchId") int libraryBranchId);
	
	//QueryParam
	@GetMapping("/lms/admin/bookLoan")
	public ResponseEntity<BookLoan> displayBookLoanById(@RequestParam(value = "bookId") int bookId,
			@RequestParam(value = "libraryBranchId") int libraryBranchId,
			@RequestParam(value = "cardNo") int cardNo);
	
	@GetMapping("/lms/admin/borrower/{cardNo}")
	public ResponseEntity<Borrower> displayBorrowerById(@PathVariable(value = "cardNo") int cardNo);
	
	@GetMapping("/lms/admin/libraryBranch/{libraryBranchId}")
	public ResponseEntity<LibraryBranch> displayLibraryBranchById(@PathVariable(value = "libraryBranchId") int libraryBranchId);
	
	@GetMapping("/lms/admin/publisher/{publisherId}")
	public ResponseEntity<Publisher> displayPublisherById(@PathVariable(value = "publisherId") int publisherId);
	
	//-----------------------------------------
	//Add
	@PostMapping("/lms/admin/author")
	public ResponseEntity<Author> addAuthor(@RequestBody Author authorDetails);
	
	@PostMapping("/lms/admin/book")
	public ResponseEntity<Book> addBook(@RequestBody Book bookDetails);
	
	@PostMapping("/lms/admin/borrower")
	public ResponseEntity<Borrower> addBorrower(@RequestBody Borrower borrowerDetails);
	
	@PostMapping("/lms/admin/libraryBranch")
	public ResponseEntity<LibraryBranch> addLibraryBranch(@RequestBody LibraryBranch libraryBranchDetails);
	
	@PostMapping("/lms/admin/publisher")
	public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisherDetails);
	
	//---------------------------------------------
	//Update
	@PutMapping("/lms/admin/author/{authorId}")
	public ResponseEntity<Author> updateAuthor(@RequestBody Author authorDetails,
			@PathVariable(value = "authorId") int authorId);
	
	@PutMapping("/lms/admin/book/{bookId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book bookDetails,
			@PathVariable(value = "bookId") int bookId);
	
	@PutMapping("/lms/admin/borrower/{cardNo}")
	public ResponseEntity<Borrower> updateBorrower(@RequestBody Borrower borrowerDetails,
			@PathVariable(value = "cardNo") int cardNo);
	
	@PutMapping("/lms/admin/publisher/{publisherId}")
	public ResponseEntity<Publisher> updatePublisher(@RequestBody Publisher publisherDetails,
			@PathVariable(value = "publisherId") int publisherId);
	
	//----------------------------------------
	//Delete
	//Delete
	@DeleteMapping("/lms/admin/author/{authorId}")
	public ResponseEntity<Author> deleteAuthor(@PathVariable(value = "authorId") int authorId);
	
	@DeleteMapping("/lms/admin/book/{bookId}")
	public ResponseEntity<Book> deleteBook(@PathVariable(value = "bookId") int bookId);
	
	@DeleteMapping("/lms/admin/borrower/{cardNo}")
	public ResponseEntity<Borrower> deleteBorrower(@PathVariable(value = "cardNo") int cardNo);
	
	@DeleteMapping("/lms/admin/publisher/{publisherId}")
	public ResponseEntity<Publisher> deletePublisher(@PathVariable(value = "publisherId") int publisherId);
	
	//-----------------------------
	//Override
	@PutMapping("/lms/admin/extendBookLoan")
	public ResponseEntity<BookLoan> extendBookLoan(@RequestParam(value = "bookId") int bookId,
			@RequestParam(value = "branchId") int libraryBranchId,
			@RequestParam(value = "cardNo") int cardNo);

}

