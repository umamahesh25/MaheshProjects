package dbexcel;

import java.sql.*;

public class DBOperations
{

	public static void InsertData
	
		
		
		
		
		(String id, String Name, String salary, String Mobile)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
				PreparedStatement pst=con.prepareStatement("insert into profileData values(?,?,?,?)");
				pst.setInt(1, Integer.parseInt(id));
				pst.setString(2, Name);
				pst.setString(3, salary);
				pst.setString(4, Mobile);
				int l=pst.executeUpdate();
				if(l>0)
				{
					System.out.println(id+"inserted successfully");
				}
			}
			
			catch(Exception ex)
			{
				System.out.println(id+"Fail to insert");
			}
		}
		public static ResultSet GetData()
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from s1");
				
				return rs;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
				
			}
		}
		
		
		

	}


