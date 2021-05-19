import java.sql.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.*;


public class NumberVarification extends JFrame implements ActionListener {
	
	String Name, Number;
	JLabel eotp;
	JTextField feotp;
	JButton varify;
	JButton resend;
	boolean flag;
	int caller;
	
	
	//rand otp
	Random rand = new Random();
	int otp = rand.nextInt(9999);
	//rand otp ends
	
	
	
	//constructor
	NumberVarification(String name, String number){
		
		//basic preset
		super("Varify Account | YourNotes");		
		setSize(1280,720);
        setLocation(500,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//basic preset end
		
		
		Name = name; //user name
		Number = number;//user number
		
		sendOTP smsg = new sendOTP(name,number,otp);//to send otp
		
		//components
		eotp =  new JLabel("Enter OTP");
		eotp.setFont(new Font("Arial", Font.BOLD, 30));
		eotp.setForeground(Color.white);
		
		feotp = new JTextField(15);
		feotp.setFont(new Font("Arial",Font.PLAIN,20));
		feotp.setForeground(Color.white);
		
		varify = new JButton("Varify ");
		varify.setFont(new Font("Arial",Font.PLAIN,20));
		varify.setBackground(Color.white);
		varify.setForeground(new Color(0, 204, 68));
		
		resend = new JButton("Resend");
		resend.setFont(new Font("Arial",Font.PLAIN,20));
		resend.setBackground(Color.white);
		resend.setForeground(new Color(0, 204, 68));
		//components end
		
				//back button
				JLabel iback = new JLabel("<< Back");
				iback.setFont(new Font("Arial",Font.PLAIN,20));
				iback.setForeground(Color.white);
				iback.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
				iback.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				//back button end
		
       
        Container myContain = this.getContentPane();
        myContain.setLayout(new BorderLayout()); 
        //main container 
        
        JPanel BackGround =  new JPanel(new BorderLayout());//back panel
        BackGround.setBackground(new Color(0, 204, 68));
        
        JScrollPane sp = new JScrollPane(BackGround);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        myContain.add(sp);
        
        
        JPanel opan = new JPanel();//first panel
        opan.setBackground(new Color(0, 204, 68));
        opan.setLayout(new GridLayout(4,1,50,50));
        opan.setBorder(BorderFactory.createEmptyBorder(150, 400, 150, 400));
        opan.add(eotp);
        feotp.setBackground(new Color(0, 204, 68));
        opan.add(feotp);
        opan.add(varify);
        opan.add(resend);
        
        BackGround.add(iback, BorderLayout.NORTH);
        BackGround.add(opan);
        
        //actions
		varify.addActionListener(this);
		resend.addActionListener(this);
		 
		iback.addMouseListener(new MouseAdapter()   {   

             public void mouseClicked(MouseEvent e)   
             {   
            	 new Login().setVisible(true);
                 setVisible(false);
             }   
         });
		
		//actions end
		
		System.out.println(otp); //for progammer help
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int varo = Integer.parseInt(feotp.getText()); //user input otp 
		
		//for verify button
		if(e.getSource() ==  varify) {
			
		System.out.println("Clicked"+varo);	
		if(varo==otp) {
		flag=true;
		new forgotpswd().goToNext(this,Name,Number);
		}
		else
		resend.setText("Invalid! Click to Resend ");
		}	
		
		//for varify button ends
		
		
		//for resend button
		if(e.getSource() == resend) {
			new sendOTP(Name, Number,otp);
			
		}//for resend button ends
		
	}
		
	//return varified or not
	boolean isVarify() {	
		return flag;
	}
}
