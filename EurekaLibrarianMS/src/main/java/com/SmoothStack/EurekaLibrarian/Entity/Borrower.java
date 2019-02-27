package com.SmoothStack.EurekaLibrarian.Entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "tbl_borrower")
@Table(name = "tbl_borrower")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
	allowGetters = true)
public class Borrower implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cardNo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cardNo;
	
	@NotBlank
	@Column(name = "name", nullable = false)
	private String borrowerName;
	
	@NotBlank
	@Column(name = "address", nullable = false)
	private String borrowerAddress;
	
	@NotBlank
	@Column(name = "phone", nullable = false)
	private String borrowerPhone;
	
	@NotBlank
	@Column(name = "userName", nullable = false)
	private String borrowerUserName;
	
	@NotBlank
	@Column(name = "password", nullable = false)
	private String borrowerPassword;

	public int getCardNo() {
		return cardNo;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getBorrowerAddress() {
		return borrowerAddress;
	}

	public void setBorrowerAddress(String borrowerAddress) {
		this.borrowerAddress = borrowerAddress;
	}

	public String getBorrowerPhone() {
		return borrowerPhone;
	}

	public void setBorrowerPhone(String borrowerPhone) {
		this.borrowerPhone = borrowerPhone;
	}

	public String getBorrowerUserName() {
		return borrowerUserName;
	}

	public void setBorrowerUserName(String borrowerUserName) {
		this.borrowerUserName = borrowerUserName;
	}

	public String getBorrowerPassword() {
		return borrowerPassword;
	}

	public void setBorrowerPassword(String borrowerPassword) {
		this.borrowerPassword = borrowerPassword;
	}

	@Override
	public String toString() {
		return "Borrower [cardNo=" + cardNo + ", borrowerName=" + borrowerName + ", borrowerAddress=" + borrowerAddress
				+ ", borrowerPhone=" + borrowerPhone + ", borrowerUserName=" + borrowerUserName + ", borrowerPassword="
				+ borrowerPassword + "]";
	}
	
	
}

