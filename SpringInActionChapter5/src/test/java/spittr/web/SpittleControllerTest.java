package spittr.web;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.Spittle;
import spittr.data.SpittleRepository;

public class SpittleControllerTest {
	@Test
	public void shouldShowRecentSpittles() throws Exception {
		final int count = 20;
		final long max = Long.MAX_VALUE;

		final List<Spittle> expectedSpittles = createSpittleList(count);

		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(max, count)).thenReturn(expectedSpittles);

		SpittleController spittleController = new SpittleController(mockRepository);

		/*
		 * this test calls setSingleView() on the MockMvc builder. This is so
		 * the mock framework won’t try to resolve the view name coming from the
		 * controller on its own
		 */
		/*
		 * For this controller method, the view name will be similar to the
		 * request’s path; left to its default view resolution, MockMvc will
		 * fail because the view path will be confused with the controller’s
		 * path.
		 */
		MockMvc mockMvc = standaloneSetup(spittleController).setSingleView(
				new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();

		mockMvc.perform(get("/spittles")).andExpect(view().name("spittles"))
				.andExpect(model().attributeExists("spittleList"))
				.andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
	}

	@Test
	public void shouldShowPagedSpittles() throws Exception {
		final int count = 50;
		final int max = 238900;

		List<Spittle> expectedSpittles = createSpittleList(count);
		SpittleRepository mockRepository = mock(SpittleRepository.class);

		when(mockRepository.findSpittles(max, count)).thenReturn(expectedSpittles);

		SpittleController controller = new SpittleController(mockRepository);

		MockMvc mockMvc = standaloneSetup(controller).setSingleView(
				new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();

		mockMvc.perform(get("/spittles?max=238900&count=50")).andExpect(view().name("spittles"))
				.andExpect(model().attributeExists("spittleList"))
				.andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
	}

	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("Spittle " + i, new Date()));
		}
		return spittles;
	}
}
