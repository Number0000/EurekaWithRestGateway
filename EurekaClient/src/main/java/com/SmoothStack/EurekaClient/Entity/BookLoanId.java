package com.SmoothStack.EurekaClient.Entity;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class BookLoanId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonDeserialize(as = Book.class)
	private Book book;
	
	@JsonDeserialize(as = LibraryBranch.class)
	private LibraryBranch libraryBranch;
	
	@JsonDeserialize(as = Borrower.class)
	private Borrower borrower;
	
	public BookLoanId() {}
	
	public BookLoanId(Book book, LibraryBranch libraryBranch, Borrower borrower) {
		this.book = book;
		this.libraryBranch = libraryBranch;
		this.borrower = borrower;
	}
	
	public Book getBook() {
		return book;
	}

	public LibraryBranch getLibraryBranch() {
		return libraryBranch;
	}

	public Borrower getBorrower() {
		return borrower;
	}
}

