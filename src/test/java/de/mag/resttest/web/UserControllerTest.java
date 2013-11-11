package de.mag.resttest.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
	 * This is the recommended response
	 */
	@Test
	public void respondsWithNotFoundOnPutRequestToCollection() throws Exception {
		try (InputStream in = new FileInputStream("src/test/resources/createUser.json")) {
			mvc.perform(
					put("/users")
							.contentType(MediaType.APPLICATION_JSON)
							.content(IOUtils.toByteArray(in)))
					.andExpect(status().isNotFound());
		}
	}

	@Test
	public void updatesSingleExistingUser() throws Exception {
		try (InputStream in = new FileInputStream("src/test/resources/updateUser.json")) {
			mvc.perform(
					put("/users/1")
							.contentType(MediaType.APPLICATION_JSON)
							.content(IOUtils.toByteArray(in)))
					.andExpect(status().isNoContent());
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
	public void respondsWithBadRequestOnPutRequestWithoutUserData() throws Exception {
		mvc.perform(
				put("/users/1")
						.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isBadRequest());
	}

	@Test
	public void createsUser() throws Exception {
		/*
		 * No location header is returned, instead the response body contains the id
		 * of the created resource.
		 */
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

	/*
	 * This is the recommended response
	 */
	@Test
	public void respondsWithNotFoundOnPostRequestToSpecificUser() throws Exception {
		try (InputStream in = new FileInputStream("src/test/resources/createUser.json")) {
			mvc.perform(
					post("/users/15")
							.contentType(MediaType.APPLICATION_JSON)
							.content(IOUtils.toByteArray(in)))
					.andExpect(status().isNotFound());
		}
	}

	@Test
	public void deletesUser() throws Exception {
		mvc.perform(
				delete("/users/2"))
				.andExpect(status().isOk());
	}

	@Test
	public void respondsWithNotFoundOnDeleteRequestToNonExistentUser() throws Exception {
		mvc.perform(
				delete("/users/1000"))
				.andExpect(status().isNotFound());
	}

	/*
	 * This is the recommended response
	 */
	@Test
	public void respondsWithNotFoundOnDeleteRequestToCollection() throws Exception {
		mvc.perform(
				delete("/users"))
				.andExpect(status().isNotFound());
	}
}
