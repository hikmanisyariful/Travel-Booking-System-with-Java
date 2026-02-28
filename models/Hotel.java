package models;

public class Hotel {
  private int hotelId;
  private String hotelName;
  private String location;
  private String checkInDate;
  private String checkOutDate;
  private int guestCount;
  private int price;
  private String confirmationNumber;

  // Constructor
  public Hotel(
      int hotelId,
      String hotelName,
      String location,
      String checkInDate,
      String checkOutDate,
      int guestCount,
      int price,
      String confirmationNumber) {
    this.hotelId = hotelId;
    this.hotelName = hotelName;
    this.location = location;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.guestCount = guestCount;
    this.price = price;
    this.confirmationNumber = confirmationNumber;
  }

  // Getters
  public int getHotelId() {
    return hotelId;
  }

  public String getHotelName() {
    return hotelName;
  }

  public String getLocation() {
    return location;
  }

  public String getCheckInDate() {
    return checkInDate;
  }

  public String getCheckOutDate() {
    return checkOutDate;
  }

  public int getGuestCount() {
    return guestCount;
  }

  public int getPrice() {
    return price;
  }

  public String getConfirmationNumber() {
    return confirmationNumber;
  }

  // Setters (enkapsulasi)
  public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setCheckInDate(String checkInDate) {
    this.checkInDate = checkInDate;
  }

  public void setCheckOutDate(String checkOutDate) {
    this.checkOutDate = checkOutDate;
  }

  public void setGuestCount(int guestCount) {
    this.guestCount = guestCount;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void setConfirmationNumber(String confirmationNumber) {
    this.confirmationNumber = confirmationNumber;
  }

  @Override
  public String toString() {
    return "Hotel ID:" + hotelId +
        " | " + hotelName +
        " | " + location +
        " | Check-in:" + checkInDate +
        " | Check-out:" + checkOutDate +
        " | Guests:" + guestCount +
        " | Price:" + price;
  }
}