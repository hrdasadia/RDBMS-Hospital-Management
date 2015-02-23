

public class Patient {
	
	private String patientId;
	private String DOB;
	private String SSN;
	private String bloodType;
	private String address;
	private String name;
	private String gender;
	
	public Patient() {
		super();
	}
	
	public Patient(String patientId, String dOB, String sSN, String bloodType,
			String address, String name, String gender) {
		super();
		this.patientId = patientId;
		DOB = dOB;
		SSN = sSN;
		this.bloodType = bloodType;
		this.address = address;
		this.name = name;
		this.gender = gender;
	}
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	}
