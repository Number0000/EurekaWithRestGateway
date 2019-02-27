package com.SmoothStack.EurekaBorrower.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.EurekaBorrower.Entity.Author;
import com.SmoothStack.EurekaBorrower.Exception.ResourceNotFoundException;
import com.SmoothStack.EurekaBorrower.Repository.AuthorRepository;

@RestController
public class AuthorController {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	//Get All Author
	public List<Author> getAllAuthor(){
		return authorRepository.findAll();
	}
	
	//Create a new author
	public Author createAuthor(Author author) {
		return authorRepository.save(author);
	}
	
	//Get a Single Author
	public Author getAuthorById(int authorId) {
		return authorRepository.findById(authorId)
				.orElseThrow(()-> new ResourceNotFoundException("Author", "id", authorId));
	}
	
	//Get a Single Author by line within authors
	public List<Author> getAuthorByLine(int line) {
		Page<Author> authorPage = authorRepository.findAll(PageRequest.of(line-1, 1));
		List<Author> author = authorPage.getContent();
		return author;
	}
	
	//Update a Author
	public Author updateAuthor(int authorId, Author authorDetails) {
		Author author = authorRepository.findById(authorId)
				.orElseThrow(()-> new ResourceNotFoundException("Author", "id", authorId));
		author.setAuthorName(authorDetails.getAuthorName());
		
		Author updatedAuthor = authorRepository.save(author);
		return updatedAuthor;
	}
	
	//Delete a Author
	public void deleteAuthor(int authorId){
		Author author = authorRepository.findById(authorId)
				.orElseThrow(()-> new ResourceNotFoundException("Author", "id", authorId));
		authorRepository.delete(author);
	}
}

