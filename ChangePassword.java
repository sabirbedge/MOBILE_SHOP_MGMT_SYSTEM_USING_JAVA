import java.sql.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ChangePassword extends JFrame implements ActionListener
{
	Container co;
	JTextField txt;
	JPasswordField newp,conf;
	JLabel l1,l2,l3,l4;
	JButton b1,b2,b3;
	Connection con;
	PreparedStatement ps;

	public ChangePassword()
	{
	setLayout(null);
	setVisible(true);
	setSize(600,500);
	setTitle("Change Password");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	co=getContentPane();
	co.setBackground(Color.green);
	txt=new JTextField();
	newp=new JPasswordField();
	conf=new JPasswordField();
	l3=new JLabel("CHANGE PASSWORD");
	l1=new JLabel("Old Password");
	l2=new JLabel("New Password");
	l4=new JLabel("Confirm Password");
	b1=new JButton("Change");
	b2=new JButton("Clear");
	b3=new JButton("Login");

	l3.setBounds(170,50,250,30);
	l3.setFont(new Font("Candara",Font.BOLD,25));
	l1.setBounds(150,150,100,30);
	l2.setBounds(150,200,100,30);
	l4.setBounds(150,250,120,30);

	txt.setBounds(270,150,150,30);
	newp.setBounds(270,200,150,30);
	conf.setBounds(270,250,150,30);
	
	b1.setBounds(150,330,100,30);
	b2.setBounds(300,330,100,30);
	b3.setBounds(20,50,100,30);
	
	co.add(l3);	co.add(l2);	co.add(l1);	co.add(l4);	co.add(txt);	co.add(newp);	co.add(conf);	co.add(b1);	co.add(b2);
	co.add(b3);
	b1.addActionListener(this);	b2.addActionListener(this);	b3.addActionListener(this);
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
	public void clear()
	{
	txt.setText("");
	newp.setText("");
	conf.setText("");
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
		set();
			try
			{
			String p,a,b,c;
			p="";	a="";	b="";	c="";
			PreparedStatement ps=con.prepareStatement("select * from tbl_login");
			ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
				p=rs.getString("pass");
				a=txt.getText();
				b=newp.getText();
				c=conf.getText();
				
				}
				if(a.equals(p) && b.equals(c))
				{
				System.out.println("a");	
				ps=con.prepareStatement("update tbl_login set pass=? where id=? ");
				ps.setString(1,b);
				ps.setInt(2,1);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(this,"Password changed successfully ...");
				con.close();
				}
				else
				{
				JOptionPane.showMessageDialog(null,"Invalid password ...");
				 clear();
				}
			
			}
			catch(Exception exc){}
		}

		if(e.getSource()==b2)
		{
		clear();
		}
		if(e.getSource()==b3)
		{
		clear();
		this.setVisible(false);
		Login l=new Login();
		l.setVisible(true);
		}
	}
	public static void main(String arg[])
	{
	new ChangePassword();
	}
}