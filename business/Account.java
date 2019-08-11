package business;

public class Account {
	Double totalRevenue;
	Double totalProfit;

	public Account(Double totalRevenue, Double totalProfit)
	{
		this.totalRevenue = totalRevenue;
		this.totalProfit = totalProfit;;
	}

	public Double getRevenue() {
		return totalRevenue;
	}

	public Double getProfit() {
		return totalProfit;
	}


	public void setProfit(Double totalProfit) {
		this.totalProfit += totalProfit;
	}

	public void setRevenue(Double totalRevenue) {
		this.totalRevenue += totalRevenue;
	}
}
