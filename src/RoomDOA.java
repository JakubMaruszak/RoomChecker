import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class RoomDOA {
	static int finalsize;
	
	static ArrayList<Room> rooms = new ArrayList<Room>();
	
	
static void addRooms()
{
	
		rooms.add(new Room("101", false, true, true, true, true));
		rooms.add(new Room("102", false, true, true, true, true));
		rooms.add(new Room("103", false, false, true, false, true));
		rooms.add(new Room("104", true, false, false, false, true));
		rooms.add(new Room("105", false, false, true, false, true));
		rooms.add(new Room("106", false, true, true, false, false));
		rooms.add(new Room("107", true, false, true, false, false));
		rooms.add(new Room("108", false, true, true, false, false));
		rooms.add(new Room("109", true, false, false, false, false));
		rooms.add(new Room("110", false, true, true, false, true));
		rooms.add(new Room("202", false, true, true, true, true));
		rooms.add(new Room("204", false, true, true, true, true));
		rooms.add(new Room("206", false, true, true, false, true));
		rooms.add(new Room("208", true, false, false, false, true));
		rooms.add(new Room("210", false, true, true, false, true));
		rooms.add(new Room("212", false, true, true, false, true));
		rooms.add(new Room("214", false, true, true, false, true));
		rooms.add(new Room("216", false, false, true, true, true));
		rooms.add(new Room("218", false, true, false, false, true));
		rooms.add(new Room("220", true, false, true, false, false));
		rooms.add(new Room("222", false, true, true, false, false));
		rooms.add(new Room("224", false, true, true, false, false));
		rooms.add(new Room("226", true, false, true, false, false));
		rooms.add(new Room("303", false, true, true, false, true));
		rooms.add(new Room("306", false, true, true, true, true));
		rooms.add(new Room("309", false, true, true, true, true));
		rooms.add(new Room("312", true, false, false, false, true));
		rooms.add(new Room("315", false, true, true, false, true));
		rooms.add(new Room("318", false, true, true, false, true));
		rooms.add(new Room("321", false, true, true, false, true));
		rooms.add(new Room("324", false, false, true, true, true));
		rooms.add(new Room("327", false, true, false, false, true));
		rooms.add(new Room("330", true, false, true, false, false));
		rooms.add(new Room("333", false, true, true, false, false));
		rooms.add(new Room("336", false, true, true, false, false));
		rooms.add(new Room("339", true, false, true, false, false));
		RoomDOA.finalsize=rooms.size();
	
		
}


static void writeToFile()
{
	
	try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("db.db")))
	{
		for(int i=0; i<RoomDOA.finalsize; i++)
		{
			out.writeObject(rooms.get(i));
		}
		
		out.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}

static void readFromFile()
{
	try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("db.db")))
	{
		for(int i=0; i<RoomDOA.finalsize; i++)
		{
			rooms.add(i, (Room) in.readObject());
		}
		in.close();
	} catch (FileNotFoundException e) {
		new File("db.db");
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();	
	}
}
static void setAllClean()
{
	for(int i=0; i<finalsize; i++)
	{
		rooms.get(i).setClean(true);
	}
}

}