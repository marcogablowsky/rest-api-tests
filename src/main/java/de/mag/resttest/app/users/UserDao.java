package de.mag.resttest.app.users;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class UserDao {

	private Map<Long, User> users = new HashMap<>();
	private long maxId = 0;

	@PostConstruct
	void initUsers() {
		createDemoUsers();
	}

	Collection<User> findAll() {
		return this.users.values();
	}

	User findById(Long userId) {
		return this.users.get(userId);
	}

	Long persist(User user) {
		if (user.getId() == null) {
			user.setId(Long.valueOf(++maxId));
		}
		this.users.put(user.getId(), user);
		return user.getId();
	}

	private void createDemoUsers() {

		persist(User.create().firstName("Marco").lastName("Gablowsky").age(30));
		persist(User.create().firstName("John").lastName("Doe").age(1337));
	}
}
