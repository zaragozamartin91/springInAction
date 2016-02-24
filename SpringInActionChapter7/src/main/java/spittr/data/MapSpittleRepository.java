package spittr.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import spittr.Spittle;
import spittr.web.DuplicateSpittleException;

@Component
public class MapSpittleRepository implements SpittleRepository {
	private Map<Long, Spittle> spittles = new LinkedHashMap<>();
	private static Long spittleId = 0L;

	@PostConstruct
	private void createDefaultSpittles() {
		final int count = 5;
		for (int i = 0; i < count; i++) {
			this.save(newSpittle(i));
		}
	}

	private Spittle newSpittle(long i) {
		return new Spittle("Spittle " + i, new Date());
	}

	@Override
	public List<Spittle> findSpittles(long max, int count) {
		List<Spittle> collected = new ArrayList<>();

		for (Spittle spittle : spittles.values()) {
			if (collected.size() > count) {
				break;
			}

			if (spittle.getId() < max) {
				collected.add(spittle);
			}
		}

		return collected;
	}

	@Override
	public Spittle findOne(Long id) {
		return spittles.get(id);
	}

	@Override
	public void save(Spittle spittle) {
		if (spittle.isUnknown()) {
			spittle.setId(++spittleId);
		}

		if (spittleExists(spittle)) {
			throw new DuplicateSpittleException("El spittle de id " + spittle.getId()
					+ " ya existe!");
		}
		spittles.put(spittle.getId(), spittle);
	}

	private boolean spittleExists(Spittle spittle) {
		return spittles.containsKey(spittle.getId());
	}
}
