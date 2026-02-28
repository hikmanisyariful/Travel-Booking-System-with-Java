import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import models.Flight;
import models.Hotel;
import models.Reservation;
import models.FlightReservation;
import models.HotelReservation;

public class TravelBookingSystem {

  // Inventory
  private final ArrayList<Flight> flights = new ArrayList<>();
  private final ArrayList<Hotel> hotels = new ArrayList<>();

  // Current reservations (polymorphism)
  private final ArrayList<Reservation> reservations = new ArrayList<>();

  // ===== Seed Data (opsional tapi sangat membantu) =====
  public void seedData() {
    // Flights
    flights.add(new Flight("GA-101", "JKT", "DPS", "2026-03-10", 10, 1500000, null));
    flights.add(new Flight("QZ-202", "JKT", "SUB", "2026-03-11", 5, 900000, null));
    flights.add(new Flight("JT-303", "SUB", "DPS", "2026-03-10", 8, 1100000, null));
    flights.add(new Flight("ID-404", "JKT", "DPS", "2026-03-10", 3, 1750000, null));

    // Hotels (note: kamu simpan check-in/out & guestCount di entitas Hotel sesuai requirement)
    hotels.add(new Hotel(1, "Bali Sunset", "DPS", "2026-03-10", "2026-03-12", 2, 1200000, null));
    hotels.add(new Hotel(2, "Surabaya Inn", "SUB", "2026-03-11", "2026-03-12", 1, 500000, null));
    hotels.add(new Hotel(3, "Jakarta Stay", "JKT", "2026-03-10", "2026-03-11", 2, 800000, null));
    hotels.add(new Hotel(4, "Bali Budget", "DPS", "2026-03-10", "2026-03-12", 1, 650000, null));
  }

  // ===== Getters koleksi (opsional) =====
  public List<Flight> getFlights() {
    return flights;
  }

  public List<Hotel> getHotels() {
    return hotels;
  }

  public List<Reservation> getReservations() {
    return reservations;
  }

  // ===== Search Flights (Lambda / Stream) =====
  public List<Flight> searchFlights(String origin, String destination, String date) {
    return flights.stream()
        .filter(f -> f.getFlightOrigin().equalsIgnoreCase(origin))
        .filter(f -> f.getFlightDestination().equalsIgnoreCase(destination))
        .filter(f -> f.getFlightDate().equalsIgnoreCase(date))
        .collect(Collectors.toList());
  }

  // ===== Book Flight =====
  // passengerCount digunakan untuk mengurangi seats dan disimpan di FlightReservation
  public Reservation bookFlight(String flightNumber, int passengerCount) {
    if (passengerCount <= 0) return null;

    for (Flight f : flights) {
      if (!f.getFlightNumber().equalsIgnoreCase(flightNumber)) continue;

      if (f.getAvailableSeats() < passengerCount) {
        return null; // seats tidak cukup
      }

      String conf = generateConfirmationNumber();

      // update flight (sesuai requirement: flight punya confirmationNumber)
      f.setAvailableSeats(f.getAvailableSeats() - passengerCount);
      f.setConfirmationNumber(conf);

      // simpan reservation
      FlightReservation r = new FlightReservation(conf, f, passengerCount);
      reservations.add(r);
      return r;
    }
    return null; // flight tidak ditemukan
  }

  // ===== Search Hotels (Loop + Conditional sesuai pedoman) =====
  public List<Hotel> searchHotels(String location, String checkIn, String checkOut, int guestCount) {
    List<Hotel> result = new ArrayList<>();
    for (Hotel h : hotels) {
      boolean match =
          h.getLocation().equalsIgnoreCase(location)
              && h.getCheckInDate().equalsIgnoreCase(checkIn)
              && h.getCheckOutDate().equalsIgnoreCase(checkOut)
              && h.getGuestCount() == guestCount;

      if (match) result.add(h);
    }
    return result;
  }

  // ===== Book Hotel =====
  public Reservation bookHotel(int hotelId) {
    for (Hotel h : hotels) {
      if (h.getHotelId() != hotelId) continue;

      // jika sudah ada confirmation, anggap sudah dibooking
      if (h.getConfirmationNumber() != null && !h.getConfirmationNumber().isBlank()) {
        return null;
      }

      String conf = generateConfirmationNumber();

      // update hotel (sesuai requirement: hotel punya confirmationNumber)
      h.setConfirmationNumber(conf);

      HotelReservation r = new HotelReservation(conf, h);
      reservations.add(r);
      return r;
    }
    return null; // hotel tidak ditemukan
  }

  // ===== Cancel Reservation (Pattern Matching instanceof Java 17+) =====
  public boolean cancelReservation(String confirmationNumber) {
    if (confirmationNumber == null || confirmationNumber.isBlank()) return false;

    Reservation target = null;
    for (Reservation r : reservations) {
      if (r.getConfirmationNumber().equalsIgnoreCase(confirmationNumber)) {
        target = r;
        break;
      }
    }
    if (target == null) return false;

    // pattern matching
    if (target instanceof FlightReservation fr) {
      Flight f = fr.getFlight();

      // balikin seats berdasarkan passengerCount
      f.setAvailableSeats(f.getAvailableSeats() + fr.getPassengerCount());
      f.setConfirmationNumber(null);

    } else if (target instanceof HotelReservation hr) {
      hr.getHotel().setConfirmationNumber(null);
    }

    reservations.remove(target);
    return true;
  }

  // ===== List Reservations (Polymorphism) =====
  public void listReservations() {
    if (reservations.isEmpty()) {
      System.out.println("No reservations yet.");
      return;
    }
    for (Reservation r : reservations) {
      r.display(); // polymorphism: display() milik subclass
      System.out.println("--------------------------");
    }
  }

  // ===== Helpers =====
  private String generateConfirmationNumber() {
    return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
  }
}