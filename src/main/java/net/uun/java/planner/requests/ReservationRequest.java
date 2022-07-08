package net.uun.java.planner.requests;

import java.time.LocalDateTime;

public class ReservationRequest {

  public int roomId;

  public String name;

  public LocalDateTime from;

  public LocalDateTime to;
}
