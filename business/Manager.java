package business;

/*
 * Class:			Manager
 * Author:			[VuDuyAnhKhoa] - [s3678490]
 */

public class Manager extends WarehouseStaff {

	public Manager(String name, String id, int securityPin) {
		super(name, id, securityPin);
	}

	@Override
	public String toString() {
		return "Name  :" + name + "ID  :" + id + "Security Pin :" + securityPin;
	}
}