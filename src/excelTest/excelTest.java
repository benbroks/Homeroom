package excelTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

//N/a (0), Social Studies (1), Science (2), English (3), Math (4)
public class excelTest {
	
	public static void hiltPlacement(int bigGroup, int smallGroup, int numBigGroup, int numSmallGroup, ArrayList<Teacher> teacherData, ArrayList<Student> studentData, String grade) {
		int teacherCounter = 0;
		int j = 0; //student identifier
		int i = 0; //teacher identifier
		while(teacherCounter< numBigGroup){
			int studentCounter = 0;
			if(i>=teacherData.size()){
				break;
			}
			if(teacherData.get(i).getGrade().equals(grade)){
				teacherCounter++;
				while(studentCounter<bigGroup){
					if((studentData.get(j).isHilt())&&(studentData.get(j).getGrade().equals(grade))){
						studentCounter++;
						teacherData.get(i).addStudent(studentData.get(j));
						studentData.remove(j);
						j--;
					}
					j++;
				}
			}
			i++;
		}
		
		teacherCounter = 0;
		while(teacherCounter < numSmallGroup){
			if(i>=teacherData.size()){
				break;
			}
			int studentCounter = 0;
			if(teacherData.get(i).getGrade().equals(grade)){
				teacherCounter++;
				while(studentCounter< smallGroup){
					if((studentData.get(j).isHilt())&&(studentData.get(j).getGrade().equals(grade))){
						studentCounter++;
						teacherData.get(i).addStudent(studentData.get(j));
						studentData.remove(j);
						j--;
					}
					j++;
				}
			}
			i++;
		}
		
	}
	
	public static void specPlacement(int bigGroup, int smallGroup, int numBigGroup, int numSmallGroup, ArrayList<Teacher> teacherData, ArrayList<Student> studentData, String grade) {
		int teacherCounter = 0;
		int j = 0;
		int i = 0;
		while(teacherCounter< numBigGroup){
			if(i>=teacherData.size()){
				break;
			}
			int studentCounter = 0;
			if(teacherData.get(i).getGrade().equals(grade)){
				teacherCounter++;
				while(studentCounter<bigGroup){
					if((studentData.get(j).isSpecEd())&&(studentData.get(j).getGrade().equals(grade))){
						studentCounter++;
						teacherData.get(i).addStudent(studentData.get(j));
						studentData.remove(j);
						j--;
					}
					j++;
				}
			}
			i++;
		}
		
		teacherCounter = 0;
		while(teacherCounter < numSmallGroup){
			if(i>=teacherData.size()){
				break;
			}
			int studentCounter = 0;
			if(teacherData.get(i).getGrade().equals(grade)){
				teacherCounter++;
				while(studentCounter< smallGroup){
					if((studentData.get(j).isSpecEd())&&(studentData.get(j).getGrade().equals(grade))){
						studentCounter++;
						teacherData.get(i).addStudent(studentData.get(j));
						studentData.remove(j);
						j--;
					}
					j++;
				}
			}
			i++;
		}
	}
	
	public static void classPlacement(ArrayList<Teacher> teacherData, ArrayList<Student> studentData){
		int j = 0;
		while(j < studentData.size()){
			if(!(studentData.get(j).getClassPref().equals("0"))){
				int i = 0;
				while (!((teacherData.get(i).equals(studentData.get(j).getGrade())) && (teacherData.get(i).getSubject().equals(studentData.get(j).getClassPref())))){
					i ++;
					if (i == teacherData.size()){
						break;
					}
				}
				if((i < teacherData.size())&&(teacherData.get(i).getNumStudents() < 25)){
					teacherData.get(i).addStudent(studentData.get(j));
					studentData.remove(j);
					j --;
				}
			}
			j ++;
		}
	}

	public static void main(String[] args) throws IOException {
		String teachers = args[0];
		String students = args[1];
		
		//load the teacher data
		FileReader fr = new FileReader(new File(teachers));
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		ArrayList<Teacher> teacherData = new ArrayList<Teacher>();
		br.readLine(); //prevents the first row from being saved as a teacher object
		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			if(values.length > 0){
				teacherData.add(new Teacher(values[0], values[1], values[2], values[3], values[4]));
			}
		}
		
		br.close();
		fr.close();
		
		//load student data
		int[] numHILT = new int[4];
		int[] numSpec = new int[4];
		
		
		FileReader fr2 = new FileReader(new File(students));
		BufferedReader br2 = new BufferedReader(fr2);
		String line2 = null;
		
