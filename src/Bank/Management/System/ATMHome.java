package Bank.Management.System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ATMHome extends JFrame implements ActionListener{

    String PINno, cardno;

    JLabel labelTran;
    JButton deposit, cash, fastCash, miniSt, PINChange, balanceEq, exit;

    ATMHome(String PINno, String cardno){
        super("Bank Management System : ATM Home Page");

    //BackGround Img
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1080,700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel atmImg = new JLabel(i3);
        atmImg.setBounds(0,0,1280,700);
        add(atmImg);

    //Transaction text
        labelTran = new JLabel("Please Select your Transaction");
        labelTran.setFont(new Font("Raleway", Font.BOLD,18));
        labelTran.setForeground(Color.BLACK);
        labelTran.setBounds(425,280,800,25);
        atmImg.add(labelTran);

    //DEPOSIT Button
        deposit = new JButton("DEPOSIT");
        deposit.setFont(new Font("Raleway", Font.BOLD,10));
        deposit.setForeground(Color.WHITE);
        deposit.setBackground(new Color(65,125,128));
        deposit.setBounds(355,346,129,18);
        deposit.addActionListener(this);
        atmImg.add(deposit);
        
    //WITHDRAWAL Button
        cash = new JButton("CASH WITHDRAWL");
        cash.setFont(new Font("Raleway", Font.BOLD,10));
        cash.setForeground(Color.WHITE);
        cash.setBackground(new Color(65,125,128));
        cash.setBounds(626,346,129,18);
        cash.addActionListener(this);
        atmImg.add(cash);

    //FAST CASH Button
        fastCash = new JButton("FAST CASH");
        fastCash.setFont(new Font("Raleway", Font.BOLD,10));
        fastCash.setForeground(Color.WHITE);
        fastCash.setBackground(new Color(65,125,128));
        fastCash.setBounds(355,368,129,18);
        fastCash.addActionListener(this);
        atmImg.add(fastCash);

    //MINI STATEMENT Button
        miniSt = new JButton("MINI STATEMENT");
        miniSt.setFont(new Font("Raleway", Font.BOLD,10));
        miniSt.setForeground(Color.WHITE);
        miniSt.setBackground(new Color(65,125,128));
        miniSt.setBounds(626,368,129,18);
        miniSt.addActionListener(this);
        atmImg.add(miniSt);

    //PIN CHANGE Button
        PINChange = new JButton("PIN CHANGE");
        PINChange.setFont(new Font("Raleway", Font.BOLD,10));
        PINChange.setForeground(Color.WHITE);
        PINChange.setBackground(new Color(65,125,128));
        PINChange.setBounds(355,390,129,18);
        PINChange.addActionListener(this);
        atmImg.add(PINChange);

    //BALANCE ENQUIRY Button
        balanceEq = new JButton("BALANCE ENQUIRY");
        balanceEq.setFont(new Font("Raleway", Font.BOLD,10));
        balanceEq.setForeground(Color.WHITE);
        balanceEq.setBackground(new Color(65,125,128));
        balanceEq.setBounds(626,390,129,18);
        balanceEq.addActionListener(this);
        atmImg.add(balanceEq);

    //EXIT Button
        exit = new JButton("EXIT");
        exit.setFont(new Font("Raleway", Font.BOLD,10));
        exit.setForeground(Color.WHITE);
        exit.setBackground(new Color(65,125,128));
        exit.setBounds(626,412,129,18);
        exit.addActionListener(this);
        atmImg.add(exit);

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
        if(e.getSource()==deposit){
            setVisible(false);
            new Deposit(PINno, cardno);
        }else if (e.getSource()==cash){
            setVisible(false);
            new Withdrawal(PINno, cardno);
        }else if (e.getSource()==fastCash){
            setVisible(false);
            new FastCash(PINno, cardno);
        }else if (e.getSource()==miniSt){
            new Mini(PINno, cardno);
        }else if (e.getSource()==PINChange){
            setVisible(false);
            new ChangePin(PINno, cardno);
        }else if (e.getSource()==balanceEq){
            setVisible(false);
            new BalanceEnquiry(PINno,cardno);
        }else if (e.getSource()==exit){
        //System.exit(); it is used to exit or completely stop the program
        //setVisible(false); it only closes the frame, it does not stop the program from running    
            System.exit(0);
        }
    }

     public static void main(String[] args) {
       new ATMHome("", "");
    }
}
