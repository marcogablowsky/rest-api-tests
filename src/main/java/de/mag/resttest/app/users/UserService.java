package de.mag.resttest.app.users;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Resource
	private UserDao userDao;

	public Collection<User> findAll() {
		return userDao.findAll();
	}

	public User findById(Long userId) {
		return userDao.findById(userId);
	}

	public Long create(User user) {
		return userDao.persist(user);
	}

	public void update(User user) {
		userDao.persist(user);
	}

}
