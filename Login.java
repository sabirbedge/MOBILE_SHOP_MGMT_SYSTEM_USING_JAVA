import java.sql.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Login extends JFrame implements ActionListener
{
	Container co;
	JTextField txt;
	JPasswordField pass;
	JLabel l1,l2,l3;
	JButton b1,b2,b3;
	JCheckBox ch;
	Connection con;
	PreparedStatement ps;
	public Login()
	{
	setLayout(null);
	setVisible(true);
	setSize(600,500);
	setTitle("Admin Login");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	co=getContentPane();
	co.setBackground(Color.green);
	txt=new JTextField();
	pass=new JPasswordField();
	l1=new JLabel("Username");
	l2=new JLabel("Password");
	l3=new JLabel("LOGIN"); 
	b1=new JButton("Login");
	b2=new JButton("Clear");
	b3=new JButton("Change Password");
	ch=new JCheckBox("Show Password");

	l3.setBounds(240,50,100,30);
	l3.setFont(new Font("Candara",Font.BOLD,30));
	l1.setBounds(150,150,100,30);
	l2.setBounds(150,200,100,30);
	txt.setBounds(250,150,150,30);
	pass.setBounds(250,200,150,30);	
	ch.setBounds(250,250,130,30);	
	ch.setBackground(Color.green);
	b1.setBounds(150,300,100,30);
	b2.setBounds(300,300,100,30);
	b3.setBounds(300,350,150,30);
	
	co.add(l3);	co.add(l2);	co.add(l1);	co.add(txt);	co.add(pass);	co.add(ch);	co.add(b1);	co.add(b2);	co.add(b3);
	b1.addActionListener(this);		b3.addActionListener(this);	b2.addActionListener(this);
	set();
	}	
	public void set()
	{

		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");	
		con=DriverManager.getConnection("jdbc:odbc:mob_dsn");
		}
		catch(Exception exc){}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
		set();
			try
			{
			String u,p,a,b;
			u="";	p="";	a="";	b="";
			PreparedStatement ps=con.prepareStatement("select * from tbl_login");
			ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
				u=rs.getString("unm");
				p=rs.getString("pass");
				a=txt.getText();
				b=pass.getText();
				
				}
				if(a.equals(u) && b.equals(p))
				{
				JOptionPane.showMessageDialog(this,"Login successfully...");
				con.close();
				Home h=new Home();
				h.setVisible(true);
				this.setVisible(false);
				}
				else
				{
				JOptionPane.showMessageDialog(null,"Login failed...");
				 clear();
				}
			
			}
			catch(Exception exc){}
		
		}
		if(e.getSource()==b3)
		{
		ChangePassword cp=new ChangePassword();
		cp.setVisible(true);
		}
		if(e.getSource()==b2)
		{
		 clear();
		}
		
	}
	public void clear()
	{
	txt.setText("");
	pass.setText("");
	}
	
	public static void main(String arg[])
	{
	new Login();
	}
}