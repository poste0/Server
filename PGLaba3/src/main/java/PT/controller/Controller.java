package PT.controller;


import PT.entity.Session;
import PT.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class Controller {

    @Autowired
    private Session session;


    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public ResponseEntity findSession(@RequestParam("name") String name) throws InterruptedException {
        User user = new User(name);
        session.setUser(user);
        System.out.println("21412");
        while(!session.getStatus()){
            if(session.getStatus()){
                break;
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @RequestMapping(value = "/go" , method = RequestMethod.POST)
    public ResponseEntity go(@RequestBody User user){
        session.setAction(user);
        while(session.getPlayer() < 2){
            if(session.getPlayer() == 2){
                break;
            }
        }

        User user1 = session.go();
        if(user1 == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A");
        }
        if(user1.getId() == session.getUser1().getId()){
            return ResponseEntity.status(HttpStatus.OK).body(user1);

        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(user1);

        }

    }

    public class UserResponse{
        User user;
        String message;

        public UserResponse(User user , String message){
            this.user = user;
            this.message = message;
        }
    }
}
