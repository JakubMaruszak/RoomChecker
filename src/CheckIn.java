import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class CheckIn extends JFrame  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CheckBox checkbox;
	JDatePanelImpl panimp;
	ArrayList<String> selected = new ArrayList<>();
	CheckIn() {
		super();
		JPanel roompanel = new JPanel();
		JPanel mainpanel = new JPanel();
		JPanel buttonpanel = new JPanel();
		JButton button = new CheckButton();
		
		for (int i=0; i<RoomDOA.finalsize; i++)
		{
			checkbox = new CheckBox(RoomDOA.rooms.get(i).describe());
			if (RoomDOA.rooms.get(i).isOccupied() == true) {
				checkbox.setEnabled(false);
			}
			roompanel.add(checkbox);

		}
		
		
		UtilDateModel model = new UtilDateModel(new Date());
		Properties p = new Properties();
		p.put("this.day", "Today");
		 panimp = new JDatePanelImpl(model, p);
		JDatePickerImpl pickimp = new JDatePickerImpl(panimp, new DateLabelFormatter());
		
		roompanel.add(checkbox);
		roompanel.setLayout(new GridLayout(30, 1));
		buttonpanel.add(pickimp);
		buttonpanel.add(button);
		mainpanel.add(roompanel);
		mainpanel.add(buttonpanel);
		
		this.add(mainpanel);
		this.pack();
		this.setVisible(true);
		this.setPreferredSize(new Dimension(600,600));
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
	
	class CheckBox extends JCheckBox implements ItemListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		CheckBox(String name)
		{
			super();
			this.setText(name);
			addItemListener(this);
			
			
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox source = (JCheckBox) e.getItemSelectable();
			if(source.isSelected())
			{
				selected.add("+"+source.getText().substring(0, 3));
			}
			if(source.isSelected()==false)
			{
				selected.add("-"+source.getText().substring(0, 3));
			}
		}
	}
	public class CheckButton extends JButton implements ActionListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		CheckButton()
		{
			super();
			addActionListener(this);
			this.setText("Check");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			for(String g:selected)
			{
				if(g.contains("+"))
				{
					for(int i=0; i<RoomDOA.finalsize; i++)
					{
						if(RoomDOA.rooms.get(i).getName().equals(g.substring(1)))
						{					
							RoomDOA.rooms.get(i).setOccupied(true);
							RoomDOA.rooms.get(i).setArrival(new Date());
							RoomDOA.rooms.get(i).setDeparture((Date) panimp.getModel().getValue());
						}
					}
				}
			}
			
			Window.loadButtons();
			dispose();
			
			
			
		}
	}

	
}
