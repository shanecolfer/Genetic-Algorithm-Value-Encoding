
public class Student {

	private String studentName;		//Stores the student name
	private String studentID;		//Stores the student ID
	private String supervisorID;	//Stores the student's supervisor ID
	
	
	//Constructor
	public Student (String studentName, String studentID, String supervisorID)
	{
		this.studentName = studentName;
		this.studentID = studentID;
		this.supervisorID = supervisorID;
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

	
}