		ArrayList<Student> studentData = new ArrayList<Student>();
		br2.readLine(); //prevents the first row from being saved as a student object
		while ((line2 = br2.readLine()) != null) {
			String[] values = line2.split(",");
			if(values.length > 0){
				boolean H;
				boolean S;
				if (values[4].equals("1")){
					H = true;
				}
				else {
					H = false;
				}
				if (values[5].equals("1")){
					S = true;
				}
				else {
					S = false;
				}
				if(values.length==7){
					studentData.add(new Student(values[0], values[1], values[2], values[3], H, S, values[6], "0"));
				}
				else {
					studentData.add(new Student(values[0], values[1], values[2], values[3], H, S, values[6], values[7]));
				}
			}
		}
		
		br2.close();
		fr2.close();
		
		//finding number of HILT and Special Ed students (add iteration for specific grades)
		for (int i = 0; i < studentData.size(); i++){
			for(int a = 9; a <= 12; a++){
				if ((studentData.get(i).isHilt())&&(studentData.get(i).getGrade().equals(String.valueOf(a)))){
					numHILT[a-9] += 1;
				}
				if ((studentData.get(i).isSpecEd())&&(studentData.get(i).getGrade().equals(String.valueOf(a)))){
					numSpec[a-9] += 1;
				}
			}
		}
		//placing students function
		
		//placing HILT students
		//iterate through all the ninth grade hilt students, place the first five in the first ninth grade gp you find
		for(int a = 9; a <= 12; a++){//iterates through grades
			int remainder = numHILT[a-9]%5;
			int n = numHILT[a-9];
			
			if(n <= 5){
				hiltPlacement(n, 0, 1, 0, teacherData, studentData, Integer.toString(a));
			}
			
			else if(n==6){
					hiltPlacement(3,0,2,0, teacherData, studentData, Integer.toString(a));
			}
			else if(n==7){
					hiltPlacement(4,3,1,1, teacherData, studentData, Integer.toString(a));
			}
			else if(n==11){
					hiltPlacement(4,3,2,1, teacherData, studentData, Integer.toString(a));
			}
			
			else{
				if(remainder==0){
					hiltPlacement(5,0,n/5,0, teacherData, studentData, Integer.toString(a));
				}
				else if(remainder == 1){
					hiltPlacement(5,4,(n-1)/5-3,4, teacherData, studentData, Integer.toString(a));
				}
				else if(remainder == 2){
					hiltPlacement(5,4,(n-2)/5-2,3, teacherData, studentData, Integer.toString(a));
				}
				else if(remainder == 3){
					hiltPlacement(5,4,(n-3)/5-1,2, teacherData, studentData, Integer.toString(a));
				}
				else{
					hiltPlacement(5,4,(n-4)/5,1, teacherData, studentData, Integer.toString(a));
				}
			}
			
		}
		
		//placing Special Ed Students based on number per grade
		
		for(int a = 9; a <= 12; a++){//iterates through grades
			int remainder = numSpec[a-9]%5;
			int n = numSpec[a-9];
			
			if(n <= 5){
				specPlacement(n, 0, 1, 0, teacherData, studentData, Integer.toString(a));
			}
			
			else if(n==6){
					specPlacement(3,0,2,0, teacherData, studentData, Integer.toString(a));
			}
			else if(n==7){
					specPlacement(4,3,1,1, teacherData, studentData, Integer.toString(a));
			}
			else if(n==11){
					specPlacement(4,3,2,1, teacherData, studentData, Integer.toString(a));
			}
			else{
				if(remainder==0){
					specPlacement(5,0,n/5,0, teacherData, studentData, Integer.toString(a));
				}
				else if(remainder == 1){
					specPlacement(5,4,(n-1)/5-3,4, teacherData, studentData, Integer.toString(a));
				}
				else if(remainder == 2){
					specPlacement(5,4,(n-2)/5-2,3, teacherData, studentData, Integer.toString(a));
				}
				else if(remainder == 3){
					specPlacement(5,4,(n-3)/5-1,2, teacherData, studentData, Integer.toString(a));
				}
				else{
					specPlacement(5,4,(n-4)/5,1, teacherData, studentData, Integer.toString(a));
				}
			}
			
		}
		
		//placing students by class preference
		classPlacement(teacherData, studentData);
		for(int i = 0;i<teacherData.size(); i ++){
			System.out.println(teacherData.get(i).getNumStudents());
		}
		
		//write output
		
		File myOutput = new File("/Users/benbrooks/output.csv");
		
		FileOutputStream fos = new FileOutputStream(myOutput);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		for (int i = 1; i < teacherData.size(); i++) { //start at i = 1 to skip sample title row
			bw.write(teacherData.get(i).getFirstName() + " " + teacherData.get(i).getLastName() + " ,");
		}
		
		bw.newLine();

		//class preferences
	 
		bw.close();
	}

}
