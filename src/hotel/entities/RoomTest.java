package hotel.entities;

import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class RoomTest {
    public RoomTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//testing book method of class room
    
	@Test
    public void testBookCheckConfNumber() {
        System.out.println("Testing Confirmation Number");
        Guest guest = new Guest("name", "address", 123456);
        Date arrivalDate = new Date();
        int stayLength = 5;
        int numberOfOccupants = 5;
        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 253, 444);
        Room instance = new Room(3, RoomType.SINGLE);
        Booking expResult = new Booking(guest, instance, arrivalDate, stayLength, numberOfOccupants, creditCard);
        Booking result = instance.book(guest, arrivalDate, stayLength, numberOfOccupants, creditCard);
        System.out.println("Expected Confirmation Number = " + expResult.getConfirmationNumber() + " \nResult Confirmation Number = " + result.getConfirmationNumber());
        assertEquals(expResult.getConfirmationNumber(), result.getConfirmationNumber());
    }
    
    @Test
    public void testBookCheckBookingList() {
        System.out.println("Test if booking is added to list by checking Size of List<Booking> bookings.");
        Guest guest = new Guest("name1", "address1", 1123456);
        Date arrivalDate = new Date();
        int stayLength = 5;
        int numberOfOccupants = 5;
        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 253, 444);
        Room instance = new Room(3, RoomType.SINGLE);
        int initialBookingSize = instance.bookings.size();
        Booking expResult = new Booking(guest, instance, arrivalDate, stayLength, numberOfOccupants, creditCard);
        Booking result = instance.book(guest, arrivalDate, stayLength, numberOfOccupants, creditCard);
        int finalBookingSize = instance.bookings.size();
        System.out.println("Initial Booking Size = " + initialBookingSize + ",\nFinal Booking Size = " + finalBookingSize);
        assertEquals(initialBookingSize + 1, finalBookingSize);
//        assertEquals(expResult.isCheckedIn(), result.isCheckedIn());
    }
    
   @Test
    public void testBookCheckIn() {
        System.out.println("Test the checkin status of booking.");
        Guest guest = new Guest("name1", "address1", 1123456);
        Date arrivalDate = new Date();
        int stayLength = 5;
        int numberOfOccupants = 5;
        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 253, 444);
        Room instance = new Room(3, RoomType.SINGLE);
        Booking expResult = new Booking(guest, instance, arrivalDate, stayLength, numberOfOccupants, creditCard);
        Booking result = instance.book(guest, arrivalDate, stayLength, numberOfOccupants, creditCard);
        System.out.println("Expected checkIn = " + expResult.isCheckedIn() + ",\nResult checkIn = " + result.isCheckedIn());
        assertEquals(expResult.isCheckedIn(), result.isCheckedIn());
    }

    
   //testing checkin method of class room
    @Test
    public void testCheckinOccupied() {
        System.out.println("Test if room state is changed after checkin ");
        Room instance = new Room(3, RoomType.SINGLE);
        instance.checkin();
        System.out.println("Expected state =  OCCUPIED \nResult state = " + instance.enumString());
        System.out.println(instance.enumString());
        assertEquals("OCCUPIED", instance.enumString());
    }

    @Test (expected = RuntimeException.class)
    public void testCheckinRuntimeException() {
        System.out.println("Test Runtime Exception when room is already occupied");
        Room instance = new Room(3, RoomType.SINGLE);
        instance.checkin();    
        System.out.println("Expected: Runtime Exception");
        instance.checkin();    
    }

    //testing checkout method of class room

    @Test
    public void testCheckoutState() {
        System.out.println("Test the checkout and the state must be ready after checkout");
        Guest guest = new Guest("name", "address", 123456);
        Date arrivalDate = new Date();
        int stayLength = 5;
        int numberOfOccupants = 5;
        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 558, 444);        
        Room instance = new Room(5, RoomType.DOUBLE);

        Booking booking = instance.book(guest, arrivalDate, stayLength, numberOfOccupants, creditCard);
        booking.checkIn();

        instance.checkout(booking);
        System.out.println("Expected state =  READY \nResult state = " + instance.enumString());
        assertEquals("READY", instance.enumString());
    }


    @Test (expected = RuntimeException.class)
    public void testCheckoutRuntimeException() {
        System.out.println("Test Runtime Exception when room is not occupied");
        Guest guest = new Guest("name", "address", 123456);
        Date arrivalDate = new Date();
        int stayLength = 5;
        int numberOfOccupants = 5;
        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 558, 444);    
        Room instance = new Room(5, RoomType.DOUBLE);

        Booking booking = instance.book(guest, arrivalDate, stayLength, numberOfOccupants, creditCard);
        booking.checkIn();

        instance.checkout(booking);
        System.out.println("Expected output is runtime exception (Room is READY)");
        instance.checkout(booking);        
    }
    
}
