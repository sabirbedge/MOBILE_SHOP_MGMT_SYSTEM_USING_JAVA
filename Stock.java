import java.sql.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
public class Stock extends JFrame implements ActionListener
{
	Container co;
	JButton b1,b2,b3;
	JTextField t1,t2,t3,t4;
	JLabel l1,l2,l3,l4,l5;
	JTable tbl;
	DefaultTableModel model;
	JScrollPane pane;
	Connection con;
	PreparedStatement ps;
	public Stock()
	{
	setLayout(null);
	setVisible(true);
	setSize(1500,800);
	setTitle("Stock");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	co=getContentPane();
	co.setBackground(Color.green);

	model=new DefaultTableModel();
	tbl=new JTable(model);
	model.addColumn("Prod_ID");
	model.addColumn("Prod_name");
	model.addColumn("Comp_name");
	model.addColumn("QuantityLeft");

	b1=new JButton("HOME");
	b2=new JButton("CHECK");
	b3=new JButton("CLEAR");
	
	l1=new JLabel(" Product ID");
	l2=new JLabel(" Product Name ");
	l3=new JLabel(" Company Name ");
	l4=new JLabel("Quantity Left");
	l5=new JLabel("STOCK");
	
	t1=new JTextField();
	t2=new JTextField();
	t3=new JTextField();
	t4=new JTextField();

	l5.setBounds(600,20,200,50);
	l5.setFont(new Font("Verdana",Font.BOLD,30));
	l1.setBounds(500,100,150,30);
	l2.setBounds(500,150,150,50);
	l3.setBounds(500,200,150,50);
	l4.setBounds(500,250,150,50); 

	t1.setBounds(700,100,150,30);
	t2.setBounds(700,150,150,30);
	t3.setBounds(700,200,150,30);
	t4.setBounds(700,250,150,30);


	b1.setBounds(100,20,100,30);
	b2.setBounds(500,300,100,30);
	b3.setBounds(700,300,100,30);

	pane=new JScrollPane(tbl);
	pane.setBounds(50,450,1200,200);
	
	co.add(b1);	co.add(b2);	co.add(b3);		
	co.add(l1);	co.add(l2);	co.add(l3);	co.add(l4);	co.add(l5);
	co.add(t1);	co.add(t2);	co.add(t3);	co.add(t4);
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
	}
	public void select()
	{
		set();
		   try
		 {
           		 ps= con.prepareStatement("select prod_id,prod_nm,comp_nm,quantity from tbl_product ");
            		ResultSet rs = ps.executeQuery();
		model.setRowCount(0);
       		     while(rs.next())
		{
		 try
		 {
              		  model.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4)});
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
           			 ps= con.prepareStatement("select quantity from tbl_product where prod_id=? and prod_nm=? and comp_nm=?");
				ps.setString(1,t1.getText());
				ps.setString(2,t2.getText());
				ps.setString(3,t3.getText());
	
            			ResultSet rs = ps.executeQuery();
			 while(rs.next())
			{
			t4.setText(rs.getString("quantity"));
			}
				con.close();
				
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
	new Stock();
	}
}