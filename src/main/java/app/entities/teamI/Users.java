package app.entities.teamI;

import java.util.Objects;

public class Users {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private int user_id;

    public Users(String firstname, String lastname, String email, String password, int user_id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return user_id == users.user_id && Objects.equals(firstname, users.firstname) && Objects.equals(lastname, users.lastname) && Objects.equals(email, users.email) && Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, email, password, user_id);
    }
}

