package application;

import java.util.Scanner;
import java.util.StringTokenizer;

import business.Facade;
import utilities.DateTime;

/*
 * Class:			Menu
 * Author:			[VuDuyAnhKhoa] - [s3678490]
 */

public class Menu {
	private Facade facade = new Facade();
	private Scanner sc = new Scanner(System.in);

	public void loginMenu() {
		String ch;
		do {
			ch = printMenu("Login");
			if (ch.equals("W") && !facade.isStaff("WarehouseStaff")) {
				System.out.print("\tLogin as Warehouse Staff to use this option");
			} else if (ch.equals("M") && !facade.isStaff("Manager")) {
				System.out.print("\tLogin as Manager Staff to use this option");
			} else if (ch.equals("C") && !facade.isCustomer()) {
				System.out.print("\tLogin as Customer to use this option");
			} else {
				System.out.println("\t*********************************");
				switch (ch) {
				case "C":
					customerMenu();
					break;
				case "W":
					warehouseStaffMenu();
					break;
				case "M":
					managerMenu();
					break;
				case "U":
					customerLogin();
					break;
				case "L":
					staffLogin();
					break;
				case "D":
					seedData();
					break;
				case "E":
					break;
				default:
					System.out.print("\n\tPlease choose in the option above!");
				}
			}
		} while (!ch.equals("E"));
		System.out.println("\t__________END OF PROGRAM__________");
	}

	private void customerMenu() {
		String ch;
		do {
			ch = printMenu("Customer");
			System.out.println("\t*********************************");
			switch (ch) {
			case "1":
				getProductDetailsCustomer();
				break;
			case "2":
				getProductPrice();
				break;
			case "3":
				getProductDiscount();
				break;
			case "4":
				getProductDetailsCustomer();
				addToCart();
				break;
			case "5":
				checkout();
				customerLogout();
				break;
			case "E":
				customerLogout();
				break;
			default:
				System.out.print("\n\tPlease choose in the option above!");
			}
		} while (!ch.equals("E")&!ch.equals("5"));
		System.out.println("\t________BACK TO LOGIN MENU________");
	}

	private void warehouseStaffMenu() {
		String ch;
		do {
			ch = printMenu("Warehouse");
			System.out.println("\t*********************************");
			switch (ch) {
			case "1":
				checkStock();
				break;
			case "2":
				replenishStock();
				break;
			case "3":
				replenishShelfStock();
				break;
			case "E":
				staffLogout();
				break;
			default:
				System.out.print("\n\tPlease choose in the option above!");
			}
		} while (!ch.equals("E"));
		System.out.println("\t________BACK TO LOGIN MENU________");
	}

	private void managerMenu() {
		String ch;
		do {
			ch = printMenu("Manager");
			System.out.println("\t*********************************");
			switch (ch) {
			case "1":
				productDetail();
				break;
			case "2":
				setPrice();
				break;
			case "3":
				setDiscountPrice();
				break;
			case "4":
				setDiscountPercentage();
				break;
			case "5":
				setReplenishStockQuantity();
				break;
			case "6":
				checkStock();
				break;
			case "7":
				replenishRequest();
				break;
			case "8":
				isSupplierAddable();
				break;
			case "9":
				isSupplierDeleteable();
				break;
			case "10":
				displaySupplierDetails();
				break;
			case "11":
				setSupplierContact();
				break;
			case "12":
				setSupplierAddress();
				break;
			case "13":
				getSalesReport();
				break;
			case "14":
				getMostSoldReport();
				break;
			case "15":
				getRevenueList();
				break;
			case "16":
				getSupplyReport();
				break;
			case "17":
				getReportAddress();
				break;
			case "18":
				setMarketingCampaign();
				break;
			case "E":
				staffLogout();
				break;
			default:
				System.out.print("\n\tPlease choose in the option above!");
			}
		} while (!ch.equals("E"));
		System.out.println("\t________BACK TO LOGIN MENU________");
	}

	private void promtCheckout() {
		String ch;
		do {
			ch = printMenu("Checkout");
			System.out.println("\t*********************************");
			switch (ch) {
			case "1":
				System.out.println("\n" + facade.pay(true));
				break;
			case "2":
				removeProduct();
				break;
			case "3":
				cancelTransaction(true);
				break;
			case "4":
				System.out.println("\n\t" + facade.getCart());
				break;
			default:
				System.out.print("\n\tPlease choose in the option above!");
			}
		} while (!ch.equals("3") && !ch.equals("1"));
	}

