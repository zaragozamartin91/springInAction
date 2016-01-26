package spittr.web;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import spittr.Spitter;
import spittr.data.SpitterRepository;

public class SpitterControllerTest {
	@Test
	public void testShowRegisterForm() throws Exception {
		SpitterController spitterController = new SpitterController(null);

		MockMvc mockMvc = standaloneSetup(spitterController).build();

		mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
	}

	@Test
	public void shouldProcessRegistration() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		Spitter unsaved = new Spitter("jbauer", "Jbauer", "Jack", "Bauer", "some@mail.com");
		Spitter saved = new Spitter(24L, "jbauer", "Jbauer", "Jack", "Bauer", "some@mail.com");

		when(mockRepository.save(unsaved)).thenReturn(saved);

		SpitterController controller = new SpitterController(mockRepository);

		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(
				post("/spitter/register").param("firstName", "Jack").param("lastName", "Bauer")
						.param("username", "jbauer").param("password", "Jbauer")).andExpect(
				redirectedUrl("/spitter/jbauer"));

		verify(mockRepository, atLeastOnce()).save(unsaved);
	}
}
