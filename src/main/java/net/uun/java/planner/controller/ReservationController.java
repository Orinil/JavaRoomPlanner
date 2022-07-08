package net.uun.java.planner.controller;

import net.uun.java.planner.controller.utils.NotAuthenticated;
import net.uun.java.planner.controller.utils.NotAuthorized;
import net.uun.java.planner.entity.Room;
import net.uun.java.planner.entity.RoomReservation;
import net.uun.java.planner.requests.CreateReservationRequest;
import net.uun.java.planner.requests.ReservationRequest;
import net.uun.java.planner.service.ReservationService;
import net.uun.java.planner.utils.InvalidRequest;
import net.uun.java.planner.utils.ReservationFailedRoomIsFull;
import net.uun.java.planner.utils.UnknownLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

  @Autowired
  private ReservationService reservationService;

  @Transactional
  @GetMapping
  public List<RoomReservation> getAll(){
    return reservationService.getAllReservations();
  }

  @Transactional
  @PostMapping(value = "/create")
  public RoomReservation create(@RequestBody ReservationRequest request) throws InvalidRequest, NotAuthorized, NotAuthenticated, ReservationFailedRoomIsFull {
    return reservationService.createReservation(request);
  }

  @Transactional
  @PutMapping(value = "/update")
  public RoomReservation update(@RequestBody ReservationRequest request) throws InvalidRequest, NotAuthorized, NotAuthenticated, ReservationFailedRoomIsFull {
    return reservationService.createReservation(request);
  }

  @ExceptionHandler(ReservationFailedRoomIsFull.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> unknownLocation(){
    return new ResponseEntity<String>("Room is full", HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidRequest.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> invalidRequest(){
    return new ResponseEntity<String>("Request is invalid start is greather than to", HttpStatus.BAD_REQUEST);
  }

}
