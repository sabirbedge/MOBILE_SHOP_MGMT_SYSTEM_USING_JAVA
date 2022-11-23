import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Home extends JFrame implements ActionListener
{
	Container co;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
	JLabel l,l2;
	ImageIcon i;
	public Home()
	{
	setLayout(null);
	setVisible(true);
	setSize(1500,800);
	setTitle("Home : Mobile Shop Management");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	co=getContentPane();
	co.setBackground(Color.green);

	l=new JLabel();
	i=new ImageIcon("mobile.png");
	l.setIcon(i);
	l.setBounds(600,150,300,300);

	l2=new JLabel("MOBILE SHOP MANAGEMENT");
	l2.setBounds(450,20,400,100);
	l2.setFont(new Font("Candara",Font.BOLD,30));

	b1=new JButton("HOME");
	b2=new JButton("CUSTOMER");
	b3=new JButton("EMPLOYEE");
	b4=new JButton("PRODUCT");
	b5=new JButton("STOCK");
	b6=new JButton("ATTENDANCE");
	b7=new JButton("MOBILE REPAIR");
	b8=new JButton("LOG OUT");
	b9=new JButton("SALARY");

	b1.setBounds(5,50,150,50);
	b2.setBounds(5,150,150,50);
	b3.setBounds(5,250,150,50);
	b4.setBounds(5,350,150,50);
	b9.setBounds(5,450,150,50);
	b5.setBounds(1200,50,150,50);
	b6.setBounds(1200,150,150,50);
	b7.setBounds(1200,250,150,50);
	b8.setBounds(1200,350,150,50);

	co.add(b1);	co.add(b2);	co.add(b3);	co.add(b4);	co.add(b5);	co.add(b6);	co.add(b7);	co.add(b8);	co.add(b9);
	b8.addActionListener(this);	b4.addActionListener(this);	b2.addActionListener(this);	b5.addActionListener(this);	b7.addActionListener(this);	b3.addActionListener(this);
	b6.addActionListener(this);	b9.addActionListener(this);
	co.add(l);	co.add(l2);
	}	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b8)
		{
		Login l=new Login();
		l.setVisible(true);
		this.setVisible(false);
		}

		if(e.getSource()==b4)
		{
		Product p=new Product();
		p.setVisible(true);
		this.setVisible(false);
		}
		
		if(e.getSource()==b2)
		{
		Customer c=new Customer();
		c.setVisible(true);
		this.setVisible(false);
		}
	
		if(e.getSource()==b5)
		{
		Stock s=new Stock();
		s.setVisible(true);
		this.setVisible(false);
		}

		if(e.getSource()==b7)
		{
		Mobile m=new Mobile();
		m.setVisible(true);
		this.setVisible(false);
		}
		if(e.getSource()==b3)
		{
		Employee emp=new Employee();
		emp.setVisible(true);
		this.setVisible(false);
		}
		if(e.getSource()==b6)
		{
		Attendance at=new Attendance();
		at.setVisible(true);
		this.setVisible(false);
		}
		if(e.getSource()==b9)
		{
		Salary sal=new Salary();
		sal.setVisible(true);
		this.setVisible(false);
		}
	}
	
	public static void main(String arg[])
	{
	new Home();
	}
}