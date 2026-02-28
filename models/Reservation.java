package models;

public sealed abstract class Reservation
    permits FlightReservation, HotelReservation {

  private String confirmationNumber;

  protected Reservation(String confirmationNumber) {
    this.confirmationNumber = confirmationNumber;
  }

  public String getConfirmationNumber() {
    return confirmationNumber;
  }

  public void setConfirmationNumber(String confirmationNumber) {
    this.confirmationNumber = confirmationNumber;
  }

  // wajib di-override oleh subclass (polimorfisme)
  public abstract void display();
}