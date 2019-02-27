package com.SmoothStack.EurekaClient.Entity;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class BookCopyId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonDeserialize(as = Book.class)
	private Book book;
	
	@JsonDeserialize(as = LibraryBranch.class)
	private LibraryBranch libraryBranch;
	
	public BookCopyId() {}
	
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

