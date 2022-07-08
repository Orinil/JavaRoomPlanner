package net.uun.java.planner.repository;

import net.uun.java.planner.entity.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAuthTokenRepository extends JpaRepository<AuthToken, UUID> {
}
