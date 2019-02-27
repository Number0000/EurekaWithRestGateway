package com.SmoothStack.EurekaAdmin.Entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "tbl_library_branch")
@Table(name = "tbl_library_branch")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
	allowGetters = true)
public class LibraryBranch implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "branchId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int libraryBranchId;
	
	@NotBlank
	@Column(name = "branchName", nullable = false)
	private String libraryBranchName;
	
	@NotBlank
	@Column(name = "branchAddress", nullable = false)
	private String libraryBranchAddress;
	
	public int getLibraryBranchId() {
		return libraryBranchId;
	}

	public void setLibraryBranchId(int libraryBranchId) {
		this.libraryBranchId = libraryBranchId;
	}

	public String getLibraryBranchName() {
		return libraryBranchName;
	}

	public void setLibraryBranchName(String libraryBranchName) {
		this.libraryBranchName = libraryBranchName;
	}

	public String getLibraryBranchAddress() {
		return libraryBranchAddress;
	}

	public void setLibraryBranchAddress(String libraryBranchAddress) {
		this.libraryBranchAddress = libraryBranchAddress;
	}

	@Override
	public String toString() {
		return "LibraryBranch [libraryBranchId=" + libraryBranchId + ", libraryBranchName=" + libraryBranchName + ", libraryBranchAddress="
				+ libraryBranchAddress + "]";
	}	
	
}

