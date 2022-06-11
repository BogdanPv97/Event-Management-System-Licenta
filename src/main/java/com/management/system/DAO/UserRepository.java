package com.management.system.DAO;

import com.management.system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "Select email from user u where u.newsletter=true")
    public List<String> getAllEmailsFromSubscription();

    @Query( value = "select u from User u where u.username= ?1")
    public User getUserByUsername(String username);
}
