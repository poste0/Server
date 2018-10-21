/**
 * Created by Виктор on 11.10.2018.
 */

public class Vacuumcleaner {
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    int face = RIGHT;
    private int[] sensors = new int[]{0 , 0 , 0};
    private int x;
    private int y;
    private int m , n;
    private int points;
    void turnLeft(){
        points -= 1;
        if(face != UP){
            face -= 1;
        }
        else{
           face = LEFT;
        }
    }

    void turnRight(){
        points -= 1;
        if(face != LEFT){
            face += 1;
        }
        else{
            face = UP;
        }
    }

    void suckUp(){
        if(sensors[1] == 1){
            points += 100;
        }

    }
    void turnOff(){
        if(x != 0 || y != 0){
            points -= 1000;
        }

    }

    void goForward(){
        points -= 1;
        switch(face){
            case UP:
                y -= 1;
                break;
            case RIGHT:
                x += 1;
                break;
            case DOWN:
                y += 1;
                break;
            case LEFT:
                x -= 1;
                break;
        }

    }

    void turn(){
        if(sensors[0] == 1){
            switch(face){
                case UP:
                    y += 1;
                    break;
                case RIGHT:
                    x -= 1;
                    if(x == m - 1 && y == n - 1){turnLeft();}
                    else turnRight();
                    break;
                case DOWN:
                    y -= 1;
                    if(y == (n - 1) || x == (n - 1)){turnLeft();}
                    else turnRight();
                    break;
                case LEFT:
                    x += 1;
                    turnLeft();
                    break;
            }

        }
        if(sensors[1] == 1){
            suckUp();
        }
        if(sensors[2] == 1){
            turnOff();
            System.exit(1);
        }
    }


    public Vacuumcleaner(int points , int m , int n ) {
        x = 1;
        y = 1;
        this.points = points;
        this.n = n;
        this.m = m;
    }

    public int[] getSensors() {
        return sensors;
    }

    public void setSensors(int[] sensors) {
        this.sensors = sensors;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getSign(){
        switch (face){
            case UP:
                return "^";
            case RIGHT:
                return ">";
            case DOWN:
                return "V";
            case LEFT:
                return "<";
        }
        return "B";
    }
}
