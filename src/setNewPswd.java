import java.awt.event.*;
import java.sql.ResultSet;
import java.awt.*;
import javax.swing.*;


public class setNewPswd extends JFrame implements ActionListener{
	
	//components
	JLabel pswd,header;
	JPasswordField fpswd;
	JButton change;
	String Name, Number;
	

	//constructor
	public setNewPswd(String name, String number) {
		
		
		//basic preset starts
		super("Set New Password! | YourNotes");
		setSize(1280,720);
        setLocation(500,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//basic preset ends
		
		Color bg = new Color(0, 204, 68); // software green colour
				
		Name = name; //user name
		Number = number;//user number
		
		
		//header details
		header = new JLabel("Set New Password!", SwingConstants.CENTER);
		header.setFont(new Font("Arial",Font.BOLD, 40));
		header.setForeground(Color.white);
		
		//pswd details
		pswd = new JLabel("New Password");
		pswd.setFont(new Font("Arial",Font.PLAIN, 30));
		pswd.setForeground(Color.white);
		
		//pswd field
		fpswd = new JPasswordField(20);
		fpswd.setFont(new Font("Arial",Font.BOLD, 30));
		
		//change button
		change = new JButton("Change");
		change.setFont(new Font("Arial",Font.BOLD, 25));
		change.setForeground(bg);
		change.setBackground(Color.white);
		change.setSize(new Dimension(100,50));
		
		//main container starts
		 Container myContain = this.getContentPane();
	     myContain.setLayout(new BorderLayout()); 
	    
	     
	     JPanel BackGround =  new JPanel(new BorderLayout());//back panel
	     BackGround.setBackground(bg);
	        
	        JScrollPane sp = new JScrollPane(BackGround);
	        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        myContain.add(sp);
	     
	     
	     //main container ends
	     
	     //top layer starts
	     JPanel top = new JPanel();
	     top.setLayout(new GridLayout(3,1,25,25));
	     top.setBackground(bg);
	     top.setBorder(BorderFactory.createEmptyBorder(100, 360, 210, 360));
	      
	   
	     //add according to layers
	     
	     top.add(pswd);
	     top.add(fpswd);
	     top.add(change);
	     
	     
	   header.setBorder(BorderFactory.createEmptyBorder(50, 370, 20, 370)); //header border
	   BackGround.add(header,BorderLayout.NORTH);
	   BackGround.add(top,BorderLayout.CENTER);
	     
	     //actions
	     change.addActionListener(this);
	     
	     
	     

		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	try {
		set_connection sc = new set_connection();
		char[] c = fpswd.getPassword();
		String s = new String(c);
		
		sc.ps = sc.connection.prepareStatement("update user_data set password = '" +s+ "' where contactno = '"+Number+"'");
		if(!sc.ps.execute()) {
			System.out.println("Changed");
			new Login().setVisible(true);
			dispose();
		}
		else
			System.out.println("Not Data Changed");
	
	}
	catch(Exception e) {
	System.out.println(e);
	}
	
	}
		
	

}
