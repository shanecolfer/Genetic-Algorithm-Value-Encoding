
public class Staff {
	
	private String staffName;
	private int staffID;
	
	//Constructor
	public Staff(String staffName, int staffID)
	{
		this.staffName = staffName;
		this.staffID = staffID;
	}
	
	//To String
	@Override
	public String toString() {
		return "Staff [staffName=" + staffName + ", staffID=" + staffID + "]";
	}

	//Constructor
	public Staff(String staffName)
	{
		this.staffName = staffName;
	}
	
	//Getters and setters
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	
}
