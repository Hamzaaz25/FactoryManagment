public class User {
    private String username;
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

    public User(String username, String password , Role r) {
        this.username = username;
        this.password = password;
        this.role = r;
    }

    public User(String username, String password ) {
        this.username = username;
        this.password = password;

    }

public boolean isEmailEquals(User user2){
        return this.getUsername().equals(user2.getUsername());
}


 public boolean isPassEqual( User user2){
        if(this.getPassword().equals(user2.getPassword()))
            return true;
        return false;
    }

}
