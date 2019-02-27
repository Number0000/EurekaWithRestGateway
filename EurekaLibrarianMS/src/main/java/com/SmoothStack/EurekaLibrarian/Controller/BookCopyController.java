package com.SmoothStack.EurekaLibrarian.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.EurekaLibrarian.Entity.Book;
import com.SmoothStack.EurekaLibrarian.Entity.BookCopy;
import com.SmoothStack.EurekaLibrarian.Entity.LibraryBranch;
import com.SmoothStack.EurekaLibrarian.Exception.ResourceNotFoundException;
import com.SmoothStack.EurekaLibrarian.Repository.BookCopyRepository;
import com.SmoothStack.EurekaLibrarian.Repository.BookRepository;
import com.SmoothStack.EurekaLibrarian.Repository.LibraryBranchRepository;

@RestController
public class BookCopyController {
	
	@Autowired
	private	BookCopyRepository bookCopyRepository;
	
	@Autowired
	private	BookRepository bookRepository;
	
	@Autowired
	private	LibraryBranchRepository libraryBranchRepository;
	
	//Get All BookCopy
	public List<BookCopy> getAllBookCopy(){
		return bookCopyRepository.findAll();
	}
	
	//Create a new bookCopy
	public BookCopy createBookCopy(BookCopy bookCopy) {
		return bookCopyRepository.save(bookCopy);
	}
	
	//Get a Single BookCopy
	public BookCopy getBookCopyById(int bookId, int libraryBranchId) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		BookCopy bookCopy = bookCopyRepository.getByBookIdAndBranchId(bookId, libraryBranchId);
		if(bookCopy.equals(null))
			throw new ResourceNotFoundException("BookCopy", "id", "bookId" + bookId + "libraryBranchId" + libraryBranchId);
		return bookCopy;
	}
	
	//Get a Single Author by line within authors
	public List<BookCopy> getBookCopyByLine(int line) {
		Page<BookCopy> bookCopyPage = bookCopyRepository.findAll(PageRequest.of(line-1, 1));
		List<BookCopy> bookCopy = bookCopyPage.getContent();
		return bookCopy;
	}
	
	//Update a BookCopy
	public BookCopy updateBookCopy(int bookId, int libraryBranchId, BookCopy bookCopyDetails) {
		BookCopy updatedBookCopy = bookCopyRepository.save(bookCopyDetails);
		return updatedBookCopy;
	}
	
	//Delete a BookCopy
	public void deleteBookCopy(int bookCopyId){
		BookCopy bookCopy = bookCopyRepository.findById(bookCopyId)
				.orElseThrow(()-> new ResourceNotFoundException("BookCopy", "id", bookCopyId));
		bookCopyRepository.delete(bookCopy);
	}
}

