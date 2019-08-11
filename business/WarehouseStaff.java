package business;

public class WarehouseStaff extends Staff {
	private boolean restockStockMarker;
	private boolean restockShelfMarker;

	public WarehouseStaff(String name, String id, int securityPin) {
		super(name, id, securityPin);
	}

	public void setReStockMarker(boolean restockStockMarker) {
		this.restockStockMarker = restockStockMarker;
	}

	public void setReShelfMarker(boolean restockShelfMarker) {
		this.restockShelfMarker = restockShelfMarker;
	}

	public boolean getRestockStockMarker() {
		return restockStockMarker;
	}

	public boolean getRestockShelfMarker() {
		return restockShelfMarker;
	}
}