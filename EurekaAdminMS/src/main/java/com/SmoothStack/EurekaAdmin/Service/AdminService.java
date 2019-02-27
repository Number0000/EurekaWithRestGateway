package com.SmoothStack.EurekaAdmin.Service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.EurekaAdmin.Entity.Author;
import com.SmoothStack.EurekaAdmin.Entity.Book;
import com.SmoothStack.EurekaAdmin.Entity.BookCopy;
import com.SmoothStack.EurekaAdmin.Entity.BookLoan;
import com.SmoothStack.EurekaAdmin.Entity.Borrower;
import com.SmoothStack.EurekaAdmin.Entity.LibraryBranch;
import com.SmoothStack.EurekaAdmin.Entity.Publisher;
import com.SmoothStack.EurekaAdmin.Controller.AuthorController;
import com.SmoothStack.EurekaAdmin.Controller.BookCopyController;
import com.SmoothStack.EurekaAdmin.Controller.BookLoanController;
import com.SmoothStack.EurekaAdmin.Controller.BookController;
import com.SmoothStack.EurekaAdmin.Controller.BorrowerController;
import com.SmoothStack.EurekaAdmin.Controller.LibraryBranchController;
import com.SmoothStack.EurekaAdmin.Controller.PublisherController;

@RestController
@RequestMapping("/lms/admin")
public class AdminService {
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
	
