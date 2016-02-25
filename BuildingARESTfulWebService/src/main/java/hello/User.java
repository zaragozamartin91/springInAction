package hello;

public class User {
	private String name;
	private Long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User(String name, Long id) {
		super();
		this.name = name;
		this.id = id;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
