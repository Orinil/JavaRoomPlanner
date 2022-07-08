package net.uun.java.planner.service;

import net.uun.java.planner.entity.Room;
import net.uun.java.planner.requests.RoomRequest;
import net.uun.java.planner.utils.EntityNotFound;
import net.uun.java.planner.utils.ReservedInFuture;
import net.uun.java.planner.utils.UnknownLocation;

import java.util.List;
import java.util.Optional;

public interface IRoomService {
    List<Room> getAllRooms();

    Optional<Room> showRoom(int id);

    Room createRoom(RoomRequest roomRequest) throws UnknownLocation;

    void deleteRoom(int id) throws EntityNotFound, ReservedInFuture;

    Optional<Room> updateRoom(int id, RoomRequest roomRequest) throws UnknownLocation;
}
