/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package hotel.core;
import java.util.*;

// line 3 "../../../model.ump"
// line 39 "../../../model.ump"
public class HotelCompany
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HotelCompany Attributes
  private String name;

  //HotelCompany Associations
  private List<Hotel> owns;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HotelCompany(String aName)
  {
    name = aName;
    owns = new ArrayList<Hotel>();
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

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetMany */
  public Hotel getOwn(int index)
  {
    Hotel aOwn = owns.get(index);
    return aOwn;
  }

  public List<Hotel> getOwns()
  {
    List<Hotel> newOwns = Collections.unmodifiableList(owns);
    return newOwns;
  }

  public int numberOfOwns()
  {
    int number = owns.size();
    return number;
  }

  public boolean hasOwns()
  {
    boolean has = owns.size() > 0;
    return has;
  }

  public int indexOfOwn(Hotel aOwn)
  {
    int index = owns.indexOf(aOwn);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOwns()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Hotel addOwn(String aName, String aAddress)
  {
    return new Hotel(aName, aAddress, this);
  }

  public boolean addOwn(Hotel aOwn)
  {
    boolean wasAdded = false;
    if (owns.contains(aOwn)) { return false; }
    HotelCompany existingHotelCompany = aOwn.getHotelCompany();
    boolean isNewHotelCompany = existingHotelCompany != null && !this.equals(existingHotelCompany);
    if (isNewHotelCompany)
    {
      aOwn.setHotelCompany(this);
    }
    else
    {
      owns.add(aOwn);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOwn(Hotel aOwn)
  {
    boolean wasRemoved = false;
    //Unable to remove aOwn, as it must always have a hotelCompany
    if (!this.equals(aOwn.getHotelCompany()))
    {
      owns.remove(aOwn);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOwnAt(Hotel aOwn, int index)
  {  
    boolean wasAdded = false;
    if(addOwn(aOwn))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwns()) { index = numberOfOwns() - 1; }
      owns.remove(aOwn);
      owns.add(index, aOwn);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOwnAt(Hotel aOwn, int index)
  {
    boolean wasAdded = false;
    if(owns.contains(aOwn))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwns()) { index = numberOfOwns() - 1; }
      owns.remove(aOwn);
      owns.add(index, aOwn);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOwnAt(aOwn, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=owns.size(); i > 0; i--)
    {
      Hotel aOwn = owns.get(i - 1);
      aOwn.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}