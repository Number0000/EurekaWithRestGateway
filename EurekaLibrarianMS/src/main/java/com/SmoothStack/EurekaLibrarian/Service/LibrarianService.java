package com.SmoothStack.EurekaLibrarian.Service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SmoothStack.EurekaLibrarian.Controller.AuthorController;
import com.SmoothStack.EurekaLibrarian.Controller.BookController;
import com.SmoothStack.EurekaLibrarian.Controller.BookCopyController;
import com.SmoothStack.EurekaLibrarian.Controller.BookLoanController;
import com.SmoothStack.EurekaLibrarian.Controller.BorrowerController;
import com.SmoothStack.EurekaLibrarian.Controller.LibraryBranchController;
import com.SmoothStack.EurekaLibrarian.Controller.PublisherController;
import com.SmoothStack.EurekaLibrarian.Entity.Author;
import com.SmoothStack.EurekaLibrarian.Entity.Book;
import com.SmoothStack.EurekaLibrarian.Entity.BookCopy;
import com.SmoothStack.EurekaLibrarian.Entity.BookLoan;
import com.SmoothStack.EurekaLibrarian.Entity.LibraryBranch;
import com.SmoothStack.EurekaLibrarian.Entity.Publisher;
import com.SmoothStack.EurekaLibrarian.Exception.ResourceNotFoundException;

@Service
@RequestMapping("/lms/librarian")
public class LibrarianService {
	@Autowired
	AuthorController authorController;
	
	@Autowired
	BookController bookController;
	
	@Autowired
	BookCopyController bookCopyController;
	
	@Autowired
	BookLoanController bookLoanController;
	
	@Autowired
	BorrowerController borrowerController;
	
	@Autowired
	LibraryBranchController libraryBranchController;
	
	@Autowired
	PublisherController publisherController;
	
	//Display
	@GetMapping("/authors")
	public ResponseEntity<List<Author>> displayAuthor() {
		List<Author> list = authorController.getAllAuthor();
		return new ResponseEntity<List<Author>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> displayBook() {
		List<Book> list = bookController.getAllBook();
		return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/bookCopies")
	public ResponseEntity<List<BookCopy>> displayBookCopy() {
		List<BookCopy> list = bookCopyController.getAllBookCopy();
		return new ResponseEntity<List<BookCopy>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/bookLoans")
	public ResponseEntity<List<BookLoan>> displayBookLoan() {
		List<BookLoan> list = bookLoanController.getAllBookLoan();
		return new ResponseEntity<List<BookLoan>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/libraryBranches")
	public ResponseEntity<List<LibraryBranch>> displayLibraryBranch() {
		List<LibraryBranch> list = libraryBranchController.getAllLibraryBranch();
		return new ResponseEntity<List<LibraryBranch>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/publishers")
	public ResponseEntity<List<Publisher>> displayPublisher() {
		List<Publisher> list = publisherController.getAllPublisher();
		return new ResponseEntity<List<Publisher>>(list, HttpStatus.OK);
	}
	
	//Display by Id
	@GetMapping("/author/{id}")
	public ResponseEntity<Author> displayAuthorById(@PathVariable(value = "id") int authorId) {
		Author author = authorController.getAuthorById(authorId);
		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> displayBookById(@PathVariable(value = "id") int bookId) {
		Book book = bookController.getBookById(bookId);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	//QueryParam
	@GetMapping("/bookCopy")
	public ResponseEntity<BookCopy> displayBookCopyById(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId) {
		
		BookCopy bookCopy = bookCopyController.getBookCopyById(bookId, libraryBranchId);
		return new ResponseEntity<BookCopy>(bookCopy, HttpStatus.OK);
	}
	
	//QueryParam
	@GetMapping("/bookLoan")
	public ResponseEntity<BookLoan> displayBookLoanById(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo) {

		BookLoan bookloan = bookLoanController.getBookLoanById(bookId, libraryBranchId, cardNo);
		return new ResponseEntity<BookLoan>(bookloan, HttpStatus.OK);
	}
	
	@GetMapping("/libraryBranch/{id}")
	public ResponseEntity<LibraryBranch> displayLibraryBranchById(@PathVariable(value = "id") int libraryBranchId) {
		LibraryBranch librarybranch = libraryBranchController.getLibraryBranchById(libraryBranchId);
		return new ResponseEntity<LibraryBranch>(librarybranch, HttpStatus.OK);
	}
	
	@GetMapping("/publisher/{id}")
	public ResponseEntity<Publisher> displayPublisherById(@PathVariable(value = "id") int publisherId) {
		Publisher publisher = publisherController.getPublisherById(publisherId);
		return new ResponseEntity<Publisher>(publisher, HttpStatus.OK);
	}
	
	
	//--------------------------------
	//Feature here
	//--------------------------------
	//Query Param for limit scope
	@PostMapping("/bookCopy")
	public ResponseEntity<BookCopy> addBookCopy(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@Valid @RequestBody BookCopy bookCopyDetails) {
		Book book = bookController.getBookById(bookId);
		System.out.println("---------book " + book);
		LibraryBranch libraryBranch = libraryBranchController.getLibraryBranchById(libraryBranchId);
		System.out.println("---------libraryBranch " + libraryBranch);

		BookCopy bookCopy = new BookCopy(book, libraryBranch);
		bookCopy.setNoOfCopies(bookCopyDetails.getNoOfCopies());
		System.out.println("---------bookCopy " + bookCopy);
		BookCopy savedbookcopy = bookCopyController.createBookCopy(bookCopy);
		
		return new ResponseEntity<BookCopy>(savedbookcopy, HttpStatus.CREATED);

	}
	
	//Query Param for limit scope
	@PutMapping("/bookCopy")
	public ResponseEntity<BookCopy> updateBookCopy(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@Valid @RequestBody BookCopy bookCopyDetails){
		Book book = bookController.getBookById(bookId);
		LibraryBranch libraryBranch = libraryBranchController.getLibraryBranchById(libraryBranchId);
		
		BookCopy bookCopy = new BookCopy(book, libraryBranch);
		bookCopy.setNoOfCopies(bookCopyDetails.getNoOfCopies());
		BookCopy savedbookcopy = bookCopyController.updateBookCopy(bookId, libraryBranchId, bookCopy);
		
		return new ResponseEntity<BookCopy>(savedbookcopy, HttpStatus.OK);
	}
	
	@PutMapping("/libraryBranch/{id}")
	public ResponseEntity<LibraryBranch> updateLibraryBranch(@PathVariable(value = "id") int libraryBranchId,
			@Valid @RequestBody LibraryBranch libraryBranchDetails){
		
		LibraryBranch libraryBranch = libraryBranchController.updateLibraryBranch(libraryBranchId, libraryBranchDetails);
		return new ResponseEntity<LibraryBranch>(libraryBranch, HttpStatus.OK);
	}
	
}