	private String printMenu(String selection) {
		if (selection.equals("Login")) {
			System.out.print(loginOption());
		} else if (selection.equals("Customer")) {
			System.out.print(customerOption());
		} else if (selection.equals("Warehouse")) {
			System.out.print(warehouseStaffOption());
		} else if (selection.equals("Manager")) {
			System.out.print(managerOption());
		} else if (selection.equals("Checkout")) {
			System.out.print(checkoutOption());
		}
		String ch = getInput("\n\tEnter selection:").toUpperCase();
		return ch;
	}

	private String loginOption() {
		return "\n\n\t___Bussiness Intelligent System___\n\n" + String.format("\t%-31s%2s\n", "Customer", "C")
				+ String.format("\t%-31s%2s\n", "Warehouse staff Options", "W")
				+ String.format("\t%-31s%2s\n", "Manager Options", "M")
				+ String.format("\t%-31s%2s\n", "Staff Login", "L")
				+ String.format("\t%-31s%2s\n", "Customer Login", "U") + String.format("\t%-31s%2s\n", "Seed Data", "D")
				+ String.format("\t%-31s%2s\n", "Exit Program", "E");
	}

	private String customerOption() {
		return "\n\n\t_________Customer Option_________\n\n" 
				+ String.format("\t%-31s%2s\n", "Show All Products", "1")
				+ String.format("\t%-31s%2s\n", "Show Product Price", "2")
				+ String.format("\t%-31s%2s\n", "Show Product Discount", "3")
				+ String.format("\t%-31s%2s\n", "Add Product to Cart", "4")
				+ String.format("\t%-31s%2s\n", "Check out", "5") 
				+ String.format("\t%-31s%2s\n", "Logout", "E");
	}

	private String warehouseStaffOption() {
		return "\n\n\t_____Warehouse Staff Options_____\n\n" 
				+ String.format("\t%-31s%2s\n", "Check Stock", "1")
				+ String.format("\t%-31s%2s\n", "Replenish Stock", "2") 
				+ String.format("\t%-31s%2s\n", "Restock Shelf", "3")
				+ String.format("\t%-31s%2s\n", "Logout", "E");
	}

	private String managerOption() {
		return "\n\n\t________Manager Options________\n\n" 
				+ String.format("\t%-39s%12s\n", "Display Specific Product Details", "1")
				+ String.format("\t%-39s%12s\n", "Update Product Price", "2")
				+ String.format("\t%-39s%12s\n", "Set Product Discount Price", "3")
				+ String.format("\t%-39s%12s\n", "Set Product Discount Percentage", "4")
				+ String.format("\t%-39s%12s\n", "Update Product Replenishment Level", "5")
				+ String.format("\t%-39s%12s\n", "Check Stock", "6")
				+ String.format("\t%-39s%12s\n", "Request Product Replenishment", "7")
				+ String.format("\t%-39s%12s\n", "Add Supplier", "8")
				+ String.format("\t%-39s%12s\n", "Remove Supplier", "9")
				+ String.format("\t%-39s%12s\n", "Display Specific Supplier Details", "10")
				+ String.format("\t%-39s%12s\n", "Update Supplier Contact Information", "11")
				+ String.format("\t%-39s%12s\n", "Update Supplier Address Information", "12")
				+ String.format("\t%-39s%12s\n", "Generate Sales Report", "13")
				+ String.format("\t%-39s%12s\n", "Generate Fast Moving Products Report", "14")
				+ String.format("\t%-39s%12s\n", "Generate Product Revenue Report", "15")
				+ String.format("\t%-39s%12s\n", "Generate Supplier Report", "16")
				+ String.format("\t%-39s%12s\n", "Generate Sales Report by Address", "17")
				+ String.format("\t%-39s%12s\n", "Set Marketting Campaign", "18")
				+ String.format("\t%-39s%12s\n", "Logout", "E");
	}

	private String checkoutOption() {
		return "\n\t________Checkout Menu________\n\n" + String.format("\t%-31s%2s\n", "Pay", "1")
				+ String.format("\t%-31s%2s\n", "Remove Product", "2")
				+ String.format("\t%-31s%2s\n", "Cancel Transaction", "3")
				+ String.format("\t%-31s%2s\n", "Check Cart", "4");
	}

	private String getInput(String promt) {
		System.out.printf("%-34s", promt);
		String input = sc.nextLine();
		return input.replaceAll("\\s+", "");
	}

	private void staffLogin() {
		String userId = getInput("\tEnter staff ID:").toUpperCase();
		int securityPin = Integer.parseInt(getInput("\tEnter Security Pin:"));
		System.out.print(facade.login(userId, securityPin));
	}

