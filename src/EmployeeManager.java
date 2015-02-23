import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class EmployeeManager {
	Connection conn = DBConnection.getConnection();
	 Statement stmt = null;
	 ResultSet rs = null;
	 
	 public Employee[] searchEmployee(String etype){
		 Employee[] e = new Employee[50];
			
			String sql = "SELECT * FROM Employee where employeeType="+etype.toLowerCase()+"'";
		     try {
				stmt = conn.createStatement();
		    	rs = stmt.executeQuery(sql);
		    	int i=0;
		    	while(rs.next()){
		    		 e[i]=new Employee();
		    		 e[i].setEmployeeName(rs.getString("employeename"));
		    		 e[i].setEmployeeType(rs.getString("employeeType"));
		    		 i++;
		    	 }
				rs.close();
			    stmt.close();
			    conn.close();
		     } catch (SQLException exp) {
				exp.printStackTrace();
				return null;
		     }
		     
			return e;
		}
	 
	 public String insertEmployee(Employee e){
			
			
		 
			int id = 0;
			 	String sql = "SELECT max(empid) as empid FROM employee";
		     try {
				stmt = conn.createStatement();
		    	rs = stmt.executeQuery(sql);
		    	 while(rs.next()){
		    		 id  = rs.getInt("empid");
		    	 }
				
				id++;
				sql = "insert into employee(empid,gender,address,employeetype,ssn,phone,clinicid) values("+id+",'"+e.getGender()+"','"+e.getAddress()+"','"+e.getEmployeeType()+ "'," + e.getSSN() + "," +e.getPhone()+ ",'" + "1" + "')";
				stmt.executeUpdate(sql);
				rs.close();
			    stmt.close();
			    conn.close();
		     } catch (SQLException exp) {
				exp.printStackTrace();
				return "0";
		     }
		     
		     
			return Integer.toString(id);
		}
}
