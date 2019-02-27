package com.SmoothStack.EurekaLibrarian.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.EurekaLibrarian.Entity.Publisher;
import com.SmoothStack.EurekaLibrarian.Exception.ResourceNotFoundException;
import com.SmoothStack.EurekaLibrarian.Repository.PublisherRepository;

@RestController
public class PublisherController {
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	//Get All Publisher
	public List<Publisher> getAllPublisher(){
		return publisherRepository.findAll();
	}
	
	//Create a new publisher
	public Publisher createPublisher(Publisher publisher) {
		return publisherRepository.save(publisher);
		
	}
	
	//Get a Single Publisher
	public Publisher getPublisherById(int publisherId) {
		return publisherRepository.findById(publisherId)
				.orElseThrow(()-> new ResourceNotFoundException("Publisher", "id", publisherId));
	}
	
	//Get a Single Publisher by line within Publishers
	public List<Publisher> getPublisherByLine(int line) {
		Page<Publisher> publisherPage = publisherRepository.findAll(PageRequest.of(line-1, 1));
		List<Publisher> publisher = publisherPage.getContent();
		return publisher;
	}
	
	//Update a Publisher
	public Publisher updatePublisher(int publisherId, Publisher publisherDetails) {
		Publisher publisher = publisherRepository.findById(publisherId)
				.orElseThrow(()-> new ResourceNotFoundException("Publisher", "id", publisherId));
		publisher.setPublisherName(publisherDetails.getPublisherName());
		publisher.setPublisherAddress(publisherDetails.getPublisherAddress());
		publisher.setPublisherPhone(publisherDetails.getPublisherPhone());
		
		Publisher updatedPublisher = publisherRepository.save(publisher);
		return updatedPublisher;
	}
	
	//Delete a Publisher
	public void deletePublisher(int publisherId){
		Publisher publisher = publisherRepository.findById(publisherId)
				.orElseThrow(()-> new ResourceNotFoundException("Publisher", "id", publisherId));
		publisherRepository.delete(publisher);
	}
}