	private void customerLogin() {
		String userId = getInput("\tEnter customer ID:").toUpperCase();
		System.out.print(facade.customerLogin(userId));
	}

	private void staffLogout() {
		System.out.println("\tLog out successfully !\n");
		facade.staffLogout();
	}

	private void customerLogout() {
		System.out.println("\tLog out successfully !\n");
		facade.customerLogout();
	}

	private void isSupplierDeleteable() {
		String name = getInput("\tEnter Supplier name:");
		if (facade.isSupplierExists(name)) {
			deleteSupplier(name);
		} else {
			System.out.print("\n\tSupplier is not exists!");
		}
	}

	private void isSupplierAddable() {
		String name = getInput("\tEnter Supplier name:");
		if (facade.isSupplierExists(name)) {
			System.out.print("\n\tSupplier already exists!");
		} else {
			addSupplier(name);
		}
	}

	private void deleteSupplier(String name) {
		facade.deleteSupplier(name);
		System.out.print("\n\tDelete supplier successfully!");
	}

	private void setSupplierContact() {
		String name = getInput("\tEnter Supplier name:");
		if (facade.isSupplierExists(name)) {
			String contactNumber = getInput("\tEnter new Comntact number:");
			facade.setSupplierContact(name, contactNumber);
			System.out.print("\n\tUpdate supplier contact successfully!");
		} else {
			System.out.print("\n\tSupplier is not exists!");
		}
	}

	private void setSupplierAddress() {
		String name = getInput("\tEnter Supplier name:");
		if (facade.isSupplierExists(name)) {
			int postCode = Integer.parseInt(getInput("\tEnter new Post code:"));
			String suburb = getInput("\tEnter new Surburb:");
			String state = getInput("\tEnter new State:");
			String city = getInput("\tEnter new City:");
			facade.setSupplierAddress(name, postCode, suburb, state, city);
			System.out.print("\n\tUpdate supplier contact successfully!");
		} else {
			System.out.print("\n\tSupplier is not exists!");
		}
	}

	private void addSupplier(String name) {
		String contactNumber = getInput("\tEnter Comntact number:");
		int postCode = Integer.parseInt(getInput("\tEnter Post code:"));
		String suburb = getInput("\tEnter Surburb:");
		String state = getInput("\tEnter State:");
		String city = getInput("\tEnter City:");
		String productSupplied = getInput("\tEnter Product:");
		int quantityBought = Integer.parseInt(getInput("\tEnter Quantity:"));
		facade.addSupplier(name, contactNumber, postCode, suburb, state, city, productSupplied, quantityBought);
		System.out.print("\n\tAdd supplier successfully!");
	}

	private void displaySupplierDetails() {
		System.out.printf("\t%-31s\n", facade.displaySupplierDetails());
		String supplierName = getInput("\tEnter supplier name:");
		System.out.print(facade.getSpecificSupplierDetails(supplierName));
	}

	private void getSupplyReport() {
		System.out.printf("\t%-31s\n", facade.getSupplyReport());
	}


	private void setMarketingCampaign() 
	{
		System.out.printf("\t%-31s\n", facade.setMarketingCampaign());
	}

	private void getReportAddress() 
	{
		System.out.printf("\t%-31s\n", facade.getReportAddress());
	}

	private void getRevenueList() 
	{
		System.out.printf("\t%-31s\n", facade.getRevenueList());
	}

	private void getMostSoldReport() 
	{
		System.out.printf("\t%-31s\n", facade.getMostSoldReport());	
	}

	private void getSalesReport() {
		String startingDate = getInput("\tEnter start date(dd/mm/yyyy):");
		String endingDate = getInput("\tEnter end date(dd/mm/yyyy):");
		StringTokenizer str = new StringTokenizer(startingDate, "/");
		int startDay = Integer.parseInt(str.nextToken());
		int startMonth = Integer.parseInt(str.nextToken());
		int startYear = Integer.parseInt(str.nextToken());
		StringTokenizer str1 = new StringTokenizer(endingDate, "/");
		int endDay = Integer.parseInt(str1.nextToken());
		int endMonth = Integer.parseInt(str1.nextToken());
		int endYear = Integer.parseInt(str1.nextToken());
		DateTime startDate=new DateTime(startDay, startMonth, startYear);
		DateTime endDate= new DateTime(endDay, endMonth, endYear);
		System.out.printf("\t%-31s\n", facade.getSalesReport(endDate, startDate));
	}

