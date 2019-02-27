package com.SmoothStack.EurekaClient.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.EurekaClient.Entity.Author;
import com.SmoothStack.EurekaClient.Entity.Book;
import com.SmoothStack.EurekaClient.Entity.BookCopy;
import com.SmoothStack.EurekaClient.Entity.BookLoan;
import com.SmoothStack.EurekaClient.Entity.LibraryBranch;
import com.SmoothStack.EurekaClient.Entity.Publisher;
import com.SmoothStack.EurekaClient.IFeign.IFeignLibrarian;

@RestController
@RequestMapping("/lms/librarian")
public class LibrarianController{
	
	@Autowired
	private IFeignLibrarian iFeignLibrarian;
	
	//Display
	@GetMapping("/authors")
	public ResponseEntity<List<Author>> displayAuthor() {
		return iFeignLibrarian.displayAuthor();
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> displayBook() {
		return iFeignLibrarian.displayBook();
	}
	
	@GetMapping("/bookCopies")
	public ResponseEntity<List<BookCopy>> displayBookCopy() {
		return iFeignLibrarian.displayBookCopy();
	}
	
	@GetMapping("/bookLoans")
	public ResponseEntity<List<BookLoan>> displayBookLoan() {
		return iFeignLibrarian.displayBookLoan();
	}
	
	@GetMapping("/libraryBranches")
	public ResponseEntity<List<LibraryBranch>> displayLibraryBranch() {
		return iFeignLibrarian.displayLibraryBranch();
	}
	
	@GetMapping("/publishers")
	public ResponseEntity<List<Publisher>> displayPublisher() {
		return iFeignLibrarian.displayPublisher();
	}
	
	//Display by Id
	@GetMapping("/author/{id}")
	public ResponseEntity<Author> displayAuthorById(@PathVariable(value = "id") int authorId) {
		return iFeignLibrarian.displayAuthorById(authorId);
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> displayBookById(@PathVariable(value = "id") int bookId) {
		return iFeignLibrarian.displayBookById(bookId);
	}
	
	//QueryParam
	@GetMapping("/bookCopy")
	public ResponseEntity<BookCopy> displayBookCopyById(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId) {
		
		return iFeignLibrarian.displayBookCopyById(bookId, libraryBranchId);
	}
	
	//QueryParam
	@GetMapping("/bookLoan")
	public ResponseEntity<BookLoan> displayBookLoanById(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo) {

		return iFeignLibrarian.displayBookLoanById(bookId, libraryBranchId, cardNo);
	}
	
	@GetMapping("/libraryBranch/{id}")
	public ResponseEntity<LibraryBranch> displayLibraryBranchById(@PathVariable(value = "id") int libraryBranchId) {
		return iFeignLibrarian.displayLibraryBranchById(libraryBranchId);
	}
	
	@GetMapping("/publisher/{id}")
	public ResponseEntity<Publisher> displayPublisherById(@PathVariable(value = "id") int publisherId) {
		return iFeignLibrarian.displayPublisherById(publisherId);
	}
	
	
	//--------------------------------
	//Feature here
	//--------------------------------
	//Query Param for limit scope
	@PostMapping("/bookCopy")
	public ResponseEntity<BookCopy> addBookCopy(@RequestBody BookCopy bookCopyDetails,
			@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId) {
		return iFeignLibrarian.addBookCopy(bookCopyDetails, bookId, libraryBranchId);
	}
	
	//Query Param for limit scope
	@PutMapping("/bookCopy")
	public ResponseEntity<BookCopy> updateBookCopy(@RequestBody BookCopy bookCopyDetails,
			@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId){
		return iFeignLibrarian.updateBookCopy(bookCopyDetails, bookId, libraryBranchId);
	}
	
	@PutMapping("/libraryBranch/{id}")
	public ResponseEntity<LibraryBranch> updateLibraryBranch(@RequestBody LibraryBranch libraryBranchDetails, 
			@PathVariable(value = "id") int libraryBranchId){
		
		return iFeignLibrarian.updateLibraryBranch(libraryBranchDetails, libraryBranchId);
	}
	
}

