import hotel.core.Hotel;
import hotel.core.HotelCompany;
import hotel.core.Room;

public class main {
    public static void main(String[] args) {
        Room r1 = new Room("1.2","17",
            new Hotel("Caesars Palace", "Fake street 123", new HotelCompany("Fake hotel company")));
        Room r2 = new Room("1.3","18",
            new Hotel("Caesars Palace", "Fake street 123", new HotelCompany("Fake hotel company")));
        if(r1.book())
            System.out.println("Booked room1");
        if(r1.cancel())
            System.out.println("Canceled room1");
        if(r2.book())
            System.out.println("Booked room2");
        if(r2.checkIn())
            System.out.println("CheckIn room2");
        if(r2.checkOut())
            System.out.println("CheckOut room2");
    }
}
