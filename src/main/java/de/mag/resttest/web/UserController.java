package de.mag.resttest.web;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.mag.resttest.app.users.User;
import de.mag.resttest.app.users.UserService;
import de.mag.resttest.web.exceptions.ResourceNotFoundException;

@Controller
@RequestMapping("/users")
public class UserController {

	@Resource
	private UserService userService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Collection<User> getUsers() {
		return userService.findAll();
	}

	@ResponseBody
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long userId) {
		User user = userService.findById(userId);
		if (user == null) {
			throw new ResourceNotFoundException("No user with id " + userId);
		}
		return user;
	}

	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Long createUser(@RequestBody User user) {
		return userService.create(user);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		userService.update(user);
	}

}
