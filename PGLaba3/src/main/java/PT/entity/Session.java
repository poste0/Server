package PT.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("singleton")
public class Session {

    private User user1;

    private User user2;

    private UUID id;

    private boolean status = false;

    private int player = 0;

    public User getUser1(){
        return user1;
    }

    public User getUser2(){
        return user2;
    }
    public User go(){
        String action1 = this.user1.getAction();
        String action2 = this.user2.getAction();

        if(action1.equals("stone")){
            if(action2.equals("cut")){
                return user1;
            }
            else if(action2.equals("paper")){
                return user2;
            }
            else return null;
        }
        else if (action1.equals("cut")){
            if(action2.equals("stone")){
                return user2;
            }
            else if(action2.equals("paper")){
                return user1;
            }
            else return null;
        }
        else if(action1.equals("paper")){
            if(action2.equals("stone")){
                return user1;
            }
            else if(action2.equals("cut")){
                return user2;
            }
            else return null;
        }
        return null;
    }

    public void setAction(User user){
        if(user.getId().equals(this.user1.getId())){
            player++;
            user1.setAction(user.getAction());
        }
        else if(user.getId().equals(this.user2.getId())){
            player++;
            user2.setAction(user.getAction());
        }
        //status = true;

    }

    public Session(User user1 , User user2){
        this.user1 = user1;
        this.user2 = user2;
        id = UUID.randomUUID();
    }

    public Session(){
        id = UUID.randomUUID();
    }
     public void setUser(User user){

        if(this.user1 == null){
            this.user1 = user;
        }
        else{
            this.user2 = user;
            status = true;
        }
     }

     public boolean getStatus(){
        return status;
     }

    public int getPlayer() {
        return player;
    }
}
