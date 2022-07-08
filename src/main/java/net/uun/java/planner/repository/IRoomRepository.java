package net.uun.java.planner.repository;

import net.uun.java.planner.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IRoomRepository extends JpaRepository<Room, Integer> {
  @Query("SELECT role.id FROM Role role join role.rooms rooms WHERE rooms.Id = ?1")
  List<Integer> getRoleIds(int roomId);
}
