import java.util.List;
import java.util.Scanner;

import models.Flight;
import models.Hotel;
import models.Reservation;

public class Main {

  public static void main(String[] args) {

    TravelBookingSystem system = new TravelBookingSystem();
    system.seedData(); // isi data awal

    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.println("\n===== TRAVEL BOOKING SYSTEM =====");
      System.out.println("1. Search Flights");
      System.out.println("2. Book Flight");
      System.out.println("3. Search Hotels");
      System.out.println("4. Book Hotel");
      System.out.println("5. List Reservations");
      System.out.println("6. Cancel Reservation");
      System.out.println("0. Exit");
      System.out.print("Choose: ");

      try {
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {

          case 1 -> {
            System.out.print("Origin: ");
            String origin = sc.nextLine();

            System.out.print("Destination: ");
            String dest = sc.nextLine();

            System.out.print("Date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            List<Flight> results = system.searchFlights(origin, dest, date);

            if (results.isEmpty()) {
              System.out.println("No flights found.");
            } else {
              results.forEach(System.out::println); // lambda
            }
          }

          case 2 -> {
            System.out.print("Flight Number: ");
            String flightNumber = sc.nextLine();

            System.out.print("Passenger Count: ");
            int passengerCount = Integer.parseInt(sc.nextLine());

            Reservation r = system.bookFlight(flightNumber, passengerCount);

            if (r == null) {
              System.out.println("Booking failed (flight not found or seats insufficient).");
            } else {
              System.out.println("Booking successful!");
              r.display();
            }
          }

          case 3 -> {
            System.out.print("Location: ");
            String location = sc.nextLine();

            System.out.print("Check-in Date (YYYY-MM-DD): ");
            String checkIn = sc.nextLine();

            System.out.print("Check-out Date (YYYY-MM-DD): ");
            String checkOut = sc.nextLine();

            System.out.print("Guest Count: ");
            int guests = Integer.parseInt(sc.nextLine());

            List<Hotel> results = system.searchHotels(location, checkIn, checkOut, guests);

            if (results.isEmpty()) {
              System.out.println("No hotels found.");
            } else {
              results.forEach(System.out::println);
            }
          }

          case 4 -> {
            System.out.print("Hotel ID: ");
            int hotelId = Integer.parseInt(sc.nextLine());

            Reservation r = system.bookHotel(hotelId);

            if (r == null) {
              System.out.println("Booking failed (hotel not found or already booked).");
            } else {
              System.out.println("Booking successful!");
              r.display();
            }
          }

          case 5 -> system.listReservations();

          case 6 -> {
            System.out.print("Enter Confirmation Number: ");
            String conf = sc.nextLine();

            boolean success = system.cancelReservation(conf);

            if (success) {
              System.out.println("Reservation cancelled.");
            } else {
              System.out.println("Reservation not found.");
            }
          }

          case 0 -> {
            System.out.println("Goodbye!");
            sc.close();
            return;
          }

          default -> System.out.println("Invalid choice.");
        }

      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
      } catch (Exception e) {
        System.out.println("Unexpected error: " + e.getMessage());
      }
    }
  }
}