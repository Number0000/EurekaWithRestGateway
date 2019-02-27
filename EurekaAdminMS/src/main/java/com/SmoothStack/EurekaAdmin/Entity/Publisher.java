package com.SmoothStack.EurekaAdmin.Entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "tbl_publisher")
@Table(name = "tbl_publisher")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
	allowGetters = true)
public class Publisher implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "publisherId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int publisherId;
	
	@NotBlank
	@Column(name = "publisherName", nullable = false)
	private String publisherName;
	
	@NotBlank
	@Column(name = "publisherAddress", nullable = false)
	private String publisherAddress;
	
	@NotBlank
	@Column(name = "publisherPhone", nullable = false)
	private String publisherPhone;

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherAddress() {
		return publisherAddress;
	}

	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	@Override
	public String toString() {
		return "Publisher [publisherId=" + publisherId + ", publisherName=" + publisherName + ", publisherAddress="
				+ publisherAddress + ", publisherPhone=" + publisherPhone + "]";
	}	
	
}

