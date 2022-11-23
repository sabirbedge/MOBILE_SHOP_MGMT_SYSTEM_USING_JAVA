import java.sql.*;
import java.lang.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
public class Employee extends JFrame implements ActionListener
{
	Container co;
	String path;
	ImageIcon i1;
	JButton b1,b2,b3,b4,b5;
	JTextField t1,t2,t3,t4,t5,t6,t7;
	JTextArea add;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,labimg;
	JTable tbl;
	DefaultTableModel model;
	JScrollPane pane;
	Connection con;
	PreparedStatement ps;
	public Employee()
	{
	setLayout(null);
	setVisible(true);
	setSize(1500,800);
	setTitle("Employee");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	co=getContentPane();
	co.setBackground(Color.green);

	model=new DefaultTableModel();
	tbl=new JTable(model);
	model.addColumn("Emp_ID");
	model.addColumn("F_Name");
	model.addColumn("L_Name");
	model.addColumn("Mobile");
	model.addColumn("Email  ");
	model.addColumn(" Adhar ");
	model.addColumn("Salary");
	model.addColumn("Address");

	b1=new JButton("HOME");
	b2=new JButton("ADD");
	b3=new JButton("UPDATE");
	b4=new JButton("DELETE");
	b5=new JButton("ADD PHOTO");
	
	l1=new JLabel("Employee  ID");
	l2=new JLabel("Last Name");
	l3=new JLabel("Email ID");
	l4=new JLabel("Salary");
	l5=new JLabel("EMPLOYEE");
	l6=new JLabel("First Name");
	l7=new JLabel("Mobile No");
	l8=new JLabel("Adhar No");
	l9=new JLabel("Address");
	labimg=new JLabel();
	
	t1=new JTextField();
	t2=new JTextField();
	t3=new JTextField();
	t4=new JTextField();
	t5=new JTextField();
	t6=new JTextField();
	t7=new JTextField();
	add=new JTextArea();

	l5.setBounds(600,20,200,50);
	l5.setFont(new Font("Verdana",Font.BOLD,30));
	l1.setBounds(100,100,150,30);
	l2.setBounds(100,150,150,50);
	l3.setBounds(100,200,150,50);
	l4.setBounds(100,250,150,50); 
	l6.setBounds(700,100,150,30);
	l7.setBounds(700,150,150,50);
	l8.setBounds(700,200,150,50);
	l9.setBounds(700,250,150,50);
	labimg.setBounds(1200,100,100,120);
	setImg("defaultimg.png");

	t1.setBounds(250,100,150,30);
	t2.setBounds(250,150,150,30);
	t3.setBounds(250,200,150,30);
	t4.setBounds(250,250,150,30);
	t5.setBounds(900,100,150,30);
	t6.setBounds(900,150,150,30);
	t7.setBounds(900,200,150,30);
	add.setBounds(900,250,150,50);

	b1.setBounds(100,20,100,30);
	b2.setBounds(250,350,100,30);
	b3.setBounds(450,350,100,30);
	b4.setBounds(650,350,100,30);
	b5.setBounds(1200,300,110,30);

	pane=new JScrollPane(tbl);
	pane.setBounds(50,450,1200,200);
	
	co.add(b1);	co.add(b2);	co.add(b3);	co.add(b4);	co.add(b5);
	co.add(l1);	co.add(l2);	co.add(l3);	co.add(l4);	co.add(l5);	co.add(l6);	co.add(l7);	co.add(l8);	co.add(l9);	
	co.add(labimg);
	co.add(t1);	co.add(t2);	co.add(t3);	co.add(t4);	co.add(t5);	co.add(t6);	co.add(t7);	co.add(add);
	co.add(pane);
	b1.addActionListener(this);	b2.addActionListener(this);	b3.addActionListener(this);	b4.addActionListener(this);	b5.addActionListener(this);
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
	t7.setText("");
	add.setText("");
	setImg("defaultimg.png");
	}
	public void select()
	{
		set();
		   try
		 {
           		 ps= con.prepareStatement("select * from tbl_employee ");
            		ResultSet rs = ps.executeQuery();
		model.setRowCount(0);
       		     while(rs.next())
		{
		 try
		 {
              		  model.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8)});
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
				ps=con.prepareStatement("insert into tbl_employee values(?,?,?,?,?,?,?,?,?)");
				ps.setInt(1,Integer.parseInt(t1.getText()));
				ps.setString(2,t5.getText());
				ps.setString(3,t2.getText());
				ps.setString(4,t6.getText());
				ps.setString(5,t3.getText());
				ps.setString(6,t7.getText());
				ps.setString(7,t4.getText());
				ps.setString(8,add.getText());
				ps.setString(9,path);
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
		set();
			try
			{
			
				ps=con.prepareStatement("update tbl_employee set fnm=?,lnm=?,mob=?,email=?,adhar=?,salary=?,add=?,photo=? where emp_id=? ");
				ps.setString(1,t5.getText());
				ps.setString(2,t2.getText());
				ps.setString(3,t6.getText());
				ps.setString(4,t3.getText());
				ps.setString(5,t7.getText());
				ps.setString(6,t4.getText());
				ps.setString(7,add.getText());
				ps.setString(8,path);
				ps.setInt(9,Integer.parseInt(t1.getText()));
				ps.executeUpdate();
				JOptionPane.showMessageDialog(this," Record updated successfully ...");
				con.close();
				select();
				 clear();
			}
			catch(Exception exc){}
		}

		if(e.getSource()==b4)
		{
		set();
			try
			{
			
				ps=con.prepareStatement("delete from tbl_employee where emp_id=? ");
				ps.setInt(1,Integer.parseInt(t1.getText()));
				ps.executeUpdate();
				JOptionPane.showMessageDialog(this," Record deleted successfully ...");
				con.close();
				select();
				 clear();
			}
			catch(Exception exc){}
		}

		if(e.getSource()==b1)
		{
	
	
		 clear();
		Home h=new Home();
		h.setVisible(true);
		this.setVisible(false);
		}
		if(e.getSource()==b1)
		{
		Home h=new Home();
		h.setVisible(true);
		this.setVisible(false);
		}
		if(e.getSource()==b5)
		{
			FileDialog f=new FileDialog(this,"Select Image",FileDialog.LOAD);
			f.setDirectory("C:\\");
			f.setVisible(true);
			path=f.getDirectory()+f.getFile();
			setImg(path);
		}
		
	}

	public void setImg(String path)
	{
	i1=new ImageIcon(path);
	labimg.setIcon(i1);
	}
	
	public static void main(String arg[])
	{
	new Employee();
	}
}