	private void productDetail() {
		System.out.printf("\t%-31s\n", facade.displayProductDetails());
		String productId = getInput("\tEnter product ID:");
		System.out.print(facade.getSpecificProductDetails(productId));
	}

	private void addToCart() {
		System.out.println("\n\t*********************************");
		String id = getInput("\tEnter Product ID:").toUpperCase();
		System.out.print(facade.addProduct(id));
	}

	private void replenishStock() {
		String id = getInput("\tEnter Product ID:");
		System.out.println(facade.replenishStock(id.toUpperCase()));
	}

	private void replenishShelfStock() {
		String id = getInput("\tEnter Product ID:");
		int quantity = Integer.parseInt(getInput("\tEnter Quantity of the product."));
		System.out.print(facade.restockShelf(id.toUpperCase(), quantity));
	}

	private void checkStock() {
		String id = getInput("\tEnter Product ID:");
		System.out.println(facade.checkStock(id.toUpperCase()));
	}

	private void setReplenishStockQuantity() {
		String id = getInput("\tEnter Product ID:");
		int level = Integer.parseInt(getInput("\tEnter Stock Level:"));
		System.out.println(facade.setReplenishLevel(id.toUpperCase(), level));
	}

	private void setDiscountPercentage() {
		String id = getInput("\tEnter Product ID:");
		int percentage = Integer.parseInt(getInput("\tEnter Discount percentage:"));
		System.out.println(facade.setDiscountPercentage(id.toUpperCase(), percentage));
	}

	private void setDiscountPrice() {
		String id = getInput("\tEnter Product ID:");
		int price = Integer.parseInt(getInput("\tEnter Discounted price:"));
		System.out.println(facade.setDiscountPrice(id.toUpperCase(), price));
	}

	private void setPrice() 
	{
		String id = getInput("\tEnter Product ID:");
		int price = Integer.parseInt(getInput("\tEnter Product price:"));
		System.out.println(facade.setPrice(id.toUpperCase(), price));
	}

	private void replenishRequest() 
	{
		String productId = getInput("\tEnter Product ID:");
		System.out.println(facade.replenishRequest(productId.toUpperCase()));
	}

	public void getProductDetailsCustomer() 
	{
		System.out.print("\n" + facade.listProducts());
	}

	public void getProductPrice() {
		String productId = getInput("\n\tEnter Product ID:");
		System.out.println("\n" + facade.getPrice(productId.toUpperCase()));
	}

	public void getProductDiscount() 
	{
		String productId = getInput("\n\tEnter Product ID:");
		String temp = facade.checkDiscount(productId.toUpperCase());
		if (temp.contains("Sorry")) {
			System.out.println(temp);
		} else if (temp.contains("No")) {
			System.out.println(temp);
		} else if (temp.contains("percentage")) {
			System.out.println(facade.getDiscountPercentage(productId));
		} else {
			System.out.println(facade.getDiscountPrice(productId));
		}
	}

	private void checkout() 
	{
		System.out.println("\n\t" + facade.getCart());
		String response=facade.checkOut();
		System.out.print("\n" + response);
		if (response.equals("\tSorry you cannot check out with an empty cart")) {
			System.out.println("\n\tBack to the menu");
		} else {
			boolean correctInput = true;
			do {
				String selection = getInput("\tPay Now?(Y/N)").toUpperCase();
				if (selection.equals("Y")) {
					System.out.print("\n" + facade.pay(true));
					correctInput = false;
				} else if (selection.equals("N")) {
					promtCheckout();
					correctInput = false;
				} else {
					System.out.println("\n\tPlease choose (Y/N) to countinue");
				}
			} while (correctInput);
			System.out.print("\n\tCheckout process finish\n");
		}
	}

	private void removeProduct() 
	{
		System.out.println("\n\tPlease waiting for Sales Staff!");
		do {
			facade.staffLogout();
			System.out.println("\n\tLogin as Sales Staff to use this option");
			staffLogin();
		} while (!facade.isStaff("SalesStaff"));
		String productId = getInput("\n\tEnter Product ID:");
		System.out.println("\n\t" + facade.removeProduct(productId.toUpperCase(), false));
	}
	
	private void cancelTransaction(boolean b)
	{
		System.out.println("\n\tPlease waiting for Sales Staff!");
		do {
			facade.staffLogout();
			System.out.println("\n\tLogin as Sales Staff to use this option");
			staffLogin();
		} while (!facade.isStaff("SalesStaff"));
		System.out.println("\n" + facade.cancelTransaction(b));
	}

	private void seedData() 
	{
		System.out.print(facade.seedData());
	}
}
