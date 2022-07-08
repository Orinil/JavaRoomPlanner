package net.uun.java.planner.service;

import com.speedment.jpastreamer.application.JPAStreamer;
import net.uun.java.planner.auth.AuthService;
import net.uun.java.planner.controller.utils.NotAuthenticated;
import net.uun.java.planner.controller.utils.NotAuthorized;
import net.uun.java.planner.entity.*;
import net.uun.java.planner.repository.IRoomReservationRepository;
import net.uun.java.planner.requests.ReservationRequest;
import net.uun.java.planner.utils.EntityNotFound;
import net.uun.java.planner.utils.InvalidRequest;
import net.uun.java.planner.utils.ReservationFailedRoomIsFull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {
  @Autowired
  IRoomReservationRepository roomReservationRepository;

  @Autowired
  private JPAStreamer jpaStreamer;

  @Autowired
  private AuthService authService;

  public List<RoomReservation> getAllReservations() {
    return roomReservationRepository.findAll();
  }

  public Optional<RoomReservation> getReservation(int id) {
    return roomReservationRepository.findById(id);
  }

  public RoomReservation createReservation(ReservationRequest reservationRequest) throws InvalidRequest, ReservationFailedRoomIsFull, NotAuthorized {

    if(reservationRequest.from.isAfter(reservationRequest.to))
      throw new InvalidRequest();

    if(!IsRoomEmpty(reservationRequest.from, reservationRequest.to, reservationRequest.roomId))
      throw new ReservationFailedRoomIsFull();

    if(!authService.CanReserveRoom(reservationRequest.roomId))
      throw new NotAuthorized();

    RoomReservation entity = new RoomReservation();
    //noinspection OptionalGetWithoutIsPresent
    setDataFromRequestAndSave(entity, reservationRequest, authService.getAuthorizeUserId().get());

    return entity;
  }

  public void deleteReservation(int id) throws EntityNotFound {

    if (!roomReservationRepository.existsById(id)) {
      throw new EntityNotFound();
    }

    roomReservationRepository.deleteById(id);

  }

  public Optional<RoomReservation> updateReservation(int id, ReservationRequest reservationRequest) throws NotAuthenticated, InvalidRequest,NotAuthorized, ReservationFailedRoomIsFull {

    Optional<RoomReservation> roomReservationById = roomReservationRepository.findById(id);

    if(reservationRequest.from.isAfter(reservationRequest.to))
      throw new InvalidRequest();


    if(IsRoomEmpty(reservationRequest.from, reservationRequest.to, reservationRequest.roomId))
      throw new ReservationFailedRoomIsFull();


    if(!authService.CanReserveRoom(reservationRequest.roomId))
      throw new NotAuthorized();

    roomReservationById.ifPresent(r -> setDataFromRequestAndSave(r, reservationRequest, authService.getAuthorizeUserId().get()));
    return roomReservationById;
  }

  private void setDataFromRequestAndSave(RoomReservation entity, ReservationRequest request, int userId) {

    entity.setStartDate(request.from);
    entity.setEndDate(request.to);
    entity.setRoomId(request.roomId);
    entity.setName(request.name);
    entity.setUserId(userId);

    roomReservationRepository.save(entity);
  }

  private boolean IsRoomEmpty(LocalDateTime from, LocalDateTime to, int roomId) {

    var isReserved = jpaStreamer.stream(RoomReservation.class)
      .filter(RoomReservation$.roomId.equal(roomId))
      .filter(RoomReservation$.startDate.lessOrEqual(to))
      .filter(RoomReservation$.endDate.greaterOrEqual(from)).findAny();

    return isReserved.isEmpty();

  }

}
