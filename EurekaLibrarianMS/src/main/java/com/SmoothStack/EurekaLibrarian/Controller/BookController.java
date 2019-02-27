package com.SmoothStack.EurekaLibrarian.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.EurekaLibrarian.Entity.Book;
import com.SmoothStack.EurekaLibrarian.Exception.ResourceNotFoundException;
import com.SmoothStack.EurekaLibrarian.Repository.BookRepository;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	//Get All Book
	public List<Book> getAllBook(){
		return bookRepository.findAll();
	}
	
	//Create a new book
	public Book createBook(Book book) {
		return bookRepository.save(book);
		
	}
	
	//Get a Single Book
	public Book getBookById(int bookId) {
		return bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
	}
	
	//Get a Single Author by line within authors
	public List<Book> getBookByLine(int line) {
		Page<Book> bookPage = bookRepository.findAll(PageRequest.of(line-1, 1));
		List<Book> book = bookPage.getContent();
		return book;
	}
	
	//Update a Book
	public Book updateBook(int bookId, Book bookDetails) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		book.setTitle(bookDetails.getTitle());
		
		Book updatedBook = bookRepository.save(book);
		return updatedBook;
	}
	
	//Delete a Book
	public void deleteBook(int bookId){
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		bookRepository.delete(book);
	}
}

