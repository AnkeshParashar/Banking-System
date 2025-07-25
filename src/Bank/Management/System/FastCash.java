package Bank.Management.System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FastCash extends JFrame implements ActionListener{

    String PINno, cardno;

    JLabel labelTran;
    JButton rs100, rs200, rs500, rs1000, rs5000, rs10000, back; 

    FastCash(String PINno, String cardno){
        super("Bank Management System : Fast Cash");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1080,700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel atmImg = new JLabel(i3);
        atmImg.setBounds(0,0,1280,700);
        add(atmImg);

        labelTran = new JLabel("Please Select your Withdrawal Amount");
        labelTran.setFont(new Font("Raleway", Font.BOLD,18));
        labelTran.setForeground(Color.BLACK);
        labelTran.setBounds(390,280,800,25);
        atmImg.add(labelTran);

        rs100 = new JButton("Rs. 100");
        rs100.setFont(new Font("Raleway", Font.BOLD,10));
        rs100.setForeground(Color.WHITE);
        rs100.setBackground(new Color(65,125,128));
        rs100.setBounds(355,346,129,18);
        rs100.addActionListener(this);
        atmImg.add(rs100);
        
        rs200 = new JButton("Rs. 200");
        rs200.setFont(new Font("Raleway", Font.BOLD,10));
        rs200.setForeground(Color.WHITE);
        rs200.setBackground(new Color(65,125,128));
        rs200.setBounds(626,346,129,18);
        rs200.addActionListener(this);
        atmImg.add(rs200);

        rs500 = new JButton("Rs. 500");
        rs500.setFont(new Font("Raleway", Font.BOLD,10));
        rs500.setForeground(Color.WHITE);
        rs500.setBackground(new Color(65,125,128));
        rs500.setBounds(355,368,129,18);
        rs500.addActionListener(this);
        atmImg.add(rs500);

        rs1000 = new JButton("Rs. 1000");
        rs1000.setFont(new Font("Raleway", Font.BOLD,10));
        rs1000.setForeground(Color.WHITE);
        rs1000.setBackground(new Color(65,125,128));
        rs1000.setBounds(626,368,129,18);
        rs1000.addActionListener(this);
        atmImg.add(rs1000);

        rs5000 = new JButton("Rs. 5000");
        rs5000.setFont(new Font("Raleway", Font.BOLD,10));
        rs5000.setForeground(Color.WHITE);
        rs5000.setBackground(new Color(65,125,128));
        rs5000.setBounds(355,390,129,18);
        rs5000.addActionListener(this);
        atmImg.add(rs5000);

        rs10000 = new JButton("Rs. 10000");
        rs10000.setFont(new Font("Raleway", Font.BOLD,10));
        rs10000.setForeground(Color.WHITE);
        rs10000.setBackground(new Color(65,125,128));
        rs10000.setBounds(626,390,129,18);
        rs10000.addActionListener(this);
        atmImg.add(rs10000);

        back = new JButton("BACK");
        back.setFont(new Font("Raleway", Font.BOLD,10));
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(65,125,128));
        back.setBounds(626,412,129,18);
        back.addActionListener(this);
        atmImg.add(back);

        this.PINno = PINno;
        this.cardno = cardno;
        
        setLayout(null);
        setUndecorated(true);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Link con8 = new Link();
            Date date = new Date();
            SimpleDateFormat D = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String d = D.format(date);
            String q = "SELECT * FROM transaction WHERE PINno = '"+PINno+"' AND cardno = '"+cardno+"'";
            ResultSet result1 = con8.statement.executeQuery(q);
            int balance = 0;
            int limit = 0;
            while (result1.next()) {
                if(result1.getString("type").equals("deposit")){
                    balance = balance + Integer.parseInt(result1.getString("amount"));
                }else if (result1.getString("type").equals("withdrawal")){
                    balance = balance - Integer.parseInt(result1.getString("amount"));
                }
            }
            PreparedStatement ps2 = con8.connection.prepareStatement("SELECT * FROM transaction WHERE PINno = ? AND cardno = ? AND type = 'withdrawal' AND date LIKE ?");
            String dOnly = d.substring(0,10);
            ps2.setString(1, PINno);
            ps2.setString(2, cardno);
            ps2.setString(3, dOnly + "%");
            ResultSet result2 = ps2.executeQuery();
            while(result2.next()){
                limit = limit + Integer.parseInt(result2.getString("amount"));
            }
            if(e.getSource()==rs100){
                if(balance < 100){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }else {
                    int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to withdraw Rs.100");
                    if(option==JOptionPane.OK_OPTION){
                        if (limit >= 10000){
                            JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                            return;
                        }else if (limit<10000){
                            int limit2 = limit + 100;
                            if(limit2 >= 10000){
                                JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                                return;
                            }else {
                                balance = balance - 100;
                                Random ran = new Random();
                                Long id = (ran.nextLong(900000000)) + 1000000000L;
                                String transactionID = String.valueOf(id);
                                String Q = "INSERT INTO transaction VALUES ('"+PINno+"','"+d+"','100','withdrawal','"+cardno+"',NULL,'"+transactionID+"')";
                                con8.statement.executeUpdate(Q);
                                JOptionPane.showMessageDialog(null,"Rs. 100 debited Successfully.\nTransaction ID : " + transactionID + "\nTime : " + d);
                                setVisible(false);
                                new ATMHome(PINno, cardno);
                            }
                        }
                    }else {
                        return;
                    }
                }
            }else if(e.getSource()==rs200){
                if(balance < 200){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }else {
                    int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to withdraw Rs. 200");
                    if(option==JOptionPane.OK_OPTION){
                        if(limit >= 10000){
                            JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                            return;
                        }else if (limit < 10000){
                            int limit2 = limit + 200;
                            if(limit2 >= 10000){
                                JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                                return;
                            }else{
                                balance = balance - 200;
                                Random ran = new Random();
                                Long id = (ran.nextLong(900000000)) + 1000000000L;
                                String transactionID = String.valueOf(id);
                                String Q = "INSERT INTO transaction VALUES ('"+PINno+"','"+d+"','200','withdrawal','"+cardno+"',NULL,'"+transactionID+"')";
                                con8.statement.executeUpdate(Q);
                                JOptionPane.showMessageDialog(null,"Rs. 200 debited Successfully.\nTransaction ID : " + transactionID + "\nTime : " + d);
                                setVisible(false);
                                new ATMHome(PINno, cardno);
                            }
                        }
                    }else {
                        return;
                    }
                }
            }else if(e.getSource()==rs500){
                if(balance < 500){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }else {
                    int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to withdraw Rs. 500");
                    if(option==JOptionPane.OK_OPTION){
                        if(limit >= 10000){
                            JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                            return;
                        }else if (limit < 10000){
                            int limit2 = limit + 500;
                            if(limit2 >= 10000){
                                JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                                return;
                            }else{
                                balance = balance - 500;
                                Random ran = new Random();
                                Long id = (ran.nextLong(900000000)) + 1000000000L;
                                String transactionID = String.valueOf(id);
                                String Q = "INSERT INTO transaction VALUES ('"+PINno+"','"+d+"','500','withdrawal','"+cardno+"',NULL,'"+transactionID+"')";
                                con8.statement.executeUpdate(Q);
                                JOptionPane.showMessageDialog(null,"Rs. 500 debited Successfully.\nTransaction ID : " + transactionID + "\nTime : " + d);
                                setVisible(false);
                                new ATMHome(PINno, cardno);
                            }
                        }
                    }else {
                        return;
                    }
                }
            }else if(e.getSource()==rs1000){
                if(balance < 1000){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }else {
                    int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to withdraw Rs. 1000");
                    if(option==JOptionPane.OK_OPTION){
                        if(limit >= 10000){
                            JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                            return;
                        }else if (limit < 10000){
                            int limit2 = limit + 1000;
                            if(limit2 >= 10000){
                                JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                                return;
                            }else{
                                balance = balance - 1000;
                                Random ran = new Random();
                                Long id = (ran.nextLong(900000000)) + 1000000000L;
                                String transactionID = String.valueOf(id);
                                String Q = "INSERT INTO transaction VALUES ('"+PINno+"','"+d+"','1000','withdrawal','"+cardno+"',NULL,'"+transactionID+"')";
                                con8.statement.executeUpdate(Q);
                                JOptionPane.showMessageDialog(null,"Rs. 1000 debited Successfully.\nTransaction ID : " + transactionID + "\nTime : " + d);
                                setVisible(false);
                                new ATMHome(PINno, cardno);
                            }
                        }
                    }else {
                        return;
                    }
                }
            }else if(e.getSource()==rs5000){
                if(balance < 5000){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }else {
                    int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to withdraw Rs. 5000");
                    if(option==JOptionPane.OK_OPTION){
                        if(limit >= 10000){
                            JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                            return;
                        }else if (limit < 10000){
                            int limit2 = limit + 5000;
                            if(limit2 >= 10000){
                                JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                                return;
                            }else{
                                balance = balance - 5000;
                                Random ran = new Random();
                                Long id = (ran.nextLong(900000000)) + 1000000000L;
                                String transactionID = String.valueOf(id);
                                String Q = "INSERT INTO transaction VALUES ('"+PINno+"','"+d+"','5000','withdrawal','"+cardno+"',NULL,'"+transactionID+"')";
                                con8.statement.executeUpdate(Q);
                                JOptionPane.showMessageDialog(null,"Rs. 5000 debited Successfully.\nTransaction ID : " + transactionID + "\nTime : " + d);
                                setVisible(false);
                                new ATMHome(PINno, cardno);
                            }
                        }
                    }else {
                        return;
                    }
                }
            }else if(e.getSource()==rs10000){
                if(balance < 10000){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }else {
                    int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to withdraw Rs. 10000");
                    if(option==JOptionPane.OK_OPTION){
                        if(limit >= 10000){
                            JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                            return;
                        }else if (limit < 10000){
                            int limit2 = limit + 10000;
                            if(limit2 >= 10000){
                                JOptionPane.showMessageDialog(null,"Daily limit exceeded.\nLIMIT = Rs. 10000");
                                return;
                            }else{
                                balance = balance - 10000;
                                Random ran = new Random();
                                Long id = (ran.nextLong(900000000)) + 1000000000L;
                                String transactionID = String.valueOf(id);
                                String Q = "INSERT INTO transaction VALUES ('"+PINno+"','"+d+"','10000','withdrawal','"+cardno+"',NULL,'"+transactionID+"')";
                                con8.statement.executeUpdate(Q);
                                JOptionPane.showMessageDialog(null,"Rs. 10000 debited Successfully.\nTransaction ID : " + transactionID + "\nTime : " + d);
                                setVisible(false);
                                new ATMHome(PINno, cardno);
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
        new FastCash("","");
    }
}
