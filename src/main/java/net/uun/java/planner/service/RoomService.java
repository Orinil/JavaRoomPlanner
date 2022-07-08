package net.uun.java.planner.service;
import static com.speedment.jpastreamer.streamconfiguration.StreamConfiguration.*;
import com.speedment.jpastreamer.application.JPAStreamer;
import net.uun.java.planner.entity.Room;
import net.uun.java.planner.entity.RoomReservation;
import net.uun.java.planner.entity.RoomReservation$;
import net.uun.java.planner.repository.ILocationRepository;
import net.uun.java.planner.repository.IRoomRepository;
import net.uun.java.planner.repository.IRoomReservationRepository;
import net.uun.java.planner.requests.RoomRequest;
import net.uun.java.planner.utils.EntityNotFound;
import net.uun.java.planner.utils.ReservedInFuture;
import net.uun.java.planner.utils.UnknownLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class RoomService implements IRoomService {
    @Autowired
    IRoomRepository roomRepository;

    @Autowired
    IRoomReservationRepository roomReservationRepository;

    @Autowired
    ILocationRepository locationRepository;

    @Autowired
    JPAStreamer jpaStreamer;

    @Override
    public List<Room> getAllRooms() {
    return roomRepository.findAll();
    }

    @Override
    public Optional<Room> showRoom(int id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room createRoom(RoomRequest roomRequest) throws UnknownLocation {
        Room entity = new Room();
        setDataFromRequestAndSave(entity, roomRequest);
        return entity;
    }

    @Override
    public void deleteRoom(int id) throws EntityNotFound, ReservedInFuture {
        if(!roomRepository.existsById(id))
            throw new EntityNotFound();

        var reservationInFutureExists = jpaStreamer.stream(RoomReservation.class)
                .filter(RoomReservation$.roomId.equal(id))
                .anyMatch(RoomReservation$.startDate.greaterThan(LocalDateTime.now()));
        if(reservationInFutureExists)
            throw new ReservedInFuture();

        roomRepository.deleteById(id);
    }

    @Override
    public Optional<Room> updateRoom(int id, RoomRequest roomRequest) throws UnknownLocation {
        Optional<Room> roomById = roomRepository.findById(id);
        if(roomById.isPresent())
            setDataFromRequestAndSave(roomById.get(), roomRequest);
        return roomById;
    }
    private void setDataFromRequestAndSave(Room entity, RoomRequest request) throws UnknownLocation {
        entity.setName(request.name);
        entity.setCapacity(request.capacity);
        entity.setLocation(locationRepository.findById(request.locationId).orElseThrow(UnknownLocation::new));
        roomRepository.save(entity);
    }
}
