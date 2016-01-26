package spittr;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;

public class Spitter {
	private Long id;

	/*
	 * For each of the fields, the @Size annotation has message set to a string
	 * whose value is wrapped in curly braces. If you left the curly braces out,
	 * the value given to message would be the error message displayed to the
	 * user. But by using curly braces, you designate a property in a properties
	 * file that contains the actual message. All that’s left to do is to create
	 * a file named ValidationMessages.properties at the root of the classpath
	 */

	@NotNull
	@Size(min = 5, max = 16, message = "{message.size}")
	@Pattern(regexp = "(\\d|\\w)+")
	private String username;

	@NotNull
	@Size(min = 5, max = 25, message = "{password.size}")
	@Pattern(regexp = "[A-Z](\\d|\\w)+")
	private String password;

	@NotNull
	@Size(min = 2, max = 30, message = "{firstName.size}")
	private String firstName;

	@NotNull
	@Size(min = 2, max = 30, message = "{lastName.size}")
	private String lastName;

	@NotNull
	@Email(message = "{email.valid}")
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Spitter(String username, String password, String firstName, String lastName, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Spitter(Long id, String username, String password, String firstName, String lastName,
			String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Spitter() {
		super();
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "firstName", "lastName", "username",
				"password", "email");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "firstName", "lastName", "username",
				"password", "email");
	}
}
