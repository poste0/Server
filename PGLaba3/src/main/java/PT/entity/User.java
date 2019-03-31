package PT.entity;

import org.springframework.stereotype.Component;

import java.util.UUID;

public class User {

    private String name;
    private UUID id;

    private String action;
    private int k;

    public User(String name){
        this.name = name;
        id = UUID.randomUUID();
        action = "None";
    }

    public User(String name , String id , String action){
        this.name = name;
        this.id = UUID.fromString(id);
        this.action = action;
    }
    public int getK(){
        return k;
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
