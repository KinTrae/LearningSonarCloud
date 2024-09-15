package com.mas.kinga.repositories;

import com.mas.kinga.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by login
     * @param login
     * @return
     */
    User findUserByLogin(String login);

    /**
     * Checks if user exists by given login
     * @param login
     * @return
     */
    Boolean existsByLogin(String login);
}
