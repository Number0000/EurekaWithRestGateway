package com.SmoothStack.EurekaClient.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.EurekaClient.Entity.Author;
import com.SmoothStack.EurekaClient.Entity.Book;
import com.SmoothStack.EurekaClient.Entity.BookCopy;
import com.SmoothStack.EurekaClient.Entity.BookLoan;
import com.SmoothStack.EurekaClient.Entity.Borrower;
import com.SmoothStack.EurekaClient.Entity.LibraryBranch;
import com.SmoothStack.EurekaClient.Entity.Publisher;
import com.SmoothStack.EurekaClient.IFeign.IFeignBorrower;

@RestController
@RequestMapping("/lms/borrower")
public class BorrowerController{
	
	@Autowired
	private IFeignBorrower iFeignBorrower;
	
	//Display
	@GetMapping("/authors")
	public ResponseEntity<List<Author>> displayAuthor() {
		return iFeignBorrower.displayAuthor();
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> displayBook() {
		return iFeignBorrower.displayBook();
	}
	
	@GetMapping("/bookCopies")
	public ResponseEntity<List<BookCopy>> displayBookCopy() {
		return iFeignBorrower.displayBookCopy();
	}
	
	@GetMapping("/libraryBranches")
	public ResponseEntity<List<LibraryBranch>> displayLibraryBranch() {
		return iFeignBorrower.displayLibraryBranch();
	}
	
	@GetMapping("/publishers")
	public ResponseEntity<List<Publisher>> displayPublisher() {
		return iFeignBorrower.displayPublisher();
	}
	
	//Display by Id
	@GetMapping("/author/{id}")
	public ResponseEntity<Author> displayAuthorById(@PathVariable(value = "id") int authorId) {
		return iFeignBorrower.displayAuthorById(authorId);
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> displayBookById(@PathVariable(value = "id") int bookId) {
		return iFeignBorrower.displayBookById(bookId);
	}
	
	//QueryParam
	@GetMapping("/bookCopy")
	public ResponseEntity<BookCopy> displayBookCopyById(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId) {
		
		return iFeignBorrower.displayBookCopyById(bookId, libraryBranchId);
	}
	
	//QueryParam
	@GetMapping("/bookLoan")
	public ResponseEntity<BookLoan> displayBookLoanById(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo) {

		return iFeignBorrower.displayBookLoanById(bookId, libraryBranchId, cardNo);
	}
	
	@GetMapping("/borrower/{id}")
	public ResponseEntity<Borrower> displayBorrowerById(@PathVariable(value = "id") int cardNo) {
		return iFeignBorrower.displayBorrowerById(cardNo);
	}
	
	@GetMapping("/libraryBranch/{id}")
	public ResponseEntity<LibraryBranch> displayLibraryBranchById(@PathVariable(value = "id") int libraryBranchId) {
		return iFeignBorrower.displayLibraryBranchById(libraryBranchId);
	}
	
	@GetMapping("/publisher/{id}")
	public ResponseEntity<Publisher> displayPublisherById(@PathVariable(value = "id") int publisherId) {
		return iFeignBorrower.displayPublisherById(publisherId);
	}
	
	//--------------------------------
	//Feature here
	//--------------------------------
	//Query Param for limit scope
	@PostMapping("/bookLoan")
	public ResponseEntity<BookLoan> addBookLoan(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo) {
		return iFeignBorrower.addBookLoan(bookId, libraryBranchId, cardNo);
	}
	
	//Query Param for limit scope
	@PutMapping("/returnBookLoan")
	public ResponseEntity<BookLoan> returnBookLoan(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo){
		return iFeignBorrower.returnBookLoan(bookId, libraryBranchId, cardNo);
	}
	
}

