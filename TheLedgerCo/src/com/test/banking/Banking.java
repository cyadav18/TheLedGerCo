package com.test.banking;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;



/*
 *Assumptions made while designing , can be modified according to the requirement 
 1)same bank no loan again
 2)number of years to be selected as year only
 3)number of payment's per month (that is only one )
 * */
public class Banking {

	public static void main(String[] args) {
		FileInputStream fis = null;
		Scanner sc = null;
		LinkedList<Bank> banks = new LinkedList<Bank>();
		LinkedList<Loan> loans = null;
		try {
			//Please specify the i/p file name better with fully qualified file path 
			fis = new FileInputStream("C:\\Users\\cheth\\OneDrive\\Desktop\\GIT\\TheLedGerCo\\TheLedgerCo\\src\\INPUTS.txt");
			sc = new Scanner(fis);
			while (sc.hasNextLine()) {
				String query = sc.nextLine();
				String[] splitQuerry = query.trim().split(" ");
				Loan loan = null;
				if (splitQuerry[0].equals("LOAN")) {

					Customer customer = new Customer(splitQuerry[2]);
					Double principal = Double.parseDouble(splitQuerry[3]);
					Double rateOfIntrest = Double.parseDouble(splitQuerry[5]);
					Double numberOfYears = Double.parseDouble(splitQuerry[4]);
					if (banks.contains(new Bank(splitQuerry[1]))) {
						Bank bank = null;
						for (int i = 0; i < banks.size(); i++) {
							if (banks.get(i).getName().equals(splitQuerry[1])) {
								bank = banks.get(i);
								break;
							}
						}
						loan = new Loan(customer, principal, rateOfIntrest, numberOfYears, bank);
						bank.addLoan(loan);
					} else {
						Bank bank = new Bank(splitQuerry[1]);
						banks.add(bank);
						loan = new Loan(customer, principal, rateOfIntrest, numberOfYears, bank);
						bank.addLoan(loan);
					}
				} else if (splitQuerry[0].equals("BALANCE")) {
					Bank bank = null;
					if (banks.contains(new Bank(splitQuerry[1]))) {
						for (int i = 0; i < banks.size(); i++) {
							if (banks.get(i).getName().equals(splitQuerry[1])) {
								bank = banks.get(i);
								break;
							}
						}
						loans = bank.getLoans();
						Customer customer = new Customer(splitQuerry[2]);
						Loan compare = new Loan(customer, bank);
						for (int i = 0; i < loans.size(); i++) {
							if (loans.get(i).equals(compare)) {
								loan = loans.get(i);
								break;
							}
						}
						int emiNum = Integer.parseInt(splitQuerry[3]);
						System.out.println(loan.getEmiAmount(emiNum));
					} else {
						System.out.println("No Bank details found");
					}
				} else if (splitQuerry[0].equals("PAYMENT")) {
					Bank bank = null;
					if (banks.contains(new Bank(splitQuerry[1]))) {
						for (int i = 0; i < banks.size(); i++) {
							if (banks.get(i).getName().equals(splitQuerry[1])) {
								bank = banks.get(i);
								break;
							}
						}
						loans = bank.getLoans();
						Customer customer = new Customer(splitQuerry[2]);
						Loan compare = new Loan(customer, bank);
						for (int i = 0; i < loans.size(); i++) {
							if (loans.get(i).equals(compare)) {
								loan = loans.get(i);
								break;
							}
						}
						Double payment = Double.parseDouble(splitQuerry[3]);
						Double emiNum = Double.parseDouble(splitQuerry[4]);
//						Integer num = Integer.parseInt(splitQuerry[4]);
						loan.addPayment(emiNum, payment);
//						System.out.println(loan.getEmiAmount(num));
					} else {
						System.out.println("No Bank details found");
					}
				}
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
