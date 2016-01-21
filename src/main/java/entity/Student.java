package entity;

import java.util.List;

public class Student extends AbstractModelBean {
	private static final long serialVersionUID = -8807799012346193642L;
	private Integer id;
	private String firstName;
	private String lastName;
	private String group;
	private String date;
	List<Mark> marks;

	public Student() {
		super();
	}

	public Student(Integer id, String firstName, String lastName, String group,
			String date, List<Mark> marks) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.group = group;
		this.date = date;
		this.marks = marks;
	}

	public Student(String firstName, String lastName, String group, String date) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.group = group;
		this.date = date;
	}

	public Student(Integer id, String firstName, String lastName, String group,
			String date) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.group = group;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

}
