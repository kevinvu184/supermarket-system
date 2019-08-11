package business;

/*
 * Class:			Product
 * Author:			[VuDuyAnhKhoa] - [s3678490]
 */

public class Product {
	private final String name;
	private final String id;

	private double unitPrice;
	private double discountPrice;
	private int discountPercentage = 0;

	private int replenishStockQuantity;
	private boolean replenishStockMarker = false;
	private int stockLevel;
	private int warehouseStock;

	private int shelfStockLevel;
	private int replenishShelfQuantity;
	private boolean replenishShelfMarker;
	private int salesQuantity = 0;

	private boolean promotionMarker = false;
    private double revenue;

	public Product(String name, double unitPrice, int shelfStockLevel, int warehouseStock, int replenishStockQuantity,
			int replenishShelfQuantity) {
		this.name = name;
		this.id = (name.length() > 2) ? name.substring(0, 3).toUpperCase() : name.toUpperCase();
		this.unitPrice = unitPrice;
		this.shelfStockLevel = shelfStockLevel;
		this.warehouseStock = warehouseStock;
		this.replenishStockQuantity = replenishStockQuantity;
		this.replenishShelfQuantity = replenishShelfQuantity;
	}

	public String getName() {
		return this.name;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public void setSalesQuantity(int salesQuantity) {
		this.salesQuantity += salesQuantity;
	}

	public void setRevenue(double revenue) {
		this.revenue += revenue;
	}

	public String getProductId() {
		return this.id;
	}

	public double getRevenue() {
		return revenue;
	}

	public double getPrice() {
		if (discountPercentage > 0)
			return unitPrice - ((unitPrice * discountPercentage) / 100);
		return unitPrice;
	}

	public void setPrice(double price) {
		this.unitPrice = price;
	}

	public void setDiscountPrice(double price) {
		this.discountPrice = price;
	}

	public void setDiscountPercentage(int discountPercentage) {
		if (discountPercentage > 0)
			this.discountPercentage = discountPercentage;
	}

	public boolean isStockReplenish() {
		if (stockLevel < replenishStockQuantity)
			this.replenishStockMarker = true;
		return replenishStockMarker;
	}

	public boolean isShelfReplenish() {
		if (shelfStockLevel < replenishShelfQuantity)
			this.replenishShelfMarker = true;
		return replenishShelfMarker;
	}

	private void setStockReplenishMarker(boolean ReplenishStockMarker) {
		this.replenishStockMarker = ReplenishStockMarker;
	}

//	private void setShelfReplenishMarker(boolean ReplenishShelfMarker) {
//		this.replenishShelfMarker = ReplenishShelfMarker;
//	}

	public void setReplenishStockQuantity(int replenishStockQuantity) {
		this.replenishStockQuantity = replenishStockQuantity;
	}

	public boolean checkStock() {
		if ((warehouseStock + shelfStockLevel) <= replenishStockQuantity) {
			return true;
		} else {
			return false;
		}
	}

	public int getWarehouseStock() {
		return warehouseStock;
	}

//	private void setReplenishShelfMarker(boolean replenish) {
//		this.replenishShelfMarker = replenish;
//	}

	public void replenishStock() {
		setStockReplenishMarker(true);
	}

	public void replenishShelfStock(int replenish) {
		this.shelfStockLevel = shelfStockLevel + replenish;
		this.warehouseStock = warehouseStock - replenish;
	}

	public boolean isPromotion() {
		return this.promotionMarker;
	}

	public void setpromotionMarker(boolean promotionMarker) {
		this.promotionMarker = promotionMarker;
	}

	public int getStockLevel() {
		return stockLevel;
	}

	public int getSalesQuantity() {
		return salesQuantity;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setReplenishStockMarker(boolean replenishStockMarker) {
		this.replenishStockMarker = replenishStockMarker;
	}

	@Override
	public String toString() {
		return "\n\tName: " + name + "\n\tID: " + id + "\n\tUnit Price: $" + unitPrice + "\n\tDiscount Percentage: "
				+ discountPercentage + "%" + "\n\tPromotion Marker: " + ((isPromotion()) ? "Yes" : "No");
	}

	public String toStringCustomer() {
		return "\n\tName: " + name + "\n\tID: " + id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}