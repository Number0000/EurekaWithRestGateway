package com.SmoothStack.EurekaBorrower.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.EurekaBorrower.Entity.Borrower;
import com.SmoothStack.EurekaBorrower.Exception.ResourceNotFoundException;
import com.SmoothStack.EurekaBorrower.Repository.BorrowerRepository;

@RestController
public class BorrowerController {
	
	@Autowired
	private BorrowerRepository borrowerRepository;
	
	//Get All Borrower
	public List<Borrower> getAllBorrower(){
		return borrowerRepository.findAll();
	}
	
	//Create a new borrower
	public Borrower createBorrower(Borrower borrower) {
		return borrowerRepository.save(borrower);
		
	}
	
	//Get a Single Borrower
	public Borrower getBorrowerById(int borrowerId) {
		return borrowerRepository.findById(borrowerId)
				.orElseThrow(()-> new ResourceNotFoundException("Borrower", "id", borrowerId));
	}
	
	//Get a Single Borrower by line within Borrowers
	public List<Borrower> getBorrowerByLine(int line) {
		Page<Borrower> borrowerPage = borrowerRepository.findAll(PageRequest.of(line-1, 1));
		List<Borrower> borrower = borrowerPage.getContent();
		return borrower;
	}
	
	//Update a Borrower
	public Borrower updateBorrower(int borrowerId, Borrower borrowerDetails) {
		Borrower borrower = borrowerRepository.findById(borrowerId)
				.orElseThrow(()-> new ResourceNotFoundException("Borrower", "id", borrowerId));
		borrower.setBorrowerName(borrowerDetails.getBorrowerName());
		borrower.setBorrowerAddress(borrowerDetails.getBorrowerAddress());
		borrower.setBorrowerPhone(borrowerDetails.getBorrowerPhone());
		borrower.setBorrowerUserName(borrowerDetails.getBorrowerUserName());
		borrower.setBorrowerPassword(borrowerDetails.getBorrowerPassword());
		
		Borrower updatedBorrower = borrowerRepository.save(borrower);
		return updatedBorrower;
	}
	
	//Delete a Borrower
	public void deleteBorrower(int borrowerId){
		Borrower borrower = borrowerRepository.findById(borrowerId)
				.orElseThrow(()-> new ResourceNotFoundException("Borrower", "id", borrowerId));
		borrowerRepository.delete(borrower);
	}
}

