package com.pankajfsd.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class ClassRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int class_id;
	private String className;

	// one class can have many students

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Class_Student", joinColumns = {
			@JoinColumn(name = "class_id", referencedColumnName = "class_id") }, inverseJoinColumns = {
					@JoinColumn(name = "student_id", referencedColumnName = "student_id") })
	private List<Student> students;

	public ClassRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClassRoom(int class_id, String className) {
		super();
		this.class_id = class_id;
		this.className = className;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "ClassRoom [class_id=" + class_id + ", className=" + className + ", students=" + students + "]";
	}

}
