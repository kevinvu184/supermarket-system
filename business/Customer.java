package business;

import java.util.ArrayList;

import utilities.Address;

public class Customer {
	private String name;
	private String customerId;
	private Address address;
	private double swipePoints;
	private ArrayList<Transaction> transactionHistory = new ArrayList<Transaction>();
	private ArrayList<Product> cart = new ArrayList<Product>();

	public Customer(String name, String customerId, Address address, double swipePoints) {
		this.name = name;
		this.customerId = customerId;
		this.address = address;
		this.swipePoints = swipePoints;
	}

	public void addTransaction(Transaction transaction) {
		transactionHistory.add(transaction);
	}

	public void addProduct(Product product) {
		cart.add(product);
	}

	public String getName() {
		return name;
	}

	public String getCustomerId() {
		return customerId;
	}

	public Address getAddress() {
		return address;
	}

	public double getSwipePoints() {
		return swipePoints;
	}

	public ArrayList<Product> getCart() {
		return cart;
	}

	public String getCartDetail() {
		String display = "________Cart________\n";
		for (int i = 0; i < cart.size(); i++) {
			display += "\t" + cart.get(i).getName() + "\n";
		}
		return display;
	}

	public ArrayList<Transaction> getTransactionHistory() {
		return transactionHistory;
	}

	public void setPoints(double swipePoints) {
		this.swipePoints = swipePoints;
	}

	public void emptyCart() {
		cart.clear();
	}


	@Override
	public String toString() {
		return "Customer Name: " + name + ", Customer ID: " + customerId + ", Customer Address: " + address.toString()
				+ ", Customer's Points: " + swipePoints;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}