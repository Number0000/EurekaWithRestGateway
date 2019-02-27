package com.SmoothStack.EurekaBorrower.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity(name = "tbl_book_copies")
@Table(name = "tbl_book_copies")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
	allowGetters = true)
public class BookCopy implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BookCopyId bookCopyId;
		
	@PositiveOrZero
	@Column(name = "noOfCopies", nullable = false)
	private int noOfCopies;
	
//  Not setter in this case, let the constructor give you the scope
	public BookCopy() {}
		
	public BookCopy(Book book, LibraryBranch libraryBranch) {
		this.bookCopyId = new BookCopyId(book, libraryBranch);
	}
	
	public Book getBook() {
		return bookCopyId.getBook();
	}
	
	public LibraryBranch getLibraryBranch() {
		return bookCopyId.getLibraryBranch();
	}
	
	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	
	public void decrementNumberOfCopies() {
		if(noOfCopies > 0) {
			noOfCopies--;
		}
	}
	
	public void incrementNumberOfCopies() {
		noOfCopies++;
	}

	@Override
	public String toString() {
		return "BookCopy [book=" + bookCopyId.getBook() + ", libraryBranch=" + bookCopyId.getLibraryBranch() + ", noOfCopies=" + noOfCopies + "]";
	}
	
	
}

