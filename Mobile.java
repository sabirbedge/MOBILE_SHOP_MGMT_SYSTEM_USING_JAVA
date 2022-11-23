import java.sql.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
public class Mobile extends JFrame implements ActionListener
{
	Container co;
	JButton b1,b2,b3;
	JTextField t1,t2,t3,t4,t5,t6,t;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JTable tbl;
	DefaultTableModel model;
	JScrollPane pane;
	Connection con;
	PreparedStatement ps;
	public Mobile()
	{
	setLayout(null);
	setVisible(true);
	setSize(1500,800);
	setTitle("Mobile Repairing");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	co=getContentPane();
	co.setBackground(Color.green);

	model=new DefaultTableModel();
	tbl=new JTable(model);
	model.addColumn("First_name");
	model.addColumn("Last_Lname");
	model.addColumn("Comp_name");
	model.addColumn("Model");
	model.addColumn("Date");
	model.addColumn("Issue");
	model.addColumn("Charges");

	b1=new JButton("HOME");
	b2=new JButton("ADD");
	b3=new JButton("CLEAR");
	
	l1=new JLabel(" First Name ");
	l2=new JLabel(" Company Name  ");
	l3=new JLabel("Date");
	l4=new JLabel("Charges");
	l5=new JLabel("MOBILE REPAIRING");
	l6=new JLabel(" Last Name");
	l7=new JLabel("Model");
	l8=new JLabel("Issue");
	
	t1=new JTextField();
	t2=new JTextField();
	t3=new JTextField();
	t4=new JTextField();
	t5=new JTextField();
	t6=new JTextField();
	t=new JTextField();

	l5.setBounds(500,20,400,50);
	l5.setFont(new Font("Verdana",Font.BOLD,30));
	l1.setBounds(100,100,150,30);
	l2.setBounds(100,150,150,50);
	l3.setBounds(100,200,150,50);
	l4.setBounds(100,250,150,50); 
	l6.setBounds(700,100,150,30);
	l7.setBounds(700,150,150,50);
	l8.setBounds(700,200,150,50);

	t1.setBounds(250,100,150,30);
	t.setBounds(250,150,150,30);
	t2.setBounds(250,200,150,30);
	t3.setBounds(250,250,150,30);
	t4.setBounds(900,100,150,30);
	t5.setBounds(900,150,150,30);
	t6.setBounds(900,200,150,30);

	b1.setBounds(100,20,100,30);
	b2.setBounds(450,300,100,30);
	b3.setBounds(650,300,100,30);


	pane=new JScrollPane(tbl);
	pane.setBounds(50,450,1200,200);

	
	co.add(b1);	co.add(b2);	co.add(b3);	
	co.add(l1);	co.add(l2);	co.add(l3);	co.add(l4);	co.add(l5);	co.add(l6);	co.add(l7);	co.add(l8);
	co.add(t1);	co.add(t2);	co.add(t3);	co.add(t);	co.add(t4);	co.add(t5);	co.add(t6);
	co.add(pane);
	b1.addActionListener(this);	b2.addActionListener(this);	b3.addActionListener(this);
select();
	}	
	public void set()
	{
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");	
		con=DriverManager.getConnection("jdbc:odbc:mob_dsn");
		}
		catch(Exception exc){ }
	}

	public void clear()
	{
	t1.setText("");
	t2.setText("");
	t3.setText("");
	t4.setText("");
	t5.setText("");
	t6.setText("");
	t.setText("");
	}
	public void select()
	{
		set();
		   try
		 {
           		 ps= con.prepareStatement("select * from tbl_mob ");
            		ResultSet rs = ps.executeQuery();
		model.setRowCount(0);
       		     while(rs.next())
		{
		 try
		 {
              		  model.addRow(new Object[]{rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
		  } catch (Exception e){  }
           		 }

      		  } catch (Exception e){  }
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b2)
		{
		set();
			try
			{
				ps=con.prepareStatement("insert into tbl_mob values(?,?,?,?,?,?,?)");
				ps.setString(1,t1.getText());
				ps.setString(2,t4.getText());
				ps.setString(3,t.getText());
				ps.setString(4,t5.getText());
				ps.setString(5,t2.getText());
				ps.setString(6,t6.getText());
				ps.setString(7,t3.getText());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(this," Record inserted successfully ...");
				con.close();
				select();
				 clear();
			}
			catch(Exception exc){}
		}
		
		if(e.getSource()==b3)
		{
		 clear();
		}

		if(e.getSource()==b1)
		{
		Home h=new Home();
		h.setVisible(true);
		this.setVisible(false);
		}
	}
	
	public static void main(String arg[])
	{
	new Mobile();
	}
}