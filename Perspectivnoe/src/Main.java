import java.awt.*;

/**
 * Created by Виктор on 11.10.2018.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Map map = new Map(10 , 10);
        Windows frame = new Windows(map);
        //frame.repaint();
        while(true){
            map.work();
            frame.getContentPane().validate();
            frame.getContentPane().repaint();
            frame.repaint();
            Thread.sleep(2000);
            System.out.println(map.getVacuumcleaner().getX() + " " + map.getVacuumcleaner().getY() + " " + map.asString(map.getVacuumcleaner().getY() , map.getVacuumcleaner().getX()));

        }
    }
}
