package Tests;

import hotel.credit.CreditCard;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.ServiceType;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

public class Hotel_Test {

    @Test
    public void Test_Book() {
        Guest mGuest = Mockito.mock(Guest.class);
        CreditCard mCard = Mockito.mock(CreditCard.class);
        Booking mBooking = Mockito.mock(Booking.class);
        Mockito.when(mBooking.getConfirmationNumber()).thenReturn(Long.parseLong("01010001111"));
        Date date = new Date();
        Room mRoom = Mockito.mock(Room.class);
        Mockito.when(mRoom.getId()).thenReturn(111);
        Mockito.when(mRoom.book(mGuest, date, 2, 2, mCard)).thenReturn(mBooking);

        Hotel hotel = new Hotel();
        long number = hotel.book(mRoom, mGuest, date, 2, 2, mCard);
        assertEquals(Long.parseLong("01010001111"), number);
        assertEquals(mBooking, hotel.findBookingByConfirmationNumber(number));
    }
    
    @Test
    public void Test_CheckIn_Exception() {
        Hotel hotel = new Hotel();
        long id = Long.parseLong("01010001111");
        try{
            hotel.checkin(id);
        }
        catch (RuntimeException rte)
        {
            assertEquals(String.format("Booking ID %d does not exist.", id), rte.getMessage());
        }
    }
    
    @Test
    public void Test_CheckIn() {
        Hotel hotel = new Hotel();
        long id = Long.parseLong("01010001111");
        Guest mGuest = Mockito.mock(Guest.class);
        CreditCard mCard = Mockito.mock(CreditCard.class);
        Booking mBooking = Mockito.mock(Booking.class);
        Mockito.when(mBooking.getConfirmationNumber()).thenReturn(Long.parseLong("01010001111"));
        Date date = new Date();
        Room mRoom = Mockito.mock(Room.class);
        Mockito.when(mRoom.getId()).thenReturn(111);
        Mockito.when(mRoom.book(mGuest, date, 2, 2, mCard)).thenReturn(mBooking);
        Mockito.when(mBooking.getRoomId()).thenReturn(111);

        long number = hotel.book(mRoom, mGuest, date, 2, 2, mCard);
        assertEquals(id, number);
        assertEquals(mBooking, hotel.findBookingByConfirmationNumber(number));
        
        hotel.checkin(number);
        assertEquals(mBooking, hotel.findActiveBookingByRoomId(111));
    }
    
    @Test
    public void Test_AddService_Exception() {
        ServiceType service = ServiceType.getServiceTypeByIdentifier("B");
        Hotel hotel = new Hotel();
        int id = 111;
        try{
            hotel.addServiceCharge(id, service, 10.0);
        }
        catch (RuntimeException rte)
        {
            assertEquals("No booking found for the room id 111.", rte.getMessage());
        }
    }
    
    @Test
    public void Test_AddService() {
        ServiceType service = ServiceType.getServiceTypeByIdentifier("B");
        Hotel hotel = new Hotel();        
        Guest mGuest = Mockito.mock(Guest.class);
        CreditCard mCard = Mockito.mock(CreditCard.class);
        Booking mBooking = Mockito.mock(Booking.class);
        Mockito.when(mBooking.getConfirmationNumber()).thenReturn(Long.parseLong("01010001111"));
        Date date = new Date();
        Room mRoom = Mockito.mock(Room.class);
        Mockito.when(mRoom.getId()).thenReturn(111);
        Mockito.when(mRoom.book(mGuest, date, 2, 2, mCard)).thenReturn(mBooking);
        Mockito.when(mBooking.getRoomId()).thenReturn(111);

        long number = hotel.book(mRoom, mGuest, date, 2, 2, mCard);
        assertEquals(Long.parseLong("01010001111"), number);
        assertEquals(mBooking, hotel.findBookingByConfirmationNumber(number));
        
        hotel.checkin(number);
        assertEquals(mBooking, hotel.findActiveBookingByRoomId(111));
        
        hotel.addServiceCharge(111, service, 10.0);
        Mockito.verify(mBooking).addServiceCharge(service, 10.0);
    }
    
    @Test
    public void Test_CheckOut_Exception() {
        Hotel hotel = new Hotel();
        int id = 111;
        try{
            hotel.checkout(id);
        }
        catch (RuntimeException rte)
        {
            assertEquals("No booking found for the room id 111.", rte.getMessage());
        }
    }
    
    @Test
    public void Test_CheckOut() {
        Hotel hotel = new Hotel();
        long id = Long.parseLong("01010001111");
        Guest mGuest = Mockito.mock(Guest.class);
        CreditCard mCard = Mockito.mock(CreditCard.class);
        Booking mBooking = Mockito.mock(Booking.class);
        Mockito.when(mBooking.getConfirmationNumber()).thenReturn(Long.parseLong("01010001111"));
        Date date = new Date();
        Room mRoom = Mockito.mock(Room.class);
        Mockito.when(mRoom.getId()).thenReturn(111);
        Mockito.when(mRoom.book(mGuest, date, 2, 2, mCard)).thenReturn(mBooking);
        Mockito.when(mBooking.getRoomId()).thenReturn(111);

        long number = hotel.book(mRoom, mGuest, date, 2, 2, mCard);
        assertEquals(id, number);
        assertEquals(mBooking, hotel.findBookingByConfirmationNumber(number));
        
        hotel.checkin(number);
        assertEquals(mBooking, hotel.findActiveBookingByRoomId(111));
        
        hotel.checkout(111);
        Mockito.verify(mBooking).checkOut();
        assertNull(hotel.findActiveBookingByRoomId(111));
    }
}
