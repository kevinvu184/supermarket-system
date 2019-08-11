package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import business.Account;
import business.Customer;
import business.Manager;
import business.Product;
import business.SalesStaff;
import business.Staff;
import business.Supplier;
import business.Transaction;
import business.WarehouseStaff;
import utilities.Address;
import utilities.DateTime;

/*
 * Class:			Supermarket 
 * Description:		The supermarket manager the manages the 
 *              	collection of data. 
 * Author:			[Kevin Vu] - [s3678490]
 */

public class Supermarket {
	private ArrayList<Staff> colStaff = new ArrayList<Staff>();
	private ArrayList<Product> prodList = new ArrayList<Product>();
	private ArrayList<Customer> cusList = new ArrayList<Customer>();
	private ArrayList<Supplier> supList = new ArrayList<Supplier>();
	private ArrayList<Transaction> transList = new ArrayList<Transaction>();
	private Account supermarketAccount= new Account(0.0,0.0);
	private Staff staff = null;
	private Customer customer = null;

	public boolean isStaff(String type) {
		if (staff == null) {
			return false;
		} else if (type.equals("WarehouseStaff") && staff != null && (staff instanceof WarehouseStaff)) {
			return true;
		} else if (type.equals("Manager") && staff != null && (staff instanceof Manager)) {
			return true;
		} else if (type.equals("SalesStaff") && staff != null && (staff instanceof SalesStaff)) {
			return true;
		}
		return false;
	}

	public String staffLogin(String userId, int securityPin) {
		for (int i = 0; i < colStaff.size(); i++) {
			if (colStaff.get(i).getId().compareTo(userId) == 0 && colStaff.get(i).getSecurityPin() == securityPin) {
				staff = colStaff.get(i);
				return "\tWelcome " + staff.getName() + " Access Rights: [" + staff.getClass() + "]\n";
			}
		}
		return "\tInvalid username/password\n";
	}

	public boolean isCustomer() {
		if (getCustomer() == null) {
			return false;
		}
		return true;
	}

	public String customerLogin(String userId) {
		for (int i = 0; i < cusList.size(); i++) {
			if (cusList.get(i).getCustomerId().compareTo(userId) == 0) {
				customer = cusList.get(i);
				return "\tWelcome back " + getCustomer().getName() + " !";
			}
		}
		return "\tNon-existent ID number";
	}

	public void staffLogout() {
		staff = null;
	}

	public void customerLogout() {
		customer = null;
	}

	public String listProducts() {
		String temp = "\t___________PRODUCT LIST__________\n";
		for (int i = 0; i < getProdList().size(); i++) {
			temp += getProdList().get(i).toStringCustomer() + "\n";
		}
		return temp;
	}

