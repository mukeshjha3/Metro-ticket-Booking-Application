package com.metro.Metro.service;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.metro.Metro.model.User;
import com.metro.Metro.payload.UserRequest;
import com.metro.Metro.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepo userRepo;
	
	private final PasswordEncoder passwordEncoder;

	@Override
	public User createUser(User user) {
		User savedUser = userRepo.save(user);
		return savedUser;
		
	}

	@Override
	@Transactional
	public User updateUser(String email,User user) {
		User savedUser = userRepo.findByEmailId(email);
		savedUser.setEmailId(user.getEmailId());
		savedUser.setFirstName(user.getFirstName());
		savedUser.setPassword(user.getPassword());
		savedUser.setPhoneNo(user.getPhoneNo());
		savedUser.setLastName(user.getLastName());
		
		User updatedUser = userRepo.save(savedUser);
		return updatedUser;
	}

	@Override
	public void deleteUser(String email) {
		userRepo.deleteByEmailId(email);
		
	}

	@Override
	public User getUser(String email) {
	User user = userRepo.findByEmailId(email);
		return user;
	}
	

	// mapping the userRequestentity to user entity
	public User mapUserRequestToUser(UserRequest request) {
		User user = User.builder().userId(UUID.randomUUID().toString()).emailId(request.getEmailId())
				.password(passwordEncoder.encode(request.getPassword())).phoneNo(request.getPhoneNo())
				.firstName(request.getFirstName()).lastName(request.getLastName()).build();
		return user;

	}

}
