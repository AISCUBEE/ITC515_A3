package hotel.booking;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import hotel.entities.*;
import hotel.booking.BookingCTL;
import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BookingCtlTest {

   

    @Test
    public void creditDetailsTest1() {
    	 int creditCardNumber = 2;
         int ccv = 255;
         String guestname = "Ronaldo";
         String guestAddress = "Portugal";
         int guestPhone = 524875987;
         int occupantNumber = 1;
         CreditCardType type = CreditCardType.MASTERCARD;
         RoomType roomType = RoomType.SINGLE;
         Date arrivalDate = new Date(2018, 11, 01);
         int stayLength = 1;
         double cost = roomType.calculateCost(arrivalDate, stayLength);

         CreditCard creditCard = new CreditCard(type, creditCardNumber, ccv);
         Room room = new Room(1, roomType);
         Guest guest = new Guest(guestname, guestAddress, guestPhone);
         Hotel hotel = new Hotel();
         hotel.addRoom(roomType, 1);
         Booking booking = new Booking(guest, room, arrivalDate, stayLength, occupantNumber, creditCard);

         BookingCTL bookingCTL = new BookingCTL(hotel);
         bookingCTL.phoneNumberEntered(guestPhone);
         bookingCTL.guestDetailsEntered(guestname, guestAddress);
         bookingCTL.roomTypeAndOccupantsEntered(roomType, occupantNumber);
         bookingCTL.bookingTimesEntered(arrivalDate, stayLength);
         bookingCTL.creditDetailsEntered(type, creditCardNumber, ccv);

         assertEquals("COMPLETED", String.valueOf(bookingCTL.getState()));

    }
    


    @Test(expected = RuntimeException.class)
    public void creditDetailsException2() {
        int creditCardNumber = 25;
        int ccv = 568;
        String guestname = "Messi";
        String guestAddress = "Argentina";
        int guestPhone = 789456123;
        int occupantNumber = 1;
        CreditCardType type = CreditCardType.MASTERCARD;
        RoomType roomType = RoomType.SINGLE;
        Date arrivalDate = new Date(2016, 05, 16);
        int stayLength = 1;
        double cost = roomType.calculateCost(arrivalDate, stayLength);

        CreditCard creditCard = new CreditCard(type, creditCardNumber, ccv);
        Room room = new Room(1, roomType);
        Guest guest = new Guest(guestname, guestAddress, guestPhone);
        Hotel hotel = new Hotel();
        hotel.addRoom(roomType, 1);
        Booking booking = new Booking(guest, room, arrivalDate, stayLength, occupantNumber, creditCard);

        BookingCTL bookingCTL = new BookingCTL(hotel);
        bookingCTL.creditDetailsEntered(type, creditCardNumber, ccv);

       assertEquals("COMPLETED", String.valueOf(bookingCTL.getState()));

    }

}
