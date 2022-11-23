import java.sql.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
public class Attendance extends JFrame implements ActionListener
{
	Container co;
	JButton b1,b2,b3;
	JTextField t1,t2,t3,t;
	JLabel l1,l2,l3,l4,l5,l;
	JRadioButton yes,no;
	ButtonGroup grp;
	JTable tbl;
	DefaultTableModel model;
	JScrollPane pane;
	Connection con;
	PreparedStatement ps;
	public Attendance()
	{
	setLayout(null);
	setVisible(true);
	setSize(1500,800);
	setTitle("Attendance ");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	co=getContentPane();
	co.setBackground(Color.green);

	model=new DefaultTableModel();
	tbl=new JTable(model);
	model.addColumn("Emp_ID");
	model.addColumn("Emp_Fname");
	model.addColumn("Emp_Lname");
	model.addColumn("Present");
	model.addColumn("Date");

	b1=new JButton("HOME");
	b2=new JButton("ADD");
	b3=new JButton("CLEAR");
	
	grp=new ButtonGroup();
	
	l1=new JLabel("E_First_Name");
	l2=new JLabel(" E_Last_Name");
	l3=new JLabel("Present ");
	l4=new JLabel("Date");
	l5=new JLabel("ATTENDANCE");
	l=new JLabel("Emp_Id");
	
	t1=new JTextField();
	t2=new JTextField();
	t3=new JTextField();
	t=new JTextField();

	yes=new JRadioButton("Yes");
	no=new JRadioButton("No");
	yes.setBackground(Color.green);
	no.setBackground(Color.green);

	l5.setBounds(500,20,300,50);
	l5.setFont(new Font("Verdana",Font.BOLD,30));
	l.setBounds(500,100,150,30);
	l1.setBounds(500,150,150,30);
	l2.setBounds(500,200,150,50);
	l3.setBounds(500,250,150,50);
	l4.setBounds(500,300,150,50); 

	t.setBounds(700,100,150,30);
	t1.setBounds(700,150,150,30);
	t2.setBounds(700,200,150,30);
	yes.setBounds(700,250,50,30);
	no.setBounds(800,250,50,30);
	t3.setBounds(700,300,150,30);


	b1.setBounds(100,20,100,30);
	b2.setBounds(500,370,100,30);
	b3.setBounds(700,370,100,30);

	grp.add(yes);	grp.add(no);

	pane=new JScrollPane(tbl);
	pane.setBounds(50,450,1200,200);
	
	co.add(b1);	co.add(b2);	co.add(b3);		
	co.add(l1);	co.add(l2);	co.add(l3);	co.add(l4);	co.add(l5);	co.add(l);
	co.add(t1);	co.add(t2);	co.add(t3);	co.add(t);	
	co.add(yes);	co.add(no);
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
	t.setText("");
	}
	public void select()
	{
		set();
		   try
		 {
           		 ps= con.prepareStatement("select * from tbl_attendance ");
            		ResultSet rs = ps.executeQuery();
		model.setRowCount(0);
       		     while(rs.next())
		{
		 try
		 {
              		  model.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
		  } catch (Exception e){  }
           		 }

      		  } catch (Exception e){  }
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
		Home h=new Home();
		h.setVisible(true);
		this.setVisible(false);
		}

		if(e.getSource()==b2)
		{
		
		yes.setActionCommand("Yes");
		no.setActionCommand("No");
		set();
			try
			{
				ps=con.prepareStatement("insert into tbl_attendance values(?,?,?,?,?)");
				ps.setInt(1,Integer.parseInt(t.getText()));
				ps.setString(2,t1.getText());
				ps.setString(3,t2.getText());
				ps.setString(4,grp.getSelection().getActionCommand());
				ps.setString(5,t3.getText());
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
		
}
	
	public static void main(String arg[])
	{
	new Attendance();
	}
}