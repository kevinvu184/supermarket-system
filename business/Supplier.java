package business;

import utilities.Address;

public class Supplier {
	final String name;
	String contactNumber;
	Address address;
	String productSupplied;
	int quantityBought;
	boolean supplyRequestMarker = false;

	public Supplier(String name, String contactNumber, int postCode, String suburb, String state, String city,
			String productSupplied, int quantityBought) {
		this.name = name;
		this.contactNumber = contactNumber;
		this.productSupplied = productSupplied;
		this.quantityBought = quantityBought;
		this.address = new Address(postCode, suburb, state, city);
	}

	public boolean reorderProduct() {
		return false;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setAddress(int postCode, String suburb, String state, String city) {
		this.getAddress().setCity(city);
		this.getAddress().setPostCode(postCode);
		this.getAddress().setState(state);
		this.getAddress().setSuburb(suburb);
	}

	@Override
	public String toString() {
		return "\tContact: " + getContactNumber() + "\n" + getAddress() + "\n\tProduct(s): " + productSupplied
				+ "\n\tQuantity: " + quantityBought;
	}

	public String getName() {
		return name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public Address getAddress() {
		return address;
	}
}