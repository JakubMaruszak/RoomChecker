import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;


public class CheckOut extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CheckBox checkbox;
	JDatePanelImpl panimp;
	ArrayList<String> selected = new ArrayList<>();

	CheckOut() {
		super();
		JPanel roompanel = new JPanel();
		JPanel mainpanel = new JPanel();
		JPanel buttonpanel = new JPanel();
		JButton button = new CheckButton();

		for (int i = 0; i < RoomDOA.finalsize; i++)
		{
			checkbox = new CheckBox(RoomDOA.rooms.get(i).describe());
			if (RoomDOA.rooms.get(i).isOccupied() == false) {
				checkbox.setEnabled(false);
				}
			roompanel.add(checkbox);

		}

		roompanel.add(checkbox);
		roompanel.setLayout(new GridLayout(30, 1));
		buttonpanel.add(button);
		mainpanel.add(roompanel);
		mainpanel.add(buttonpanel);

		this.add(mainpanel);
		this.pack();
		this.setVisible(true);
		this.setPreferredSize(new Dimension(600, 600));
	}

	class CheckBox extends JCheckBox implements ItemListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		CheckBox(String name) {
			super();
			this.setText(name);
			addItemListener(this);

		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox source = (JCheckBox) e.getItemSelectable();
			if (source.isSelected()) {
				selected.add("+" + source.getText().substring(0, 3));
			}
			if (source.isSelected() == false) {
				selected.add("-" + source.getText().substring(0, 3));
			}
		}
	}

	public class CheckButton extends JButton implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		CheckButton() {
			super();
			addActionListener(this);
			this.setText("Check");
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			for (String g : selected) {
				if (g.contains("+")) {
					for (int i = 0; i < RoomDOA.finalsize; i++) {
						if (RoomDOA.rooms.get(i).getName().equals(g.substring(1))) {
							RoomDOA.rooms.get(i).setOccupied(false);
							RoomDOA.rooms.get(i).setArrival(null);
							RoomDOA.rooms.get(i).setDeparture(null);
						}
					}
				}
			}

			Window.loadButtons();
			dispose();

		}
	}

}
