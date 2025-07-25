package Bank.Management.System;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Mini extends JFrame implements ActionListener{

    String PINno, cardno;

    JLabel labelName, labelCardInfo, labelBalance;
    JTextArea area;
    JButton exit;
    int balance;
    String bal;

    Mini(String PINno, String cardno){
        
        this.PINno = PINno;
        this.cardno = cardno;

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Raleway", Font.BOLD, 15));
        area.setForeground(Color.BLACK);
        area.setBackground(new Color(255,204,204));
        area.setBounds(20,120,450,240);
        add(area);

        labelName = new JLabel("Kakuzu");
        labelName.setFont(new Font("Raleway", Font.BOLD, 15));
        labelName.setForeground(Color.BLACK);
        labelName.setBounds(150,20,200,20);
        add(labelName);

        labelCardInfo = new JLabel();
        labelCardInfo.setFont(new Font("Raleway", Font.BOLD, 15));
        labelCardInfo.setForeground(Color.BLACK);
        labelCardInfo.setBounds(20,80,300,20);
        add(labelCardInfo);

        labelBalance = new JLabel();
        labelBalance.setFont(new Font("Raleway", Font.BOLD, 15));
        labelBalance.setForeground(Color.BLACK);
        labelBalance.setBounds(20,400,300,20);
        add(labelBalance);

        try {
            Link con10 = new Link();
            String q1 = "SELECT * FROM login WHERE PINno = '"+PINno+"'AND cardno = '"+cardno+"'";
            ResultSet result1 = con10.statement.executeQuery(q1);
            while(result1.next()){
                labelCardInfo.setText(("Card Number : " + result1.getString("cardno").substring(0,4)+"XXXXXXXX"+result1.getString("cardno").substring(12,16)));
            }
            String q2 = "SELECT * FROM transaction WHERE PINno = '"+PINno+"' AND cardno = '"+cardno+"' ORDER BY id DESC LIMIT 6";
            ResultSet result2 = con10.statement.executeQuery(q2);
            while(result2.next()){
                area.append(result2.getString("date") + "\t" + result2.getString("type") + "\t" + result2.getString("amount") + "\n\n");;
            }
            String q3 = "SELECT * FROM transaction WHERE PINno = '"+PINno+"' AND cardno = '"+cardno+"'";
            ResultSet result3 = con10.statement.executeQuery(q3);
            balance = 0;
            while(result3.next()){
                if (result3.getString("type").equals("deposit")){
                    balance = balance + Integer.parseInt(result3.getString("amount"));
                } else if(result3.getString("type").equals("withdrawal")) {
                    balance -= Integer.parseInt(result3.getString("amount")); 
                }
                bal = Integer.toString(balance);
            }
            labelBalance.setText("Your Balance is Rs. "+bal);

        } catch (Exception e) {
            e.printStackTrace();
        }

        exit = new JButton("EXIT");
        exit.setFont(new Font("Raleway", Font.BOLD,10));
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.BLACK);
        exit.setBounds(220,520,129,20);
        exit.addActionListener(this);
        add(exit);

        setLayout(null);
        getContentPane().setBackground(new Color(255,204,204));
        setUndecorated(true);
        setSize(420,630);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new Mini("","");
    }
}
