package net.uun.java.planner.controller;

import net.uun.java.planner.entity.Room;
import net.uun.java.planner.requests.RoomRequest;
import net.uun.java.planner.service.IRoomService;
import net.uun.java.planner.utils.EntityNotFound;
import net.uun.java.planner.utils.ReservedInFuture;
import net.uun.java.planner.utils.UnknownLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private IRoomService roomService;


    @Transactional
    @RequestMapping
    public List<Room> getAll(){
        return roomService.getAllRooms();
    }

    @Transactional
    @RequestMapping("/{id}")
    public Optional<Room> showRoom(@PathVariable int id) {
        return roomService.showRoom(id);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public Room registerNewRoom(@RequestBody @NotNull RoomRequest roomRequest) throws UnknownLocation {
        return roomService.createRoom(roomRequest);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Optional<Room> updateRoom(@PathVariable int id, RoomRequest roomRequest) throws UnknownLocation {
        return roomService.updateRoom(id, roomRequest);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteRoom(@PathVariable int id) throws EntityNotFound, ReservedInFuture {
        roomService.deleteRoom(id);
    }

    @ExceptionHandler(UnknownLocation.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> unknownLocation(){
        return new ResponseEntity<String>("Unknown location", HttpStatus.BAD_REQUEST);
    }
}
