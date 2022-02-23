package com.pankajfsd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subject_id;
	private String name;

	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subject(int subject_id, String name) {
		super();
		this.subject_id = subject_id;
		this.name = name;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Subject [subject_id=" + subject_id + ", name=" + name + "]";
	}

}
