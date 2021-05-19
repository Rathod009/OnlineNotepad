import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;




public class forgotpswd extends JFrame implements ActionListener{

	JLabel number,name,header;
	JTextField fnumber,fname;
	JButton next;
	
	
	
	forgotpswd(){
		
		//basic preset
		super("Forgot Password? | YourNotes");
		setSize(1280,720);
        setLocation(500,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//basic preset ends
		
		
		//back button
		JLabel iback = new JLabel("<< Back");
		iback.setFont(new Font("Arial",Font.PLAIN,20));
		iback.setForeground(Color.white);
		iback.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
		iback.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//back button end
		
		//header 
		header = new JLabel("Oops ! Forgot", SwingConstants.CENTER);
		header.setFont(new Font("Arial",Font.BOLD,30));
		header.setForeground(Color.white);
		
		//contact number
		number = new JLabel("Contact Number");
		number.setFont(new Font("Arial",Font.PLAIN,20));
		number.setForeground(Color.white);
		
		//user name
		name = new JLabel("Name");
		name.setFont(new Font("Arial",Font.PLAIN,20));
		name.setForeground(Color.white);
		
		//contact number field
		fnumber = new JTextField(20);
		fnumber.setFont(new Font("Arial",Font.PLAIN,20));
		fnumber.setForeground(Color.white);
		fnumber.setBackground(new Color(0, 204, 68));
		
		//user name field
		fname = new JTextField(20);
		fname.setFont(new Font("Arial",Font.PLAIN,20));
		fname.setForeground(Color.white);
		fname.setBackground(new Color(0, 204, 68));
		
		//next button for "Next" frame
		next = new JButton("Next");
		next.setFont(new Font("Arial",Font.PLAIN,18));
		next.setForeground(new Color(0, 204, 68));
		next.setBackground(Color.white);
		next.setPreferredSize(new Dimension(100, 50));
		
		
		//main container		
		Container myContain = this.getContentPane();
		
		myContain.setLayout(new BorderLayout());
		//main container ends
		
		 JPanel BackGround =  new JPanel(new BorderLayout());//first panel
	        
	        JScrollPane sp = new JScrollPane(BackGround);
	        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        myContain.add(sp);
	        BackGround.setBackground(new Color(0, 204, 68));
		
		
		
		//upper-miidle
		JPanel backback = new JPanel();
		backback.setLayout(new GridLayout(5,1,10,10));
		backback.setBackground(new Color(0, 204, 68));
		backback.setBorder(BorderFactory.createEmptyBorder(80, 390, 80, 420));
	
		
		backback.add(name);
		backback.add(fname);
		backback.add(number);
		backback.add(fnumber);
		//upper-middle ends
		
		
		//bottom
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(0, 204, 68));
		bottom.add(next);
		//bottom ends
		
		
		header.setBorder(BorderFactory.createEmptyBorder(60, 370, 30, 370));
		bottom.setBorder(BorderFactory.createEmptyBorder(10, 370, 90, 370));

		BackGround.add(iback,BorderLayout.BEFORE_LINE_BEGINS);
		BackGround.add(header, BorderLayout.NORTH);
		BackGround.add(backback, BorderLayout.CENTER);
		BackGround.add(bottom,BorderLayout.SOUTH);
		
	
		//actions
		next.addActionListener(this);
		
		
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

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//user inputs
		String Name = fname.getText();
		String Number = fnumber.getText();
		//user inputs end
		
		//otp varification
		NumberVarification nv = new NumberVarification(Name, Number);
		nv.setVisible(true);
		setVisible(false);
		dispose();
		//otp varifications ends
	
		
	}


	//for number varification ...go through numbervarification class
	public void goToNext(NumberVarification nv,String Name,String Number) {
		
		//check wheather otp is valid or not
		if(nv.isVarify()) {
			new setNewPswd(Name,Number).setVisible(true);
			nv.setVisible(false);
		}
		
	}

}
