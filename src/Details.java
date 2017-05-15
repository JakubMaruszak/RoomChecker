import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class Details extends JFrame {

	private static final long serialVersionUID = 1L;
	UtilDateModel model;
	JDatePanelImpl datepanel;
	JDatePickerImpl datepicker;
	Date rawdate;

	Details(Room room) {
		super();
		JPanel detailPanel = new JPanel();
		JButton changeOccupation = new Book(room);
		detailPanel.add(new JLabel(room.describe()));
		detailPanel.add(changeOccupation);

		
		if (room.isOccupied())
			changeOccupation.setText("Check out");
		else {
			changeOccupation.setText("Book now");

			model = new UtilDateModel(new Date());

			Properties props = new Properties();
			props.put("text.today", "Today");

			datepanel = new JDatePanelImpl(model, props);
			datepicker = new JDatePickerImpl(datepanel, new DateLabelFormatter());
			
			detailPanel.setLayout(new FlowLayout());
			detailPanel.add(datepicker);
		}
		
		
		this.add(detailPanel);
		// detailPanel.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setPreferredSize(new Dimension(300, 300));
		this.pack();
	}

	public class DateLabelFormatter extends AbstractFormatter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}

			return "";
		}

	}

	public class Book extends JButton implements ActionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Room room;

		Book(Room room) {
			super();
			addActionListener(this);
			this.room = room;

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (room.isOccupied()) {
				room.setOccupied(false);
				room.setDeparture(null);
			} else {

				
			 rawdate = (Date) datepicker.getModel().getValue();	
					
				
				
				room.setOccupied(true);
				room.setDeparture(rawdate);
				room.setArrival(new Date());

			}
			RoomDOA.writeToFile();
			Window.loadButtons();
			
			dispose();

		}

	}

}
