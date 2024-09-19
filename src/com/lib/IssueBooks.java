package com.lib;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IssueBooks {

	 JFrame IssueBooks;
	private JTextField t1id;
	private JTextField t2name;
	private JTextField t3bid;
	private JTextField t4bname;
	private JTextField t5av;
	private JFormattedTextField t6date;
	Connection con;
	Statement stmt;
	ResultSet rs;
	String query;
	 Date date=new Date();
     SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
     String branch,sem,sname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBooks window = new IssueBooks();
					window.IssueBooks.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void connect()
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/library1","root","mysql1212#");  
            stmt=con.createStatement(); 
        }
        catch(Exception e)
        { 
            System.out.println(e);
            JOptionPane.showMessageDialog(IssueBooks,"connection error");
        }
    }
    public void disconnect()
    {
        try
        {
           con.close(); 
        }
        catch(Exception e)
        {}

    }
	public IssueBooks() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		IssueBooks = new JFrame();
		IssueBooks.setBounds(100, 100, 450, 433);
		IssueBooks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		IssueBooks.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Issue Book");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(162, 29, 78, 21);
		IssueBooks.getContentPane().add(lblNewLabel);
		
		JLabel sid = new JLabel("StudentID");
		sid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sid.setBounds(85, 85, 86, 14);
		IssueBooks.getContentPane().add(sid);
		
		JLabel sname = new JLabel("Student Name");
		sname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sname.setBounds(85, 131, 86, 14);
		IssueBooks.getContentPane().add(sname);
		
		JLabel bid = new JLabel("BookID");
		bid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bid.setBounds(85, 178, 46, 14);
		IssueBooks.getContentPane().add(bid);
		
		JLabel avilable = new JLabel("Avilable");
		avilable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		avilable.setBounds(85, 260, 46, 14);
		IssueBooks.getContentPane().add(avilable);
		
		JLabel bname = new JLabel("Book Name");
		bname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bname.setBounds(85, 222, 68, 14);
		IssueBooks.getContentPane().add(bname);
		
		JLabel date = new JLabel("Date");
		date.setFont(new Font("Tahoma", Font.PLAIN, 12));
		date.setBounds(85, 295, 46, 14);
		IssueBooks.getContentPane().add(date);
		
		t1id = new JTextField();
		t1id.addKeyListener(new KeyAdapter(){
			 public void keyReleased(java.awt.event.KeyEvent evt) {
	                t1idKeyReleased(evt);
	            }
		});
		t1id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1ActionPerformed(e);
			}
		});
		t1id.setBounds(216, 82, 130, 20);
		IssueBooks.getContentPane().add(t1id);
		t1id.setColumns(10);
		
		t2name = new JTextField();
		t2name.setColumns(10);
		t2name.setBounds(216, 128, 130, 20);
		IssueBooks.getContentPane().add(t2name);
		
		t3bid = new JTextField();
		t3bid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				t3KeyReleased(e);
			}
		});
		t3bid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t3bidActionPerformed(e);
			}
		});
		t3bid.setColumns(10);
		t3bid.setBounds(216, 176, 130, 20);
		IssueBooks.getContentPane().add(t3bid);
		
		t4bname = new JTextField();
		t4bname.setColumns(10);
		t4bname.setBounds(216, 220, 130, 20);
		IssueBooks.getContentPane().add(t4bname);
		
		t5av = new JTextField();
		t5av.setColumns(10);
		t5av.setBounds(216, 258, 130, 20);
		IssueBooks.getContentPane().add(t5av);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitActionPerformed(e);
			}
		});
		btnsubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnsubmit.setBounds(109, 340, 89, 23);
		IssueBooks.getContentPane().add(btnsubmit);
		
		JButton btncancle = new JButton("Cancle");
		btncancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h1 = new Home();
				h1.home.setVisible(true);
				IssueBooks.setVisible(false);
			
			}
		});
		btncancle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btncancle.setBounds(225, 340, 89, 23);
		IssueBooks.getContentPane().add(btncancle);
		
		 t6date = new JFormattedTextField();
		t6date.setBounds(216, 293, 130, 20);
		IssueBooks.getContentPane().add(t6date);
	
	}
	private void t1ActionPerformed(ActionEvent e) {
		
	}
	private void t1idKeyReleased(KeyEvent k) {
		try {
			connect();
			query="select * from studs where sid='"+t1id.getText()+"';";
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				 sname=rs.getString("sname");
				 branch = rs.getString("sbranch");
				 sem = rs.getString("ssem");
				t2name.setText(sname);
			}
			else {
				t2name.setText("");
				disconnect();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		t6date.setText(format.format(date));
	}
	private void t3bidActionPerformed(ActionEvent e) {
		
	}
	private void t3KeyReleased(KeyEvent k) {
		 try
	        {
	            connect();
	            query="select * from books where bookid='"+t3bid.getText()+"';";
	            rs=stmt.executeQuery(query); 
	            if(rs.next())
	            {
	                t5av.setText(rs.getString("avilable"));
	                t4bname.setText(rs.getString("bookname"));
	            }
	            else
	            {
	                t5av.setText("");
	                t4bname.setText("");
	            }
	            disconnect();
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
		 
	}
	private void submitActionPerformed(ActionEvent e) {
		if(t1id.getText().equals("")) {
			JOptionPane.showMessageDialog(IssueBooks, "nama yaar pehle registration to karwao");
		}
		else {
			try {
				connect();
				query="select * from books where bookid = '"+t3bid.getText()+"';";
				rs=stmt.executeQuery(query);
				rs.next();
				if(t5av.getText().equals("YES")) {
					query = "insert into issue values('"+t1id.getText()+"','"+t2name.getText()+"','"+t3bid.getText()+"','"+t4bname.getText()+"','"+t6date.getText()+"','"+branch+"','"+sem+"');";
					stmt.executeUpdate(query);
					JOptionPane.showMessageDialog(IssueBooks, "Book ID with "+t3bid.getText()+"has been issued by " + t2name.getText());
					query="update books set avilable='NO' where bookid ='"+t3bid.getText()+"';";
					stmt.executeUpdate(query);
				}
				 if(t5av.getText().equals("NO"))
                 {
                     JOptionPane.showMessageDialog(IssueBooks,"Book with this id is not available currently");
                 }
				 if(t5av.getText().equals(""))
                 {
                     JOptionPane.showMessageDialog(IssueBooks,"There is no book in the library with this id");
                 }
				 t1id.setText("");
				 t2name.setText("");
				 t3bid.setText("");
				 t4bname.setText("");
				 t6date.setText("");
				 t5av.setText("");
				 disconnect();
				
			}
			catch(SQLException t) {
				 if( t.getErrorCode()==1062)
                     JOptionPane.showMessageDialog(IssueBooks,"A student can only get a single book from library at a time ");
				t.printStackTrace();
			}
		}
	}
	
}
