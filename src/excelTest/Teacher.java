package excelTest;

import java.util.ArrayList;

public class Teacher {
	private String firstName;
	private String lastName;
	private String roomNum;
	private String grade;
	private String subject;
	private ArrayList<Student> students = new ArrayList<Student>();
	private int numStudents;
	
	public Teacher(){
		
	}
	
	public Teacher(String firstName, String lastName, String roomNum, String subject, String grade){
		this.firstName = firstName;
		this.lastName = lastName;
		this.roomNum = roomNum;
		this.grade = grade;
		this.subject = subject;
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

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void addStudent(Student s){
		students.add(s);
		numStudents ++;
	}
	
	public int getNumStudents(){
		return numStudents;
	}
	
}
