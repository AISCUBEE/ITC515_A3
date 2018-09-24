package hotel.checkin;

import hotel.credit.CreditCard;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.utils.IOUtils;
import hotel.checkin;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

 public class TestRunner {
     
    static JUnitCore junitCore;
   
   public static void main(String[] args) {
       
        
        
         System.out.println("Running Junit Test.");
         Result result = JUnitCore.runClasses(HotelTest1.class);
         System.out.println("[Test Executed]: " + result.getRunCount());
         System.out.println("[Test result]: " + result.wasSuccessful());
         for (Failure failure : result.getFailures()) {
             System.out.println(failure.toString());
         }
     }
}  	
@ Test_CheckinCLT

public class CheckinCTLTest {
    
    Room room = new Room(155, RoomType.DOUBLE);
    
    Guest guest = new Guest("Shyam", "some address",12349786);
    Date arrivalDate = new Date();
     int stayLength = 3;
    int occupantNumber = 3;
    CreditCard creditCard = new CreditCard(CreditCardType.MASTERCARD, 123456789, 236);
    Hotel hotel = new Hotel();
    long confirmationNumber = hotel.book(room, guest,arrivalDate, stayLength, occupantNumber,creditCard);
    ServiceType serviceType = ServiceType.ROOM_SERVICE;
//    long confirmationNumber = hotel.book(room, guest,arrivalDate, stayLength, occupantNumber,creditCard);
    Booking booking = new Booking(guest,room, arrivalDate,stayLength,occupantNumber, creditCard);
    static Booking bookingValue;
    Booking booked = new Booking(guest,room, arrivalDate,stayLength,occupantNumber, creditCard);;
    double cost = 20.0;
    ServiceCharge serviceCharge = new ServiceCharge(serviceType, cost);

}

@Test
    public void testCheckInConfirmed() {
        System.out.println("checkInConfirmed");
        boolean confirmed = true;
        
        CheckinCTL instance = new CheckinCTL(hotel);
        
        
        instance.confirmationNumberEntered(confirmationNumber);
        
        
        instance.checkInConfirmed(confirmed);
	}
	
	@Test
    //this is to check if the state is set to cancelled if the confirmed is set to false
    //the state is set to cancelled if the boolean is false.
    public void testCheckInConfirmed1() {
        System.out.println("checkInConfirmed for false statement");
        boolean confirmed = false;
        
        CheckinCTL instance = new CheckinCTL(hotel);
        
        
        instance.confirmationNumberEntered(confirmationNumber);
        
        
        instance.checkInConfirmed(confirmed);
	}
	
	 @Test(expected = RuntimeException.class)
    
    public void testCheckInConfirmed3() {
        System.out.println("checkInConfirmed:Check in for null point exception");
        boolean confirmed = true;
        
        CheckinCTL instance = new CheckinCTL(hotel);
      
        instance.checkInConfirmed(confirmed);
        
        
        
    }
	
	