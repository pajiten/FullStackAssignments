package excercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import sample.jdbc.program.PreparedStmtExample; 

public class AssignmentJAVAtoSQL {
	
 // Step 1- JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/db_world";
    private static final String user = "root";
    private static final String password = "";
    
 // Step2 - JDBC variables for opening and managing connection
    Connection con;
    PreparedStatement prSt;
    Statement stmt;
    
    public static void main(String[] args) {
    	AssignmentJAVAtoSQL pse = new AssignmentJAVAtoSQL();
    	
        Scanner input = new Scanner(System.in);
    	
        System.out.println("=============PREPARED STATEMENT MENU=================");
        System.out.println("1. Insert the new employee Record"); 
        System.out.println("2. Update the City Record and Customer Name");
        System.out.println("3. Delete the 2nd record");
        System.out.println("====================================================");
    	System.out.println("Enter your choice from (1-6): ");
    	
    	int number = input.nextInt();
    	System.out.println("You entered option" + number);
    	
    	switch(number)
    	{
    	case 1: pse.insertPSRecord();
    	        break;
    	
    	case 2: pse.updatePSRecord();
    	        break;
    	        
    	case 3: pse.deletePSRecord();
    	        break;
    	case 4: pse.insertPSRecord();
    			pse.insertPSRecord();
        		break;
    	case 5: pse.fetchPSRecord();
        		break;		
        		
    	}	
        	
	}
  //-------------------------- Inserting The Record-----------------------------//
  	public void insertPSRecord()
      {  
  		String query = "insert into Customer(custid,custname,orderNo,city) values(?,?,?,?)";
  		
  	      try {
  	            Class.forName("com.mysql.jdbc.Driver");
  	            con = DriverManager.getConnection(url, user, password);
  	           
  	            prSt = con.prepareStatement(query);
  	            
  	            // Take user input for insertion
  	            Scanner input = new Scanner(System.in);	
  	            
  	            System.out.print("Enter the custid:  ");
  	            int cid = input.nextInt();
  	            System.out.println();
  	            
  	            System.out.print("Enter the customer name:  ");
  	            String cname = input.next();
  	            System.out.println();
  	            
  	            System.out.print("Enter the order number:  ");
  	            int cord = input.nextInt();
  	            System.out.println();
  	            
  	            System.out.print("Enter the city name:  ");
	            String ccity = input.next();
	            System.out.println();
  	            
  	            prSt.setInt(1, cid);
  	            prSt.setString(2, cname);
  	            prSt.setInt(3, cord);
  	            prSt.setString(4, ccity);
  	            
  	            int count = prSt.executeUpdate();
  	            
  	            //show the number of records
  	            stmt = con.createStatement();
  	            
  	            String query1 = "select * from Customer";
  	            ResultSet rs =  stmt.executeQuery(query1);
  	            System.out.println("Id    Name     OrderNo     City");
  	            
  	            while (rs.next()) {
  	               int id = rs.getInt("custid");
  	               String name = rs.getString("custname");
  	               int ord = rs.getInt("orderNo");
  	               String city = rs.getString("city");
  	               System.out.println(id + "    " + name+"    "+ord+"    "+city);
  	            }   
  	            
  	        } catch (ClassNotFoundException e) {
  	            // TODO Auto-generated catch block
  	            e.printStackTrace();
  	        } catch (SQLException e) {
  	            // TODO Auto-generated catch block
  	            e.printStackTrace();
  	        } finally{
  	            try{
  	                if(prSt != null) prSt.close();
  	                if(con != null) con.close();
  	            } catch(Exception ex){}
  	        }
  	}
	//-------------------------- Updating The Record-----------------------------//
	public void updatePSRecord()
    { 
		
		String sqlUpdate1 = "UPDATE Customer SET city = ? WHERE custid = ?";
		
		try {
            try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            prSt  = con.prepareStatement(sqlUpdate1);
            
            // Take user input for insertion
            Scanner input = new Scanner(System.in);	
            
            System.out.print("Enter the custid for city update:  ");
            int eid = input.nextInt();
            System.out.println();
            
            System.out.print("Enter the new city name:  ");
            String ecity = input.next();
            System.out.println();
            
            prSt.setString(1, ecity);
            prSt.setInt(2, eid);
 
            int rowAffected = prSt.executeUpdate();
            System.out.println(String.format("Row affected %d", rowAffected));
 
            // update another record -reuse the prepared statement
            
            // Second Update
     		String sqlUpdate2 = "UPDATE Customer SET custname = ? WHERE custid = ?";
     		prSt  = con.prepareStatement(sqlUpdate2);
     		
            System.out.print("Enter the another custid to change name:  ");
            int eid1 = input.nextInt();
            System.out.println();
            
            System.out.print("Enter the new cust name:  ");
            String ename = input.next();
            System.out.println();
            
            prSt.setString(1, ename);
            prSt.setInt(2, eid1);
 
            rowAffected = prSt.executeUpdate();
            System.out.println(String.format("Row affected %d", rowAffected));
            
		}
 
    		
    		
		catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }		
    }
	
	//-------------------------- Deleting The Record-----------------------------//
	public void deletePSRecord()
    { 
		String sqlUpdate = "DELETE from Customer where custid=?";
		
		try {
            try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            prSt = con.prepareStatement(sqlUpdate);
            
            // Take user input for insertion
            Scanner input = new Scanner(System.in);	
            
            System.out.print("Enter the custid:  ");
            int eid = input.nextInt();
            System.out.println();
            
            prSt.setInt(1, eid);
            prSt.executeUpdate();
            
            System.out.println("Record deleted successfully");
		}
		catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }				
    }
	
	//-------------------------- Fetch The Record-----------------------------//
  	public void fetchPSRecord()
      {  

  		
  	      try {
  	            Class.forName("com.mysql.jdbc.Driver");
  	            con = DriverManager.getConnection(url, user, password);
	            
  	            //show the number of records
  	            stmt = con.createStatement();
  	            
  	          String query2 = "select count(*) from Customer";
    	        ResultSet rs2 =  stmt.executeQuery(query2);
    	            rs2.next();
    	            int count = rs2.getInt( 1 );
      	            System.out.println("No of records" + count );
  	            
  	            String query1 = "select * from Customer";
  	            ResultSet rs =  stmt.executeQuery(query1);
  	          
  	            
  	          
  	            System.out.println("Id    Name     OrderNo     City");
  	            
  	            while (rs.next()) {
  	               int id = rs.getInt("custid");
  	               String name = rs.getString("custname");
  	               int ord = rs.getInt("orderNo");
  	               String city = rs.getString("city");
  	               System.out.println(id + "    " + name+"    "+ord+"    "+city);
  	            }   
  	            
  	        } catch (ClassNotFoundException e) {
  	            // TODO Auto-generated catch block
  	            e.printStackTrace();
  	        } catch (SQLException e) {
  	            // TODO Auto-generated catch block
  	            e.printStackTrace();
  	        } finally{
  	            try{
  	                if(prSt != null) prSt.close();
  	                if(con != null) con.close();
  	            } catch(Exception ex){}
  	        }
  	}
	
}
