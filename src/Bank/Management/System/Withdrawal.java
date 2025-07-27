package Bank.Management.System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
//import java.sql.Date;
import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Withdrawal extends JFrame implements ActionListener{

    String PINno, cardno;

    JLabel labelWithM, labelWithA;
    JTextField textWithAmount;
    JButton withdrawal, back;

    Withdrawal(String PINno, String cardno){
        super("Bank Management System : Withdrawal");

        this.PINno = PINno;
        this.cardno = cardno;

    //BackGround Img
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1080,700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel atmImg = new JLabel(i3);
        atmImg.setBounds(0,0,1280,700);
        add(atmImg);

    //Withdraw Amount 
        labelWithM = new JLabel("MAXIMUM WITHDRAWL IS RS. 10,000");
        labelWithM.setFont(new Font("Raleway", Font.BOLD,15));
        labelWithM.setForeground(Color.BLACK);
        labelWithM.setBounds(370,290,800,20);
        atmImg.add(labelWithM);

        labelWithA = new JLabel("PLEASE ENTER YOUR AMOUNT");
        labelWithA.setFont(new Font("Raleway", Font.BOLD,15));
        labelWithA.setForeground(Color.BLACK);
        labelWithA.setBounds(370,310,800,20);
        atmImg.add(labelWithA);

        textWithAmount = new JTextField();
        textWithAmount.setFont(new Font("Raleway", Font.BOLD,14));
        textWithAmount.setForeground(Color.WHITE);
        textWithAmount.setBackground(new Color(65,125,128));
        textWithAmount.setBounds(370,340,200,20);
        atmImg.add(textWithAmount);

        withdrawal = new JButton("WITHDRAW");
        withdrawal.setFont(new Font("Raleway", Font.BOLD,10));
        withdrawal.setForeground(Color.WHITE);
        withdrawal.setBackground(new Color(65,125,128));
        withdrawal.setBounds(655,384,100,20);
        withdrawal.addActionListener(this);
        atmImg.add(withdrawal);

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
            String amount1 = textWithAmount.getText();
            Date date = new Date();
            SimpleDateFormat D = new SimpleDateFormat("dd-MM-yyyy" + " " + "hh:mm:ss");
            String d = D.format(date);
            if(e.getSource()==withdrawal){
                if(amount1==""){
                    JOptionPane.showMessageDialog(null,"Please enter the required amount");
                    return;
                }else if (!amount1.matches("[0-9]{3,5}")){
                    JOptionPane.showMessageDialog(null,"Please enter the required amount\nMinimun Amount = Rs. 100\nDAILY LIMIT = Rs. 10000");
                    textWithAmount.setText("");
                    return;
                }else {
                    int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to withdraw Rs. " + amount1);
                    if (option==JOptionPane.OK_OPTION) {
                        Link con6 = new Link(); 
                        PreparedStatement ps1 = con6.connection.prepareStatement("SELECT * FROM transaction WHERE PINno = ? AND cardno = ?");
                        ps1.setString(1, PINno);
                        ps1.setString(2, cardno);
                        ResultSet result1 = ps1.executeQuery();
                        
                    //LIMIT and BALANCE Calculation
                        int balance = 0;
                        int limit = 0;
                        while(result1.next()){
                            if (result1.getString("type").equals("deposit")){
                                balance = balance + Integer.parseInt(result1.getString("amount"));
                            } else if(result1.getString("type").equals("withdrawal")) {
                                balance -= Integer.parseInt(result1.getString("amount")); 
                            }
                        }
                        if(balance < Integer.parseInt(amount1)){
                            JOptionPane.showMessageDialog(null,"Insufficient Balance");
                            textWithAmount.setText("");
                            return;
                        }else{
                            PreparedStatement ps2 = con6.connection.prepareStatement("SELECT * FROM transaction WHERE PINno = ? AND cardno = ? AND type = 'withdrawal' AND date LIKE ?");
                            ps2.setString(1, PINno);
                            ps2.setString(2, cardno);
                            ps2.setString(3, d);
                            ResultSet result2 = ps2.executeQuery();
                            while(result2.next()){
                                limit = limit + Integer.parseInt(result2.getString("amount"));
                            }
                            if (limit >= 10000){
                                JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                                textWithAmount.setText("");
                                return;
                            }else if (limit < 10000){
                                int limit2 = limit + Integer.parseInt(amount1);
                                if(limit2 >= 10000){
                                    JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                                    textWithAmount.setText("");
                                    return;
                                }else {
                                    balance = balance - Integer.parseInt(amount1);
                                    Random ran = new Random();
                                    Long id = (ran.nextLong(900000000)) + 1000000000L;
                                    String transactionID = String.valueOf(id);
                                    PreparedStatement ps3 = con6.connection.prepareStatement("INSERT INTO transaction VALUES ('"+PINno+"','"+d+"','"+amount1+"','withdrawal','"+cardno+"',NULL,'"+transactionID+"')");
                                    ps3.executeUpdate();
                                    JOptionPane.showMessageDialog(null,"Rs. " + amount1 + " Debited Successfully.\nTransaction ID : " + transactionID + "\nTime : " + d);
                                    setVisible(false);
                                    new ATMHome(PINno, cardno);
                                }
                            }
                        } 
                    }else {
                        return;
                    }
                }
            }else if(e.getSource()==back){
                setVisible(false);
                new ATMHome(PINno, cardno);
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Withdrawal("","");
    }
}
