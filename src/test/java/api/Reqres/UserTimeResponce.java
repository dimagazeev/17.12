package api.Reqres;

public class UserTimeResponce extends UserTime {
    private String updatedAt;


    public UserTimeResponce(String name, String job, String updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }

    public UserTimeResponce() {
        super();

    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
