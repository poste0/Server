import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.*;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Main {
   static OkHttpClient client = new OkHttpClient();
   static  User user;

    public static void main(String[] args) throws IOException {

        client.setConnectTimeout(10 , TimeUnit.MINUTES);
        client.setWriteTimeout(10 , TimeUnit.MINUTES);
        client.setReadTimeout(10 , TimeUnit.MINUTES);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name\n");
        String name = scanner.next();
        System.out.println("Type start to find a game or type help to show help\n");
        String enter = scanner.next();
        while(true) {
            if (enter.equals("help")) {
                help();
                break;
            } else if (enter.equals("start")) {
                findGame(name);
                break;
            }
            else {
                System.out.println("Wrong");
            }
        }
        System.out.println("Choose an action");
        String action = scanner.next();
        System.out.println("Wait for your enemy's turn");
        play(action);


    }

    public static void help(){
        System.out.println("You choose one of these actions:" +
                " Cut , Paper , Stone." +
                " Cut wins paper" +
                " Paper wins stone" +
                " Stone wins cuts\n");
    }

    public static void findGame(String name) throws IOException {
        System.out.println("The game is being looked for\n");
        HttpUrl urls = HttpUrl.parse("http://localhost:8080/?name=" + name);
        Request request = new Request.Builder().url(urls).get().build();
        Response response = client.newCall(request).execute();
        JSONObject object = new JSONObject(response.body().string());
        user = new User(object.getString("name") , UUID.fromString(object.getString("id")) , object.getString("action"));
        System.out.println("The game has been found\n");

    }

    public static void play(String action) throws IOException {
        HttpUrl urls = HttpUrl.parse("http://localhost:8080/go");
        JSONObject object = new JSONObject();
        object.put("name" , user.getName());
        object.put("id" , user.getId().toString());
        object.put("action" , action);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8") , object.toString());
        Request request = new Request.Builder().url(urls).post(body).build();
        Response response = client.newCall(request).execute();
        String respon = response.body().string();
        if(respon.equals("A")){
            System.out.println("You and your enemy have one choice");
            return;
        }
        object = new JSONObject(respon);

        if(object.get("id").equals(user.getId().toString())){
            System.out.println("You have won");
        }
        else{
            System.out.println("You have lost");
        }
    }
}
