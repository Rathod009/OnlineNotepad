import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import javax.swing.border.EmptyBorder;


public class Dashboard extends JFrame implements ActionListener{
	

	String user_name ="Dhruv";
	String user_mail,user_number;
	JButton btnUserMenu,btnMyNotes,btnSave,btnLogOut ;
	JPanel menuPanel;
	JTextArea pad;
	JLabel labelSave;
	set_connection sc;
	Dashboard(String id){
		
		//basic preset
		super("Dashboard | YourNotes");		
		setSize(1280,720);
        setLocation(500,200);		
        Color markD  = new Color(0, 204, 68);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		user_mail = id;
		
	
		//data connection
		try {
		sc = new set_connection();
		sc.ps = sc.connection.prepareStatement("select name,contactno from user_data where email=?");
		sc.ps.setString(1, user_mail);
		ResultSet rs = sc.ps.executeQuery();
		
		if (rs.next()) {
			  user_name = rs.getString(1);
			  user_number = rs.getString(2);
			  
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		//data connection ends
		
	
		
		
		
		
		
		
		
		
		JPanel rootPanel = new JPanel();
		getContentPane().add(rootPanel, BorderLayout.CENTER);
		rootPanel.setLayout(new BorderLayout(0, 0));
		
	
		
		JPanel upperPanel = new JPanel();
		upperPanel.setPreferredSize(new Dimension(10, 80));
		rootPanel.add(upperPanel, BorderLayout.NORTH);
		upperPanel.setBackground(markD);
		SpringLayout sl_upperPanel = new SpringLayout();
		upperPanel.setLayout(sl_upperPanel);
		
		btnSave = new JButton("Save");
		btnSave.setFocusPainted(false);
		btnSave.setFocusPainted(false);
		btnSave.setBorderPainted(false);
		btnSave.setBackground(Color.WHITE);
		btnSave.setForeground(Color.black);
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sl_upperPanel.putConstraint(SpringLayout.NORTH, btnSave, 10, SpringLayout.NORTH, upperPanel);
		sl_upperPanel.putConstraint(SpringLayout.WEST, btnSave, 10, SpringLayout.WEST, upperPanel);
		sl_upperPanel.putConstraint(SpringLayout.SOUTH, btnSave, -10, SpringLayout.SOUTH, upperPanel);
		
		upperPanel.add(btnSave);
		
		btnUserMenu = new JButton("Hi,"+user_name+ " <");
		btnUserMenu.setFocusPainted(false);
		sl_upperPanel.putConstraint(SpringLayout.WEST, btnUserMenu, -171, SpringLayout.EAST, upperPanel);
		sl_upperPanel.putConstraint(SpringLayout.SOUTH, btnUserMenu, 0, SpringLayout.SOUTH, btnSave);
		sl_upperPanel.putConstraint(SpringLayout.EAST, btnUserMenu, -21, SpringLayout.EAST, upperPanel);
		btnUserMenu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnUserMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUserMenu.setForeground(Color.WHITE);
		btnUserMenu.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnUserMenu.setContentAreaFilled(false);
		btnUserMenu.setPreferredSize(new Dimension(150, 60));
		btnUserMenu.setBackground(markD);
		upperPanel.add(btnUserMenu);
		
		
		labelSave = new JLabel("Saved!");
		labelSave.setFont(new Font("Tahoma", Font.PLAIN, 25));
		labelSave.setForeground(Color.white);
		labelSave.setVisible(false);
		sl_upperPanel.putConstraint(SpringLayout.WEST, labelSave, -800 , SpringLayout.EAST, upperPanel);
		sl_upperPanel.putConstraint(SpringLayout.SOUTH, labelSave, -10, SpringLayout.SOUTH, upperPanel);
		sl_upperPanel.putConstraint(SpringLayout.EAST, labelSave, 10, SpringLayout.EAST, upperPanel);
		upperPanel.add(labelSave);
		//Center
		pad = new JTextArea();
		pad.setLayout(new BorderLayout());
		pad.setFont(new Font("Arial" ,Font.PLAIN, 25));
		rootPanel.add(pad, BorderLayout.CENTER);
		
		
		
		
		menuPanel = new JPanel();
		menuPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
		menuPanel.setPreferredSize(new Dimension(200, 300));
		menuPanel.setBackground(markD);
		menuPanel.setLayout(new GridLayout(9,1));
		
		rootPanel.add(menuPanel, BorderLayout.EAST);
		
		btnMyNotes = new JButton("My Notes");
		btnMyNotes.setFocusPainted(false);
		btnMyNotes.setBorder(new EmptyBorder(5, 0, 0, 0));
		btnMyNotes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMyNotes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMyNotes.setForeground(Color.white);
		btnMyNotes.setContentAreaFilled(false);
		menuPanel.add(btnMyNotes);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.setFocusPainted(false);
		btnLogOut.setBorder(new EmptyBorder(5, 0, 0, 0));
		btnLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogOut.setForeground(Color.white);
		btnLogOut.setContentAreaFilled(false);
		menuPanel.add(btnLogOut);
				
		menuPanel.setVisible(false);	
		btnUserMenu.addActionListener(this);
		btnMyNotes.addActionListener(this);
		btnSave.addActionListener(this);
		btnLogOut.addActionListener(this);		
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new Dashboard("Dhruv").setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource() == btnUserMenu) {
			
			if(!menuPanel.isVisible()) {
			menuPanel.setVisible(true);
			btnUserMenu.setText("Hi,"+user_name+ " >");
			}
			else {
			menuPanel.setVisible(false);
			btnUserMenu.setText("Hi,"+user_name+ " <");
			}
			
			
		}
		
		else if(e.getSource()== btnMyNotes) {
			menuPanel.setVisible(false);
			btnUserMenu.setText("Hi,"+user_name+ " <");
			new MyNotes(user_mail).setVisible(true);
			setVisible(false);
			
		}
		else if(e.getSource()== btnSave) {
			try {
				
				String tableName = "user"+ user_number;
				String query = "insert into "+tableName+" (Notes) values('"+pad.getText()+"');";
				System.out.println(query);
				sc = new set_connection();
				sc.ps = sc.connection.prepareStatement(query);
				
				
				if(!sc.ps.execute()) {
					labelSave.setVisible(true);
					System.out.println("Added TableNote");
				}
				else {
					System.out.println("Not Added TableNote");
				}
			}
				
				catch(Exception e1) {
					System.out.println("Dashboard"+e1);
				}
			
		}
		
		else if(e.getSource()== btnLogOut) {
			new Login().setVisible(true);
			dispose();
		}
		
	}
}
