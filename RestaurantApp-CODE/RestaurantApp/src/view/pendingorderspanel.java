package view;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import java.awt.Font;

public class PendingOrdersPanel extends JPanel{

	//////////panel components////////////
	private static final long serialVersionUID = 1L;
	private OrdersTablePanel ordersTable;
	private JButton btnBack;
	private JLabel lblViewOrder;
	private JButton btnCompleteOrder;
	PropertyChangeSupport propertyChangeHandler;
	////////////constructor//////////////
	public PendingOrdersPanel() {
		
		//////////////panel initialization//////////
		setPropertyChangeSupport();
		setBounds(100, 100, 900, 650);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

	}
	
	//set all the fonts for the panel components
	private void setFonts() {
		btnBack.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		lblViewOrder.setFont(new Font("Lucida Calligraphy", Font.BOLD, 26));
		btnCompleteOrder.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		
	}

	public void initPendingOrdersPanel(ArrayList<String> orderList) {

		removeAll();
		/////////////components initialization/////////////
		btnBack = new JButton("Back ->");
		btnBack.setBounds(665, 552, 116, 35);
		btnBack.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			propertyChangeHandler.firePropertyChange("BackToMenu",0, 1);
			ordersTable.clearFields();
			
		}
		});
		
		add(btnBack);
		
		lblViewOrder = new JLabel("view orders");
		lblViewOrder.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblViewOrder.setBounds(360, 46, 176, 35);
		add(lblViewOrder);
		
		ordersTable=new OrdersTablePanel(686,341);
		ordersTable.setBounds(103, 119, 686, 341);
		ordersTable.addColumnToTable("Order id");
		ordersTable.addColumnToTable("Costumer Name");
		ordersTable.addColumnToTable("Price");
		ordersTable.setTable(orderList);
		add(ordersTable);
		
		btnCompleteOrder = new JButton("Complete Order");
		btnCompleteOrder.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			//ordersTable.getTable();////////////////////////////////todo
			propertyChangeHandler.firePropertyChange("CompleteOrderEventView", 0, 1);
			ordersTable.clearFields();
		}
		
		
		});
		btnCompleteOrder.setBounds(360, 503, 197, 40);
		add(btnCompleteOrder);
		
		setFonts();
	}

	
	////////observer functions app view will listen to this panel/////
	public void setPropertyChangeSupport() {
		propertyChangeHandler = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeHandler.addPropertyChangeListener(listener); }
}
