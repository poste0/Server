import java.util.UUID;

public class User {

    private String name;
    private UUID id;

    private String action;

    public User(String name){
        this.name = name;
        id = UUID.randomUUID();
    }

    public User(String name , UUID id , String action){
        this.name = name;
        this.id = id;
        this.action = action;
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
