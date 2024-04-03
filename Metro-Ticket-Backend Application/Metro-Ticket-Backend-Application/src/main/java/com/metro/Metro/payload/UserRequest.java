package com.metro.Metro.payload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

	private String firstName;
	
	private String lastName;
	
	private String phoneNo;
	
	private String emailId;
	
	private String password;
}
