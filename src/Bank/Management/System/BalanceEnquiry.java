package Bank.Management.System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BalanceEnquiry extends JFrame implements ActionListener{

    String PINno, cardno;

    String bal;
    JLabel labelCurr, labelBalance;
    JButton back;

    BalanceEnquiry(String PINno, String cardno){
        super("Bank Management System : Balance Enquiry");

        this.PINno = PINno;
        this.cardno = cardno;

    //BackGround Img
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1080,700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel atmImg = new JLabel(i3);
        atmImg.setBounds(0,0,1280,700);
        add(atmImg);

    //Current Balance    
        labelCurr = new JLabel("Your Current Balance is");
        labelCurr.setFont(new Font("Raleway", Font.BOLD,15));
        labelCurr.setForeground(Color.BLACK);
        labelCurr.setBounds(370,290,800,20);
        atmImg.add(labelCurr);

        labelBalance = new JLabel();
        labelBalance.setFont(new Font("Raleway", Font.BOLD,15));
        labelBalance.setForeground(Color.BLACK);
        labelBalance.setBounds(370,310,800,20);
        atmImg.add(labelBalance);

    //BACK Button
        back = new JButton("BACK");
        back.setFont(new Font("Raleway", Font.BOLD,10));
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(65,125,128));
        back.setBounds(655,409,100,20);
        back.addActionListener(this);
        atmImg.add(back);

    //Calculation of Current Balance
        try {
            Link con7 = new Link();
            PreparedStatement ps = con7.connection.prepareStatement("SELECT * FROM transaction WHERE PINno = ? AND cardno = ?");
            ps.setString(1, PINno);
            ps.setString(2, cardno);
            ResultSet result = ps.executeQuery();
        //BALANCE Calculation
            int balance = 0;
            while(result.next()){
                if (result.getString("type").equals("deposit")){
                    balance = balance + Integer.parseInt(result.getString("amount"));
                } else if(result.getString("type").equals("withdrawal")) {
                    balance -= Integer.parseInt(result.getString("amount")); 
                }
            }
            bal = Integer.toString(balance);
            labelBalance.setText("Rs. " + bal);

        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(null);
        setUndecorated(true);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource()==back){
                setVisible(false);
                new ATMHome(PINno, cardno);
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BalanceEnquiry("","");
    }
}
