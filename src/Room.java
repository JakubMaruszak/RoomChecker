import java.io.Serializable;
import java.util.Date;

public class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2177380977063180461L;
	String name;
	boolean single;
	boolean two;
	boolean doublebed;
	boolean add;
	boolean occupied;
	boolean clean;
	boolean balcony;
	Date arrival;
	Date departure=null;
	static int n=100;
	
	public Room(String name, boolean single, boolean two, boolean doublebed, boolean add, boolean balcony) {
		super();
		
		
		this.name = name;
		this.single = single;
		this.two = two;
		this.doublebed = doublebed;
		this.add = add;
		this.balcony=balcony;
	}


	public boolean isClean() {
		return clean;
	}

	public void setClean(boolean clean) {
		this.clean = clean;
	}

	public String getName() {
		return name;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	String describe()
	{
		String description=name;
		if(this.single==true) description +=" single,";
		if(this.two==true) description +=" double,";
		if(this.doublebed==true) description +=" double bed,";
		if(this.add==true) description +=" additional bed,";
		if(this.balcony==true) description +=" balcony, ";
		return description;
		
	}

}
