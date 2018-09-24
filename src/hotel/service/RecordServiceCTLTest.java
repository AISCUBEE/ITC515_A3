package hotel.service;

import hotel.checkin.CheckinCTL;
import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.RoomType;
import hotel.entities.ServiceType;
import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.*;


public class RecordServiceCTLTest {
	
	
    @Test(expected = RuntimeException.class)
    public void testServiceDetailsEnteredException() {
        System.out.println("Test Runtime Exception  if state is not SERVICE.");
        ServiceType serviceType = ServiceType.ROOM_SERVICE;
        double cost = 20.0;
        Hotel hotel = new Hotel();
        RecordServiceCTL instance = new RecordServiceCTL(hotel);
        System.out.println("Present State: " + instance.getState());
        instance.serviceDetailsEntered(serviceType, cost);
    }
    
    
    @Test
    public void testServiceDetailsEnteredState() {
    	System.out.println("Test state of RecordServiceCTL after method call.");
        ServiceType serviceType = ServiceType.ROOM_SERVICE;
        double cost = 20.0;
        Hotel hotel = new Hotel();
        RecordServiceCTL instance = new RecordServiceCTL(hotel);


        Guest guest = new Guest("name1", "address1", 123456);
        Date arrivalDate = new Date();
        int stayLength = 5;
        int numberOfOccupants = 5;
        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 5, 7);
        Room room = new Room(1, RoomType.SINGLE);
        Booking booking = room.book(guest, arrivalDate, stayLength, numberOfOccupants, creditCard);
      
        hotel.book(room, guest, arrivalDate, 2, 1, creditCard);

        long confNumber = booking.getConfirmationNumber();

        CheckinCTL checkin = new CheckinCTL(hotel);
     
        checkin.confirmationNumberEntered(confNumber);
        checkin.checkInConfirmed(true);
        instance.roomNumberEntered(room.getId());


        System.out.println("State before method call: " + instance.getState());
        
        instance.serviceDetailsEntered(serviceType, cost);
        System.out.println("serviceDetailsEntered(serviceType, cost) method call success");
        System.out.println("State after method call: " + instance.getState());
        assertEquals("COMPLETED", instance.getState());
    }
}

