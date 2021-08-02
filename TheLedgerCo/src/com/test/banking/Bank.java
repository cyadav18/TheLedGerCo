package com.test.banking;

import java.util.LinkedList;

public class Bank {
	private String name;
	private LinkedList<Loan> loans = new LinkedList<Loan>();

	public LinkedList<Loan> getLoans() {
		return loans;
	}

	public Bank(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String toString() {
		return "Bank:" + name;
	}

	public void addLoan(Loan loan) {
		if (this.loans.contains(loan)) {
			throw new IllegalArgumentException("Loan already exist please clrear it " + this.name);
		} else {
			this.loans.add(loan);
		}
	}
}

class TestBank {
	public static void main(String[] args) {
		Bank b1 = new Bank("HDFC");
		Bank b2 = new Bank("ICIC");
		Customer c1 = new Customer("Steve");
		Double principal = (double) 10000;
		Double rateOfIntrest = (double) 3.2;
		Double numberOfYears = (double) 2;
		Loan l1 = new Loan(c1, principal, rateOfIntrest, numberOfYears, b1);
		Loan l2 = new Loan(c1, principal, rateOfIntrest, numberOfYears, b1);
		try {
			b1.addLoan(l1);
			b1.addLoan(l2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("loan already exists");
		}
		System.out.println(b2);
		System.out.println(l1);
		System.out.println(l2);

	}
}
