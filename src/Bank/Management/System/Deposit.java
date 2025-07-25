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
import javax.swing.JTextField;

public class Deposit extends JFrame implements ActionListener{

    String PINno, cardno;

    JLabel labelDep;
    JTextField textDepAmount;
    JButton deposit, back;

    Deposit(String PINno, String cardno){
        super("Bank Management System : Deposit");

    //BackGround Img
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1080,700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel atmImg = new JLabel(i3);
        atmImg.setBounds(0,0,1280,700);
        add(atmImg);

    //Deposit Amount   
        labelDep = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        labelDep.setFont(new Font("Raleway", Font.BOLD,15));
        labelDep.setForeground(Color.BLACK);
        labelDep.setBounds(400,280,800,20);
        atmImg.add(labelDep);

        textDepAmount = new JTextField();
        textDepAmount.setFont(new Font("Raleway", Font.BOLD,14));
        textDepAmount.setForeground(Color.WHITE);
        textDepAmount.setBackground(new Color(65,125,128));
        textDepAmount.setBounds(495,320,100,30);
        atmImg.add(textDepAmount);

        deposit = new JButton("DEPOSIT");
        deposit.setFont(new Font("Raleway", Font.BOLD,10));
        deposit.setForeground(Color.WHITE);
        deposit.setBackground(new Color(65,125,128));
        deposit.setBounds(655,384,100,20);
        deposit.addActionListener(this);
        atmImg.add(deposit);

    //BACK Button    
        back = new JButton("BACK");
        back.setFont(new Font("Raleway", Font.BOLD,10));
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(65,125,128));
        back.setBounds(655,409,100,20);
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
            String amount = textDepAmount.getText();

        //SimpleDateFormat
            Date date = new Date();
            SimpleDateFormat D = new SimpleDateFormat("dd-MM-yyyy" + " " + "hh:mm:ss");
            String d = D.format(date);

            int limit = 0;
            if(e.getSource()==deposit){
                if(amount == ""){
                    JOptionPane.showMessageDialog(null,"Please enter the required amount");
                    return;
                }else if (!amount.matches("[0-9]{3,6}")){
                    JOptionPane.showMessageDialog(null,"Please enter the required amount.\nMinimum Amount = Rs. 100\nDAILY LIMIT = Rs. 200000");
                    textDepAmount.setText("");
                    return;
                }else {
                    Link con4 = new Link();
                //Limit Calculation
                    PreparedStatement ps = con4.connection.prepareStatement("SELECT * FROM transaction WHERE PINno = ? AND cardno = ? AND type = 'deposit' AND date LIKE ?");
                    ps.setString(1, PINno);
                    ps.setString(2, cardno);
                    ps.setString(3, d);
                    ResultSet result = ps.executeQuery();
                    while(result.next()){
                        limit = limit + Integer.parseInt(result.getString("amount"));
                    }
                    int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to deposit Rs. " + amount);
                    if (option==JOptionPane.OK_OPTION){
                        if (limit >= 200000){
                            JOptionPane.showMessageDialog(null,"Daily limit reached.\nLIMIT = Rs. 200000");
                            textDepAmount.setText("");
                            return;
                        }else if (limit < 200000){
                            int limit2 = limit + Integer.parseInt(amount);
                            if (limit2 >= 200000){
                                JOptionPane.showMessageDialog(null,"Daily limit reached.\nLIMIT = Rs. 200000");
                                textDepAmount.setText("");
                                return;
                            }else {
                                Random ran = new Random();
                                Long id = (ran.nextLong(900000000)) + 1000000000L;
                                String transactionID = String.valueOf(id);
                                PreparedStatement ps2 = con4.connection.prepareStatement("INSERT INTO transaction VALUES ('"+PINno+"','"+d+"','"+amount+"','deposit','"+cardno+"',NULL,'"+transactionID+"')");
                                ps2.executeUpdate();
                                // String q = "INSERT INTO transaction VALUES ('"+PINno+"','"+d+"','"+amount+"','deposit')";
                                // con4.statement.executeUpdate(q);
                                JOptionPane.showMessageDialog(null,"Rs. " + amount + " Deposited Successfully.\nTransaction ID : " + transactionID + "\nTime : " + d);
                                new ATMHome(PINno, cardno); 
                                setVisible(false);
                            }
                        }
                    }else {
                        return;
                    }
                }
            }else if(e.getSource()==back){
                new ATMHome(PINno, cardno);
                setVisible(false);
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Deposit("","");
    }
}
