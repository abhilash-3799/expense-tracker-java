package com.tracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
	public static void saveToFile(String filename, List<Transaction> transactions) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			for (Transaction t : transactions) {
				writer.write(t.toString());
				writer.newLine();
			}
		}
	}

	public static List<Transaction> loadFromFile(String filename) throws IOException {
		List<Transaction> transactions = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				transactions.add(Transaction.fromCSV(line));
			}
		}
		return transactions;
	}
}
