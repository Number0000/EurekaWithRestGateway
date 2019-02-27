package com.SmoothStack.EurekaLibrarian.Entity;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;

@Embeddable
public class BookLoanId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonDeserialize(as = Book.class)
	@ManyToOne
	@JoinColumn(name = "bookId", referencedColumnName = "bookId", insertable = true, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Book book;
	
	@JsonDeserialize(as = LibraryBranch.class)
	@ManyToOne
	@JoinColumn(name = "branchId", referencedColumnName = "branchId", insertable = true, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private LibraryBranch libraryBranch;
	
	@JsonDeserialize(as = Borrower.class)
	@ManyToOne
	@JoinColumn(name = "cardNo", referencedColumnName = "cardNo", insertable = true, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Borrower borrower;
	
	public BookLoanId() {}
	
	//over here this set the bookLoan pk
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

