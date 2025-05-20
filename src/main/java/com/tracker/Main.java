package com.tracker;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			ExpenseTracker tracker = new ExpenseTracker();

			System.out.println("Welcome to Expense Tracker");

			while (true) {
				System.out.println("\nMenu:");
				System.out.println("1. Add Income");
				System.out.println("2. Add Expense");
				System.out.println("3. Load from File");
				System.out.println("4. Save to File");
				System.out.println("5. Show Monthly Summary");
				System.out.println("6. Exit");
				System.out.print("Choose an option: ");

				int choice = sc.nextInt();
				sc.nextLine(); 

				switch (choice) {
				case 1:
				case 2:
					String type = (choice == 1) ? "Income" : "Expense";
					System.out.print("Enter Category (e.g., Salary, Business, Food, Rent, Travel): ");
					String category = sc.nextLine();
					System.out.print("Enter Amount: ₹");
					double amount = sc.nextDouble();
					sc.nextLine();

					Transaction t = new Transaction(LocalDate.now(), type, category, amount);
					tracker.addTransaction(t);
					System.out.println(type + " recorded.");
					break;

				case 3:
					System.out.print("Enter file name to load: ");
					String loadFile = sc.nextLine();
					try {
						List<Transaction> loaded = FileHandler.loadFromFile(loadFile);
						tracker.loadTransactions(loaded);
						System.out.println("Transactions loaded from file.");
					} catch (IOException e) {
						System.out.println("Failed to load file: " + e.getMessage());
					}
					break;

				case 4:
					System.out.print("Enter file name to save: ");
					String saveFile = sc.nextLine();
					try {
						FileHandler.saveToFile(saveFile, tracker.getAllTransactions());
						System.out.println("Transactions saved to file.");
					} catch (IOException e) {
						System.out.println("Failed to save file: " + e.getMessage());
					}
					break;

				case 5:
					System.out.print("Enter year (YYYY): ");
					int year = sc.nextInt();
					System.out.print("Enter month (1-12): ");
					int month = sc.nextInt();
					tracker.showMonthlySummary(year, month);
					break;

				case 6:
					System.out.println("Goodbye!");
					return;

				default:
					System.out.println("Invalid choice.");
				}
			}
		}
	}
}
