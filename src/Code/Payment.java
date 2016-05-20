package code;

public class Payment {
	private double totalPrice;
	private double discountRate;
	private double shippingFee;
	private double discount;
	private double value;

	public Payment() {
		totalPrice = 0;
		discountRate = 0;
		shippingFee = 0;
		discount = 0;
		value = 0;
	}

	public Payment(double totalPrice, double discountRate, double shippingFee) {
		this.totalPrice = totalPrice;
		this.discountRate = discountRate;
		this.shippingFee = shippingFee;
		this.discount = this.totalPrice * discountRate;
		this.value = (totalPrice + shippingFee) - discount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public double getShippingFee() {
		return shippingFee;
	}

	public double getDiscount() {
		return discount;
	}

	public double getValue() {
		return value;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String toString() {
		String format = "Total price = %.2f, Discount rate = %.2f%%, Discount = %.2f, Shipping fee = %.2f, Payment = %.2f";
		return String.format(format, this.totalPrice, this.discountRate * 100, this.discount, this.shippingFee, this.value);
	}
}
