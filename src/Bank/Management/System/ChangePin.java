package Bank.Management.System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;

public class ChangePin extends JFrame implements ActionListener{

    String PINno, cardno;

    JLabel labelPin1,labelPinO, labelPin2, labelPin3;
    JPasswordField passPinO, passPin2, passPin3;
    JToggleButton eyeO, eye2, eye3;
    JButton change, back;
    String pO, p2, p3;

    ChangePin(String PINno, String cardno){

        this.PINno = PINno;
        this.cardno = cardno;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1080,700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel atmImg = new JLabel(i3);
        atmImg.setBounds(0,0,1280,700);
        add(atmImg);

    //Change Pin text        
        labelPin1 = new JLabel("CHANGE YOUR PIN");
        labelPin1.setFont(new Font("Raleway", Font.BOLD,15));
        labelPin1.setForeground(Color.BLACK);
        labelPin1.setBounds(370,290,800,20);
        atmImg.add(labelPin1);

        labelPinO = new JLabel("Old PIN :");
        labelPinO.setFont(new Font("Raleway", Font.BOLD,13));
        labelPinO.setForeground(Color.BLACK);
        labelPinO.setBounds(370,330,800,20);
        atmImg.add(labelPinO);

        passPinO = new JPasswordField();
        passPinO.setFont(new Font("Raleway", Font.BOLD,14));
        passPinO.setForeground(Color.WHITE);
        passPinO.setBackground(new Color(65,125,128));
        passPinO.setBounds(500,331,100,18);
        atmImg.add(passPinO);

    //Old PIN Toggle Button
        eyeO = new JToggleButton("👁️");
        eyeO.setFont(new Font("Raleway", Font.BOLD,3));
        eyeO.setBounds(600,331,18,18);
        eyeO.addActionListener(this);
        atmImg.add(eyeO);

        labelPin2 = new JLabel("New PIN :");
        labelPin2.setFont(new Font("Raleway", Font.BOLD,13));
        labelPin2.setForeground(Color.BLACK);
        labelPin2.setBounds(370,350,800,20);
        atmImg.add(labelPin2);

        passPin2 = new JPasswordField();
        passPin2.setFont(new Font("Raleway", Font.BOLD,14));
        passPin2.setForeground(Color.WHITE);
        passPin2.setBackground(new Color(65,125,128));
        passPin2.setBounds(500,351,100,18);
        atmImg.add(passPin2);

    //New PIN Toggle Button
        eye2 = new JToggleButton("👁️");
        eye2.setFont(new Font("Raleway", Font.BOLD,3));
        eye2.setBounds(600,351,18,18);
        eye2.addActionListener(this);
        atmImg.add(eye2);

        labelPin3 = new JLabel("Re-Enter New PIN :");
        labelPin3.setFont(new Font("Raleway", Font.BOLD,13));
        labelPin3.setForeground(Color.BLACK);
        labelPin3.setBounds(370,370,800,20);
        atmImg.add(labelPin3);

        passPin3 = new JPasswordField();
        passPin3.setFont(new Font("Raleway", Font.BOLD,14));
        passPin3.setForeground(Color.WHITE);
        passPin3.setBackground(new Color(65,125,128));
        passPin3.setBounds(500,371,100,18);
        atmImg.add(passPin3);

    //Re-New PIN Toggle Button
        eye3 = new JToggleButton("👁️");
        eye3.setFont(new Font("Raleway", Font.BOLD,3));
        eye3.setBounds(600,371,18,18);
        eye3.addActionListener(this);
        atmImg.add(eye3);

    //CHANGE Button
        change = new JButton("CHANGE PIN");
        change.setFont(new Font("Raleway", Font.BOLD,10));
        change.setForeground(Color.WHITE);
        change.setBackground(new Color(65,125,128));
        change.setBounds(655,384,100,20);
        change.addActionListener(this);
        atmImg.add(change);

    //BACK Button
        back = new JButton("BACK");
        back.setFont(new Font("Raleway", Font.BOLD,10));
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(65,125,128));
        back.setBounds(655,409,100,20);
        back.addActionListener(this);
        atmImg.add(back);

        setLayout(null);
        setUndecorated(true);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(eyeO.isSelected()){
                passPinO.setEchoChar((char) 0);
            }else {
                passPinO.setEchoChar('x');
            }
            if (eye2.isSelected()){
                passPin2.setEchoChar((char) 0);
            }else {
                passPin2.setEchoChar('x');
            }
            if(eye3.isSelected()){
                passPin3.setEchoChar((char) 0);
            }else {
                passPin3.setEchoChar('x');
            }
            if(e.getSource()==change){
                char[] pin2 = passPin2.getPassword();
                p2 = new String(pin2);
                char[] pin3 = passPin3.getPassword();
                p3 = new String(pin3);
                char[] pinO = passPinO.getPassword();
                pO = new String(pinO);
                if (pO=="" || !pO.matches("[0-9]{4}")) {
                    JOptionPane.showMessageDialog(null,"Please Enter Your Old PIN");
                    passPinO.setText("");
                    passPin2.setText("");
                    passPin3.setText("");
                }else if (!pO.equals(PINno)){
                    JOptionPane.showMessageDialog(null,"Incorrect Old PIN");
                    passPinO.setText("");
                    passPin2.setText("");
                    passPin3.setText("");
                }else if(pO.equals(PINno)){
                    if(p2.equals("") || p3.equals("")){
                        JOptionPane.showMessageDialog(null,"Please Enter Your New PIN");
                        passPin2.setText("");
                        passPin3.setText("");
                    }else if(!p2.matches("[0-9]{4}") || !p3.matches("[0-9]{4}")){
                        JOptionPane.showMessageDialog(null,"Please Enter the Required PIN");
                        passPin2.setText("");
                        passPin3.setText("");
                    }else if (!p2.equals(p3)){
                        JOptionPane.showMessageDialog(null,"Entered PIN does not Match");
                        passPin2.setText("");
                        passPin3.setText("");
                    }else {
                        Link con9 = new Link();
                        PreparedStatement ps1 = con9.connection.prepareStatement("UPDATE login SET PINno = ? WHERE PINno = ? AND cardno = ?");
                        PreparedStatement ps2 = con9.connection.prepareStatement("UPDATE signup3 SET PINno = ? WHERE PINno = ? AND cardno = ?");
                        PreparedStatement ps3 = con9.connection.prepareStatement("UPDATE transaction SET PINno = ? WHERE PINno = ? AND cardno = ?");
                        ps1.setString(1, p3);
                        ps1.setString(2, PINno);
                        ps1.setString(3, cardno);
                        ps2.setString(1, p3);
                        ps2.setString(2, PINno);
                        ps2.setString(3, cardno);
                        ps3.setString(1, p3);
                        ps3.setString(2, PINno);
                        ps3.setString(3, cardno);
                        ps1.executeUpdate();
                        ps2.executeUpdate();
                        ps3.executeUpdate();
                        JOptionPane.showMessageDialog(null,"PIN Changed Successfully.\nPlease Login Again.");
                        setVisible(false);
                        new Login(PINno, cardno);
                    }
                }
            } else if (e.getSource()==back){
                setVisible(false);
                new ATMHome(PINno, cardno);
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChangePin("","");
    }
}
