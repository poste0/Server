import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public native boolean square(String s1 , String s2 , int l1 , int l2);
    public static void main(String[] args) {

        System.loadLibrary("Main");
        System.out.println(new Main().square("asd" , "asdq" , 3 , 4));

        JFrame frame = new JFrame();
        JLabel l = new JLabel();
        JButton button = new JButton();
        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        t1.setPreferredSize(new Dimension(200 , 200));
        t2.setPreferredSize(new Dimension(200 , 200));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                l.setText(String.valueOf(new Main().square(t1.getText() , t2.getText() , t1.getText().length() , t2.getText().length())));
            }
        });
        JPanel panel = new JPanel();
        panel.add(button);
        panel.add(t1);
        panel.add(t2);
        panel.add(l);
        frame.setContentPane(panel);
        frame.setSize(new Dimension(500 , 500));
        frame.setVisible(true);
        frame.repaint();
    }
}
