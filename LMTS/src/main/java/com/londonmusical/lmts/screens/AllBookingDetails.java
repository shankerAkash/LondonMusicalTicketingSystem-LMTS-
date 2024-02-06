/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.londonmusical.lmts.screens;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ektasingh
 */
public class AllBookingDetails extends javax.swing.JInternalFrame {

    /**
     * Creates new form AllBookingDetails
     */
    public AllBookingDetails() {
        initComponents();
        getTableData();
    }

    public void getTableData() {

        String[] columns = {"Booking Id", "Booking Name", "Show Name", "Show Time", "Show Venue", "Student Tickets", "Adult Tickets", "Senior Tickets", "Amount", "Payment"};

        // Create table model
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try {
            // Establish a database connection
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lmts", "root", "root");
            Statement stat = con.createStatement();
            String selectQuery = "SELECT * FROM ROOT.\"Booking\"";
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

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        bookingTable = new javax.swing.JTable();
        bookingLabel = new javax.swing.JLabel();

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

        bookingLabel.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        bookingLabel.setText("All Bookings");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(bookingLabel)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(bookingLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookingLabel;
    private javax.swing.JTable bookingTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
