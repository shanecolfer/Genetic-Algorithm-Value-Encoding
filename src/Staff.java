
public class Staff {
	
	private String staffName;
	private String staffID;
	
	//Constructor
	public Staff(String staffName, String staffID)
	{
		this.staffName = staffName;
		this.staffID = staffID;
	}
	
	//Getters and setters
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	
}
