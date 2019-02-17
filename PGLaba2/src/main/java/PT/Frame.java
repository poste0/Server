package PT;

import javafx.scene.input.KeyCode;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Locale;

public class Frame extends JFrame {
    private NumberOfDigits numberOfDigits;

    private Parser parser;

    private final String file = "out.xml";

    private JScrollPane scrollPane;

    private JButton deleteButton;

    private JButton addButton;

    private JTextField digitTF;

    private JLabel label;

    public Frame() throws JAXBException {
        super();
        this.parser = new MyParser();
        File f = new File(file);
        if(f.length() == 0){
            numberOfDigits = new NumberOfDigits();
        }
        else{
            numberOfDigits = new NumberOfDigits();
            numberOfDigits = (NumberOfDigits) parser.getObject(f , numberOfDigits.getClass());
        }

        deleteButton = new JButton("delete");
        addButton = new JButton("add");
        digitTF = new JTextField();
        if(numberOfDigits.size() == 0){
            deleteButton.setEnabled(false);
        }
        label = new JLabel();

        digitTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                digitTF.getKeyListeners()[0].keyPressed(new KeyEvent(digitTF , 1 , 1 , 1 , 1 , 's' , 1));
            }

            @Override
            public void focusLost(FocusEvent e) {
                digitTF.getKeyListeners()[0].keyPressed(new KeyEvent(digitTF , 1 , 1 , 1 , 1 , 's' , 1));
            }
        });
        digitTF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(digitTF.getText().length() == 0){
                    addButton.setEnabled(false);
                    return;
                }
                for(int i = 0 ; i < digitTF.getText().length() ; i++){
                    if(digitTF.getText().charAt(i) < 48 || digitTF.getText().charAt(i) > 57){
                        addButton.setEnabled(false);
                        return;
                    }
                }
                addButton.setEnabled(true);
                if(e.getKeyCode() == KeyEvent.VK_ENTER ){
                    addButton.doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if(digitTF.getText().length() == 0){
                    addButton.setEnabled(false);
                    return;
                }
                for(int i = 0 ; i < digitTF.getText().length() ; i++){
                    if(digitTF.getText().charAt(i) < 48 || digitTF.getText().charAt(i) > 57){
                        addButton.setEnabled(false);
                        return;
                    }
                }
                addButton.setEnabled(true);
                if(e.getKeyCode() == KeyEvent.VK_ENTER ){
                    addButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(digitTF.getText().length() == 0){
                    addButton.setEnabled(false);
                    return;
                }
                for(int i = 0 ; i < digitTF.getText().length() ; i++){
                    if(digitTF.getText().charAt(i) < 48 || digitTF.getText().charAt(i) > 57){
                        addButton.setEnabled(false);
                        return;
                    }
                }
                addButton.setEnabled(true);
                if(e.getKeyCode() == KeyEvent.VK_ENTER ){
                    addButton.doClick();
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    numberOfDigits.add(Integer.parseInt(digitTF.getText()));
                }
                catch (Exception x){

                }
                digitTF.setText("");
                deleteButton.setEnabled(true);
                try {
                    parser.saveObject(f , numberOfDigits);
                } catch (JAXBException e1) {
                    e1.printStackTrace();
                }
                prints();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object result = numberOfDigits.delete();
                if(numberOfDigits.size() == 0){
                    deleteButton.setEnabled(false);
                }
                try {
                    parser.saveObject(f , numberOfDigits);
                } catch (JAXBException e1) {
                    e1.printStackTrace();
                }
                label.setText(result.toString());
                prints();
            }
        });
        //scrollPane = new JScrollPane();
        //scrollPane.add(deleteButton);
        //scrollPane.add(addButton);
        //scrollPane.add(digitTF);


    }

    public void prints(){
        JPanel panel = new JPanel();
        panel.add(deleteButton);
        panel.add(addButton);
        panel.add(digitTF);

        digitTF.setPreferredSize(new Dimension(100 , 100));
        TextArea t = new TextArea();
        t.setEnabled(false);
        for(int i = 0 ; i < numberOfDigits.size() ; i++){
            t.append(numberOfDigits.get(i).toString() + "\n");
        }
        panel.add(t);
        panel.add(label);
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        setContentPane(panel);
        setSize(800 , 800);
        setVisible(true);

        //repaint();
    }


}
