package com.SmoothStack.EurekaLibrarian.Entity;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;

@Embeddable
public class BookCopyId implements Serializable{
	
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
	
	public BookCopyId() {}
	
	//over here this set the bookCopy pk
	public BookCopyId(Book book, LibraryBranch libraryBranch) {
		this.book = book;
		this.libraryBranch = libraryBranch;
	}
	
	public Book getBook() {
		return book;
	}

	public LibraryBranch getLibraryBranch() {
		return libraryBranch;
	}
	
}

