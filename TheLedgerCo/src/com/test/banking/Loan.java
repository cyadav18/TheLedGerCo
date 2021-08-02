package com.test.banking;

import java.util.HashMap;

public class Loan {
	private Customer customer;
	private Double principal;
	private Double rateOfIntrest;
	private Double numberOfYears;
	private Bank bank; // loan transfer
	private Double intrest;
	private Double totalAmount;
	private Double emiAmount;
	private Double balance;
	private Double numBerOfMonths;
	private HashMap<Double, Double> payment = new HashMap<Double, Double>();

	public void addPayment(Double month, Double amount) {
		payment.put(month, amount);
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public Double getIntrest() {
		return intrest;
	}

	public Double getEmiAmount() {
		return emiAmount;
	}

	public Double getBalance() {
		return balance;
	}

	public Loan(Customer customer, Bank bank) {
		super();
		this.customer = customer;
		this.bank = bank;
	}

	public Loan(Customer customer, Double principal, Double rateOfIntrest, Double numberOfYears, Bank bank) {
		super();
		this.customer = customer;
		this.principal = principal;
		this.rateOfIntrest = Math.ceil(rateOfIntrest);
		this.numberOfYears = numberOfYears;
		this.bank = bank;
		this.numBerOfMonths = 12 * numberOfYears;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bank == null) ? 0 : bank.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		if (bank == null) {
			if (other.bank != null)
				return false;
		} else if (!bank.equals(other.bank))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getPrincipal() {
		return principal;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}

	public Double getRateOfIntrest() {
		return rateOfIntrest;
	}

	public void setRateOfIntrest(Double rateOfIntrest) {
		this.rateOfIntrest = rateOfIntrest;
	}

	public Double getNumberOfYears() {
		return numberOfYears;
	}

	public void setNumberOfYears(Double numberOfYears) {
		this.numberOfYears = numberOfYears;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String toString() {
		return "[customer=" + customer + ", principal=" + principal + ", rateOfIntrest=" + rateOfIntrest
				+ ", numberOfYears=" + numberOfYears + ", bank=" + bank + "]";
	}

	public void calculateIntrest() {
		this.intrest = (this.principal * this.numberOfYears * this.rateOfIntrest) / 100;
		totalAmount = principal + intrest;
		balance = totalAmount;
		emiAmount = totalAmount / (12 * numberOfYears);
		emiAmount = Math.ceil(emiAmount);
		numBerOfMonths = (double) 1;
		numBerOfMonths = numberOfYears * 12;
	}

	public String getEmiAmount(int n) {
		calculateIntrest();
		for (int i = 1; i <= n; i++) {
			if (balance > 0) {
				if (0 < numBerOfMonths) {
					numBerOfMonths = numBerOfMonths - 1;
					balance = balance - emiAmount;
					if (balance < emiAmount)
						emiAmount = balance;
					if (payment.containsKey((double) i)) {
						Double lumpsum = payment.get((double) i);
						if (lumpsum > balance) {
							throw new IllegalArgumentException(
									"Lumpsum cannot be accepted which is greater than balance");
						} else {
							balance = balance - lumpsum;
							numBerOfMonths = Math.ceil(balance / emiAmount);
							if (balance < emiAmount)
								emiAmount = balance;
						}
					}
				} else {
					throw new IllegalArgumentException("The enterted tenure doesn't exist");
				}

			} else {
				throw new IllegalArgumentException("Emi cleared no need to pay more");
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(this.bank.getName() + "\t");
		sb.append(this.customer.getName() + "\t");
		sb.append(this.totalAmount - balance + "\t");
		sb.append(this.numBerOfMonths);
//		System.out.println(sb.toString());
		calculateIntrest();
		return sb.toString();
	}

}

class TestLoan {
	public static void main(String[] args) {
		Bank b1 = new Bank("IDIDI");
		Customer c1 = new Customer("Dale");
		Double principal = (double) 10000;
		Double rateOfIntrest = (double) 4;
		Double numberOfYears = (double) 5;
		Loan l1 = new Loan(c1, principal, rateOfIntrest, numberOfYears, b1);
//		l1.addPayment((double) 5, (double) 1000);
//		l1.addPayment((double) 7, (double) 1000);
		System.out.println(l1.getEmiAmount(5));
	}
}