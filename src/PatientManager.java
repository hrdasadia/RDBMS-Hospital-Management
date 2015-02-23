import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;




public class PatientManager {

	Connection conn = DBConnection.getConnection();
	 Statement stmt = null;
	 ResultSet rs = null;
	
	public String getSuccess(){
		return "success";
	}
	
	public Patient getPatientInformation(String id){
		Patient p =new Patient();
		String sql = "SELECT * FROM Patient where patientid="+id;
	     try {
			stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql);
	    	 while(rs.next()){
	    		 p.setPatientId(Integer.toString(rs.getInt("patientid")));
	    		 p.setAddress(rs.getString("address"));
	    		 p.setBloodType(rs.getString("bloodtype"));
	    		 p.setGender(rs.getString("gender"));
	    		 p.setSSN(Integer.toString(rs.getInt("SSN")));
	    		 p.setName(rs.getString("patientname"));
	    		 java.sql.Date temp = rs.getDate("DOB");
	    		 DateFormat df = new SimpleDateFormat("dd-MMM-yy");  
	    		 String text = df.format(temp);  
	    		 p.setDOB(text);
	    	 }
			
			
			rs.close();
		    stmt.close();
		    conn.close();
	     } catch (SQLException e) {
			e.printStackTrace();
			return null;
	     }
		
		return p;
	}
	
	public Consultation getPatientDiagnosis(String id){
		Consultation c =new Consultation();
		String sql1 = "select description from illness where illnessid in(select illnessid from diagnosis where consultationid in(select consultationid from consultation where patientid ="+id+"))";
		String sql2 = "select consultationdate, notes from consultation where patientid ="+id;
		String sql3 = "select EmployeeName from employee where empid in (select empid from physician where physicianid in (select physicianid from consultation where patientid = "+id+"))";
		String sql4 = "select dosage, doseduration, frequency from prescription where consultationid in(select consultationid from consultation where patientid = "+id+")";
		String sql5 = "select medicinename from medicine where medicineid in (select medicineid from prescription where consultationid in (select consultationid from consultation where patientid = "+id+"))";
		String sql6 = "select patientid, patientname from patient where patientid = "+id;
	    String sql7 = "select medicaldata from patienthistory where patientid = "+id;
		
		try {
			stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql1);
	    	 while(rs.next()){
	    		 c.setDescription(rs.getString("description"));
	    	 }
			
	    	 rs = stmt.executeQuery(sql2);
	    	 while(rs.next()){
	    		 c.setNotes(rs.getString("notes"));
	    		 java.sql.Date temp = rs.getDate("consultationdate");
	    		 DateFormat df = new SimpleDateFormat("dd-MMM-yy");  
	    		 String text = df.format(temp);  
	    		 c.setConsultationDate(text);
	    	 }
			
	    	 rs = stmt.executeQuery(sql3);
	    	 while(rs.next()){
	    		 c.setPhysician(rs.getString("EmployeeName"));
	    	 }
	    	 
	    	 rs = stmt.executeQuery(sql4);
	    	 while(rs.next()){
	    		 c.setDosage(rs.getString("dosage"));
	    		 c.setDuration(rs.getString("doseduration"));
	    		 c.setFrequency(rs.getString("frequency"));
	    	 }
	    	 
	    	 rs = stmt.executeQuery(sql5);
	    	 while(rs.next()){
	    		 c.setMedicineName(rs.getString("medicinename"));
	    	 }
	    	 
	    	 rs = stmt.executeQuery(sql6);
	    	 while(rs.next()){
	    		 c.setPatientId(rs.getString("patientid"));
	    		 c.setPatientName(rs.getString("patientname"));
	    	 }
	    	 
	    	 rs = stmt.executeQuery(sql7);
	    	 while(rs.next()){
	    		 c.setMedicalHistory(rs.getString("medicaldata"));
	    	}
	    	 
			rs.close();
		    stmt.close();
		    conn.close();
	     } catch (SQLException e) {
			e.printStackTrace();
			return null;
	     }
		
		return c;
	}
	
	public Patient[] searchPatient(String pname){
		Patient[] p = new Patient[50];
		
		String sql = "SELECT * FROM Patient where LOWER(patientname) like '%"+pname.toLowerCase()+"%'";
	     try {
			stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql);
	    	int i=0;
	    	while(rs.next()){
	    		 p[i]=new Patient();
	    		 p[i].setName(rs.getString("patientname"));
	    		 p[i].setPatientId(rs.getString("patientid"));
	    		 i++;
	    	 }
			rs.close();
		    stmt.close();
		    conn.close();
	     } catch (SQLException e) {
			e.printStackTrace();
			return null;
	     }
	     
		return p;
	}
	
	public String insertPatient(Patient p){
		
		
		 
		int id = 0;
		 	String sql = "SELECT max(patientid) as patientid FROM Patient";
	     try {
			stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql);
	    	 while(rs.next()){
	    		 id  = rs.getInt("patientid");
	    	 }
			
			id++;
			sql = "insert into patient(patientid,dob,ssn,bloodtype,address,patientname,gender) values("+id+",'"+p.getDOB()+"',"+p.getSSN()+",'"+p.getBloodType()+ "','" + p.getAddress() + "','" +p.getName()+ "','" + p.getGender() + "')";
			stmt.executeUpdate(sql);
			rs.close();
		    stmt.close();
		    conn.close();
	     } catch (SQLException e) {
			e.printStackTrace();
			return "0";
	     }
	     
	     
		return Integer.toString(id);
	}
}
