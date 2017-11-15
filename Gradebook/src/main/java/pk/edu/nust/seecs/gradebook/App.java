package pk.edu.nust.seecs.gradebook;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import pk.edu.nust.seecs.gradebook.dao.CloDao;
import pk.edu.nust.seecs.gradebook.entity.Clo;
import pk.edu.nust.seecs.gradebook.entity.Student;
import pk.edu.nust.seecs.gradebook.dao.StudentDao;
import pk.edu.nust.seecs.gradebook.entity.Grade;
import pk.edu.nust.seecs.gradebook.dao.GradeDao;
import pk.edu.nust.seecs.gradebook.entity.Course;
import pk.edu.nust.seecs.gradebook.dao.CourseDao;
import pk.edu.nust.seecs.gradebook.entity.Teacher;
import pk.edu.nust.seecs.gradebook.dao.TeacherDao;
import pk.edu.nust.seecs.gradebook.entity.Content;
import pk.edu.nust.seecs.gradebook.dao.ContentDao;


/**
 * My main App. 
 * <p>
 This executes everything.
 */

public class App {

    private void showMenu() {
        System.out.println("Welcome to gradebook management system!");
        System.out.println("Enter 1 to add a student");
        System.out.println("Enter 2 to add a teacher");
        System.out.println("Enter 3 to add a course");
        System.out.println("Enter 4 to add content");
        System.out.println("Enter 5 to set a grade");
        System.out.println("Enter 6 to update a teacher");
        System.out.println("Enter 7 to update a student");
        System.out.println("Enter 0 to exit");

    }
    
    public static void main(String[] args) {
        CloDao clodao = new CloDao();
        Clo clo = new Clo();
        clo.setName("CLO 1");
        clo.setDescription("Design efficient solutions for algorithmic problems");
        clo.setPlo("2");
        clodao.addClo(clo);
        clodao.updateClo(clo);
        Scanner sc = new Scanner(System.in);
        int x = -1;
        App ap = new App();
	    while(x!= 0){
	        ap.showMenu();
	        x = sc.nextInt();
	        switch (x){
	        	default: System.out.println("Invalid Choice!\n");
	        	case 1: ap.addStudent(); break;
	        	case 2: ap.addTeacher(); break;
	        	case 3: ap.addCourse(); break;
	        	case 4: ap.addContent(); break;
	        	case 5: ap.addGrade(); break;
	        	case 6: ap.updateTeacher(); break;
	        	case 7: ap.updateStudent(); break;
	        	case 0: break;
	        }
	    }
    }

