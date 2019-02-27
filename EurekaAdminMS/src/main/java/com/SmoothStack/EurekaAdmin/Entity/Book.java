package com.SmoothStack.EurekaAdmin.Entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "tbl_book")
@Table(name = "tbl_book")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
	allowGetters = true)
public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bookId", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	
	@NotBlank
	@Column(name = "title", nullable = false)
	private String title;
	
	@JsonDeserialize(as = Author.class)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "authId",  referencedColumnName = "authorId", insertable = true, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Author author = new Author();
	
	@JsonDeserialize(as = Publisher.class)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pubId",  referencedColumnName = "publisherId", insertable = true, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Publisher publisher = new Publisher();

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ "]";
	}
	
	
	
}

