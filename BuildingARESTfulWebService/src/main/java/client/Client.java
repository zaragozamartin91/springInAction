package client;

import hello.UserList;

import org.springframework.web.client.RestTemplate;

public class Client {

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		{
			final String uri = "http://localhost:8080/greeting";

			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(uri, String.class);

			System.out.println(result);
		}

		{
			final String uri = "http://localhost:8080/users";

			RestTemplate restTemplate = new RestTemplate();
			UserList result = restTemplate.getForObject(uri, UserList.class);

			System.out.println(result);
		}
	}
}
