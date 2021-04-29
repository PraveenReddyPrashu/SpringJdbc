package basic.dao;
import java.util.ArrayList;
import java.util.List;

import basic.model.Student;

public interface StudentDao {
	public void insert(Student student);
	void delRecordById(int id);
	 int delRecordByNameSem(String studentName, int sem);
	public void cleanUp();
	 public void insert(ArrayList<Student> students);
	 public List<Student> getAllStudents();
	 Student findStudentById(int id);

}