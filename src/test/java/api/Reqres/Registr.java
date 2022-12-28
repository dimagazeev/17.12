package api.Reqres;

public class Registr {
    private String email;
    private String password;

    public Registr(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public Registr() {
    }

    public String getEmail() {
        return email;}

    public String getPassword() {
        return password;
    }
}
