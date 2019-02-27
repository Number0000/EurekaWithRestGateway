package com.SmoothStack.EurekaBorrower.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.EurekaBorrower.Exception.ResourceNotFoundException;
import com.SmoothStack.EurekaBorrower.Controller.AuthorController;
import com.SmoothStack.EurekaBorrower.Controller.BookCopyController;
import com.SmoothStack.EurekaBorrower.Controller.BookLoanController;
import com.SmoothStack.EurekaBorrower.Controller.BookController;
import com.SmoothStack.EurekaBorrower.Controller.BorrowerController;
import com.SmoothStack.EurekaBorrower.Controller.LibraryBranchController;
import com.SmoothStack.EurekaBorrower.Controller.PublisherController;
import com.SmoothStack.EurekaBorrower.Entity.Author;
import com.SmoothStack.EurekaBorrower.Entity.Book;
import com.SmoothStack.EurekaBorrower.Entity.BookCopy;
import com.SmoothStack.EurekaBorrower.Entity.BookLoan;
import com.SmoothStack.EurekaBorrower.Entity.Borrower;
import com.SmoothStack.EurekaBorrower.Entity.LibraryBranch;
import com.SmoothStack.EurekaBorrower.Entity.Publisher;

@RestController
@RequestMapping("/lms/borrower")
public class BorrowerService {
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
	
	@GetMapping("/borrower/{id}")
	public ResponseEntity<Borrower> displayBorrowerById(@PathVariable(value = "id") int cardNo) {
		Borrower borrower = borrowerController.getBorrowerById(cardNo);
		return new ResponseEntity<Borrower>(borrower, HttpStatus.OK);
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
	@PostMapping("/bookLoan")
	public ResponseEntity<BookLoan> addBookLoan(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo) {
		Book book = bookController.getBookById(bookId);
		LibraryBranch libraryBranch = libraryBranchController.getLibraryBranchById(libraryBranchId);
		Borrower borrower = borrowerController.getBorrowerById(cardNo);
		
		//check if library have book copies
		BookCopy bookCopy = bookCopyController.getBookCopyById(bookId, libraryBranchId);
		if (bookCopy.getNoOfCopies() > 0) {
			BookLoan bookloan = new BookLoan(book, libraryBranch, borrower);
			bookloan.setDateOut(LocalDate.now());
			bookloan.setDueDateExtend7Day(LocalDate.now());
			bookloan.setReturned(false);
			bookloan.setExtended(0);
			bookLoanController.createBookLoan(bookloan);
			
			bookCopy.decrementNumberOfCopies();
			bookCopyController.updateBookCopy(bookId, libraryBranchId, bookCopy);
			return new ResponseEntity<BookLoan>(bookloan, HttpStatus.CREATED);
		} else 
			return new ResponseEntity<BookLoan>(HttpStatus.CONFLICT);
	}
	
	//Query Param for limit scope
	@PutMapping("/returnBookLoan")
	public ResponseEntity<BookLoan> returnBookLoan(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo){
		
		BookLoan bookloan = bookLoanController.getBookLoanById(bookId, libraryBranchId, cardNo);
		if(bookloan.isReturned() == false) {
			bookloan.setReturned(true);
			bookLoanController.updateBookLoan(bookId, libraryBranchId, cardNo, bookloan);
			
			BookCopy bookCopy = bookCopyController.getBookCopyById(bookId, libraryBranchId);
			bookCopy.incrementNumberOfCopies();
			bookCopyController.updateBookCopy(bookId, libraryBranchId, bookCopy);
			return new ResponseEntity<BookLoan>(bookloan, HttpStatus.OK);
		} else {
			return new ResponseEntity<BookLoan>(bookloan, HttpStatus.CONFLICT);
		}
	}
	
}

