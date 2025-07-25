package Bank.Management.System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

//Login Class
public class Login extends JFrame implements ActionListener{

    String PINno, cardno;
    
    JLabel label1, label2, label3;
    JTextField text2;
    JPasswordField pass3;
    JButton singIN, signUP, exit;
    JToggleButton eye;

//LOGIN constructor, this constructor will have same name as our class name that is 'Login'
    Login(String PINno, String cardno){
        super("Bank Management System : Login/Sign Up Page");

    //BANK Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icon\\bank.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel bankImg = new JLabel(i3);
        bankImg.setBounds(350,10,100,100);
        add(bankImg);

    //WELCOME text
        label1 = new JLabel("WELCOME");
        label1.setFont(new Font("AvantGarde", Font.BOLD,38));
        label1.setForeground(Color.WHITE);
        label1.setBounds(305,125,450,40);
        add(label1);

    //CARD No. Text
        label2 = new JLabel("Card No.:");
        label2.setFont(new Font("Ralway", Font.BOLD, 28));
        label2.setForeground(Color.WHITE);
        label2.setBounds(150,190,375,30);
        add(label2);

    //Card no. textfield
        text2 = new JTextField(15);
        text2.setFont(new Font("Arial", Font.BOLD,14));
        text2.setBounds(325,190,230,30);
        add(text2);

    //PIN text
        label3 = new JLabel("PIN:");
        label3.setFont(new Font("Ralway", Font.BOLD, 28));
        label3.setForeground(Color.WHITE);
        label3.setBounds(150,250,375,30);
        add(label3);

    //PIN passwordfield
        pass3 = new JPasswordField(15);
        pass3.setFont(new Font("Arial",Font.BOLD,14));
        pass3.setBounds(325,250,180,30);
        add(pass3);

    //PIN eye button (JToggleButton)
        eye = new JToggleButton("👁");
        eye.setForeground(Color.WHITE);
        eye.setBackground(Color.BLACK);
        eye.setBounds(505,250,50,30);
        eye.addActionListener(this);
        add(eye);

    //SIGN IN button
        singIN = new JButton("SIGN IN");
        singIN.setFont(new Font("Arial", Font.BOLD, 14));
        singIN.setForeground(Color.WHITE);
        singIN.setBackground(Color.BLACK);
        singIN.setBounds(325,310,100,30);
        singIN.addActionListener(this);
        add(singIN);

    //SIGN UP button
        signUP = new JButton("SIGN UP");
        signUP.setFont(new Font("Arial", Font.BOLD, 14));
        signUP.setForeground(Color.WHITE);
        signUP.setBackground(Color.BLACK);
        signUP.setBounds(455,310,100,30);
        signUP.addActionListener(this);
        add(signUP);

    //EXIT button
        exit = new JButton("EXIT");
        exit.setFont(new Font("Arial", Font.BOLD, 14));
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.BLACK);
        exit.setBounds(325,360,230,30);
        exit.addActionListener(this);
        add(exit);

    //Background Image
        ImageIcon iii1 = new ImageIcon(ClassLoader.getSystemResource("Icon\\bg.jpeg"));
        Image iii2 = iii1.getImage().getScaledInstance(850,480, Image.SCALE_DEFAULT);
        ImageIcon iii3 = new ImageIcon(iii2);
        JLabel bgImg = new JLabel(iii3);
        bgImg.setBounds(0,0,850,480);
        add(bgImg);

        this.PINno = PINno;
        this.cardno = cardno;

    //Window popup using JFrame
        setLayout(null);
        setUndecorated(true);
        setSize(850,460);
        setLocation(275,150);
        setVisible(true);
    }

//Action performed by buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(eye.isSelected()){
                pass3.setEchoChar((char) 0);
            }else {
                pass3.setEchoChar('x');
            }
            if (e.getSource() == singIN){
                Link con5 = new Link();
                String cardno = text2.getText();
                char[] pin = pass3.getPassword();
                PINno = new String(pin);

                if(!cardno.matches("\\d{16}")){
                    JOptionPane.showMessageDialog(null,"Please enter your 16-digit Card Number");
                    text2.setText("");
                    pass3.setText("");
                }else if(!PINno.matches("\\d{4}")){
                    JOptionPane.showMessageDialog(null,"Please enter your 4-digit PIN");
                    pass3.setText("");
                }else{
                    PreparedStatement ps = con5.connection.prepareStatement("SELECT * FROM login WHERE cardno = ? AND PINno = ?");
                    ps.setString(1, cardno);
                    ps.setString(2, PINno);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        new ATMHome(PINno, cardno);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null,"Incorrect Card Number or PIN");
                        text2.setText("");
                        pass3.setText("");
                    }
                }

            } else if (e.getSource() == signUP) {
                new SignUp1();
                setVisible(false);
            } else if (e.getSource()==exit){
                System.exit(0);
            }

        } catch(Exception E) {
            E.printStackTrace();
        }   
    }

    public static void main(String[] args) {
        new Login("","");
    }
}