    private void addStudent(){
    	Student stu = new Student();
    	StudentDao stu1 = new StudentDao();
        Scanner sc = new Scanner(System.in);
        CourseDao cordao = new CourseDao();
    	Set <Course> courses = new HashSet<>();
    	System.out.println("Enter name:");
    	stu.setName(sc.next());
    	if(cordao.getAllCourses().size()>1){
        	System.out.println("No courses have been set yet!");
    	}else{
	    	System.out.println("Set Courses:");
	    	for(int i = 0; i < cordao.getAllCourses().size();i++){
	    		System.out.println("Press 1 to select, Press any other key to skip. \nPress 0 to exit.");
	    		System.out.println("Course: " + cordao.getAllCourses().get(i));
	    		int inp = sc.nextInt();
	    		if(inp==0){
	    			break;
	    		}else{
	    			if(inp==1){
	    				courses.add(cordao.getAllCourses().get(i));
	    			}
	    		}
	    	}
    	}
    	System.out.println("Saving to Database!");
    	stu.setCourses(courses);
    	stu1.addStudent(stu);
    }
    public void updateStudent(){
    	Student stu = new Student();
    	StudentDao stu1 = new StudentDao();
        Scanner sc = new Scanner(System.in);
        CourseDao cordao = new CourseDao();
    	Set <Course> courses = new HashSet<>();
    	System.out.println(stu1.getAllStudents());
    	System.out.println("Enter the id of the student entry you wish to change");
    	int temp = sc.nextInt();
    	for(int i=0;i<stu1.getAllStudents().size();i++){
    		if(stu1.getAllStudents().get(i).getStudentId()==temp){
    			stu = stu1.getAllStudents().get(i);
    			break;
    		}
       	}
    	System.out.println("Modify name; (Previously: "+ stu.getName() + ")" );
    	stu.setName(sc.next());
    	if(cordao.getAllCourses().size()>1){
        	System.out.println("No courses have been set yet!");
    	}else{
	    	System.out.println("Set Courses:");
	    	for(int i = 0; i < cordao.getAllCourses().size();i++){
	    		System.out.println("Press 1 to select, Press any other key to skip. \nPress 0 to exit.");
	    		System.out.println("Course: " + cordao.getAllCourses().get(i));
	    		int inp = sc.nextInt();
	    		if(inp==0){
	    			break;
	    		}else{
	    			if(inp==1){
	    				courses.add(cordao.getAllCourses().get(i));
	    			}
	    		}
	    	}
    	}
    	System.out.println("Saving to Database!");
    	stu.setCourses(courses);
    	stu1.updateStudent(stu);
    }
    public void addContent(){
        ContentDao condao = new ContentDao();
        Content con = new Content();
        Scanner sc = new Scanner(System.in);
    	StudentDao stu1 = new StudentDao();
    	CloDao clodao = new CloDao();
    	Set <Student> students = new HashSet<>();
    	List<Clo> clos = new ArrayList<>();
    	if(stu1.getAllStudents().size()< 1 || clodao.getAllClos().size()< 1){
        	System.out.println("No student/clo records exist!");
    	}else{
    		System.out.println("Select student/students");
	    	for(int i = 0; i < stu1.getAllStudents().size();i++){
	    		System.out.println("Press 1 to select, Press any other key to skip. \nPress 0 to exit.");
	    		System.out.println("Student: " + stu1.getAllStudents().get(i));
	    		int inp = sc.nextInt();
	    		if(inp==0){
	    			break;
	    		}else{
	    			if(inp==1){
	    				students.add(stu1.getAllStudents().get(i));
	    			}
	    		}
	    	}
    		System.out.println("Select clo/clos");
	    	for(int i = 0; i < clodao.getAllClos().size();i++){
	    		System.out.println("Press 1 to select, Press any other key to skip. \nPress 0 to exit.");
	    		System.out.println("Student: " + clodao.getAllClos().get(i));
	    		int inp = sc.nextInt();
	    		if(inp==0){
	    			break;
	    		}else{
	    			if(inp==1){
	    				clos.add(clodao.getAllClos().get(i));
	    			}
	    		}
	    	}
	    	con.setClo(clos);
	    	con.setStudents(students);
	    	System.out.println("Enter Title");
	    	con.setTitle(sc.next());
	        CourseDao cordao = new CourseDao();
	    	System.out.println("Enter Description");
	    	String temp = "";
			temp = sc.nextLine();
			temp = sc.nextLine();
	    	con.setDescription(temp);
	    	System.out.println("Enter StartTime (MM/dd/yyyy)");
	    	DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	    	try {
				con.setStarttime(format.parse(sc.next()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    	System.out.println("Enter EndTime:");
	    	try {
				con.setEndtime(format.parse(sc.next()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    	Course cour = new Course();
	    	System.out.println("Set Course:");
	    	if(cordao.getAllCourses().size()>1){
	        	System.out.println("No courses have been set yet!");
	    	}else{
		    	for(int i = 0; i < cordao.getAllCourses().size();i++){
		    		System.out.println("Press 1 to select, Press any other key to skip. \nPress 0 to exit.");
		    		System.out.println("Course: " + cordao.getAllCourses().get(i));
		    		int inp = sc.nextInt();
		    		if(inp==0){
		    			break;
		    		}else{
		    			if(inp==1){
		    				cour = cordao.getAllCourses().get(i);
		    				break;
		    			}
		    		}
		    	}
	    	}
	    	con.setCourse(cour);
	    	System.out.println("Saving to Database!");
	    	condao.addContent(con);
    	}
    }
    public void addGrade(){
        GradeDao grade = new GradeDao();
        Grade gra = new Grade();
        ContentDao cordao = new ContentDao();
        Scanner sc = new Scanner(System.in);
    	System.out.println("Enter name");
    	gra.setName(sc.next());
    	System.out.println("Enter Score");
    	gra.setScore(sc.nextInt());
    	if(cordao.getAllContents().size()>1){
        	System.out.println("No content has been set yet!");
    	}else{
	    	for(int i = 0; i < cordao.getAllContents().size();i++){
	    		System.out.println("Press 1 to select, Press any other key to skip. \nPress 0 to exit.");
	    		System.out.println("Course: " + cordao.getAllContents().get(i));
	    		int inp = sc.nextInt();
	    		if(inp==0){
	    			break;
	    		}else{
	    			if(inp==1){
	    				gra.setContentItem(cordao.getAllContents().get(i));
	    			}
	    		}
	    	}
    	}
    	System.out.println("Saving to Database!");
    	grade.addGrade(gra);
    }
    public void addClo(){
        CloDao clodao = new CloDao();
        Clo clo = new Clo();
        Scanner sc = new Scanner(System.in);
    	System.out.println("Enter name:");
    	clo.setName(sc.next());
    	System.out.println("Enter description:");
    	clo.setDescription(sc.next());
    	System.out.println("Enter plo:");
    	clo.setPlo(sc.next());
    	System.out.println("Enter btlevel:");
    	clo.setBtLevel(sc.next());
    	clodao.addClo(clo);
    	System.out.println("Saving to Database!");
    }
    private void addTeacher(){
    	Teacher tea = new Teacher();
    	TeacherDao teach = new TeacherDao();
        CourseDao cordao = new CourseDao();
    	Set <Course> courses = new HashSet<>();
        Scanner sc = new Scanner(System.in);
    	System.out.println("Enter Teacher Name:");
    	tea.setName(sc.next());
    	if(cordao.getAllCourses().size()>1){
        	System.out.println("No courses have been set yet!");
    	}
    	else{
	    	System.out.println("Set Courses:");
	    	for(int i = 0; i < cordao.getAllCourses().size();i++){
	    		System.out.println("Press 1 to select, Press any other key to skip. \nPress 0 to exit.");
	    		System.out.println("Course: " + cordao.getAllCourses().get(i));
	    		int inp = sc.nextInt();
	    		if(inp==0){
	    			break;
	    		}else{
	    			if(inp==1){
	    				courses.add(cordao.getAllCourses().get(i));
	    			}
	    		}
	    	}
    	}
    	tea.setCourses(courses);
    	teach.addTeacher(tea);
    	System.out.println("Saving to Database!");
    }
    private void updateTeacher(){
    	Teacher tea = new Teacher();
    	TeacherDao teach = new TeacherDao();
    	System.out.println(teach.getAllTeachers());
    	System.out.println("Enter the id of the teacher entry you wish to change");
    	Scanner sc = new Scanner(System.in);
    	int temp = sc.nextInt();
        CourseDao cordao = new CourseDao();
    	Set <Course> courses = new HashSet<>();
    	for(int i=0;i<teach.getAllTeachers().size();i++){
    		if(teach.getAllTeachers().get(i).getTeacherId()==temp){
    			tea = teach.getAllTeachers().get(i);
    		}
       	}
    	System.out.println("Modify name; previously(:"+ tea.getName() +")");
    	tea.setName(sc.next());
    	if(cordao.getAllCourses().size()>1){
        	System.out.println("No courses have been set yet!");
    	}
    	else{
	    	System.out.println("Modify courses taken:");
	    	for(int i = 0; i < cordao.getAllCourses().size();i++){
	    		System.out.println("Press 1 to select, Press any other key to skip. \nPress 0 to exit.");
	    		System.out.println("Course: " + cordao.getAllCourses().get(i));
	    		int inp = sc.nextInt();
	    		if(inp==0){
	    			break;
	    		}else{
	    			if(inp==1){
	    				courses.add(cordao.getAllCourses().get(i));
	    			}
	    		}
	    	}
    	}
    	tea.setCourses(courses);
    	teach.updateTeacher(tea);
    	System.out.println("Saving to Database!");
    }
    private void addCourse(){
    	Course cor = new Course();
        CourseDao cordao = new CourseDao();
    	Teacher tea = new Teacher();
    	TeacherDao teach = new TeacherDao();
    	Scanner sc = new Scanner(System.in);
    	if(teach.getAllTeachers().size()<=0){
	    	System.out.println("No teachers available; Enter a teacher first before entering a course.");
    	}else{
	    	System.out.println("Enter class title:");
	    	cor.setClasstitle(sc.next());
	    	System.out.println("Enter start date (MM/dd/yyyy):");
	    	
	    	DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	    	try {
				cor.setStartsOn(format.parse(sc.next()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println("Enter end date (MM/dd/yyyy):");
	    	try {
				cor.setEndsOn(format.parse(sc.next()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println("Set Teacher");
	    	for(int i = 0; i < teach.getAllTeachers().size();i++){
	    		System.out.println("Press 1 to select, Press any other key to skip. \nPress 0 to exit.");
	    		System.out.println("Teacher: " + teach.getAllTeachers().get(i));
	    		int inp = sc.nextInt();
	    		if(inp==0){
	    			break;
	    		}else{
	    			if(inp==1){
	    				cor.setTeacher(teach.getAllTeachers().get(i));
	    				
	    			}
	    		}
	    	}
	    	System.out.println("Enter credit hours");
	    	cor.setCreditHours(sc.nextInt());
	    	cordao.addCourse(cor);
	    	System.out.println("Saving to Database!");
	    	return;
	    }
    }
}