/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package hotel.core;
import java.util.*;

// line 25 "../../../model.ump"
// line 54 "../../../model.ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;
  private String address;

  //Person Associations
  private List<Booking> bookings;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName, String aAddress)
  {
    name = aName;
    address = aAddress;
    bookings = new ArrayList<Booking>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }
  /* Code from template association_GetMany */
  public Booking getBooking(int index)
  {
    Booking aBooking = bookings.get(index);
    return aBooking;
  }

  public List<Booking> getBookings()
  {
    List<Booking> newBookings = Collections.unmodifiableList(bookings);
    return newBookings;
  }

  public int numberOfBookings()
  {
    int number = bookings.size();
    return number;
  }

  public boolean hasBookings()
  {
    boolean has = bookings.size() > 0;
    return has;
  }

  public int indexOfBooking(Booking aBooking)
  {
    int index = bookings.indexOf(aBooking);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookings()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Booking addBooking(String aStartDate, String aEndDate, String aStartTime, String aEndTime, String aBedroomsRequired, String aCreditCardToBill)
  {
    return new Booking(aStartDate, aEndDate, aStartTime, aEndTime, aBedroomsRequired, aCreditCardToBill, this);
  }

  public boolean addBooking(Booking aBooking)
  {
    boolean wasAdded = false;
    if (bookings.contains(aBooking)) { return false; }
    Person existingPerson = aBooking.getPerson();
    boolean isNewPerson = existingPerson != null && !this.equals(existingPerson);
    if (isNewPerson)
    {
      aBooking.setPerson(this);
    }
    else
    {
      bookings.add(aBooking);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBooking(Booking aBooking)
  {
    boolean wasRemoved = false;
    //Unable to remove aBooking, as it must always have a person
    if (!this.equals(aBooking.getPerson()))
    {
      bookings.remove(aBooking);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBookingAt(Booking aBooking, int index)
  {  
    boolean wasAdded = false;
    if(addBooking(aBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
      bookings.remove(aBooking);
      bookings.add(index, aBooking);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBookingAt(Booking aBooking, int index)
  {
    boolean wasAdded = false;
    if(bookings.contains(aBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
      bookings.remove(aBooking);
      bookings.add(index, aBooking);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBookingAt(aBooking, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=bookings.size(); i > 0; i--)
    {
      Booking aBooking = bookings.get(i - 1);
      aBooking.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "]";
  }
}