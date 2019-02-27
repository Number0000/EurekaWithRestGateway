package com.SmoothStack.EurekaClient.Entity;

public class BookCopy{
	
	private BookCopyId bookCopyId;

	private int noOfCopies;
	
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

}

