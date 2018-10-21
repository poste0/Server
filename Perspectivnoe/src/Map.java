import java.util.Random;

/**
 * Created by Виктор on 11.10.2018.
 */
public class Map {
    private Type[][] field;
    private Vacuumcleaner vacuumcleaner;
    private int n;
    private int m;
    public Map(int n , int m ){
        vacuumcleaner = new Vacuumcleaner(10 , n , m);
        field = new Type[n + 2][m + 2];
        this.n = n + 1;
        this.m = m + 1;
        field[1][1] = Type.POWER;
        for(int i = 0 ; i < n + 1 ; i++){
            for(int j = 0 ; j < m + 1 ; j++){
                if(i == 1 && j == 1){
                    continue;
                }
                if(i == n + 1 || j == m + 1 || i == 0 || j == 0 || i == n + 2 || j == m + 2){
                    field[i][j] = Type.OBSTACLE;
                    continue;
                }
                field[i][j] = randomType();
            }
        }

    }

    private enum Type{
        OBSTACLE , CLEAN , DIRTY , POWER , CLEANED;
    }

    public void work(){
        vacuumcleaner.goForward();
            if (field[vacuumcleaner.getY()][vacuumcleaner.getX()] == Type.DIRTY) {
                vacuumcleaner.getSensors()[1] = 1;
                field[vacuumcleaner.getY()][vacuumcleaner.getX()] = Type.CLEANED;
            } else vacuumcleaner.getSensors()[1] = 0;
            if (field[vacuumcleaner.getY()][vacuumcleaner.getX()] == Type.OBSTACLE) {
                vacuumcleaner.getSensors()[0] = 1;
            }
            else vacuumcleaner.getSensors()[0] = 0;
            if (field[vacuumcleaner.getY()][vacuumcleaner.getX()] == Type.POWER) {
                vacuumcleaner.getSensors()[2] = 1;
            }
            else vacuumcleaner.getSensors()[2] = 0;
            vacuumcleaner.turn();


    }

    private Type randomType(){
        Random random = new Random();
        int value = random.nextInt() % 76;
        if(value <= 25){
            return Type.CLEAN;
        }
        else if(value > 25 && value <= 50){
            return Type.DIRTY;
        }
        else if(value > 50 && value < 75){
            return Type.OBSTACLE;
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                if(i == vacuumcleaner.getX() && j == vacuumcleaner.getY()){
                    builder.append(vacuumcleaner.getSign() + " ");
                    continue;
                }
                if (field[i][j] == Type.CLEAN) {
                    builder.append(" ");

                } else if (field[i][j] == Type.DIRTY) {
                    builder.append("* ");
                } else if (field[i][j] == Type.OBSTACLE) {
                    builder.append("O ");
                } else if (field[i][j] == Type.POWER) {
                    builder.append("P ");
                }
                else if(field[i][j] == Type.CLEANED){
                    builder.append("X ");
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    public int getN(){return n;}
    public int getM(){return m;}
    public Type[][] getField(){return field;}
    public Vacuumcleaner getVacuumcleaner(){return vacuumcleaner;}
    public String asString(int i , int j){
        if(i == vacuumcleaner.getY() && j == vacuumcleaner.getX()){
           return vacuumcleaner.getSign() + " ";
        }
        if(field[i][j] == Type.CLEAN){
            return " ";
        }
        else if(field[i][j] == Type.DIRTY){
            return "* ";
        }
        else if(field[i][j] == Type.OBSTACLE){
            return "O ";
        }
        else if(field[i][j] == Type.POWER){
            return "P ";
        }
        else if(field[i][j] == Type.CLEANED){
            return "X ";
        }
        return "O";
    }



}
