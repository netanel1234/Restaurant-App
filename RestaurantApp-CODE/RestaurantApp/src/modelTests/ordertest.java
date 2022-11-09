package modelTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import model.Cheesecake;
import model.IceCream;
import model.ItalianPizza;
import model.Order;
import model.Product;
import model.RegularPizza;
import model.VeganPizza;

public class OrderTest {

	Order order;

	@Test
	public void testSumOrder() {
		ArrayList<Product> productsInMenu = new ArrayList<Product>();
		productsInMenu.add(Cheesecake.getInstance());
		productsInMenu.add(IceCream.getInstance());
		productsInMenu.add(ItalianPizza.getInstance());
		productsInMenu.add(RegularPizza.getInstance());
		productsInMenu.add(VeganPizza.getInstance());
		HashMap<String, Integer> productsInOrder = new HashMap<>();
		productsInOrder.put("Cheesecake", 1);
		productsInOrder.put("Ice Cream", 1);
		productsInOrder.put("Italian Pizza", 1);
		productsInOrder.put("Regular Pizza", 1);
		productsInOrder.put("VeganPizza", 1);
		order = new Order(1, productsInOrder, "aaa");
		assertTrue("this is the right sum", 130 == order.sumOrder(productsInMenu));
	}

}
