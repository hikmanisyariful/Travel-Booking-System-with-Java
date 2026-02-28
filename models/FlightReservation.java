package models;

public final class FlightReservation extends Reservation {
  private Flight flight;
  private int passengerCount; // supaya bisa dipakai saat cancel (balikin seats)

  public FlightReservation(String confirmationNumber, Flight flight, int passengerCount) {
    super(confirmationNumber);
    this.flight = flight;
    this.passengerCount = passengerCount;
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }

  public int getPassengerCount() {
    return passengerCount;
  }

  public void setPassengerCount(int passengerCount) {
    this.passengerCount = passengerCount;
  }

  @Override
  public void display() {
    System.out.println("=== Flight Reservation ===");
    System.out.println("Confirmation : " + getConfirmationNumber());
    System.out.println("Flight       : " + flight.getFlightNumber());
    System.out.println("Route        : " + flight.getFlightOrigin() + " -> " + flight.getFlightDestination());
    System.out.println("Date         : " + flight.getFlightDate());
    System.out.println("Passengers   : " + passengerCount);
    System.out.println("Price        : " + flight.getPrice());
  }
}