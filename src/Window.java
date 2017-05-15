import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JPanel roomPanel;

	Window() {
		super();
		roomPanel = new JPanel();
		JPanel buttons = new JPanel();
		JPanel mainPanel = new JPanel();
		RoomDOA.addRooms();
		loadButtons();
		roomPanel.setVisible(true);
		roomPanel.setLayout(new GridLayout(3, 10));
		roomPanel.setPreferredSize(new Dimension(Main.WIDTH*4/5, Main.HEIGHT*4/5));
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(roomPanel);
		mainPanel.add(buttons);
		JButton rentfew = new CIn();
		JButton releasefew = new COut();
		JButton save = new Save();
		JButton load = new Load();
		JButton cleanall = new CleanAll();
		buttons.add(rentfew);
		buttons.add(releasefew);
		buttons.add(save);
		buttons.add(load);
		buttons.add(cleanall);

		mainPanel.setAlignmentY(BOTTOM_ALIGNMENT);
		this.add(mainPanel);
		
		RoomDOA.readFromFile();
		loadButtons();
		
	}

	

	static void loadButtons() {
		roomPanel.removeAll();
		for (int i = 0; i < RoomDOA.finalsize; i++) {
			roomPanel.add(new RoomButton(RoomDOA.rooms.get(i)));
		}
		roomPanel.revalidate();
	}

	public class Save extends JButton implements ActionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		Save() {
			super();
			this.setText("Save");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent arg0) {
			RoomDOA.writeToFile();

		}

	}

	public class Load extends JButton implements ActionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		Load() {
			super();
			this.setText("Load");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent arg0) {
			RoomDOA.readFromFile();

		}

	}

	public class CleanAll extends JButton implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		CleanAll() {
			super();
			addActionListener(this);
			this.setText("Clean All");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			RoomDOA.setAllClean();

		}
	}

	public class CIn extends JButton implements ActionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		CIn() {

			super();

			addActionListener(this);
			this.setText("Check in few");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			new CheckIn();

		}
	}

	public class COut extends JButton implements ActionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		COut() {
			super();
			this.setText("Check out few");
			addActionListener(this);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			new CheckOut();

		}

	}
}