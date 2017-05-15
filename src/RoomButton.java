import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;

public class RoomButton extends JButton implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Room room;
	SimpleDateFormat simple = new SimpleDateFormat("dd MM");
	
	
	public RoomButton(Room room) {
		super();
		this.room = room;
		this.setFocusable(false);
		this.setToolTipText(room.describe());
		
		
		if (room.isOccupied()&&countDays(room.departure)<0)
			{
			this.setText("<html><center><font size=8>"+room.getName()+"</font></center></html>");
			this.setBackground(Color.BLUE);
			}
		else if (room.isOccupied()) 
			{
			this.setBackground(Color.RED);
			this.setText("<html><center><font size=8>"+room.getName()+"</font>"+"<br>"+ "<font size=4>"+simple.format(room.getDeparture())+"</font>" +"<br>"+ "<font size=3>"+  countDays(room.departure)  +"</font></center></html>");
			if(prepareSheets(countDays(room.departure))) this.setBackground(Color.ORANGE);;
			if(countDays(room.departure)==0) this.setBackground(Color.white);
			}
		else 
			{
			this.setText("<html><center><font size=8>"+room.getName()+"</font></center></html>");
			this.setBackground(Color.GREEN);
			}
	
	/*
	 * Zielony - Wolne
	 * Czerwony - Zajete
	 * Biale - dzisiaj wyjezdzaja
	 * Pomaraonczowy - Przygotuj posciel, jutro wyjezdzaja
	 * Niebieski - Cos jest nie tak//nieodznaczono
	 * 
	 */
		
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new Details(room);
		
		
		
		
	}
	
	boolean prepareSheets(int d)
	{
		if (d==1) return true;
		else return false;
		
	}
	
	int countDays(Date departure) 
	{
		int days = -1;
		
		SimpleDateFormat  sdf = new SimpleDateFormat("dd MM yyyy");
		try {
			Date date1 = sdf.parse(sdf.format(new Date()));
			Date date2 = departure;
			long diff = date2.getTime() - date1.getTime();
			days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return days;
	}
	
	

}
