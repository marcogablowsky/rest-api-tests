package de.mag.resttest.app.users;

public class User {

	private Long id;
	private String firstName;
	private String lastName;
	private int age;

	public String getFirstName() {
		return firstName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static User create() {
		return new User();
	}

	public User firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public User lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public User age(int age) {
		this.age = age;
		return this;
	}
}
