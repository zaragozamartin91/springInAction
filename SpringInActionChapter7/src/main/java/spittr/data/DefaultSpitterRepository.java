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

		if (spitterExists(username)) {
			throw new DuplicateSpitterException("Spitter " + username + " Ya existe!");
		}

		addSpitter(spitter, username);
	}

	private Spitter addSpitter(Spitter spitter, final String username) {
		return spitters.put(username, spitter);
	}

	private boolean spitterExists(final String username) {
		return spitters.containsKey(username);
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
