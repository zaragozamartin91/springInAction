package spittr.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import spittr.Spittle;

@Component
public class DefaultSpittleRepository implements SpittleRepository {
	private List<Spittle> defaultSpittles = new ArrayList<>();

	@PostConstruct
	private void createSpittleList() {
		final int count = 5;
		for (int i = 0; i < count; i++) {
			defaultSpittles.add(newSpittle(i));
		}
	}

	private Spittle newSpittle(long i) {
		return new Spittle("Spittle " + i, new Date());
	}

	@Override
	public List<Spittle> findSpittles(long max, int count) {
		return defaultSpittles;
	}

	@Override
	public Spittle findOne(Long id) {
		return newSpittle(id);
	}

	@Override
	public void save(Spittle spittle) {
		
	}
}
