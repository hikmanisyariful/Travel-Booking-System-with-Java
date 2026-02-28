package models;

public final class HotelReservation extends Reservation {
  private Hotel hotel;

  public HotelReservation(String confirmationNumber, Hotel hotel) {
    super(confirmationNumber);
    this.hotel = hotel;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  @Override
  public void display() {
    System.out.println("=== Hotel Reservation ===");
    System.out.println("Confirmation : " + getConfirmationNumber());
    System.out.println("Hotel        : " + hotel.getHotelName());
    System.out.println("Location     : " + hotel.getLocation());
    System.out.println("Check-in/out : " + hotel.getCheckInDate() + " -> " + hotel.getCheckOutDate());
    System.out.println("Guests       : " + hotel.getGuestCount());
    System.out.println("Price        : " + hotel.getPrice());
  }
}