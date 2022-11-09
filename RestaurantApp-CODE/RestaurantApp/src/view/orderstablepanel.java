package view;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


//// this panel has been created because we cant use a table on a absolute layout like all my panels
//so i create this panel with flow layout and put my table inside
//this panel is used by pending orders panel
public class OrdersTablePanel extends JPanel {
 
	
	/////////panel components////////
	private static final long serialVersionUID = 1L;
	JTable table;
	DefaultTableModel model;
 /////////constructor
 public OrdersTablePanel(int width, int height){
	 
	 //////////// panel initialization//////////
  setLayout(new FlowLayout());
  
  ///////////compontnts initialization/////////
  model = new DefaultTableModel(); 
  table = new JTable(model) {
	private static final long serialVersionUID = 1L;

	public boolean isCellEditable(int row, int column) {
		  return false;
	  }
  };
  table.setPreferredScrollableViewportSize(new Dimension(width, height));
  table.setFillsViewportHeight(true);
  
  JScrollPane scrollPane = new JScrollPane(table);
  add(scrollPane);
  
  
 }
 
 public void addColumnToTable(String column) {
	 model.addColumn(column);
 }
 

//add the orders to the table from the arraylist that recived by the model 
public void setTable(ArrayList<String> orderList) {
	
	for(int i=0;i<orderList.size();i+=3) {
		model.addRow(new Object[] {orderList.get(i),orderList.get(i+1),orderList.get(i+2)});
	}
}

//clear table fields
public void clearFields() {
	this.model.setRowCount(0);
	
}
 
}