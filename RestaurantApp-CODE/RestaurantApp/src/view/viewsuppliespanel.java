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

public class ViewSuppliesPanel extends JPanel{

	//////////panel components////////////
	private static final long serialVersionUID = 1L;
	private ProductsTable productsTable;
	private JButton btnBack;
	private JLabel lblViewSupplies;
	private JButton btnOrderSupplies;
	PropertyChangeSupport propertyChangeHandler;
	////////////constructor//////////////
	public ViewSuppliesPanel() {
		//////////////panel initialization//////////
		setPropertyChangeSupport();
		setBounds(100, 100, 900, 650);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		
		
	}
	//set all the fonts for the panel components
	private void setFonts() {
		btnBack.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		lblViewSupplies.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 26));
		btnOrderSupplies.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		
	}

	public void initSuppliesPanel(ArrayList<String> products) {
		removeAll();
		/////////////components initialization/////////////
	btnBack = new JButton("Back ->");
	btnBack.setBounds(665, 552, 116, 35);
	btnBack.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			propertyChangeHandler.firePropertyChange("BackToMenu",0, 1);
			productsTable.clearFields();
			
		}
	});
	add(btnBack);
	
	lblViewSupplies = new JLabel("view supplies");
	lblViewSupplies.setFont(new Font("Tahoma", Font.PLAIN, 22));
	lblViewSupplies.setBounds(337, 45, 205, 35);
	add(lblViewSupplies);
	
	productsTable=new ProductsTable(686,341);
	productsTable.setBounds(107, 114, 686, 341);
	productsTable.addColumnToTable("Product Name");
	productsTable.addColumnToTable("Quantity");
	add(productsTable);
	
	btnOrderSupplies = new JButton("Order Supplies");
	btnOrderSupplies.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			propertyChangeHandler.firePropertyChange("SupplyUpdateEventView",0,1);
		}
	});
	btnOrderSupplies.setBounds(363, 486, 185, 40);
	add(btnOrderSupplies);
	
	setFonts();
	initProductsTable(products);
	}
	
	//insert to products table all the products from the array list that sent by model
	public void initProductsTable(ArrayList<String> products) {
		for(int i =0 ; i<products.size();i+=2) {
			productsTable.addItem(products.get(i), products.get(i+1));
		}
	}
	
	////////observer functions app view will listen to this panel/////
	public void setPropertyChangeSupport() {
		propertyChangeHandler = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeHandler.addPropertyChangeListener(listener); }
}
