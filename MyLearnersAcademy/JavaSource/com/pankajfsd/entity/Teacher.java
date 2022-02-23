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
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teacher_id;
	private String name;
	
	// one teacher many subjects
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Teacher_Subject", joinColumns = {@JoinColumn(name="teacher_id", referencedColumnName = "teacher_id")},
	inverseJoinColumns = {@JoinColumn(name="subject_id",referencedColumnName = "subject_id")})
	private List<Subject> subjects;

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(int teacher_id, String name) {
		super();
		this.teacher_id = teacher_id;
		this.name = name;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Teacher [teacher_id=" + teacher_id + ", name=" + name + "]";
	}

}
