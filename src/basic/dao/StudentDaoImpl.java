package basic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import basic.model.Student;

@Repository("studentDao")

public class StudentDaoImpl implements StudentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void insert(Student student_data) {
		String sql = "INSERT INTO student_data VALUES (?,?,?,?)";
		Object[] objects =	{student_data.getId(),student_data.getName(),student_data.getSem(),student_data.getAverage()};

		int no_rows_inserted =	jdbcTemplate.update(sql,objects);
		System.out.println("no of rows inserted  is "+ no_rows_inserted);
	}

	@Override
	public void delRecordById(int id) {
		String delSql = "DELETE FROM student_data WHERE id = ?";
		int noRecordsDeleted = jdbcTemplate.update(delSql,id);
		System.out.println("no of records deleted = "+ noRecordsDeleted);
		// TODO Auto-generated method stub
		
	}
	@Override
	public int delRecordByNameSem(String student_dataName, int sem) {
		String sql = "DELETE FROM student_data WHERE name = ? AND sem = ?"; //replace OR with AND and see the results
		Object[] objects = {student_dataName,sem};
		int noRecordsDeleted = jdbcTemplate.update(sql, objects);
		System.out.println("no of records deleted ="+ noRecordsDeleted);
		return noRecordsDeleted;
	}
	public void cleanUp() {
		String sql = "TRUNCATE TABLE student_data";
		jdbcTemplate.update(sql);
		System.out.println("table cleaned");
	}
	@Override
	public void insert(ArrayList<Student> student_datas) {
		String sql = "INSERT INTO student_data VALUES (?,?,?,?)";
		ArrayList<Object[]> sqlArgs = new ArrayList<>();
		for(Student student_data : student_datas) {
			Object[] student_dataData =	{student_data.getId(),student_data.getName(),student_data.getSem(),student_data.getAverage()};
			sqlArgs.add(student_dataData);
		}
		
		jdbcTemplate.batchUpdate(sql, sqlArgs);
	}

	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM student_data";
		List<Student> student_datas = jdbcTemplate.query(sql, new StudentResultSetExtractor());
				// new StudentRowMapper());
		return student_datas;
	}

	public Student findStudentById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM student_data WHERE id = ?";
		Student student_data =	jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Student>(Student.class),id);
		return student_data;	
		}

	
	
}