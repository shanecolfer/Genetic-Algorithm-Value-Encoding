
public class Student {

	private String studentName;		//Stores the student name
	private String studentID;		//Stores the student ID
	private String supervisorName;	//Stores the student's supervisor's name
	private String supervisorID;	//Stores the student's supervisor ID
	
	
	//Constructor
	public Student (String studentName, String supervisorName)
	{
		this.studentName = studentName;
		this.supervisorName = supervisorName;
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
				+ ", supervisorID=" + supervisorID + "]";
	}
	
	

	
}
