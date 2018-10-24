import javax.swing.*;
import java.awt.*;

public class Windows extends JFrame {
    private JPanel mainPanel;
    private Object[][] map;
    private int xSize;
    private int ySize;
    public Windows(Object[][] map , int xSize , int ySize){
        this.map = map;
        this.xSize = xSize + 2;
        this.ySize = ySize + 2;
        setContentPane(mainPanel);
        setSize(800 , 600);
        mainPanel.setLayout(new GridLayout(this.ySize , this.xSize));
        Button button;
        for(int i = 0 ; i < this.ySize ; i++) {
            for (int j = 0; j < this.xSize ; j++) {
                button = new Button(asString(i , j , 1 , 1 , 1));
                //button.setSize(10, 10);
                mainPanel.add(button);
            }
        }

        //mainPanel.setLayout(new GridLayout(map.getN() , map.getM()));
        setVisible(true);
        //pack();
    }

    private String asString(int i, int j , int x , int y , int face) {
            if(i == y && j == x){
                switch (face) {
                    case 0:
                        return "^";
                    case 1:
                        return ">";
                    case 2:
                        return "V";
                    case 3:
                        return "<";
                }
            }
            if(map[i][j] == Types.CLEAN){
                return " ";
            }
            else if(map[i][j] == Types.DIRTY){
                return "* ";
            }
            else if(map[i][j] == Types.OBSTACLE){
                return "O ";
            }
            else if(map[i][j] == Types.HOME){
                return "P ";
            }
            else if(map[i][j] == Types.CLEANED){
                return "X ";
            }
            else if(map[i][j] == Types.PASSED){
                return "P ";
            }
            return "O";
    }


    public void repaint(int x , int y , int face , int point) {
        Button button;
        for(int i = 0 ; i < this.ySize ; i++) {
            for (int j = 0; j < this.xSize ; j++) {
                button = (Button) mainPanel.getComponents()[i * this.ySize + j];
                if ((i == 1 && j == 1)) {
                    button.setLabel(String.valueOf(point));
                } else {
                    button.setLabel(asString(i, j, x, y, face));
                }
            }
        }
    }
}
