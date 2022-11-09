package view;


import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RestaurantMenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblRestaurantMenu;
	private JButton btnLogIn;
	private JButton LoggedinEmployees;
	private JButton managerZone;
	private JButton btnMakeOrder;
	private JButton btnLogOut;
	private JButton btnViewOrders;
	private JButton btnViewSupplies;
	PropertyChangeSupport propertyChangeHandler;
	private JButton btnAbout;

//////////constructor///////////
	public RestaurantMenuPanel() {
		
		//////////panel initialization/////////////
		setPropertyChangeSupport();
		setBounds(100, 100, 900, 650);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		
		//////////components initialization//////////////
		lblRestaurantMenu = new JLabel("Restaurant Menu",SwingConstants.CENTER);
		lblRestaurantMenu.setBounds(300, 68, 288, 53);
		add(lblRestaurantMenu);
		
		btnLogIn = new JButton("Log in");
		///log in event
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				propertyChangeHandler.firePropertyChange("LoginForm", 0, 1);
			}
		});
		btnLogIn.setBounds(92, 227, 207, 64);
		add(btnLogIn);
		
		LoggedinEmployees = new JButton("Logged in employees");
		LoggedinEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				propertyChangeHandler.firePropertyChange("LoggedInEventView", 0, 1);
			}
		});
		LoggedinEmployees.setBounds(92, 497, 220, 64);
		add(LoggedinEmployees);
		
		managerZone = new JButton("Manager Zone");
		managerZone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				propertyChangeHandler.firePropertyChange("ManagerZone", 0, 1);
			}
		});
		managerZone.setBounds(617, 497, 207, 64);
		add(managerZone);
		
		btnMakeOrder = new JButton("Make order");
		btnMakeOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				propertyChangeHandler.firePropertyChange("ViewOrderFormView", 0, 1);
			}
		});
		btnMakeOrder.setBounds(355, 362, 207, 64);
		add(btnMakeOrder);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				propertyChangeHandler.firePropertyChange("LogoutForm", 0, 1);
			}
		});
		btnLogOut.setBounds(617, 227, 207, 64);
		add(btnLogOut);
		
		btnViewOrders = new JButton("View Orders");
		btnViewOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				propertyChangeHandler.firePropertyChange("ViewPendingOrdersPanelView", 0, 1);
			}
		});
		btnViewOrders.setBounds(92, 362, 207, 64);
		add(btnViewOrders);
		
		btnViewSupplies = new JButton("View Supplies");
		btnViewSupplies.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				propertyChangeHandler.firePropertyChange("ViewSuppliesPanelView", 0, 1);
			}
		});
		btnViewSupplies.setBounds(617, 362, 207, 64);
		add(btnViewSupplies);
		
		btnAbout = new JButton("about");
		btnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				propertyChangeHandler.firePropertyChange("About", 0, 1);
			}
		});
		btnAbout.setBounds(727, 40, 97, 25);
		add(btnAbout);
		
		setFonts();
	}
	
	//set all the fonts for the panel components
	private void setFonts() {
		lblRestaurantMenu.setFont(new Font("Lucida Calligraphy", Font.BOLD, 26));
		btnLogIn.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		LoggedinEmployees.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		managerZone.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		btnMakeOrder.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		btnLogOut.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		btnViewOrders.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		btnViewSupplies.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
}

	////////observer functions app view will listen to this panel/////

	public void setPropertyChangeSupport() {
		propertyChangeHandler = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeHandler.addPropertyChangeListener(listener); }
}
