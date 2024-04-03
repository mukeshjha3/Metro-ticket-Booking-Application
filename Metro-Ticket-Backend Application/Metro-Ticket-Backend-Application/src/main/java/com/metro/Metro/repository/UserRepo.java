package com.metro.Metro.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.metro.Metro.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

	User findByEmailId(String emailId);

	void deleteByEmailId(String emailId);
}
