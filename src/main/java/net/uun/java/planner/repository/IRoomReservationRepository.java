package net.uun.java.planner.repository;

import net.uun.java.planner.entity.Location;
import net.uun.java.planner.entity.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.time.LocalDateTime;

@Repository
public interface IRoomReservationRepository extends JpaRepository<RoomReservation, Integer> {

    /*
    @Query(""" 
SELECT rr.name, rr.start_date, rr.end_date, ltt.duration
FROM room_reservation rr
JOIN room r on r.id = rr.room_id
JOIN location l on l.id = r.location_id
LEFT JOIN location_travel_time ltt on l.id = ltt.target_location_id AND ltt.starting_location_id = :starting
WHERE rr.start_date > :endDate AND rr.user_id = :userId
LIMIT 1
""")
    public Tuple getNextAfter(@Param("endDate") LocalDateTime endDate, @Param("starting") int startingLocationId, @Param("userId") int userId);
    @Query(""" 
SELECT rr.name, rr.start_date, rr.end_date, ltt.duration
FROM room_reservation rr
JOIN room r on r.id = rr.room_id
JOIN location l on l.id = r.location_id 
LEFT JOIN location_travel_time ltt on l.id = ltt.starting_location_id AND ltt.target_location_id = :targetLocationId
WHERE rr.end_date > :startDate AND rr.user_id = :userId
LIMIT 1
""")
    public Tuple getPreviousBefore(@Param("startDate") LocalDateTime endDate, @Param("targetLocationId") int targetLocationId, @Param("userId") int userId);

    public default boolean canReserve(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        // TODO
        return true;
    }*/
}
