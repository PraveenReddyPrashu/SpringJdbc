package basic;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import basic.dao.StudentDao;
import basic.dao.StudentDaoImpl;
import basic.model.Student;

public class PlayJdbc {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		/*StudentDaoHelper helper = context.getBean("studentDaoHelper",StudentDaoHelper.class);
		helper.insertStudents();*/
		
		StudentDaoImpl studentDao = (StudentDaoImpl) context.getBean("studentDao");
		//System.out.println(studentDao.findStudentById(4));
		List<Student> students = studentDao.getAllStudents();
		printStudents(students);
		//StudentDao studentDao = (StudentDao) context.getBean("studentDao");
		//studentDao.delRecordById(4);
		//studentDao.delRecordById(2);
		//studentDao.cleanUp();
		//insertStudent(studentDao);
		//studentDao.delRecordByNameSem("lahari", 5);

	}
		
	private static void printStudents(List<Student> students) {
		// TODO Auto-generated method stub
		for(Student s: students) {
			System.out.println(s);
		}
		
	}

	private static void insertStudent(StudentDao studentDao) {
		Student myStudent = new Student(2, "Praveen", 5, 70);
		Student anotherStudent = new Student(4,"Pavan",8,80);
		//= new StudentDaoImpl();
		studentDao.insert(anotherStudent);
		studentDao.insert(myStudent);
	}


}