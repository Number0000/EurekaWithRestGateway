package com.SmoothStack.EurekaLibrarian.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.EurekaLibrarian.Entity.Book;
import com.SmoothStack.EurekaLibrarian.Entity.BookLoan;
import com.SmoothStack.EurekaLibrarian.Entity.Borrower;
import com.SmoothStack.EurekaLibrarian.Entity.LibraryBranch;
import com.SmoothStack.EurekaLibrarian.Exception.ResourceNotFoundException;
import com.SmoothStack.EurekaLibrarian.Repository.BookLoanRepository;
import com.SmoothStack.EurekaLibrarian.Repository.BookRepository;
import com.SmoothStack.EurekaLibrarian.Repository.BorrowerRepository;
import com.SmoothStack.EurekaLibrarian.Repository.LibraryBranchRepository;

@RestController
public class BookLoanController {
	
	@Autowired
	private BookLoanRepository bookLoanRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LibraryBranchRepository libraryBranchRepository;
	
	@Autowired
	private BorrowerRepository borrowerRepository;
	
	//Get All BookLoan
	public List<BookLoan> getAllBookLoan(){
		return bookLoanRepository.findAll();
	}
	
	//Create a new bookLoan
	public BookLoan createBookLoan(BookLoan bookLoan) {
		return bookLoanRepository.save(bookLoan);
		
	}
	
	//Get a Single BookLoan
	public BookLoan getBookLoanById(int bookId, int libraryBranchId, int cardNo) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		Borrower borrower = borrowerRepository.findById(cardNo)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", cardNo));
		
		BookLoan bookLoan = bookLoanRepository.getByBookIdAndBranchIdAndCardNo(bookId, libraryBranchId, cardNo);
		if(bookLoan.equals(null))
			throw new ResourceNotFoundException("BookLoan", "id", "bookId" + bookId + "libraryBranchId" + libraryBranchId + "cardNo" + cardNo);
		return bookLoan;
	}
	
	//Get a Single BookLoan by line within BookLoans
	public List<BookLoan> getBookLoanByLine(int line) {
		Page<BookLoan> bookLoanPage = bookLoanRepository.findAll(PageRequest.of(line-1, 1));
		List<BookLoan> bookLoan = bookLoanPage.getContent();
		return bookLoan;
	}
	
	//Update a BookLoan
	public BookLoan updateBookLoan(int bookId, int libraryBranchId, int cardNo, BookLoan bookLoanDetails) {
		
		BookLoanController bookLoanController = new BookLoanController();
		
		BookLoan bookLoan = bookLoanController.getBookLoanById(bookId, libraryBranchId, cardNo);
		bookLoan.setDateOut(bookLoanDetails.getDateOut());
		bookLoan.setDueDate(bookLoanDetails.getDueDate());
		bookLoan.setReturned(bookLoanDetails.isReturned());
		bookLoan.setExtended(bookLoanDetails.getExtended());
		
		BookLoan updatedBookLoan = bookLoanRepository.save(bookLoan);
		return updatedBookLoan;
	}
	
	//Delete a BookLoan
	public void deleteBookLoan(int bookLoanId){
		BookLoan bookLoan = bookLoanRepository.findById(bookLoanId)
				.orElseThrow(()-> new ResourceNotFoundException("BookLoan", "id", bookLoanId));
		bookLoanRepository.delete(bookLoan);
	}
}

