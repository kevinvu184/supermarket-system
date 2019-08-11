package business;

/*
 * Class:			SalesStaff
 * Author:			[VuDuyAnhKhoa] - [s3678490]
 */

public class SalesStaff extends Staff {
	public SalesStaff(String name, String id, int securityPin) {
		super(name, id, securityPin);
	}

	@Override
	public String toString() {
		return "Name : " + name + "ID :" + id + "Security Pin :" + securityPin;
	}
}
