package gui.main;

import code.behavior.ButtonFactory;
import code.customer.Customer;
import code.file.FileFactory;
import code.product.ProductExt;
import code.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * @author kamontat
 * @since 20/5/59 - 22:55
 */
public class MainPage extends JFrame implements ButtonFactory {
	private JButton customerButton;
	private JButton shoppingButton;
	private JButton storeButton;
	private JButton exitButton;
	private JPanel panel;

	private static FileFactory factory = new FileFactory();
	private static Store store = assignStore();

	public static Customer shopper = store.getGuest();

	public MainPage() {
		super("Main Page");
		setContentPane(panel);

		toShopping(this, shoppingButton);
		toCustomer(this, customerButton);
		toLogin(this, storeButton);
		toExit(exitButton);
	}

	/**
	 * rewrite customer file by using array of customer
	 */
	public static void reWriteCustomer() {
		factory.setPath("src/textfile/Customer.txt");
		factory.write(store.getAllCustomer());
	}

	/**
	 * rewrite newer product into file by using array of product
	 */
	public static void reWriteProductInfo() {
		factory.setPath("src/textfile/Product.txt");
		factory.write(store.getAllProduct(false));
	}

	/**
	 * rewrite customer file by using array of customer
	 */
	public static void reWriteStoreInfo() {
		factory.setPath("src/textfile/StoreInfo.txt");
		factory.write(new Object[][]{{store.getRevenue(), store.getExpense()}});
	}

	/**
	 * Assign product into the arrayList By using <i>text file</i> and <i>return</i>
	 *
	 * @return Product Array
	 */
	private static ArrayList<ProductExt> assignProduct() {
		ArrayList<ProductExt> temp = new ArrayList<>();

		factory.setPath("src/textfile/Product.txt");
		String[][] allProduct = factory.read(":");

		for (String[] product : allProduct) {
			if (product.length == 9)
				temp.add(new ProductExt(product[0], product[1], product[2], product[3], product[4], product[5], product[6], product[7], product[8]));
			else System.err.println("product text-file error");
		}
		return temp;
	}

	/**
	 * Assign customer into the arrayList By using <i>text file</i> and <i>return</i>
	 *
	 * @return Customer Array
	 */
	private static ArrayList<Customer> assignCustomer() {
		ArrayList<Customer> temp = new ArrayList<>();

		// guest member
		temp.add(new Customer());

		factory.setPath("src/textfile/Customer.txt");
		String[][] allCustomer = factory.read(":");

		for (String[] customer : allCustomer) {
			if (customer.length == 6)
				temp.add(new Customer(customer[0], customer[1], customer[2], customer[3], customer[4], customer[5]));
			else System.err.println("customer text-file error");
		}
		Customer.setNumCustomers(temp.size());

		return temp;
	}

	/**
	 * Assign store information By using <i>text file</i> and <i>return</i>
	 *
	 * @return Store
	 */
	private static Store assignStore() {
		ArrayList<ProductExt> productList = assignProduct();
		ArrayList<Customer> customerList = assignCustomer();

		Store temp = null;
		factory.setPath("src/textfile/StoreInfo.txt");
		String[][] informations = factory.read(":");
		for (String[] info : informations) {
			temp = Store.getInstance(productList, customerList, info[0], info[1]);
		}

		return temp;
	}

	/**
	 * run this oldgui
	 */
	public void run(Point point) {
		pack();
		setVisible(true);
		setLocation(point);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(650, 400));
	}
}