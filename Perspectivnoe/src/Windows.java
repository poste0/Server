import javax.swing.*;
import java.awt.*;

public class Windows extends JFrame {
    private JPanel mainPanel;
    private Map map;
    public Windows(Map map){
        setContentPane(mainPanel);
        this.map = map;
        setSize(800 , 600);
        mainPanel.setLayout(new GridLayout(map.getN() + 1  ,map.getM()));
        Button button;
        for(int i = 0 ; i < map.getN() ; i++) {
            for (int j = 0; j < map.getM() ; j++) {
                button = new Button(map.asString(i, j));
                //button.setSize(10, 10);
                mainPanel.add(button);
            }
        }
        //mainPanel.setLayout(new GridLayout(map.getN() , map.getM()));
        setVisible(true);
        //pack();
    }

    @Override
    public void repaint() {
        Button button;
        for(int i = 0 ; i < map.getN() ; i++) {
            for (int j = 0; j < map.getM() ; j++) {
                button = (Button) mainPanel.getComponents()[i * map.getM() + j];
                button.setLabel(map.asString(i  , j));
            }
        }
    }
}
