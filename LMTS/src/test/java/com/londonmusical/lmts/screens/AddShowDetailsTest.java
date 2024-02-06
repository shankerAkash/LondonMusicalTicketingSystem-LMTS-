package com.londonmusical.lmts.screens;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class AddShowDetailsTest {

	@Test
	public void testAddButtonActionPerformed() {
		// Prepare test data
		String name = "Test Show";
		// Add more test data initialization here...

		try {
			// Create an instance of the AddShowDetails class
			AddShowDetails addShowDetails = new AddShowDetails();

			// Manually set test data to the UI components (text fields, combo box, etc.)
			addShowDetails.nameField.setText(name);
			// Set more test data to other UI components...

			// Simulate button click action
			addShowDetails.addButton.doClick();

			// Verify the result by checking the database if the data was added
			Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lmts", "root", "root");
			Statement stat = con.createStatement();
			// Query the database to check if the test data was added
			// For example:
			// ResultSet rs = stat.executeQuery("SELECT * FROM ROOT.\"Shows\" WHERE name =
			// '" + name + "'");
			// Perform assertions based on the result

			// Add assertions here to verify the expected outcome of the method

			// Close the database connection (if needed)
			con.close();

		} catch (Exception e) {
			fail("Exception occurred: " + e.getMessage());
		}
	}

}
