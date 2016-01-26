package npu.edu.eventProject.model;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @NotNull
    @Size(max = 64)
    @Column(name = "username", nullable = false, updatable = false)
    private String username;

    @NotNull
    @Size(max = 64)
    @Column(name = "password", nullable = false)
    private String password;
    
    
    @NotNull
    @Size(max = 64)
    @Column(name = "email", nullable = false)
    private String email;

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	User() {
    }

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
    
    public User(final String username, final String password,String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

  

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
		return username;
	}

	

	@Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("username", username)
                .add("password", password)
                .add("email", email)
                .toString();
    }
}
