package spittr.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import spittr.Spitter;

@Component
public class DefaultSpitterRepository implements SpitterRepository {
	private Map<String, Spitter> spitters = new HashMap<>();

	@Override
	public Spitter save(Spitter spitter) {
		setSpitterId(spitter);

		saveSpitterByUsername(spitter);

		return spitter;
	}

	private void saveSpitterByUsername(Spitter spitter) {
		final String username = spitter.getUsername();
		spitters.put(username, spitter);
	}

	private void setSpitterId(Spitter spitter) {
		final long spitterCount = spitters.size();
		spitter.setId(spitterCount);
	}

	@Override
	public Spitter findByUsername(final String username) {
		return spitters.get(username);
	}
}
