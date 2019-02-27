package com.SmoothStack.EurekaClient.Entity;

import java.time.LocalDate;

public class BookLoan{

	private BookLoanId bookLoanId;
	
	private LocalDate dateOut;
	
	private LocalDate dueDate;
	
	private Boolean returned;
	
	private int extended;
	
	public BookLoan() {}
	
	public BookLoan(Book book, LibraryBranch libraryBranch, Borrower borrower) {
		this.bookLoanId = new BookLoanId(book, libraryBranch, borrower);
	}
	
	public Book getBook() {
		return bookLoanId.getBook();
	}
	
	public LibraryBranch getLibraryBranch() {
		return bookLoanId.getLibraryBranch();
	}
	
	public Borrower getBorrower() {
		return bookLoanId.getBorrower();
	}
	
	//---
	public LocalDate getDateOut() {
		return dateOut;
	}

	public void setDateOut(LocalDate dateOut) {
		this.dateOut = dateOut;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	//custom
	public void setDateOutToDueDate(LocalDate dueDate) {
		this.dateOut = dueDate;
	}
	
	public void setDueDateExtend7Day(LocalDate dueDate) {
		LocalDate weekLater = dueDate.plusDays(7);
		this.dueDate = weekLater;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public int getExtended() {
		return extended;
	}

	public void setExtended(int extended) {
		this.extended = extended;
	}
	
	public void setExtended1Time(int extended) {
		this.extended = extended++;
	}
	
}

