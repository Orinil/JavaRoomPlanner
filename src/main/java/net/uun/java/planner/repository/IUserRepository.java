package net.uun.java.planner.repository;

import net.uun.java.planner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

  @Query("SELECT role.id FROM User u join u.roles role WHERE u.id = ?1")
  List<Integer> getRoleIds(int userId);

}
