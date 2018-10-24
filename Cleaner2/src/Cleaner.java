import java.lang.reflect.Type;
import java.util.*;

public class Cleaner {


    private Types[][] map;
    private Types[][] knownMap;
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
        knownMap = new Types[this.ySize][this.xSize];
        for(int i = 0 ; i < this.ySize ; i++){
            for(int j = 0 ; j < this.xSize ; j++){
                if(i == 1 && j ==1){
                    map[i][j] = Types.HOME;
                    continue;
                }
                if(i == 0 || j == 0 || i == this.ySize - 1 || j == this.xSize - 1 ){
                    map[i][j] = Types.OBSTACLE;
                    knownMap[i][j] = Types.OBSTACLE;
                    continue;

                }
                map[i][j] = Types.CLEAN;
                knownMap[i][j] = Types.NONE;
            }
        }
        knownMap[1][1] = Types.PASSED;
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
            Thread.sleep(500);
           /*if(y == upBorder){
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
            }*/
           if(steps >= 5)
           {
               goHome1();
           }
           if(y == upBorder + 1 && face == UP){
               for(int i = 0 ; i < xSize ; i++){
                   knownMap[upBorder][i] = Types.BORDER;
               }
           }

           move();
           paint();
            System.out.println(upBorder);

        }
    }

    private void goHome1()
    {
        int[][] markedNode = new int[ySize][xSize]; // массив, где будут хранится "отметки" каждого узла
        int markNumber = 1;                        // счетчик
        markedNode[y][x] = markNumber;         // инициализация стартового узла
        while (markedNode[1][1] == 0)
        {          // пока не достигли финишного узла
            for (int i = 0; i < ySize; i++)
            {
                for(int j = 0 ; j < xSize ; j++)
                {
                    if (markedNode[i][j] == markNumber)
                    {                                          // начинаем со стартового узла
                               // просматриваем все соседние узлы
                            if (markedNode[i-1][j] == 0 && knownMap[i-1][j] != Types.OBSTACLE && knownMap[i-1][j] != Types.NONE )
                            {
                                markedNode[i-1][j] = markNumber;
                            }
                        if (markedNode[i+1][j] == 0 && knownMap[i-1][j] != Types.OBSTACLE && knownMap[i-1][j] != Types.NONE && i != ySize - 1 )
                        {
                            markedNode[i+1][j] = markNumber;
                        }
                        if (markedNode[i][j-1] == 0 && knownMap[i-1][j] != Types.OBSTACLE && knownMap[i-1][j] != Types.NONE )
                        {
                            markedNode[i][j-1] = markNumber;
                        }
                        if (markedNode[i][j+1] == 0 && knownMap[i-1][j] != Types.OBSTACLE && knownMap[i-1][j] != Types.NONE )
                        {
                            markedNode[i][j+1] = markNumber;
                        }

                        }
                    }
                }
            markNumber++;
            }
        System.out.println(" ");
        }

    private void goHome() throws InterruptedException
    {
        int startX = x;
        int startY = y;
        knownMap[y][x] = Types.GOING_HOME;
        int[][] goHome = new int[ySize][xSize];
        for(int i = 0 ; i < ySize ; i++){
            for(int j = 0 ; j < xSize ; j++){
                goHome[i][j] = -1;
            }
        }
        int d = 0;
        goHome[y][x] = d++;
        do{
            for(int i = 1 ; i < ySize ; i++){
                for(int j = 1 ; j < xSize ; j++){
                    if(knownMap[i][j] == Types.GOING_HOME){
                        d++;
                        goHome[i-1][j] = knownMap[i-1][j] == Types.OBSTACLE || knownMap[i-1][j] ==  Types.NONE ? -1 : d;
                        if(i != ySize - 1)
                            goHome[i+1][j] = knownMap[i+1][j] == Types.OBSTACLE || knownMap[i+1][j] ==  Types.NONE ? -1 : d;
                        goHome[i][j-1] = knownMap[i][j-1] == Types.OBSTACLE || knownMap[i][j-1] ==  Types.NONE ? -1 : d;
                        if(j != xSize - 1)
                            goHome[i][j+1] = knownMap[i][j+1] == Types.OBSTACLE || knownMap[i][j+1] ==  Types.NONE ? -1 : d;
                        knownMap[i-1][j] = knownMap[i-1][j] == Types.OBSTACLE || knownMap[i-1][j] ==  Types.NONE ? knownMap[i-1][j] : Types.GOING_HOME;
                        if(i != ySize - 1)
                            knownMap[i+1][j] = knownMap[i-1][j] == Types.OBSTACLE || knownMap[i+1][j] ==  Types.NONE ? knownMap[i+1][j] : Types.GOING_HOME;
                        knownMap[i][j-1] = knownMap[i-1][j] == Types.OBSTACLE || knownMap[i][j-1] ==  Types.NONE ? knownMap[i][j-1] : Types.GOING_HOME;
                        if(j != xSize - 1)
                            knownMap[i][j+1] = knownMap[i-1][j] == Types.OBSTACLE || knownMap[i][j+1] ==  Types.NONE ? knownMap[i][j+1] : Types.GOING_HOME;
                    }
                    
                }
            }
        }while(knownMap[1][1] != Types.GOING_HOME);
        int len = goHome[1][1];            // длина кратчайшего пути из (ax, ay) в (bx, by)
        int xx = 1;
        int yy = 1;
        int dd = len;
        int[] px = new int[ySize * xSize];
        int[] py = new int[ySize * xSize];
        int dx[] = {1, 0, -1, 0};   // смещения, соответствующие соседям ячейки
        int dy[] = {0, 1, 0, -1};
        while ( dd > 0 )
        {
            px[d] = x;
            py[d] = y;                   // записываем ячейку (x, y) в путь
            d--;
            for (int k = 0; k < 4; ++k)
            {
                int iy=y + dy[k], ix = x + dx[k];
                if ( iy >= 0 && iy < ySize && ix >= 0 && ix < xSize &&
                    goHome[iy][ix] == d)
                {
                    x = x + dx[k];
                    y = y + dy[k];           // переходим в ячейку, которая на 1 ближе к старту
                    break;
                }
            }
            paint();
        }
        px[0] = x;
        py[0] = y;
        System.out.println(x + " " + y);
    }

    private boolean isBlocked(){
        if((knownMap[y-1][x] == Types.PASSED || knownMap[y-1][x-1] == Types.OBSTACLE)
            && (knownMap[y+1][x] == Types.PASSED || knownMap[y-1][x] == Types.OBSTACLE)
            && (knownMap[y][x-1] == Types.PASSED || knownMap[y][x-1] == Types.OBSTACLE)
            && (knownMap[y][x+1] == Types.PASSED || knownMap[y][x] == Types.OBSTACLE))
            return true;
        return false;
    }

    private int steps = 0;
    void move() throws InterruptedException
    {
        turnLeft();
        paint();
        goForward();
        //paint();
        if(check()){
            turnRight();
            paint();
            goForward();
            //paint();
            if(check()){
                turnRight();
                paint();
                goForward();
                //paint();
                if(check()){
                    turnRight();
                    paint();
                    goForward();
                    //paint();
                }
            }
        }
        if(knownMap[y][x] == Types.PASSED){
            steps++;
        }
        else{
            steps = 0;
        }
        knownMap[y][x] = Types.PASSED;
        map[y][x] = Types.PASSED;
        for(int i = 0 ; i < ySize ; i++){
            System.out.println(Arrays.toString(knownMap[i]));
        }

    }
    void paint() throws InterruptedException
    {
        Thread.sleep(500);
        frame.repaint(x , y , face);
    }
    boolean check(){
        if(knownMap[y][x] == Types.BORDER){
            goBack();
            return true;
        }
        if(sensors[0] == 1){
            goBack();
            sensors[0] = 0;
            return true;
            //turn();
        }
        else if(sensors[1] == 1){
            suckUp();
            sensors[1] = 0;
        }
        else if(sensors[2] == 1){
            turnOff();
            sensors[2] = 0;
        }
        return false;
    }
    void check(int face){
        if(sensors[0] == 1){
            goBack();
            turn(face);
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
