package com.SmoothStack.EurekaAdmin.Entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "tbl_author")
@Table(name = "tbl_author")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt", "hibernateLazyInitializer", "handler"}, 
	allowGetters = true)
public class Author implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "authorId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authorId;
	
	@NotBlank
	@Column(name = "authorName", nullable = false)
	private String authorName;
	
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + "]";
	}
	
}

