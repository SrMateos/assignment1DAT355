/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package hotel.core;
import java.util.*;

// line 14 "../../../model.ump"
// line 49 "../../../model.ump"
public class Booking
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Booking Attributes
  private String startDate;
  private String endDate;
  private String startTime;
  private String endTime;
  private String bedroomsRequired;
  private String creditCardToBill;

  //Booking Associations
  private List<Room> rooms;
  private Person person;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Booking(String aStartDate, String aEndDate, String aStartTime, String aEndTime, String aBedroomsRequired, String aCreditCardToBill, Person aPerson)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    startTime = aStartTime;
    endTime = aEndTime;
    bedroomsRequired = aBedroomsRequired;
    creditCardToBill = aCreditCardToBill;
    rooms = new ArrayList<Room>();
    boolean didAddPerson = setPerson(aPerson);
    if (!didAddPerson)
    {
      throw new RuntimeException("Unable to create booking due to person. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(String aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(String aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(String aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(String aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setBedroomsRequired(String aBedroomsRequired)
  {
    boolean wasSet = false;
    bedroomsRequired = aBedroomsRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setCreditCardToBill(String aCreditCardToBill)
  {
    boolean wasSet = false;
    creditCardToBill = aCreditCardToBill;
    wasSet = true;
    return wasSet;
  }

  public String getStartDate()
  {
    return startDate;
  }

  public String getEndDate()
  {
    return endDate;
  }

  public String getStartTime()
  {
    return startTime;
  }

  public String getEndTime()
  {
    return endTime;
  }

  public String getBedroomsRequired()
  {
    return bedroomsRequired;
  }

  public String getCreditCardToBill()
  {
    return creditCardToBill;
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
  public Person getPerson()
  {
    return person;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRooms()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addRoom(Room aRoom)
  {
    boolean wasAdded = false;
    if (rooms.contains(aRoom)) { return false; }
    rooms.add(aRoom);
    if (aRoom.indexOfBooking(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRoom.addBooking(this);
      if (!wasAdded)
      {
        rooms.remove(aRoom);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeRoom(Room aRoom)
  {
    boolean wasRemoved = false;
    if (!rooms.contains(aRoom))
    {
      return wasRemoved;
    }

    int oldIndex = rooms.indexOf(aRoom);
    rooms.remove(oldIndex);
    if (aRoom.indexOfBooking(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRoom.removeBooking(this);
      if (!wasRemoved)
      {
        rooms.add(oldIndex,aRoom);
      }
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
  public boolean setPerson(Person aPerson)
  {
    boolean wasSet = false;
    if (aPerson == null)
    {
      return wasSet;
    }

    Person existingPerson = person;
    person = aPerson;
    if (existingPerson != null && !existingPerson.equals(aPerson))
    {
      existingPerson.removeBooking(this);
    }
    person.addBooking(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Room> copyOfRooms = new ArrayList<Room>(rooms);
    rooms.clear();
    for(Room aRoom : copyOfRooms)
    {
      aRoom.removeBooking(this);
    }
    Person placeholderPerson = person;
    this.person = null;
    if(placeholderPerson != null)
    {
      placeholderPerson.removeBooking(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "startDate" + ":" + getStartDate()+ "," +
            "endDate" + ":" + getEndDate()+ "," +
            "startTime" + ":" + getStartTime()+ "," +
            "endTime" + ":" + getEndTime()+ "," +
            "bedroomsRequired" + ":" + getBedroomsRequired()+ "," +
            "creditCardToBill" + ":" + getCreditCardToBill()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "person = "+(getPerson()!=null?Integer.toHexString(System.identityHashCode(getPerson())):"null");
  }
}