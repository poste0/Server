import java.util.List;
import java.util.Random;

public class Cleaner {


    private Types[][] map;
    private List<List<Types>> knownMap;
    private int x;
    private int y;
    private int points;
    private int xSize;
    private int ySize;
    private final int UP = 0;
    private final int RIGHT = 1;
    private final int DOWN = 2;
    private final int LEFT = 3;
    private int face;
    private Windows frame;
    private int[] sensors = new int[]{ 0 , 0 , 0};
    public Cleaner(int xSize , int ySize , int startPoints){
        this.xSize = xSize + 2;
        this.ySize = ySize + 2;
        map = new Types[this.ySize][this.xSize];
        for(int i = 0 ; i < this.ySize ; i++){
            for(int j = 0 ; j < this.xSize ; j++){
                if(i == 1 && j ==1){
                    map[i][j] = Types.HOME;
                    continue;
                }
                if(i == 0 || j == 0 || i == this.ySize - 1 || j == this.xSize - 1 ){
                    map[i][j] = Types.OBSTACLE;
                    continue;

                }
                map[i][j] = Types.CLEAN;
            }
        }
        this.points = startPoints;
        x = 1;
        y = 1;
        face = RIGHT;
        frame = new Windows(map , xSize , ySize);
        randomInit();

    }
    public void init(){}
    public void randomInit(){
            Random random = new Random();
        int value;
        for(int i = 0 ; i < ySize ; i++) {
                for (int j = 0; j < xSize; j++) {
                    if(i == 1 && j ==1){
                        map[i][j] = Types.HOME;
                        continue;
                    }
                    if(i == 0 || j == 0 || i == this.ySize - 1 || j == this.xSize - 1 ){
                        map[i][j] = Types.OBSTACLE;
                        continue;

                    }
                    value = random.nextInt() % 76;
                    if (value <= 25) {
                        map[i][j] = Types.CLEAN;
                    } else if (value > 25 && value <= 50) {
                        map[i][j] = Types.DIRTY;
                    } else if (value > 50 && value < 75) {
                        map[i][j] = Types.OBSTACLE;
                    }
                }
            }
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < ySize ; i++){
            for(int j = 0 ; j < xSize ; j++){
                if(map[i][j] == Types.OBSTACLE){
                    builder.append("O ");
                }
                else if(map[i][j] == Types.CLEAN){
                    builder.append("C ");
                }
                else if(map[i][j] == Types.DIRTY){
                    builder.append("* ");
                }
                else if(map[i][j] == Types.HOME){
                    builder.append("P ");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public void turnLeft(){
        points--;
        face = face == UP ? LEFT : face - 1;
    }
    public void turnRight(){
        points--;
        face = face == LEFT ? UP : face + 1;
    }
    public void goForward(){
        points--;
        switch (face){
            case UP:
                y--;
                break;
            case RIGHT:
                x++;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
        }
        if(map[y][x] == Types.OBSTACLE){
            sensors[0] = 1;
        }
        else if(map[y][x] == Types.DIRTY){
            sensors[1] = 1;
        }
        else if(map[y][x] == Types.HOME){
            sensors[2] = 1;
        }
    }
    public void suckUp(){
        points += 100;
    }
    public void turnOff(){}

    private int lastFace = DOWN;
    public void work() throws InterruptedException {
        int upBorder = 1;
        int rightBorder = -1;
        while(true){
            Thread.sleep(1000);
           if(y == upBorder){
               turn(RIGHT);
               goForward();
               check();
               paint();
           }
           if(y != upBorder){
               turn();
               goForward();
               check();
               goForward();
               paint();
            }
        }
    }
    void paint(){
        frame.repaint(x , y , face);
    }
    void check(){
        if(sensors[0] == 1){
            goBack();
            turn();
        }
        else if(sensors[1] == 1){
            suckUp();
        }
        else if(sensors[2] == 1){
            turnOff();
        }
    }
    void goBack(){
        switch (face){
            case UP:
                y++;
                break;
            case RIGHT:
                x--;
                break;
            case DOWN:
                y--;
                break;
            case LEFT:
                x++;
                break;
        }
        sensors[0] = 0;
    }

    void turn(){
        switch(lastFace){
            case UP:
                switch (face){
                    case UP:
                        //lastFace = UP;
                        break;
                    case RIGHT:
                        //lastFace = RIGHT;
                        turnLeft();
                        break;
                    case DOWN:
                       // lastFace = DOWN;
                        turnLeft();
                        turnLeft();
                        break;
                    case LEFT:
                    //    lastFace = LEFT;
                        turnRight();
                        break;
                }
                break;
            case RIGHT:
                switch (face){
                    case UP:
                     //   lastFace = UP;
                        turnRight();
                        break;
                    case RIGHT:
                      //  lastFace = RIGHT;
                        break;
                    case DOWN:
                      //  lastFace = DOWN;
                        turnLeft();
                        break;
                    case LEFT:
                      //  lastFace = LEFT;
                        turnRight();
                        turnRight();
                        break;
                }
                break;
            case DOWN:
                switch (face){
                    case UP:
                       // lastFace = UP;
                        turnRight();
                        turnRight();
                        break;
                    case RIGHT:
                     //   lastFace = RIGHT;
                        turnRight();
                        break;
                    case DOWN:
                       // lastFace = DOWN;
                        break;
                    case LEFT:
                      //  lastFace = LEFT;
                        turnLeft();
                        break;
                }
                break;
            case LEFT:
                switch (face){
                    case UP:
                       // lastFace = UP;
                        turnLeft();
                        break;
                    case RIGHT:
                       // lastFace = RIGHT;
                        turnLeft();
                        turnLeft();
                        break;
                    case DOWN:
                      //  lastFace = DOWN;
                        turnRight();
                        break;
                    case LEFT:
                      //  lastFace = LEFT;
                        break;
                }
                break;

        }

    }
    void turn(int newFace){
        switch (newFace) {
            case RIGHT:
                switch (face) {
                case UP:
                    //lastFace = UP;
                    turnRight();
                    break;
                case RIGHT:
                    //lastFace = RIGHT;
                    break;
                case DOWN:
                    //lastFace = DOWN;
                    turnLeft();
                    break;
                case LEFT:
                    //lastFace = LEFT;
                    turnRight();
                    turnRight();
                    break;
            }
            break;
            case DOWN:
                switch (face) {
                    case UP:
                        //lastFace = UP;
                        turnRight();
                        turnRight();
                        break;
                    case RIGHT:
                        //lastFace = RIGHT;
                        turnRight();
                        break;
                    case DOWN:
                        //lastFace = DOWN;
                        break;
                    case LEFT:
                        //lastFace = LEFT;
                        turnLeft();
                        break;
                }
                break;
            case LEFT:
                switch (face) {
                    case UP:
                        //lastFace = UP;
                        turnLeft();
                        break;
                    case RIGHT:
                        //lastFace = RIGHT;
                        turnLeft();
                        turnLeft();
                        break;
                    case DOWN:
                        //lastFace = DOWN;
                        turnRight();
                        break;
                    case LEFT:
                        //lastFace = LEFT;
                        break;
                }
                break;
            case UP:
                switch (face) {
                    case UP:
                       // lastFace = UP;
                        break;
                    case RIGHT:
                      //  lastFace = RIGHT;
                        turnLeft();
                        break;
                    case DOWN:
                      //  lastFace = DOWN;
                        turnLeft();
                        turnLeft();
                        break;
                    case LEFT:
                        //lastFace = LEFT;
                        turnRight();
                        break;
                }
                break;
        }
    }
}
