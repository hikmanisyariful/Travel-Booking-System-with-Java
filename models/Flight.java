package models;

public class Flight {
  private String flightNumber;
  private String flightOrigin;
  private String flightDestination;
  private String flightDate;
  private int availableSeats;
  private double price;
  private String confirmationNumber;

  // Constructor
  public Flight(String flightNumber, String flightOrigin, String flightDestination,
      String flightDate, int availableSeats, double price,
      String confirmationNumber) {
    this.flightNumber = flightNumber;
    this.flightOrigin = flightOrigin;
    this.flightDestination = flightDestination;
    this.flightDate = flightDate;
    this.availableSeats = availableSeats;
    this.price = price;
    this.confirmationNumber = confirmationNumber;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public String getFlightOrigin() {
    return flightOrigin;
  }

  public String getFlightDestination() {
    return flightDestination;
  }

  public String getFlightDate() {
    return flightDate;
  }

  public int getAvailableSeats() {
    return availableSeats;
  }

  public double getPrice() {
    return price;
  }

  public String getConfirmationNumber() {
    return confirmationNumber;
  }

  public void setAvailableSeats(int availableSeats) {
    this.availableSeats = availableSeats;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setConfirmationNumber(String confirmationNumber) {
    this.confirmationNumber = confirmationNumber;
  }

  @Override
  public String toString() {
    return flightNumber + " " + flightOrigin + "->" + flightDestination +
        " " + flightDate + " seats:" + availableSeats + " price:" + price;
  }

}
