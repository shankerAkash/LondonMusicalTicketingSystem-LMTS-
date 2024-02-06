/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.londonmusical.lmts.screens;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;

/**
 *
 * @author ektasingh
 */
public class ViewBookings extends javax.swing.JInternalFrame {

    String username;

    /**
     * Creates new form ViewBookings
     */
    public ViewBookings(String user) {
        username = user;
        initComponents();
        getTableData(user);
    }

    public void getTableData(String user) {

        String[] columns = {"Booking Id", "Booking Name", "Show Name", "Show Time", "Show Venue", "Student Tickets", "Adult Tickets", "Senior Tickets", "Amount", "Payment"};

        // Create table model
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try {
            // Establish a database connection
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lmts", "root", "root");
            Statement stat = con.createStatement();
            String selectQuery = "SELECT * FROM ROOT.\"Booking\" WHERE username = '" + username + "'";
            ResultSet rs = stat.executeQuery(selectQuery);

            // Populate the table model with data
            while (rs.next()) {

                int bookingId = rs.getInt("BOOKING_ID");
                String bookingName = rs.getString("BOOKING_NAME");
                String showName = rs.getString("SHOW_NAME");
                String showTime = rs.getString("SHOW_TIME");
                String showVenue = rs.getString("SHOW_VENUE");
                int studentTickets = rs.getInt("STUDENT_TICKETS");
                int seniorTickets = rs.getInt("SENIOR_TICKETS");
                int adultTickets = rs.getInt("ADULT_TICKETS");
                int amount = rs.getInt("AMOUNT");
                String payment = rs.getString("PAYMENT");

                // Add a new row to the table model
                model.addRow(new Object[]{bookingId, bookingName, showName, showTime, showVenue, studentTickets, adultTickets, seniorTickets, amount, payment});
            }

            // Close the result set, statement, and connection
            rs.close();
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from the database", "Error", JOptionPane.ERROR_MESSAGE);
        }

        bookingTable.setModel(model);

    }

    private String getUserDetails() {
        String userDetails = "";
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lmts", "root", "root");
            Statement stat = con.createStatement();
            String selectQuery = "SELECT * FROM ROOT.\"User\" WHERE username='" + username + "'";
            try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                // Add venue names to the Vector

                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    String username = resultSet.getString("USERNAME");
                    String email = resultSet.getString("EMAIL");
                    userDetails = "****** USER DETAILS******"
                            + "Name: " + name + "\n"
                            + "Username: " + username + "\n"
                            + "Email: " + email + "\n";
                }
                resultSet.close();
                stat.close();
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data from the database!");
        }
        return userDetails;
    }

    private String getShowDetails(String showName) {
        String showDetails = "";
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lmts", "root", "root");
            Statement stat = con.createStatement();
            String selectQuery = "SELECT * FROM ROOT.\"Shows\" WHERE name='" + showName + "'";
            try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
                ResultSet rs = preparedStatement.executeQuery();
                // Add venue names to the Vector

                while (rs.next()) {
                    String name = rs.getString("NAME");
                    String runtime = rs.getString("RUNTIME");
                    String categories = rs.getString("CATEGORIES");
                    String venue = rs.getString("VENUE");
                    String studentPrice = rs.getString("STUDENT_PRICE");
                    String adultPrice = rs.getString("ADULT_PRICE");
                    String seniorPrice = rs.getString("SENIOR_PRICE");
                    showDetails = "****** SHOW DETAILS******"
                            + "Name: " + name + "\n"
                            + "RunTIME: " + runtime + "\n"
                            + "Categories: " + categories + "\n"
                            + "Venue: " + venue + "\n"
                            + "Student Price: $" + studentPrice + "\n"
                            + "Adult Price: $" + adultPrice + "\n"
                            + "Senior Price: $" + seniorPrice + "\n"
                            + "--------------------------------\n";
                }
                rs.close();
                stat.close();
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data from the database!");
        }
        return showDetails;
    }

    private String getBookingDetails(int bookingId) {
        String bookingDetails = "";
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lmts", "root", "root");
            Statement stat = con.createStatement();
            String selectQuery = "SELECT * FROM ROOT.\"Booking\" WHERE BOOKING_ID=" + bookingId + "";
            try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
                ResultSet rs = preparedStatement.executeQuery();
                // Add venue names to the Vector

                while (rs.next()) {
                    String bookingName = rs.getString("BOOKING_NAME");
                    String showName = rs.getString("SHOW_NAME");
                    String showTime = rs.getString("SHOW_TIME");
                    String showVenue = rs.getString("SHOW_VENUE");
                    int studentTickets = rs.getInt("STUDENT_TICKETS");
                    int seniorTickets = rs.getInt("SENIOR_TICKETS");
                    int adultTickets = rs.getInt("ADULT_TICKETS");
                    int amount = rs.getInt("AMOUNT");
                    String payment = rs.getString("PAYMENT");
                    bookingDetails = "****** BOOKING DETAILS******"
                            + "Booking Refrence: " + bookingId + "\n"
                            + "Name: " + bookingName + "\n"
                            + "Show: " + showName + "\n"
                            + "Time: " + showTime + "\n"
                            + "Venue: " + showVenue + "\n"
                            + "Student Tickets: " + studentTickets + "\n"
                            + "Adult Tickets: " + adultTickets + "\n"
                            + "Senior Tickets: " + seniorTickets + "\n"
                            + "Total Amount: " + amount + "\n"
                            + "Payment Status: " + payment + "\n"
                            + "--------------------------------\n";
                }
                rs.close();
                stat.close();
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data from the database!");
        }
        return bookingDetails;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookingTable = new javax.swing.JTable();
        downloadTicket = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel1.setText("My Bookings");

        bookingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(bookingTable);

        downloadTicket.setText("Download Ticket");
        downloadTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadTicketActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(downloadTicket)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(downloadTicket)
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void downloadTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadTicketActionPerformed
        // TODO add your handling code here:
        Object showName = bookingTable.getValueAt(bookingTable.getSelectedRow(), 2);
        Object bookingName = bookingTable.getValueAt(bookingTable.getSelectedRow(), 0);

        String showDetails = getShowDetails(showName.toString());
        String bookingDetails = getBookingDetails(Integer.parseInt(bookingName.toString()));
        String userDetails = getUserDetails();

        Path output = Paths.get(System.getProperty("user.home"), "Documents/Booking", "Receipts");
        String message = bookingDetails + showDetails + userDetails;

        try {
            Files.createDirectories(output.getParent());
            Files.write(output, message.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            infoMessage("Booking Reciept is Downloaded at Documets/Bookings/Reciept path in your system", "Download Alert");
        } catch (IOException e) {
            infoMessage("Unable to download Ticket " + e, "Download Alert");
        }

    }//GEN-LAST:event_downloadTicketActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookingTable;
    private javax.swing.JButton downloadTicket;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void infoMessage(String message, String tittle) {
        JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);
    }
}
