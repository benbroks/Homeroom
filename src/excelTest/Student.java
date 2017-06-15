package excelTest;

public class Student {
	private String firstName;
	private String lastName;
	private String ID;
	private String grade;
	private boolean hilt;
	private boolean specEd;
	private String classPref;
	private String NoNo; //ID of student(s) to avoid
	
	public Student(){
	
	}
	
	public Student(String firstName, String lastName, String ID, String grade, boolean hilt, boolean specEd, String classPref, String NoNo){
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = ID;
		this.grade = grade;
		this.hilt = hilt;
		this.specEd = specEd;
		this.classPref = classPref;
		this.NoNo = NoNo;
	}

	public String getClassPref() {
		return classPref;
	}

	public void setClassPref(String classPref) {
		this.classPref = classPref;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public boolean isHilt() {
		return hilt;
	}

	public void setHilt(boolean hilt) {
		this.hilt = hilt;
	}

	public boolean isSpecEd() {
		return specEd;
	}

	public void setSpecEd(boolean specEd) {
		this.specEd = specEd;
	}

	public String getNoNo() {
		return NoNo;
	}

	public void setNoNo(String noNo) {
		NoNo = noNo;
	}
}

