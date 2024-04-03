package com.metro.Metro.model;
import org.hibernate.validator.constraints.Length;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="user_details")
public class User {

	@Id
	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	@Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")   //for mailId validating
	@NotBlank
	@Column(name="email_address",unique = true)
	private String emailId;
	
	@Length(max = 10,min = 10)
	@Column(unique = true)
	private String phoneNo;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	

}
