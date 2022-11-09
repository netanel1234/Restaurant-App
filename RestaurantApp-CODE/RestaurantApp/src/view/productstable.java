package view;
import java.awt.*;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


//// this panel has been created because we cant use a table on a absolute layout like all my panels
//so i create this panel with flow layout and put my table inside
//this panel is used by order panel and view supplies panel
public class ProductsTable extends JPanel {
 
	HashMap<String,Integer> productsToOrder;
	
	/////////panel components////////
	private static final long serialVersionUID = 1L;
	JTable table;
	DefaultTableModel model;
 /////////constructor
 public ProductsTable(int width, int height){
	 
	 //////////// panel initialization//////////
  setLayout(new FlowLayout());
  
  ///////////compontnts initialization/////////
  model = new DefaultTableModel(); 
  table = new JTable(model){
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column) {
			  return false;
		  }
	  };
  table.setPreferredScrollableViewportSize(new Dimension(width, height));
  table.setFillsViewportHeight(true);
  
  JScrollPane scrollPane = new JScrollPane(table);
  add(scrollPane);
  
  
  productsToOrder= new HashMap<String,Integer>();
  
 }
 
 public void addColumnToTable(String column) {
	 model.addColumn(column);
 }
 
 public void addItem(String Product,String Quantity) {//this function get two strings
	 //and add them to the table
	 model.addRow(new Object[]{Product , Quantity});  
 }

public HashMap<String, Integer> getTable() {
	// this function uses all the rows of the table and create a hashtable of orders
	//that will be sent to the model
	for(int i=0;i<model.getRowCount();i++) {
		if(this.productsToOrder.containsKey((String) model.getValueAt(i, 0)))
		{
			this.productsToOrder.put((String) model.getValueAt(i, 0), this.productsToOrder.get((String) model.getValueAt(i, 0))+Integer.parseInt((String) model.getValueAt(i, 1)));
		}
		else
		{
		this.productsToOrder.put((String) model.getValueAt(i, 0), Integer.parseInt((String) model.getValueAt(i, 1)));
		}
	}
	return this.productsToOrder;
}



public void clearFields() {//clear the table fields
	this.model.setRowCount(0);
	
}
 
}