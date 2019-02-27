package com.SmoothStack.EurekaClient.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.SmoothStack.EurekaClient.Entity.Author;
import com.SmoothStack.EurekaClient.Entity.Book;
import com.SmoothStack.EurekaClient.Entity.BookCopy;
import com.SmoothStack.EurekaClient.Entity.BookLoan;
import com.SmoothStack.EurekaClient.Entity.Borrower;
import com.SmoothStack.EurekaClient.Entity.LibraryBranch;
import com.SmoothStack.EurekaClient.Entity.Publisher;
import com.SmoothStack.EurekaClient.IFeign.IFeignAdmin;

@RestController
@RequestMapping("/lms/admin")
public class AdminController{
	
	@Autowired
	private IFeignAdmin iFeignAdmin;
	
	//Display
	@GetMapping("/authors")
	public ResponseEntity<List<Author>> displayAuthor(){
		return iFeignAdmin.displayAuthor();
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> displayBook() {
		return iFeignAdmin.displayBook();
	}
	
	@GetMapping("/bookCopies")
	public ResponseEntity<List<BookCopy>> displayBookCopy() {
		return iFeignAdmin.displayBookCopy();
	}
	
	@GetMapping("/bookLoans")
	public ResponseEntity<List<BookLoan>> displayBookLoan() {
		return iFeignAdmin.displayBookLoan();
	}
	
	@GetMapping("/borrowers")
	public ResponseEntity<List<Borrower>> displayBorrower() {
		return iFeignAdmin.displayBorrower();
	}
	
	@GetMapping("/libraryBranches")
	public ResponseEntity<List<LibraryBranch>> displayLibraryBranch() {
		return iFeignAdmin.displayLibraryBranch();
	}
	
	@GetMapping("/publishers")
	public ResponseEntity<List<Publisher>> displayPublisher() {
		return iFeignAdmin.displayPublisher();
	}
	
	//--------------------------------------
	//Display by Id
	@GetMapping("/author/{id}")
	public ResponseEntity<Author> displayAuthorById(@PathVariable(value = "id") int authorId) {
		System.out.println("----------------" + authorId);
		return iFeignAdmin.displayAuthorById(authorId);
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> displayBookById(@PathVariable(value = "id") int bookId) {
		return iFeignAdmin.displayBookById(bookId);
	}
	
	//QueryParam
	@GetMapping("/bookCopy")
	public ResponseEntity<BookCopy> displayBookCopyById(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId) {

		return iFeignAdmin.displayBookCopyById(bookId, libraryBranchId);
	}
	
	//QueryParam
	@GetMapping("/bookLoan")
	public ResponseEntity<BookLoan> displayBookLoanById(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo) {
		
		return iFeignAdmin.displayBookLoanById(bookId, libraryBranchId, cardNo);
	}
	
	@GetMapping("/borrower/{id}")
	public ResponseEntity<Borrower> displayBorrowerById(@PathVariable(value = "id") int cardNo) {
		return iFeignAdmin.displayBorrowerById(cardNo);
	}
	
	@GetMapping("/libraryBranch/{id}")
	public ResponseEntity<LibraryBranch> displayLibraryBranchById(@PathVariable(value = "id") int libraryBranchId) {
		return iFeignAdmin.displayLibraryBranchById(libraryBranchId);
	}
	
	@GetMapping("/publisher/{id}")
	public ResponseEntity<Publisher> displayPublisherById(@PathVariable(value = "id") int publisherId) {
		return iFeignAdmin.displayPublisherById(publisherId);
	}
	
	//-----------------------------
	//Add
	@PostMapping("/author")
	public ResponseEntity<Author> addAuthor(@RequestBody Author authorDetails) {
		return iFeignAdmin.addAuthor(authorDetails);
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> addBook(@RequestBody Book bookDetails) {
		return iFeignAdmin.addBook(bookDetails);
	}
	
	@PostMapping("/borrower")
	public ResponseEntity<Borrower> addBorrower(@RequestBody Borrower borrowerDetails) {
		return iFeignAdmin.addBorrower(borrowerDetails);
	}
	
	@PostMapping("/libraryBranch")
	public ResponseEntity<LibraryBranch> addLibraryBranch(@RequestBody LibraryBranch libraryBranchDetails) {
		return iFeignAdmin.addLibraryBranch(libraryBranchDetails);
	}
	
	@PostMapping("/publisher")
	public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisherDetails) {
		return iFeignAdmin.addPublisher(publisherDetails);
	}
	
	//---------------------------------
	//Update
	@PutMapping("/author/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable(value = "id") int authorId,
			@RequestBody Author authorDetails){
		return iFeignAdmin.updateAuthor(authorDetails, authorId);
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "id") int bookId,
			@RequestBody Book bookDetails){
		return iFeignAdmin.updateBook(bookDetails, bookId);
	}
	
	@PutMapping("/borrower/{id}")
	public ResponseEntity<Borrower> updateBorrower(@PathVariable(value = "id") int cardNo,
			@RequestBody Borrower borrowerDetails){
		return iFeignAdmin.updateBorrower(borrowerDetails, cardNo);
	}
	
	@PutMapping("/publisher/{id}")
	public ResponseEntity<Publisher> updatePublisher(@PathVariable(value = "id") int publisherId,
			@RequestBody Publisher publisherDetails){
		return iFeignAdmin.updatePublisher(publisherDetails, publisherId);
	}
	
	//----------------------------
	//Delete
	@DeleteMapping("/author/{id}")
	public ResponseEntity<Author> deleteAuthor(@PathVariable(value = "id") int authorId){
		return iFeignAdmin.deleteAuthor(authorId);
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable(value = "id") int bookId){
		return iFeignAdmin.deleteBook(bookId);
	}
	
	@DeleteMapping("/borrower/{id}")
	public ResponseEntity<Borrower> deleteBorrower(@PathVariable(value = "id") int cardNo){
		return iFeignAdmin.deleteBorrower(cardNo);
	}
	
	@DeleteMapping("/publisher/{id}")
	public ResponseEntity<Publisher> deletePublisher(@PathVariable(value = "id") int publisherId){		
		return iFeignAdmin.deletePublisher(publisherId);
	}
	
	//Override
	//Query param for limit scope
	@PutMapping("/extendBookLoan")
	public ResponseEntity<BookLoan> extendBookLoan(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo){
		return iFeignAdmin.extendBookLoan(bookId, libraryBranchId, cardNo);
	}
	
}