	@GetMapping("/borrowers")
	public ResponseEntity<List<Borrower>> displayBorrower() {
		List<Borrower> list = borrowerController.getAllBorrower();
		return new ResponseEntity<List<Borrower>>(list, HttpStatus.OK);
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
	
	//Add
	@PostMapping("/author")
	public ResponseEntity<Author> addAuthor(@Valid @RequestBody Author authorDetails) {
		authorController.createAuthor(authorDetails);
		return new ResponseEntity<Author>(authorDetails, HttpStatus.CREATED);
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book bookDetails) {
		int authorId = bookDetails.getAuthor().getAuthorId();
		Author author = authorController.getAuthorById(authorId);
		int publisherId = bookDetails.getPublisher().getPublisherId();
		Publisher publisher = publisherController.getPublisherById(publisherId);
		
		Book book = new Book();
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setTitle(bookDetails.getTitle());
		bookController.createBook(book);
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}
	
	@PostMapping("/borrower")
	public ResponseEntity<Borrower> addBorrower(@Valid @RequestBody Borrower borrowerDetails) {
		borrowerController.createBorrower(borrowerDetails);
		return new ResponseEntity<Borrower>(borrowerDetails, HttpStatus.CREATED);
	}
	
	@PostMapping("/libraryBranch")
	public ResponseEntity<LibraryBranch> addLibraryBranch(@Valid @RequestBody LibraryBranch libraryBranchDetails) {
		libraryBranchController.createLibraryBranch(libraryBranchDetails);
		return new ResponseEntity<LibraryBranch>(libraryBranchDetails, HttpStatus.CREATED);
	}
	
	@PostMapping("/publisher")
	public ResponseEntity<Publisher> addPublisher(@Valid @RequestBody Publisher publisherDetails) {
		publisherController.createPublisher(publisherDetails);
		return new ResponseEntity<Publisher>(publisherDetails, HttpStatus.CREATED);
	}
	
	//Update
	@PutMapping("/author/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable(value = "id") int authorId,
			@Valid @RequestBody Author authorDetails){
		Author author = authorController.getAuthorById(authorId);
		author.setAuthorName(authorDetails.getAuthorName());
		
		authorController.updateAuthor(authorId, author);
		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "id") int bookId,
			@Valid @RequestBody Book bookDetails){
		Book book = bookController.getBookById(bookId);
		book.setTitle(bookDetails.getTitle());
		
		bookController.updateBook(bookId, book);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@PutMapping("/borrower/{id}")
	public ResponseEntity<Borrower> updateBorrower(@PathVariable(value = "id") int cardNo,
			@Valid @RequestBody Borrower borrowerDetails){
		Borrower borrower = borrowerController.getBorrowerById(cardNo);
		borrower.setBorrowerName(borrowerDetails.getBorrowerName());
		borrower.setBorrowerAddress(borrowerDetails.getBorrowerAddress());
		borrower.setBorrowerPhone(borrowerDetails.getBorrowerPhone());
		borrower.setBorrowerUserName(borrowerDetails.getBorrowerUserName());
		borrower.setBorrowerPassword(borrowerDetails.getBorrowerPassword());
		
		borrowerController.updateBorrower(cardNo, borrower);
		return new ResponseEntity<Borrower>(borrower, HttpStatus.OK);
	}
	
	@PutMapping("/publisher/{id}")
	public ResponseEntity<Publisher> updatePublisher(@PathVariable(value = "id") int publisherId,
			@Valid @RequestBody Publisher publisherDetails){
		Publisher publisher = publisherController.getPublisherById(publisherId);
		publisher.setPublisherName(publisherDetails.getPublisherName());
		publisher.setPublisherAddress(publisherDetails.getPublisherAddress());
		publisher.setPublisherPhone(publisherDetails.getPublisherPhone());
		
		publisherController.updatePublisher(publisherId, publisher);
		return new ResponseEntity<Publisher>(publisher, HttpStatus.OK);
	}
	
	//Delete
	@DeleteMapping("/author/{id}")
	public ResponseEntity<Author> deleteAuthor(@PathVariable(value = "id") int authorId){
		authorController.deleteAuthor(authorId);
		
		return new ResponseEntity<Author>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable(value = "id") int bookId){
		bookController.deleteBook(bookId);
		
		return new ResponseEntity<Book>(HttpStatus.ACCEPTED);
	}
	
	//Query Param
//	@DeleteMapping("/bookCopy")
//	public ResponseEntity<BookCopy> deleteBookCopy(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
//			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId){
//
//		bookCopyController.deleteBookCopy(bookId, libraryBranchId);
//		
//		return new ResponseEntity<BookCopy>(HttpStatus.ACCEPTED);
//	}
	
//	//QueryParam
//	@DeleteMapping("/bookLoan")
//	public ResponseEntity<BookLoan> deleteBookLoan(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
//			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
//			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo){
//
//		bookLoanController.deleteBookLoan(bookId, libraryBranchId, cardNo);
//		
//		return new ResponseEntity<BookLoan>(HttpStatus.ACCEPTED);
//	}
	
	@DeleteMapping("/borrower/{id}")
	public ResponseEntity<Borrower> deleteBorrower(@PathVariable(value = "id") int cardNo){
		borrowerController.deleteBorrower(cardNo);
		
		return new ResponseEntity<Borrower>(HttpStatus.ACCEPTED);
	}
	
//	@DeleteMapping("/libraryBranch/{id}")
//	public ResponseEntity<LibraryBranch> deleteLibraryBranch(@PathVariable(value = "id") int libraryBranchId){
//		libraryBranchController.deleteLibraryBranch(libraryBranchId);
//		
//		return new ResponseEntity<LibraryBranch>(HttpStatus.ACCEPTED);
//	}
	
	@DeleteMapping("/publisher/{id}")
	public ResponseEntity<Publisher> deletePublisher(@PathVariable(value = "id") int publisherId){
		publisherController.deletePublisher(publisherId);
		
		return new ResponseEntity<Publisher>(HttpStatus.ACCEPTED);
	}
	
	//Override
	//Query param for limit scope
	@PutMapping("/extendBookLoan")
	public ResponseEntity<BookLoan> extendBookLoan(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo){
		BookLoan bookloan = bookLoanController.getBookLoanById(bookId, libraryBranchId, cardNo);
		if(bookloan.getExtended() <= 3 && bookloan.getExtended() > 0) {
			bookloan.setDateOutToDueDate(bookloan.getDueDate());
			bookloan.setDueDateExtend7Day(bookloan.getDueDate());
			bookloan.setExtended1Time(bookloan.getExtended());
		
			bookLoanController.updateBookLoan(bookId, libraryBranchId, cardNo, bookloan);
			return new ResponseEntity<BookLoan>(bookloan, HttpStatus.OK);
		} else {
			return new ResponseEntity<BookLoan>(bookloan, HttpStatus.CONFLICT);
		}
	}
	
}

