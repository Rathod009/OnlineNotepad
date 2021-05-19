import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;



public class MyNotes extends JFrame implements ActionListener{


	JTable notesData;
	String user_mail;
	set_connection sc;
	String user_name, user_number;
	JButton btnUserMenu,btnEditor,btnDelete,btnEdit;
	JPanel menuPanel;
	JTextField fnoteid;
	DefaultTableModel modelTable; 
	
	MyNotes(String id){
		
		//basic preset
		super("MyNotes | YourNotes");		
		setSize(1280,720);
        setLocation(500,200);		
        Color markD  = new Color(0, 204, 68);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		user_mail = id;
		//data connection
		try {
		sc = new set_connection();
		sc.ps = sc.connection.prepareStatement("select * from user_data where email=?");
		sc.ps.setString(1, user_mail);
		ResultSet rs = sc.ps.executeQuery();
		
		if (rs.next()) {
				user_name = rs.getString(1);
				user_number = rs.getString(4);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		//data connection ends
		
		
		JPanel rootPanel = new JPanel();
		rootPanel.setLayout(new BorderLayout(0,0));
		getContentPane().add(rootPanel,BorderLayout.CENTER);
		
		JPanel upperPanel = new JPanel();
		upperPanel.setPreferredSize(new Dimension(10, 80));
		upperPanel.setBackground(markD);
		SpringLayout sl_upperPanel = new SpringLayout();
		upperPanel.setLayout(sl_upperPanel);
		
		btnEditor = new JButton("Back To Editor");
		btnEditor.setBackground(Color.WHITE);
		btnEditor.setForeground(Color.BLACK);
		btnEditor.setFocusPainted(false);
		btnEditor.setFocusPainted(false);
		btnEditor.setBorderPainted(false);
		btnEditor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sl_upperPanel.putConstraint(SpringLayout.NORTH, btnEditor, 10, SpringLayout.NORTH, upperPanel);
		sl_upperPanel.putConstraint(SpringLayout.WEST, btnEditor, 10, SpringLayout.WEST, upperPanel);
		sl_upperPanel.putConstraint(SpringLayout.SOUTH, btnEditor, -10, SpringLayout.SOUTH, upperPanel);
		upperPanel.add(btnEditor);
		
		btnUserMenu = new JButton("Hi,"+user_name);
		btnUserMenu.setFocusPainted(false);
		sl_upperPanel.putConstraint(SpringLayout.WEST, btnUserMenu, -171, SpringLayout.EAST, upperPanel);
		sl_upperPanel.putConstraint(SpringLayout.SOUTH, btnUserMenu, 0, SpringLayout.SOUTH, btnEditor);
		sl_upperPanel.putConstraint(SpringLayout.EAST, btnUserMenu, -21, SpringLayout.EAST, upperPanel);
		
		btnUserMenu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnUserMenu.setForeground(Color.WHITE);
		btnUserMenu.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnUserMenu.setContentAreaFilled(false);
		btnUserMenu.setPreferredSize(new Dimension(150, 60));
		btnUserMenu.setBackground(markD);
		upperPanel.add(btnUserMenu);
		
		JPanel centerPanel = new JPanel();		
		rootPanel.add(upperPanel, BorderLayout.NORTH);
		rootPanel.add(centerPanel,BorderLayout.CENTER);
		
		notesData = new JTable();
	
		
		centerPanel.setLayout(new BorderLayout(0,0));
		centerPanel.add(notesData,BorderLayout.CENTER);
		
		
		modelTable = new DefaultTableModel();
		
		modelTable.addColumn("ID");
		modelTable.addColumn("Notes");
		notesData.setModel(modelTable);
		
		notesData.getColumnModel().getColumn(0).setPreferredWidth(50);
		notesData.getColumnModel().getColumn(1).setPreferredWidth(1220);
		notesData.setRowHeight(50);
		
		JScrollPane sp = new JScrollPane(centerPanel);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rootPanel.add(sp);
        
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(markD);
        rootPanel.add(bottomPanel,BorderLayout.SOUTH);
        btnDelete = new JButton("Delete Note");
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnDelete.setBackground(Color.white);
        btnDelete.setForeground(Color.black);
        
        fnoteid = new JTextField();
        fnoteid.setBackground(Color.white);
        fnoteid.setFont(new Font("Tahoma", Font.PLAIN, 20));
        fnoteid.setText("Enter Note ID");
       
    
        btnEdit = new JButton("Edit Note");
        btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnEdit.setBackground(Color.white);
        btnEdit.setForeground(Color.black);
        
        bottomPanel.add(fnoteid);
        bottomPanel.add(btnDelete);
        bottomPanel.add(btnEdit);
                
        
		btnEditor.addActionListener(this);
		btnEdit.addActionListener(this);
		btnDelete.addActionListener(this);
		
		
		//data connection
				try {
				sc = new set_connection();			
				String dbname = "user"+user_number;
				
				String query = "Select * from "+dbname;
				sc.ps = sc.connection.prepareStatement(query);
				ResultSet rs = sc.ps.executeQuery();
				
				while(rs.next()) {
					modelTable.addRow(new Object[]{rs.getInt(1), rs.getString(2)});
				}
				
				
				}
				catch(Exception e) {
					System.out.println(e);
				}
				//data connection ends
		
		
		
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == btnEditor) {
				new Dashboard(user_mail).setVisible(true);
				dispose();
			}
			
			else if(e.getSource() == btnDelete) {
				//data connection
				try {
					String tableName = "user"+user_number;
					String idNote = fnoteid.getText();
					String query = "delete from "+tableName+" where Id ="+idNote;
					
					sc.ps = sc.connection.prepareStatement(query);
					if(!sc.ps.execute())
						System.out.println("Deleted");
					
				}
				catch(Exception e1) {
					System.out.println(e);
				}
				//data connection ends
			}
		}
	}

