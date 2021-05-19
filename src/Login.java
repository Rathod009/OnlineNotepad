import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Login extends JFrame implements ActionListener{
	JLabel Header, uid, pswd, forgot, signup, Headerline;
	JTextField fuid;
	JPasswordField fpswd;
	JButton logIn;
	
	
	
	Login(){
		super("LOGIN | YourNotes");
		setSize(1280,720);
        setLocation(500,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//components
		
		Header = new JLabel("LOGIN", SwingConstants.CENTER);
		Header.setFont(new Font("Arial",Font.BOLD,45));
		
		
		Headerline = new JLabel("Welcome back!", SwingConstants.CENTER);
		Headerline.setFont(new Font("Century Gothic",Font.BOLD,20));
		
		uid =  new JLabel("User Id");
		uid.setFont(new Font("Arial" ,Font.PLAIN, 25));
		
		pswd = new JLabel("Password");
		pswd.setFont(new Font("Arial" ,Font.PLAIN, 25));
		
		forgot = new JLabel("Forgot Password?",SwingConstants.CENTER);
		forgot.setFont(new Font("Arial" ,Font.PLAIN,12));
		
		signup = new JLabel("Create a new account!", SwingConstants.CENTER);
		signup.setFont(new Font("Arial" ,Font.PLAIN, 12));
		
		fuid = new JTextField(10);
		fuid.setFont(new Font("Arial" ,Font.PLAIN, 25));
		
		fpswd = new JPasswordField(10);
		fpswd.setFont(new Font("Arial" ,Font.PLAIN, 25));
		

		logIn = new JButton("Login");
		logIn.setBackground(Color.black);
	    logIn.setForeground(Color.WHITE);
	     
	    setLayout(null);
	    
	  
	  
        Container myContain = this.getContentPane();
         
        myContain.setLayout(new BorderLayout()); //main container
        
        
        
        JPanel BackGround =  new JPanel(new GridLayout(1,2));//first panel
        
        JScrollPane sp = new JScrollPane(BackGround);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        myContain.add(sp);
        
       
        
        //left panel start
        JPanel leftL = new JPanel();
        leftL.setLayout(new BorderLayout());
        leftL.setBackground(new Color(0, 204, 68));
        JLabel yourNotes = new JLabel("<html>YOUR<br>NOTES</html>", SwingConstants.CENTER);
        yourNotes.setFont(new Font("Arial", Font.BOLD,45));
        yourNotes.setForeground(Color.WHITE);
        leftL.add(yourNotes);
        BackGround.add(leftL);
        
        //left panel end
         
        
        
        //right panel start
        Header.setForeground(new Color(0, 51, 0));
        
        JPanel rightL =  new JPanel(new GridLayout(3,1));
        rightL.setBackground(Color.white);
        
        //upper panel
        JPanel upper =  new JPanel(new GridLayout(2,1,0,0));
        upper.setBackground(Color.WHITE);
        
        upper.setBorder(BorderFactory.createEmptyBorder(40, 0, 00, 0));
        Header.setBorder(BorderFactory.createEmptyBorder(05, 0, 0, 0));
     
        upper.add(Header);
        upper.add(Headerline);
        
        //upper panel end
        
        //middle panel starts
        
        JPanel middle =  new JPanel(new GridLayout(4,2,10,10));
        middle.setBackground(Color.white);
        
        middle.setBorder(BorderFactory.createEmptyBorder(00, 100, 00, 100));
        
        middle.add(uid);
        middle.add(fuid);
       
        middle.add(pswd);
        middle.add(fpswd);
        
        //middle panel ends
        
        //bottom panel starts
        
        JPanel bottom =  new JPanel(new GridLayout(3,1,20,20));
        bottom.setBackground(Color.white);
        
        bottom.setBorder(BorderFactory.createEmptyBorder(30, 100, 00, 100)); 
        
        logIn.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
         
         bottom.add(logIn);
         bottom.add(forgot);
         bottom.add(signup);
         
         //bottom panel ends
       
       
        rightL.add(upper);
        rightL.add(middle);
        rightL.add(bottom);
        
        BackGround.add(rightL);
        
        //rigth panel ends
       
         logIn.addActionListener(this);
         
         
         forgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         forgot.addMouseListener(new MouseAdapter()   {   

             public void mouseClicked(MouseEvent e)   
             {   
                 new forgotpswd().setVisible(true);
                 setVisible(false);
                 dispose();
             }   
         });
         
         signup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         signup.addMouseListener(new MouseAdapter()   {   

             public void mouseClicked(MouseEvent e)   
             {   
            	
            	 new Signup().setVisible(true);
                 setVisible(false);
                 dispose();
             }   
         });
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		try{
			
		set_connection sc = new set_connection();
		String id = fuid.getText();
		char[] pwd = fpswd.getPassword();
		String pswd = new String(pwd);
		
		sc.ps = sc.connection.prepareStatement("select * from user_data where email = '"+id+"' and password = '"+pswd+"'");
		
		
		ResultSet rs = sc.ps.executeQuery();
		if(rs.next()) {
			Dashboard db = new Dashboard(id);
			db.setVisible(true);
			dispose();
			
		}
		
		}
		
		catch(Exception e) {
			
			System.out.println(e);
		}
	}
	
	public static void main(String[] args){
        new Login().setVisible(true);
    } 

}
