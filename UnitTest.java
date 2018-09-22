package hotel.entities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;


import org.junit.Test;

public class BookingTest {

	@Test
	public void checkIn() {
		Guest guest = new Guest("sam", "Melbourne", 12);
		RoomType roomType = RoomType.DOUBLE;
		Room room = new Room(1, roomType );
		CreditCardType creditCardType= CreditCardType.MASTERCARD;
		CreditCard creditCard = new CreditCard(creditCardType, 125, 258);
			
		Booking booking = new Booking( guest,  room, 
				 new Date(2019,05,02),  5, 
				 8, 
				 creditCard);
		booking.checkIn();
		assertEquals(true, booking.isCheckedIn());
		
		//assertEquals(true, booking.isPending());
	}
@Test(expected = RuntimeException.class)
public void checkInException() {
	Guest guest = new Guest("Batman", "Sydney", 15);
	RoomType roomType = RoomType.DOUBLE;
	Room room = new Room(1, roomType );
	CreditCardType creditCardType= CreditCardType.MASTERCARD;
	CreditCard creditCard = new CreditCard(creditCardType, 541, 123);
		
	Booking booking = new Booking( guest,  room, new Date(2019,05,02),  4,  7, creditCard);
	booking.checkIn();
	//assertEquals(false, booking.isPending());
	//assertEquals(false, booking.isCheckedIn());
	booking.checkIn();
	
}

@Test(expected = RuntimeException.class)
public void checkOut() {
	Guest guest = new Guest("Superman", "Kathmandu", 14);
	RoomType roomType = RoomType.DOUBLE;
	Room room = new Room(1, roomType );
	CreditCardType creditCardType= CreditCardType.MASTERCARD;
	CreditCard creditCard = new CreditCard(creditCardType, 511, 213);
		
	Booking booking = new Booking( guest,  room, 
			 new Date(2019,05,02),  5, 
			 8, 
			 creditCard);
	
	booking.checkOut();
	assertEquals(true, booking.isCheckedOut());
	
}
@Test
public void checkOut2() {
	Guest guest = new Guest("Ironman", "Nepal", 11);
	RoomType roomType = RoomType.DOUBLE;
	Room room = new Room(1, roomType );
	CreditCardType creditCardType= CreditCardType.MASTERCARD;
	CreditCard creditCard = new CreditCard(creditCardType, 415, 213);
		
	Booking booking = new Booking( guest,  room, 
			 new Date(2019,05,02),  5, 
			 8, 
			 creditCard);
	booking.checkIn();
	booking.checkOut();
	assertEquals(true, booking.isCheckedOut());
	
}
@Test(expected = RuntimeException.class)// for checking exception
public void serviceCharge() {
	ServiceType serviceType = ServiceType.BAR_FRIDGE;
	ServiceCharge serviceCharge = new ServiceCharge(serviceType, 400);
	Guest guest = new Guest("Logan", "Canada", 13);
	RoomType roomType = RoomType.DOUBLE;
	Room room = new Room(1, roomType );
	CreditCardType creditCardType= CreditCardType.MASTERCARD;
	CreditCard creditCard = new CreditCard(creditCardType, 121, 211);
		
	Booking booking = new Booking( guest,  room, 
			 new Date(2019,05,02),  5, 
			 8, 
			 creditCard);
	booking.addServiceCharge(serviceType, 400);
	
			
}

@Test
public void serviceCharge2() {
	
	List<ServiceCharge> charges = new ArrayList<>();
	ServiceType serviceType = ServiceType.BAR_FRIDGE;
	ServiceCharge serviceCharge = new ServiceCharge(serviceType, 400);
	charges.add(serviceCharge);
	
	Guest guest = new Guest("Aquaman", "Newyork", 16);
	RoomType roomType = RoomType.DOUBLE;
	Room room = new Room(1, roomType );
	CreditCardType creditCardType= CreditCardType.MASTERCARD;
	CreditCard creditCard = new CreditCard(creditCardType, 310, 511);
	Booking booking = new Booking( guest,  room, 
			 new Date(2019,05,02),  5, 
			 8, 
			 creditCard);
	booking.checkIn();
	
	booking.addServiceCharge(serviceType, 400);
	List<ServiceCharge> bookingServiceCharge = booking.getCharges();
	assertEquals(bookingServiceCharge, charges);
	
	
			
}


}
