
public class Student {

	private String studentName;		//Stores the student name
	private int studentID;		//Stores the student ID
	private String supervisorName;	//Stores the student's supervisor's name
	private int supervisorID;	//Stores the student's supervisor ID
	private String secondReaderName;
	private int secondReaderID;
	
	
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
	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public int getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(int supervisorID) {
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

	public int getSecondReaderID() {
		return secondReaderID;
	}

	public void setSecondReaderID(int secondReaderID) {
		this.secondReaderID = secondReaderID;
	}

	public String getSecondReaderName() {
		return secondReaderName;
	}

	public void setSecondReaderName(String secondReaderName) {
		this.secondReaderName = secondReaderName;
	}
	
	

	
}
