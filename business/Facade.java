package business;

import application.Supermarket;
import application.SupermarketSystem;
import utilities.DateTime;

public class Facade implements SupermarketSystem {
	Supermarket supermarket = new Supermarket();

	@Override
	public boolean isStaff(String type) {
		return supermarket.isStaff(type);
	}

	@Override
	public String login(String userId, int passWord) {
		return supermarket.staffLogin(userId, passWord);
	}

	@Override
	public String listProducts() {
		return supermarket.listProducts();
	}

	@Override
	public String addProduct(String i) {
		return supermarket.addProduct(i);
	}

	@Override
	public String getPrice(String id) {
		return supermarket.getPrice(id);
	}

	@Override
	public String checkDiscount(String id) {
		return supermarket.checkDiscount(id);
	}

	@Override
	public String getDiscountPercentage(String id) {
		return supermarket.getDiscountPercentage(id);
	}

	@Override
	public String getDiscountPrice(String id) {
		return supermarket.getDiscountPrice(id);
	}

	@Override
	public String checkOut() {
		return supermarket.checkOut();
	}

	@Override
	public String pay(boolean pay) {
		return supermarket.pay(pay);
	}

	@Override
	public String removeProduct(String id, boolean all) {
		return supermarket.removeProduct(id, all);
	}

	@Override
	public String cancelTransaction(boolean cancel) {
		return supermarket.cancelTransaction(cancel);
	}

	@Override
	public String checkStock(String id) {
		return supermarket.checkStock(id);
	}

	@Override
	public String replenishStock(String id) {
		return supermarket.replenishStock(id);
	}

	@Override
	public String restockShelf(String id, int quantity) {
		return supermarket.restockShelf(id, quantity);
	}

	@Override
	public String setPrice(String id, int price) {
		return supermarket.setPrice(id, price);
	}

	@Override
	public String setReplenishLevel(String id, int level) {
		return supermarket.setReplenishLevel(id, level);
	}

	@Override
	public String replenishRequest(String id) 
	{
		return supermarket.replenishRequest(id);
	}

	@Override
	public void setSupplierContact(String name, String contactNumber) {
		supermarket.setSupplierContact(name, contactNumber);
	}

	@Override
	public void setSupplierAddress(String id, int postCode, String suburb, String state, String city) {
		supermarket.setSupplierAddress(id, postCode, suburb, state, city);
	}

	@Override
	public String setDiscountPrice(String name, int price) {
		return supermarket.setDiscountPrice(name, price);
	}

	@Override
	public String setDiscountPercentage(String id, int percentage) {
		return supermarket.setDiscountPercentage(id, percentage);
	}

	@Override
	public String getSalesReport(DateTime end, DateTime start)
	{
		return supermarket.getSalesReport(end, start);
	}

	@Override
	public String getMostSoldReport() 
	{
		return supermarket.getMostSoldReport();
	}

	@Override
	public String getReportAddress() 
	{
		return supermarket.getReportAddress();
	}

	@Override
	public String setMarketingCampaign() 
	{
		return supermarket.setMarketingCampaign();
	}

	@Override
	public String getSupplyReport() {
		return supermarket.getSupplyReport();
	}

	@Override
	public String getRevenueList() 
	{
		return supermarket.getRevenueList();
	}

	@Override
	public String seedData() {
		return supermarket.seedData();
	}

	@Override
	public String customerLogin(String userId) {
		return supermarket.customerLogin(userId);
	}

	@Override
	public boolean isCustomer() {
		return supermarket.isCustomer();
	}

	@Override
	public void addSupplier(String name, String contactNumber, int postCode, String suburb, String state, String city,
			String productSupplied, int quantityBought) {
		supermarket.addSupplier(name, contactNumber, postCode, suburb, state, city, productSupplied, quantityBought);
	}

	@Override
	public boolean isSupplierExists(String name) {
		return supermarket.isSupplierExists(name);
	}

	@Override
	public void deleteSupplier(String name) {
		supermarket.deleteSupplier(name);
	}

	@Override
	public void staffLogout() {
		supermarket.staffLogout();
	}
	
	@Override
	public void customerLogout() {
		supermarket.customerLogout();
	}

	@Override
	public String displaySupplierDetails() {
		return supermarket.displaySupplierDetails();
	}

	@Override
	public String getSpecificSupplierDetails(String supplierName) {
		return supermarket.getSpecificSupplierDetails(supplierName);
	}

	@Override
	public String displayProductDetails() {
		return supermarket.displayProductDetails();
	}

	@Override
	public String getSpecificProductDetails(String productId) {
		return supermarket.getSpecificProductDetails(productId);
	}
	
	@Override
	public String getCart() {
		return supermarket.getCart();
	}
	

}