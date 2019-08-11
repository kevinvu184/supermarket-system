package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import utilities.DateTime;

public class Transaction {
	// all methods done except file input and output

	private double transactionNumber;
	private double paymentDue;
	private boolean paid;
	DateTime saleDate;
	private ArrayList<Product> soldProducts;
	private String customerId;
	double points;

	public Transaction(DateTime saleDate, ArrayList<Product> soldProducts, String customerId) {
		this.transactionNumber = new Random().nextDouble();
		this.paymentDue = 0;
		this.paid = false;
		this.saleDate = saleDate;
		this.soldProducts = new ArrayList<Product>(soldProducts);
		this.customerId = customerId;
	}

	public void calculatePayment(double points) {
		this.points = points;
		for (int i = 0; i < soldProducts.size(); i++) {
			if (soldProducts.get(i).getDiscountPercentage() != 0) {
				paymentDue += soldProducts.get(i).getPrice()
						* (1 - (soldProducts.get(i).getDiscountPercentage() / 100));
			} else if (soldProducts.get(i).getDiscountPrice() != 0) {
				paymentDue += soldProducts.get(i).getDiscountPrice();
			} else {
				paymentDue += soldProducts.get(i).getPrice();
			}
		}
		paymentDue -= this.points / 20 * 5;
	}

	public void removeProduct(Product product, boolean all) {
		if (all) {
			soldProducts.removeAll(Collections.singleton(product));
			paymentDue = 0;
			calculatePayment(points);
		} else {
			soldProducts.remove(product);
			paymentDue = 0;
			calculatePayment(points);
		}
	}

	public DateTime getSaleDate() 
	{
		return saleDate;
	}

	public double getTransactionNumber() {
		return transactionNumber;
	}

	public boolean isPaid() {
		return paid;
	}

	public String getCustomerId() {
		return customerId;
	}

	public double getPaymentDue() {
		return paymentDue;
	}

	public void paid(boolean paid) {
		this.paid = paid;
	}

	public double updatePoints() {
		double update = (points % 20) + (paymentDue / 10);
		return update;
	}

	public Double getPoints() {
		return points;
	}

}
