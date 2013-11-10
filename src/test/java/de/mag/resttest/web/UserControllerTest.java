package de.mag.resttest.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import de.mag.resttest.AbstractWebIntegrationTest;

public class UserControllerTest extends AbstractWebIntegrationTest {

	/*
	 * Testing controller behavior according to
	 * http://www.restapitutorial.com/lessons/httpmethods.html
	 */

	@Test
	public void deliversAllUsers() throws Exception {
		mvc.perform(
				get("/users"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().encoding("UTF-8"));
	}

	@Test
	public void deliversSingleUserById() throws Exception {
		mvc.perform(
				get("/users/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().encoding("UTF-8"));;
	}

	@Test
	public void respodsWithNotFoundOnInvalidUserId() throws Exception {
		mvc.perform(
				get("/users/100"))
				.andExpect(status().isNotFound())
				.andReturn().getResponse();
	}

	/*
	 * This is not the recommended behavior of the article but I think it's more
	 * comprehensive than a NotFound response.
	 */
	@Test
	public void respondsWithMethodNotAllowedOnPutRequestToCollection() throws Exception {
		try (InputStream in = new FileInputStream("src/test/resources/createUser.json")) {
			mvc.perform(
					put("/users")
							.contentType(MediaType.APPLICATION_JSON)
							.content(IOUtils.toByteArray(in)))
					.andExpect(status().isMethodNotAllowed());
		}
	}

	@Test
	public void updatesSingleExistingUser() throws Exception {
		try (InputStream in = new FileInputStream("src/test/resources/updateUser.json")) {
			mvc.perform(
					put("/users/1")
							.contentType(MediaType.APPLICATION_JSON)
							.content(IOUtils.toByteArray(in)))
					.andExpect(status().isOk());
		}
	}

	@Test
	public void respondsWithNotFoundOnPutRequestToNonExistingUser() throws Exception {
		try (InputStream in = new FileInputStream("src/test/resources/updateUser.json")) {
			mvc.perform(
					put("/users/100")
							.contentType(MediaType.APPLICATION_JSON)
							.content(IOUtils.toByteArray(in)))
					.andExpect(status().isNotFound());
		}
	}

	@Test
	public void respondsWithNoContentOnPutRequestWithoutUserData() throws Exception {
		mvc.perform(
				put("/users/1")
						.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isNoContent());
	}

	@Test
	public void createsUser() throws Exception {
		try (InputStream in = new FileInputStream("src/test/resources/createUser.json")) {
			mvc.perform(
					post("/users")
							.contentType(MediaType.APPLICATION_JSON)
							.content(IOUtils.toByteArray(in)))
					.andExpect(status().isCreated())
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(content().encoding("UTF-8"));
		}
	}
}
