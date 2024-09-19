package com.lib;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHome {

	 JFrame AdHome;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHome window = new AdminHome();
					window.AdHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


		
	public AdminHome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		AdHome = new JFrame();
		AdHome.setBounds(100, 100, 450, 217);
		AdHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AdHome.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Add New Book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				AddBooks ab = new AddBooks();
				ab.AddBooks.setVisible(true);
				AdHome.setVisible(false);
			}
		});
		btnNewButton.setBounds(73, 75, 107, 23);
		AdHome.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Admin");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(187, 34, 59, 14);
		AdHome.getContentPane().add(lblNewLabel);
		
		JButton btnRemoveBooks = new JButton("Remove Books");
		btnRemoveBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRemoveBooks.setBounds(252, 75, 107, 23);
		AdHome.getContentPane().add(btnRemoveBooks);
		
		JButton btnNewButton_1 = new JButton("Cancle");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin ad = new Admin();
				ad.Admin.setVisible(true);
				AdHome.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(175, 121, 89, 23);
		AdHome.getContentPane().add(btnNewButton_1);
	}

}
