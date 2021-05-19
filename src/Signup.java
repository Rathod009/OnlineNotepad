import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;


public class Signup extends JFrame implements ActionListener {

	JLabel Header, name, emailid, number, pswd, iback;
	JTextField fname,femail,fnumber;
	JPasswordField fpswd;
	JButton signup;
	String data[] = new String[4];
	
	
	Signup(){
		//frame title and othe prop
		super("Signup | YourNotes");
		setSize(1280,720);
        setLocation(500,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	//frame prop end
		
		
		//back button
		iback = new JLabel("<< Back");
		iback.setFont(new Font("Arial",Font.PLAIN,20));
		iback.setForeground(Color.white);
		iback.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
		iback.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//back button end
		
		
		//components
		Header = new JLabel("Signup", SwingConstants.CENTER);
		Header.setFont(new Font("Arial",Font.BOLD, 40));
		
		name = new JLabel("Name");
		name.setFont(new Font("Arial",Font.PLAIN,25));
		
		emailid = new JLabel("Email Id");
		emailid.setFont(new Font("Arial",Font.PLAIN,25));
		
		number = new JLabel("Contact Number");
		number.setFont(new Font("Arial",Font.PLAIN,25));
		
		pswd = new JLabel("Password");
		pswd.setFont(new Font("Arial",Font.PLAIN,25));
		
		
		
		
		fname = new JTextField(20);
		fname.setFont(new Font("Arial",Font.PLAIN,20));
		
		femail = new JTextField();
		femail.setFont(new Font("Arial",Font.PLAIN,20));
		
		fnumber = new JTextField();
		fnumber.setFont(new Font("Arial",Font.PLAIN,20));
		
		fpswd = new JPasswordField();
		fpswd.setFont(new Font("Arial",Font.PLAIN,20));
		
		
		signup = new JButton("Signup");
		signup.setFont(new Font("Arial",Font.PLAIN,18));
		signup.setBackground(Color.black);
	    signup.setForeground(Color.WHITE);
		
	    //componenets end
		
		
	
       
        Container myContain = this.getContentPane();
        myContain.setLayout(new BorderLayout()); //main container
        
        
        JPanel BackGround =  new JPanel(new GridLayout(1,2));
       
        
        JScrollPane sp = new JScrollPane(BackGround);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        myContain.add(sp);
        
        
        //left panel start
        JPanel leftL = new JPanel();
        leftL.setLayout(new BorderLayout());
        leftL.setBackground(new Color(0, 204, 68));
        JLabel yourNotes = new JLabel("<html>YOUR<br>NOTES</html>", SwingConstants.CENTER);
        yourNotes.setBorder(BorderFactory.createEmptyBorder(-40, 0, 0, 0));
        yourNotes.setFont(new Font("Arial", Font.BOLD,45));
        yourNotes.setForeground(Color.WHITE);
        leftL.add(yourNotes, BorderLayout.CENTER);
        leftL.add(iback, BorderLayout.NORTH);
        
        BackGround.add(leftL);
        
        //left panel end
	    
        
        //right panel start
        JPanel rightL = new JPanel();
        rightL.setLayout(new BorderLayout());
        rightL.setBackground(Color.white);
        BackGround.add(rightL);
        
        //upper layer
        JPanel upper = new JPanel();
        upper.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        upper.setBackground(Color.white);
        upper.add(Header);
        //upper end
        
        //middle start
        JPanel middle = new JPanel();
        middle.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));
        middle.setBackground(Color.white);
        middle.setLayout(new GridLayout(6,2,-100,20));
        middle.add(name);
        middle.add(fname);        
        middle.add(emailid);
        middle.add(femail);
        middle.add(pswd);
        middle.add(fpswd);
        middle.add(number);
        middle.add(fnumber);
        
        //middle end
       
        //bottom start 
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        bottom.setBorder(BorderFactory.createEmptyBorder(15, 0, 40, 20));
        signup.setPreferredSize(new Dimension(100, 50));
        bottom.setBackground(Color.white);
        bottom.add(signup);
        //bottom end
        
       
        //layers 
        rightL.add(upper,BorderLayout.NORTH);
        rightL.add(middle,BorderLayout.WEST);
        rightL.add(bottom, BorderLayout.SOUTH);
        
        
        //actions start
        signup.addActionListener(this);
        
        iback.addMouseListener(new MouseAdapter()   {   

             public void mouseClicked(MouseEvent e)   
             {   
            	 new Login().setVisible(true);
                 setVisible(false);
             }   
         });
        //actions end
 	
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stu

				
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	try {
		//text inputs start
		String name = fname.getText();
		String email = femail.getText();
		
		String number = fnumber.getText();
		
		char[] apswd = fpswd.getPassword();
		String pswd = new String(apswd);
		
		
		
		//text inputs end
		
		data[0] = name;
		data[1] = email;
		data[2] = pswd;
		data[3] = number;
		setData();
		
	}
	catch(Exception e1) {
		System.out.println("ahiya"+e1);
	}
	
	}
	
	//to set users data in user table
	void setData() {
		try {
		set_connection sc = new set_connection();
		sc.ps = sc.connection.prepareStatement("insert into user_data(name,email,password,contactno)"
				+ "values(?,?,?,?);");
		
		sc.ps.setString(1,data[0] );
		sc.ps.setString(2, data[1]);
		sc.ps.setString(3, data[2]);
		sc.ps.setString(4, data[3]);
		
		if(!sc.ps.execute()) {
			System.out.println("Added");
		}
		else
			System.out.println("Not DAta Added");
		String dbname = "create table user" +data[3] + "(Id int primary key not null AUTO_INCREMENT, Notes varchar(5000));";
		sc.ps = sc.connection.prepareStatement(dbname);
		
		
		if(!sc.ps.execute()) {
			System.out.println("Table Added");
			Dashboard db = new Dashboard(data[1]);
			db.setVisible(true);
			dispose();
		}
		else
			System.out.println("Not Table Added");
		}
		
		
		catch(Exception e) {
			System.out.println(e);
		}
	}


	
	

}
