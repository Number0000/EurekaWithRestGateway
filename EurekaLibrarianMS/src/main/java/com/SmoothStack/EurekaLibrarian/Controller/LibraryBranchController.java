package com.SmoothStack.EurekaLibrarian.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.EurekaLibrarian.Entity.LibraryBranch;
import com.SmoothStack.EurekaLibrarian.Exception.ResourceNotFoundException;
import com.SmoothStack.EurekaLibrarian.Repository.LibraryBranchRepository;

@RestController
public class LibraryBranchController {
	
	@Autowired
	private LibraryBranchRepository libraryBranchRepository;
	
	//Get All LibraryBranch
	public List<LibraryBranch> getAllLibraryBranch(){
		return libraryBranchRepository.findAll();
	}
	
	//Create a new libraryBranch
	public LibraryBranch createLibraryBranch(LibraryBranch libraryBranch) {
		return libraryBranchRepository.save(libraryBranch);
		
	}
	
	//Get a Single LibraryBranch
	public LibraryBranch getLibraryBranchById(int libraryBranchId) {
		return libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
	}
	
	//Get a Single libraryBranch by line within libraryBranchs
	public List<LibraryBranch> getLibraryBranchByLine(int line) {
		Page<LibraryBranch> libraryBranchPage = libraryBranchRepository.findAll(PageRequest.of(line-1, 1));
		List<LibraryBranch> libraryBranch = libraryBranchPage.getContent();
		return libraryBranch;
	}
	
	//Update a LibraryBranch
	public LibraryBranch updateLibraryBranch(int libraryBranchId, LibraryBranch libraryBranchDetails) {
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		libraryBranch.setLibraryBranchName(libraryBranchDetails.getLibraryBranchName());
		libraryBranch.setLibraryBranchAddress(libraryBranchDetails.getLibraryBranchAddress());
		
		LibraryBranch updatedLibraryBranch = libraryBranchRepository.save(libraryBranch);
		return updatedLibraryBranch;
	}
	
	//Delete a LibraryBranch
	public void deleteLibraryBranch(int libraryBranchId){
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		libraryBranchRepository.delete(libraryBranch);
	}
	
	public int getLibraryBranchCount() {
		int count = (int) libraryBranchRepository.count();
		return count;
	}
}

