package Model;

import Enums.Role;

public class User {
    private String username;
    private String email;
    private String password;
    private Role role;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public User(String email, String password , Role r) {
        this.email = email;
        this.password = password;
        this.role = r;
    }

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password ) {
        this.email = email;
        this.password = password;
    }

public boolean isEmailEquals(User user2){
        return this.getEmail().equals(user2.getEmail());
}


 public boolean isPassEqual( User user2){
        if(this.getPassword().equals(user2.getPassword()))
            return true;
        return false;
    }

}
