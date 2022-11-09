package view;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OrderPanel extends JPanel{

	//////////panel components////////////
	private JComboBox comboBoxProduct;
	private static final long serialVersionUID = 1L;
	private ProductsTable menuTable;
	private JTextField quantityField;
	PropertyChangeSupport propertyChangeHandler;
	private JTextField costumerNameField;
	private JButton btnBack;
	private JLabel lblMakeOrder;
	private JLabel lblProduct;
	private JLabel lblQuantity;
	private JLabel lblChooseProductFrom;
	private JButton btnAdd;
	private JButton btnMakeOrder;
	private JLabel lblCostumerName;
	////////////constructor//////////////
	public OrderPanel() {
		
		//////////////panel initialization//////////
		setLayout(null);
		setPropertyChangeSupport();
		setBounds(100, 100, 900, 650);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		//////////////////////////////////////////////////////////////
	
		
	}
	//set all the fonts for the panel components
	private void setFonts() {
		btnBack.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		lblMakeOrder.setFont(new Font("Lucida Calligraphy", Font.BOLD, 26));
		lblProduct.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblQuantity.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblChooseProductFrom.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		btnAdd.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		btnMakeOrder.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		lblCostumerName.setFont(new Font("Thaoma", Font.PLAIN, 16));
		
	}

	///initializa panel components
	public void initOrderPanel(ArrayList<String> menuItems) {
		removeAll(); //delets all the previous panel components
		String[] menuItemsArray = new String[menuItems.size()];
		menuItemsArray = menuItems.toArray(menuItemsArray);
		comboBoxProduct = new JComboBox();
		comboBoxProduct.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxProduct.setBounds(191, 246, 129, 22);
		comboBoxProduct.setModel(new DefaultComboBoxModel(menuItemsArray));
		add(comboBoxProduct);
		
		/////////////components initialization/////////////
		btnBack = new JButton("Back ->");
		btnBack.setBounds(665, 552, 116, 35);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				propertyChangeHandler.firePropertyChange("BackToMenu",0, 1);
				menuTable.clearFields();
				clearFields();
			}
		});
		add(btnBack);
		
		lblMakeOrder = new JLabel("make order");
		lblMakeOrder.setBounds(335, 33, 248, 37);
		add(lblMakeOrder);
		menuTable=new ProductsTable(400,100);
		menuTable.setBounds(227, 305, 412, 141);
		menuTable.addColumnToTable("Product");
		menuTable.addColumnToTable("Quantity");
		add(menuTable);
		quantityField = new JTextField();
		quantityField.setBounds(418, 248, 116, 22);
		add(quantityField);
		quantityField.setColumns(10);
		
		lblProduct = new JLabel("Product");
		lblProduct.setBounds(191, 217, 56, 16);
		add(lblProduct);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(417, 219, 70, 16);
		add(lblQuantity);
		
		lblChooseProductFrom = new JLabel("Choose product from menu and enter its quantity then press add");
		lblChooseProductFrom.setBounds(176, 95, 567, 29);
		add(lblChooseProductFrom);
		
		
		btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(quantityField.getText().equals("")) {
					propertyChangeHandler.firePropertyChange("EmptyFields",getCostumerName(),menuTable.getTable());
				}
				else {
					menuTable.addItem(comboBoxProduct.getSelectedItem().toString(), quantityField.getText());
				}
			}
		});
		btnAdd.setBounds(621, 247, 97, 25);
		add(btnAdd);
		
		btnMakeOrder = new JButton("Make Order");
		btnMakeOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(costumerNameField.getText().equals("")) {
					propertyChangeHandler.firePropertyChange("EmptyFields",getCostumerName(),menuTable.getTable());	
				}
				else {
					propertyChangeHandler.firePropertyChange("MakeOrderEventView",getCostumerName(),menuTable.getTable());	
				}
				menuTable.clearFields();
				clearFields();
			}

		});
		btnMakeOrder.setBounds(346, 498, 188, 41);
		add(btnMakeOrder);
		
		lblCostumerName = new JLabel("Costumer Name");
		lblCostumerName.setBounds(188, 137, 119, 29);
		add(lblCostumerName);
		
		costumerNameField = new JTextField();
		costumerNameField.setBounds(191, 182, 116, 22);
		add(costumerNameField);
		costumerNameField.setColumns(10);
		
		setFonts();
	
	}
	//clear the panel fields
	private void clearFields() {
		this.quantityField.setText("");
		this.costumerNameField.setText("");
	}
	
	//returns hashmap of the user name 
	//we need to use this function because observer cant get different objects
	private HashMap<String,Integer> getCostumerName() {
		HashMap<String,Integer> user=new HashMap<String,Integer>();
		user.put(costumerNameField.getText(), 0);
		return user;
	}
	
	////////observer functions app view will listen to this panel/////
	public void setPropertyChangeSupport() {
		propertyChangeHandler = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeHandler.addPropertyChangeListener(listener); }
}