	public String addProduct(String id) {
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().toUpperCase().equals(id)) {
				customer.addProduct(getProdList().get(i));
				return "\tProduct has been added.";
			}
		}
		return "\tSorry, this product does not exist.";
	}

	public String getPrice(String id) {
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().equals(id)) {
				return "\tThe price for the product is: " + getProdList().get(i).getPrice();
			}
		}
		return "\tSorry, the product does not exist.";
	}

	public String checkDiscount(String id) {
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().equals(id)) {
				if (getProdList().get(i).getDiscountPercentage() != 0) {
					return "\tYes, there is a discount percentage.";
				} else if (getProdList().get(i).getDiscountPrice() != 0) {
					return "\tYes, there is a discounted price.";
				}
				return "\tNo, there is no discount applicable.";
			}
		}
		return "\tSorry, the product does not exist.";
	}

	public String getDiscountPercentage(String id) {
		
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().equals(id)) {
				return "\tThe discount percentage for product with product id : " + id + " is "
						+ getProdList().get(i).getDiscountPercentage() + "%";
			}
		}
		return "\tSorry, the product does not exist.";
	}

	public String getDiscountPrice(String id) {
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().equals(id)) {
				return "\tThe discounted price for product with product id : " + id + " is $"
						+ getProdList().get(i).getDiscountPrice();
			}
		}
		return "\tSorry, the product does not exist.";
	}

	public String checkOut() {
		if (customer.getCart().size() != 0) {
			Transaction transaction = new Transaction(new DateTime(), customer.getCart(), customer.getCustomerId());
			transaction.calculatePayment(customer.getSwipePoints());
			transList.add(transaction);
			return "\tPayment due: " + transaction.getPaymentDue() + "\n";
		} else {
			return "\tSorry you cannot check out with an empty cart";
		}
	}

	public String pay(boolean pay) {
		transList.get(transList.size() - 1).paid(pay);
		customer.addTransaction(transList.get(transList.size() - 1));
		customer.setPoints(transList.get(transList.size() - 1).updatePoints());
		supermarketAccount.setRevenue(transList.get(transList.size()-1).getPaymentDue());
		for(Product product: customer.getCart())
		{
			if (product.getDiscountPercentage() != 0) 
			{
				double revenue = product.getPrice()
						* (1 - (product.getDiscountPercentage() / 100));
				product.setRevenue(revenue);
			} 
			else if (product.getDiscountPrice() != 0) 
			{
				double revenue = product.getDiscountPrice();
				product.setRevenue(revenue);
			} 
			else 
			{
				double revenue = product.getPrice();
				product.setRevenue(revenue);
			}
			product.setSalesQuantity(1);
		}
		customer.emptyCart();
		return "\tYou have successfully paid";
	}

	public String removeProduct(String id, boolean all) {
		for (int i = 0; i < customer.getCart().size(); i++) {
			if (customer.getCart().get(i).getProductId().equals(id)) {
				transList.get(transList.size() - 1).removeProduct(customer.getCart().get(i), all);
				customer.getCart().remove(i);
				return "Product has been removed, new Payment Due is: "
						+ transList.get(transList.size() - 1).getPaymentDue();
			}
		}
		return "Product was not found";
	}

	public String cancelTransaction(boolean cancel) {
		if (cancel) {
			transList.remove(transList.size() - 1);
			return "\tTransaction has been cancelled";
		} else {
			return "\tTransaction was not cancelled";
		}
	}

	public String checkStock(String id) {
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().equals(id)) {
				boolean temp = getProdList().get(i).checkStock();
				if (temp) {
					return "\tProduct needs to be replenished.";
				} else {
					return "\tProduct does not need to be replenished.";
				}

			}
		}
		return "\tSorry, the product does not exist.";
	}

	public String replenishStock(String id) // The warehouse function to order the product
	{
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().equals(id)) {
				getProdList().get(i).replenishStock();
				return "\tThe product with product id " + id + " has been requested for replenishment";
			}
		}
		return "\tSorry, the product does not exist.";
	}

	public String restockShelf(String id, int quantity) {
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().equals(id)) {
				if (quantity > getProdList().get(i).getWarehouseStock()) {
					return "\tThere is not enough stock in the warehouse to restock by that quantity";
				} else {
					getProdList().get(i).replenishShelfStock(quantity);
					return "\tThe product with product id " + id + " has been replenished on the shelf.";
				}
			}
		}
		return "\tSorry, the product does not exist.";
	}

	public String setPrice(String id, int price) {
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().equals(id)) {
				getProdList().get(i).setPrice(price);
				return "\tThe price has been updated.";
			}
		}
		return "\tSorry, the product does not exist.";
	}

	public String setReplenishLevel(String id, int level) {
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().equals(id)) {
				getProdList().get(i).setReplenishStockQuantity(level);
				return "\tThe replenish stock level has been updated.";
			}
		}
		return "\tSorry, the product does not exist.";
	}

	public String replenishRequest(String id) // The manager function to tell the warehouse Staff to reorder the product
	{
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().equals(id)) {
				getProdList().get(i).setReplenishStockMarker(true);
				for (int x = 0; x < colStaff.size(); x++) {
					if (colStaff.get(x) instanceof WarehouseStaff) {
						WarehouseStaff w1 = (WarehouseStaff) colStaff.get(x);
						w1.setReStockMarker(true);
						colStaff.set(x, w1);
					}
				}
				return "\tReplenish Request Successfully Made.";
			}
		}
		return "\tSorry, the product does not exist.";
	}

	public void setSupplierContact(String name, String contactNumber) {
		for (int i = 0; i < supList.size(); i++) {
			if (supList.get(i).getName().equals(name)) {
				supList.get(i).setContactNumber(contactNumber);
			}
		}
	}

	public void setSupplierAddress(String id, int postCode, String suburb, String state, String city) {
		for (int i = 0; i < supList.size(); i++) {
			if (supList.get(i).getName().equals(id)) {
				supList.get(i).setAddress(postCode, suburb, state, city);
			}
		}
	}

	public String setDiscountPrice(String id, int price) {
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().equals(id)) {
				getProdList().get(i).setDiscountPrice(price);
				return "\tThe discounted price has been updated.";
			}
		}
		return "\tSorry, the product does not exist.";
	}

	public String setDiscountPercentage(String id, int percentage) {
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().equals(id)) {
				getProdList().get(i).setDiscountPercentage(percentage);
				return "\tThe discount percentage has been updated.";
			}
		}
		return "\tSorry, the product does not exist.";
	}

	public String getSalesReport(DateTime end, DateTime start)
	{
		String reportOut;
		DateTime today = new DateTime();
		reportOut = "The Sales Report\nFrom : "+start+"\nUntil: "+end+"\n";
		if(DateTime.diffDays(end, start) > 0)
		{
			for(int i=0; i<transList.size();i++)
			{
				if((DateTime.diffDays(transList.get(i).getSaleDate(),start))>0 && (DateTime.diffDays(end,transList.get(i).getSaleDate())>0))
				{
					reportOut += 
							"\nTransaction Number: "+transList.get(i).getTransactionNumber()+
							"\nPayment Due: "+transList.get(i).getPaymentDue()+
							"\nPaid Status: "+transList.get(i).isPaid()+
							"\nSale Date: "+transList.get(i).getSaleDate()+
							"\nCustomer Number: "+transList.get(i).getCustomerId()+
							"\n____________________________";
				}
				else
				{
					reportOut += "\nThere are no transactions within the given time frame.";
				}
			}

			try {
				File file = new File("Time Report "+today+".txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(reportOut + "\nGenerate date: " + today.toString());
				bw.close();

				return "Report successfully created";

			} catch (IOException ioe) {
				ioe.printStackTrace();
				return "Exception occurred:";
			}
		}
		else
		{
			return "Error - Invalid Time Frame";
		}
	}

	public String getMostSoldReport() 
	{
		Product Array[] = new Product [prodList.size()];
		prodList.toArray(Array);
		Product temp;
		String reportOut;
		DateTime today = new DateTime();
		
		for(int i=0; i<Array.length; i++)
		{
			for(int j=0; j<Array.length-1; j++)
			{
				if(Array[j+1].getSalesQuantity() > Array[j].getSalesQuantity())
				{
					temp = Array[j];
					Array[j] = Array[j+1];
					Array[j+1] = temp;
				}
			}
		}
		reportOut = "Fast Moving Item Report:\n";
		for(int i=0; i<Array.length;i++)
		{
			reportOut += "\nProduct ID: "+Array[i].getProductId()+" , Sold Quantity: "+Array[i].getSalesQuantity();
		}

		try {
			File file = new File("Fast Report "+today+".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(reportOut + "\nGenerate date: " + today.toString());
			bw.close();

			return "Report successfully created";

		} catch (IOException ioe) {
			ioe.printStackTrace();
			return "Exception occurred:";
		}
	}

	public String getReportAddress() 
	{
		DateTime today = new DateTime();
		String report = "\n\t_________Address report_________\n";

		for (int i = 0; i < cusList.size(); i++) {
		report += "\t" + cusList.get(i).getName() + "\n\n" + cusList.get(i).getAddress().toString()
		+ "\n\t--------------------------" + "\n";
		}

		int[] postCode = new int[cusList.size()];

		for (int i = 0; i < cusList.size(); i++) {
		postCode[i] = cusList.get(i).getAddress().getPostCode();
		}

		Arrays.sort(postCode);

		int max_count = 1, res = postCode[0];
		int curr_count = 1;

		for (int i = 1; i < cusList.size(); i++) {
		if (postCode[i] == postCode[i - 1])
		curr_count++;

		else {
		if (curr_count > max_count) {
		max_count = curr_count;
		res = postCode[i - 1];

		}
		curr_count = 1;
		}
		}

		if (curr_count > max_count) {
		max_count = curr_count;
		res = postCode[cusList.size() - 1];
		}

		report += "\n\tThe most frequency post code: " + res + "\n";

		if (supList.size() == 0)
		return "\tNo customer exists";
		try {
		File file = new File("AddressReport.txt");
		if (!file.exists()) {
		file.createNewFile();
		}
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(report + "\tGenerate date: " + today.toString());
		bw.close();

		return "Report successfully created";

		} catch (IOException ioe) {
		ioe.printStackTrace();
		return "Exception occurred:";
		}

	}

	public String setMarketingCampaign() 
	{
		int[] postCode = new int[cusList.size()];

		for (int i = 0; i < cusList.size(); i++) {
		postCode[i] = cusList.get(i).getAddress().getPostCode();
		}

		Arrays.sort(postCode);

		int max_count = 1, res = postCode[0];
		int curr_count = 1;

		for (int i = 1; i < cusList.size(); i++) {
		if (postCode[i] == postCode[i - 1])
		curr_count++;

		else {
		if (curr_count > max_count) {
		max_count = curr_count;
		res = postCode[i - 1];

		}
		curr_count = 1;
		}
		}

		if (curr_count > max_count) {
		max_count = curr_count;
		res = postCode[cusList.size() - 1];
		}

		return "\n\t Marketing Campaign set successfully for area_Post Code: " + res + "\n";
	}

	public String getSupplyReport() {
		DateTime today = new DateTime();
		String report = "\n\t_________Supplier Report_________\n";

		for (int i = 0; i < supList.size(); i++) {
			report += "\t" + supList.get(i).getName() + "\n\n" + supList.get(i).toString()
					+ "\n\t--------------------------" + "\n";
		}
		if (supList.size() == 0)
			return "\tNo supplier exists";

		try {
			File file = new File("Supply Report "+today+".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(report + "Generate date: " + today.toString());
			bw.close();

			return "Report Successfully Created";

		} catch (IOException ioe) {
			ioe.printStackTrace();
			return "Exception occurred:";
		}
	}

	public String getRevenueList() 
	{
		Product Array[] = new Product[prodList.size()];
		prodList.toArray(Array);
		Product temp;
		DateTime today = new DateTime();
		String reportOut;
			
		for(int j=0; j<Array.length;j++)
		{
			for(int i=0; i<Array.length-1;i++)
			{			
				if(Array[i+1].getRevenue() > Array[i].getRevenue())
				{
					temp = Array[i];
					Array[i] = Array[i+1];
					Array[i+1] = temp;
				}
			}
		}
		reportOut = "The Latest Product Revenue List:";
		for (int i=0; i<Array.length;i++)
		{
			reportOut += "\nProduct ID: "+Array[i].getProductId()+" , Revenue: "+Array[i].getRevenue();
		}
		
		try {
			File file = new File("Revenue Report "+today+".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(reportOut + "\tGenerate date: " + today.toString());
			bw.close();

			return "Report successfully created";

		} catch (IOException ioe) {
			ioe.printStackTrace();
			return "Exception occurred:";
		}
	}

	public String seedData() {
		colStaff.add(new WarehouseStaff("Kevin Vu", "KEVIN", 1));
		colStaff.add(new WarehouseStaff("Nicholas Oliver", "NICHOLAS", 1));
		colStaff.add(new Manager("Charles Thevevan", "CHARLES", 1));
		colStaff.add(new SalesStaff("Jungmin Ha", "JUNGMIN", 1));
		colStaff.add(new SalesStaff("Shrey Parekh", "SHREY", 1));

		getProdList().add(new Product("Pepsi", 29, 20, 40, 10, 8));
		getProdList().add(new Product("Coca-Cola", 35, 20, 40, 10, 8));
		getProdList().add(new Product("Mountain Dew", 40, 20, 40, 10, 8));
		getProdList().add(new Product("Lipton Peach", 50, 20, 40, 10, 8));

		Customer milly = new Customer("Thomas", "AAAA", new Address(3161, "Caufield", "VIC", "Melbourne"), 100);

		cusList.add(milly);
		cusList.add(new Customer("Eden", "AAAB", new Address(3006, "Southbank", "VIC", "Melbourne"), 200));

		supList.add(new Supplier("Pepsico", "0999999", 3000, "North Melbourne", "VIC", "Melbourne", "Pepsi can", 20));
		supList.add(new Supplier("Cocaco", "045009213", 3102, "Footsrcay", "VIC", "Melbourne", "Coca-cola can", 30));
		supList.add(new Supplier("MonsterDrinkco", "01234556", 3111, "Shunshine", "VIC", "Melbourne",
				"Monnster ultra can", 50));
		supList.add(new Supplier("Lipton", "1234567", 3004, "Chatswood", "NSW", "Sydney", "Lipton Ice-Tea", 300));

		return "\tSeed data successfully.\n";
	}

	public void addSupplier(String name, String contactNumber, int postCode, String suburb, String state, String city,
			String productSupplied, int quantityBought) {
		supList.add(new Supplier(name, contactNumber, postCode, suburb, state, city, productSupplied, quantityBought));
	}

	public boolean isSupplierExists(String name) {
		for (int i = 0; i < supList.size(); i++) {
			if (supList.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public void deleteSupplier(String name) {
		for (int i = 0; i < supList.size(); i++) {
			if (supList.get(i).getName().equals(name)) {
				supList.remove(i);
			}
		}
	}

	public String displaySupplierDetails() {
		String supplierDetails = "_________Supplier lists_________\n";
		for (int i = 0; i < supList.size(); i++) {
			supplierDetails += "\t" + (i + 1) + ") " + supList.get(i).getName() + "\n";
		}
		if (supList.size() == 0)
			return "\tNo supplier exists";
		return supplierDetails;
	}

	public String getSpecificSupplierDetails(String supplierName) {
		for (int i = 0; i < supList.size(); i++) {
			if (supList.get(i).getName().equals(supplierName))
				return supList.get(i).toString();
		}
		return "\n\tWrong Supplier Name!";
	}

	public Staff getStaff() {
		return staff;
	}

	public Customer getCustomer() {
		return customer;
	}

	public ArrayList<Product> getProdList() {
		return prodList;
	}

	public String displayProductDetails() {
		String productDetails = "_________Products lists_________\n";
		for (int i = 0; i < getProdList().size(); i++) {
			productDetails += "\t" + (i + 1) + ") " + getProdList().get(i).getName() + "\t\t"
					+ getProdList().get(i).getProductId() + "\n";
		}
		if (supList.size() == 0)
			return "\tNo product exists";
		return productDetails;
	}

	public String getSpecificProductDetails(String productId) {
		for (int i = 0; i < getProdList().size(); i++) {
			if (getProdList().get(i).getProductId().equals(productId))
				return getProdList().get(i).toString();
		}
		return "\n\tWrong Product Name!";
	}

	public String getCart() {
		return customer.getCartDetail();
	}

}