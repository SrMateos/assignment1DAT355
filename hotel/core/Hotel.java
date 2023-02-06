/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package hotel.core;
import java.util.*;

// line 8 "../../../model.ump"
// line 44 "../../../model.ump"
public class Hotel
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Hotel Attributes
  private String name;
  private String address;

  //Hotel Associations
  private List<Room> rooms;
  private HotelCompany hotelCompany;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hotel(String aName, String aAddress, HotelCompany aHotelCompany)
  {
    name = aName;
    address = aAddress;
    rooms = new ArrayList<Room>();
    boolean didAddHotelCompany = setHotelCompany(aHotelCompany);
    if (!didAddHotelCompany)
    {
      throw new RuntimeException("Unable to create own due to hotelCompany. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  public Room getRoom(int index)
  {
    Room aRoom = rooms.get(index);
    return aRoom;
  }

  public List<Room> getRooms()
  {
    List<Room> newRooms = Collections.unmodifiableList(rooms);
    return newRooms;
  }

  public int numberOfRooms()
  {
    int number = rooms.size();
    return number;
  }

  public boolean hasRooms()
  {
    boolean has = rooms.size() > 0;
    return has;
  }

  public int indexOfRoom(Room aRoom)
  {
    int index = rooms.indexOf(aRoom);
    return index;
  }
  /* Code from template association_GetOne */
  public HotelCompany getHotelCompany()
  {
    return hotelCompany;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRooms()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Room addRoom(String aCostPerDay, String aFloorArea)
  {
    return new Room(aCostPerDay, aFloorArea, this);
  }

  public boolean addRoom(Room aRoom)
  {
    boolean wasAdded = false;
    if (rooms.contains(aRoom)) { return false; }
    Hotel existingHotel = aRoom.getHotel();
    boolean isNewHotel = existingHotel != null && !this.equals(existingHotel);
    if (isNewHotel)
    {
      aRoom.setHotel(this);
    }
    else
    {
      rooms.add(aRoom);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRoom(Room aRoom)
  {
    boolean wasRemoved = false;
    //Unable to remove aRoom, as it must always have a hotel
    if (!this.equals(aRoom.getHotel()))
    {
      rooms.remove(aRoom);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRoomAt(Room aRoom, int index)
  {  
    boolean wasAdded = false;
    if(addRoom(aRoom))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRooms()) { index = numberOfRooms() - 1; }
      rooms.remove(aRoom);
      rooms.add(index, aRoom);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRoomAt(Room aRoom, int index)
  {
    boolean wasAdded = false;
    if(rooms.contains(aRoom))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRooms()) { index = numberOfRooms() - 1; }
      rooms.remove(aRoom);
      rooms.add(index, aRoom);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRoomAt(aRoom, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setHotelCompany(HotelCompany aHotelCompany)
  {
    boolean wasSet = false;
    if (aHotelCompany == null)
    {
      return wasSet;
    }

    HotelCompany existingHotelCompany = hotelCompany;
    hotelCompany = aHotelCompany;
    if (existingHotelCompany != null && !existingHotelCompany.equals(aHotelCompany))
    {
      existingHotelCompany.removeOwn(this);
    }
    hotelCompany.addOwn(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=rooms.size(); i > 0; i--)
    {
      Room aRoom = rooms.get(i - 1);
      aRoom.delete();
    }
    HotelCompany placeholderHotelCompany = hotelCompany;
    this.hotelCompany = null;
    if(placeholderHotelCompany != null)
    {
      placeholderHotelCompany.removeOwn(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hotelCompany = "+(getHotelCompany()!=null?Integer.toHexString(System.identityHashCode(getHotelCompany())):"null");
  }
}