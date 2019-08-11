package utilities;

public class Address {
	private String city;
	private String state;
	private int postCode;
	private String suburb;

	public Address(int postCode, String suburb, String state, String city) {
		this.city = city;
		this.state = state;
		this.postCode = postCode;
		this.suburb = suburb;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	@Override
	public String toString() {
		return "\tCity: " + getCity() + "\n\tState: " + getState() + "\n\tPost Code: " + getPostCode() + "\n\tSuburb: "
				+ getSuburb();
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getPostCode() {
		return postCode;
	}

	public String getSuburb() {
		return suburb;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + postCode;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((suburb == null) ? 0 : suburb.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (postCode != other.postCode)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (suburb == null) {
			if (other.suburb != null)
				return false;
		} else if (!suburb.equals(other.suburb))
			return false;
		return true;
	}

}