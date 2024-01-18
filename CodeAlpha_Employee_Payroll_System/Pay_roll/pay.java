

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

class Frame1 extends JFrame implements ActionListener {

       // initializa the lbl with caption name is employee information.
       JLabel lbl=new JLabel("Simple Payroll System Version 2.0");

       Font f=new Font("Times",Font.BOLD,30);
       Font f1=new Font("Times",Font.BOLD,16);
       Font f2=new Font("Times",Font.BOLD,12);
       
       JLabel lblid,lblname,lbldepartment,lbldays,lblrate,lblsubmit;
       JLabel lblsalary;
       JTextField txtid,txtname,txtdepartment,txtdays;
       JTextField txtrate,txtsalary;
       JRadioButton rbmale,rbfemale;
       JButton btnadd,btnsave,btnupdate,btndelete,btnexit;
       JButton btnnext,btnprev,btnlast,btnfirst;
       JButton btncompute;
       String gen;
       ResultSet rs=null;
       Connection con=null;
       Statement stmt=null;
       
        float days,rate,salary;
       
       Frame1()
       {
       // this is display in a Frame titlebar.
       super("Employees Information ");
       addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent we)
                {
                System.exit(0);
                }
       });
       // set layout to null
       setLayout(null);

       lblsubmit=new JLabel("Developed By : Prachi");
       add(lblsubmit);
       lblsubmit.setHorizontalAlignment(lblsubmit.CENTER );
       lblsubmit.setBounds(220,510,450,20);
       lblsubmit.setFont(f1);

       // add lbl label on form.
       add(lbl);

       // set the particular position on a screen
       lbl.setBounds(200,50,500,100);
        lbl.setHorizontalAlignment(lbl.CENTER );
       // set the font of lbl label 
       lbl.setFont(f);
      

       // initializa all the label which are declared in the example above with its caption name 
       lblid=new JLabel("ID");
       lblname=new JLabel("NAME");
       lbldepartment=new JLabel("DEPARTMENT");
       lbldays=new JLabel("N0. OF DAYS ");
       lblrate=new JLabel("RATE PER DAY");
       lblsalary=new JLabel("SALARY");
       
       lblid.setBounds(300,140,100,20);
       lblname.setBounds(300,180,100,20);
       lbldepartment.setBounds(300,220,100,20);
       lbldays.setBounds(300,250,100,20);
       lblrate.setBounds(300,280,100,20);
       lblsalary.setBounds(300,310,100,20);
       
        // add all the label on the frame
       add(lblid);
       add(lblname);
       add(lbldepartment);
       add(lbldays);
       add(lblrate);
       add(lblsalary);
       
       // set font
       lblid.setFont(f2);
       lblname.setFont(f2);
       lbldepartment.setFont(f2);
       lbldays.setFont(f2);
       lblrate.setFont(f2);
       lblsalary.setFont(f2);

       // initialize the textfield with size
       txtid=new JTextField(15);
       txtname=new JTextField(15);
       txtdepartment=new JTextField(15);
       txtdays=new JTextField(15);
       txtrate=new JTextField(15);
       txtsalary=new JTextField(15);
       

       // set a particlar position on a screen with setbounds constructor
       txtid.setBounds(400,140,100,20);
       txtname.setBounds(400,180,100,20);
       txtdepartment.setBounds(400,220,100,20);
       txtdays.setBounds(400,250,100,20);
       txtrate.setBounds(400,280,100,20);
       txtsalary.setBounds(400,310,100,20);
     
     
       // add textfield on a Frame
       add(txtid);
       add(txtname);
       add(txtdepartment);
       add(txtdays);
       add(txtrate);
       add(txtsalary);
      

        
     
       // initializa button with its caption
       btnadd=new JButton("Add");
       btnsave=new JButton("Save");
       btnupdate=new JButton("Update");
       btndelete=new JButton("Delete");

      // To add tooltip in the buttons
      btnadd.setToolTipText("Click this button to Add record in the Database.");
      btnsave.setToolTipText("Click this button to Save record in the Database.");
      btnupdate.setToolTipText("Click this button to Update record in the Database.");
      btndelete.setToolTipText("Click this button to Delete record in the Database.");
      
       // set a particular position on a Frame
       btnadd.setBounds(200,400,100,30);
       btnsave.setBounds(310,400,100,30);
       btnupdate.setBounds(420,400,100,30);
       btndelete.setBounds(530,400,100,30);

       // add button on a frame
       add(btnadd);
       add(btnsave);
       add(btndelete);
       add(btnupdate);

       // register all the button
       btnadd.addActionListener(this);
       btnsave.addActionListener(this);
       btnupdate.addActionListener(this);
       btndelete.addActionListener(this);
       

       // initializa nevigation button with its caption
       btnfirst=new JButton("First");
       btnnext=new JButton("Next");
       btnprev=new JButton("Previous");
       btnlast=new JButton("Last");
       
       // To add tooltip in the buttons
      btnfirst.setToolTipText("Click this button to move to the First Record.");
      btnnext.setToolTipText("Click this button to move to the Next Record.");
      btnprev.setToolTipText("Click this button to move to the Previous Record.");
      btnlast.setToolTipText("Click this button to move to the Last Record.");
      

       // set a particular position on a  screen with setbounds constructor
       btnfirst.setBounds(200,440,100,30);
       btnnext.setBounds(310,440,100,30);
       btnprev.setBounds(420,440,100,30);
       btnlast.setBounds(530,440,100,30);

       // add all the button on frame
       add(btnfirst);
       add(btnnext);
       add(btnprev);
       add(btnlast);

       // register all the button 
       btnfirst.addActionListener(this);
       btnnext.addActionListener(this);
       btnprev.addActionListener(this);
       btnlast.addActionListener(this);
       
       btnexit=new JButton("Exit");
       btnexit.setToolTipText("Click this button to Quit Program.");
       btnexit.setBounds(360,480,100,30);
       add(btnexit);
       btnexit.addActionListener(this);
 
       btncompute=new JButton("Compute");
       btncompute.setToolTipText("Click this button to compute the salary of the employee.");
       btncompute.setBounds(360,350,100,30);
       add(btncompute);
       btncompute.addActionListener(this);

       // open database connection
       // here we call a dbopen() method
       dbOpen();
       }

       
       public void actionPerformed(ActionEvent ae)
	{
		try
		{
			
	 		if(ae.getActionCommand()=="Add")
			{
				txtid.setText("");
				txtname.setText("");
				txtdepartment.setText("");
				txtdays.setText("");
				txtrate.setText("");
				txtsalary.setText("");
			}
			if(ae.getActionCommand()=="Update")
			{
			
				
			 stmt.executeUpdate("UPDATE pay1 SET name='" + txtname.getText() + "',department='" + txtdepartment.getText() + "',days='" + txtdays.getText() + "',rate='" + txtrate.getText()+ "',salary='" + txtsalary.getText() + "' WHERE id=" + txtid.getText() + "");
			 
				dbClose();
				dbOpen();
			}
			if(ae.getActionCommand()=="Delete")
			{
				stmt.executeUpdate("DELETE FROM pay1 WHERE id=" + txtid.getText() + "");			
				dbClose();
				dbOpen();
			}	
			if(ae.getActionCommand()=="Save")
			{

  	             stmt.executeUpdate("INSERT  INTO pay1 VALUES('" +txtid.getText()+ "','"+ txtname.getText() + "','" + txtdepartment.getText() + "','" + txtdays.getText() + "','"+txtrate.getText() + "','" +txtsalary.getText() +"')");
		
		
		
				dbClose();
				dbOpen();
			}
			if(ae.getActionCommand()=="Next")
			{
				if(rs.next())
				{
					setText();                 			setText();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You are At Already Last Record", "Message", JOptionPane.ERROR_MESSAGE);	
                  		}
			}
			if(ae.getActionCommand()=="Previous")
			{
				if(rs.previous())
				{
                  			setText();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You Are At Already First Record", "Message", JOptionPane.ERROR_MESSAGE);
                  		}
			}
			if (ae.getActionCommand()=="First")
			{
				if(rs.first())
				{
					setText();
				}
			}
			if (ae.getActionCommand()=="Last")
			{
				if(rs.last())
				{
					setText();
				}
			}
			
			if(ae.getActionCommand()=="Compute")
			{
		
		        days=Float.parseFloat(txtdays.getText());
                        rate=Float.parseFloat(txtrate.getText());
                        salary=(days*rate);
                         float round = Round(salary,2);
                        txtsalary.setText(Float.toString(round));
		        txtsalary.setEditable(false);
			}
			if(ae.getActionCommand()=="Exit")
			{
			System.exit(0);		
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	} 
	

  
// Method to round off decimal values  

  public static float Round(float Rval, int Rpl) {
  float p = (float)Math.pow(10,Rpl);
  Rval = Rval * p;
  float tmp = Math.round(Rval);
  return (float)tmp/p;
    }
	
	public void dbOpen()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

                        // here in this statement mydata is a DSN name which u have to create before run this program
                        // step to create dsn
                        // open control panel-> open administrativr tools-> open data source(ODBC)-> press add
                        //->select microsoft access driver(*.mdb) then finish->give data source name-> select database and press ok
                        // again press ok.
                        con=DriverManager.getConnection("jdbc:odbc:mydata");
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("Select * from pay1");
			if(rs.next())
			setText();
		}catch(Exception e){}
	}
	public void dbClose()
	{
		try{stmt.close();
		rs.close();
		con.close();
		}catch(Exception e){}
	}
	public void setText(){
		try{
			txtid.setText(rs.getString(1));
			txtname.setText(rs.getString(2));
			txtdepartment.setText(rs.getString(3));
			txtdays.setText(rs.getString(4));
			txtrate.setText(rs.getString(5));
			txtsalary.setText(rs.getString(6));
			
	    	}catch(Exception ex){}		
	    	}
}




public class pay
{
        public static void main(String ar[])throws Exception
        {

        // create a object of Frame1 class in main method
        Frame1 f1=new Frame1();

        // set frame size
        f1.setSize(800,600);

        // set frame visible true
        f1.setVisible(true);
        //set look and feel for frame
	UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
}
