package com.tracker;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker {
	private List<Transaction> transactions = new ArrayList<>();

	public void addTransaction(Transaction t) {
		transactions.add(t);
	}

	public void loadTransactions(List<Transaction> loaded) {
		transactions.addAll(loaded);
	}

	public void showMonthlySummary(int year, int month) {
		double income = 0, expense = 0;
		System.out.println("\nSummary for " + year + "-" + String.format("%02d", month));

		for (Transaction t : transactions) {
			if (t.getDate().getYear() == year && t.getDate().getMonthValue() == month) {
				System.out
						.println(t.getDate() + " - " + t.getType() + " - " + t.getCategory() + " - ₹" + t.getAmount());

				if (t.getType().equalsIgnoreCase("Income")) {
					income += t.getAmount();
				} else {
					expense += t.getAmount();
				}
			}
		}

		System.out.println("Total Income: ₹" + income);
		System.out.println("Total Expense: ₹" + expense);
		System.out.println("Net Savings: ₹" + (income - expense));
	}

	public List<Transaction> getAllTransactions() {
		return transactions;
	}
}
