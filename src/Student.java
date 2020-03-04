
public class Student {

	private String studentName;		//Stores the student name
	private String studentID;		//Stores the student ID
	private String supervisorName;	//Stores the student's supervisor's name
	private String supervisorID;	//Stores the student's supervisor ID
	private String secondReaderName;
	private String secondReaderID;
	
	
	//Constructor
	public Student (String studentName, String supervisorName, String secondReaderName)
	{
		this.studentName = studentName;
		this.supervisorName = supervisorName;
		this.secondReaderName = secondReaderName;
	}
	
	public Student (String studentName)
	{
		this.studentName = studentName;
	}

	//Getters and setters
	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(String supervisorID) {
		this.supervisorID = supervisorID;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	
	@Override
	public String toString() {
		return "Student [studentName=" + studentName + ", studentID=" + studentID + ", supervisorName=" + supervisorName
				+ ", supervisorID=" + supervisorID + ", secondReaderName=" + secondReaderName + ", secondReaderID="
				+ secondReaderID + "]";
	}

	public String getSecondReaderID() {
		return secondReaderID;
	}

	public void setSecondReaderID(String secondReaderID) {
		this.secondReaderID = secondReaderID;
	}

	public String getSecondReaderName() {
		return secondReaderName;
	}

	public void setSecondReaderName(String secondReaderName) {
		this.secondReaderName = secondReaderName;
	}
	
	

	
}
