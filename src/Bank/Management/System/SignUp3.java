package Bank.Management.System;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.swing.*;
// import javax.swing.ButtonGroup;
// import javax.swing.ImageIcon;
// import javax.swing.JButton;
// import javax.swing.JCheckBox;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JRadioButton;
// import javax.swing.JTextField;

public class SignUp3 extends JFrame implements ActionListener{

//****for carring application form no
    String formno;
//****

    JLabel labelApp, labelPage, labelDetails, labelAccount, labelCard, labelCardno, labelCardNO, labelCardNo, labelPIN, labelPINno, labelPINNO, labelService;
    JRadioButton a1, a2, a3, a4;
    JCheckBox s1, s2, s3, s4, s5, s6, s7;  
    JButton submit, cancel;

    SignUp3(String formno){
        super("Bank Management System : Application Form (Page-3)");

        this.formno = formno;
        
    //BANK Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icon\\bank.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel bankImg = new JLabel(i3);
        bankImg.setBounds(25,15,70,70);
        add(bankImg);  

    //Application Form text        
        labelApp = new JLabel("APPLICATION FORM NO. :" + formno);
        labelApp.setFont(new Font("Raleway", Font.BOLD,30));
        labelApp.setForeground(Color.WHITE);
        labelApp.setBounds(140,20,600,40);
        add(labelApp);    
        
    //Page : 2 text    
        labelPage = new JLabel("Page : 3");
        labelPage.setFont(new Font("Raleway", Font.BOLD,22));
        labelPage.setForeground(Color.WHITE);
        labelPage.setBounds(330,70,600,40);
        add(labelPage);

    //Aditional Dtails text    
        labelDetails = new JLabel("Account Details");
        labelDetails.setFont(new Font("Raleway", Font.BOLD,25));
        labelDetails.setForeground(Color.WHITE);
        labelDetails.setBounds(270,100,600,40);
        add(labelDetails);

    //Account Type text    
        labelAccount = new JLabel("Account Type :");
        labelAccount.setFont(new Font("Raleway", Font.BOLD, 20));
        labelAccount.setForeground(Color.WHITE);
        labelAccount.setBounds(100,160,600,30);
        add(labelAccount);

    //Account Type Radiobuttons    
        a1 = new JRadioButton("Saving Account");
        a1.setFont(new Font("Ralway", Font.BOLD,14));
        a1.setForeground(Color.WHITE);
        a1.setBackground(new Color(3,41,20));
        a1.setBounds(300,160,170,30);
        add(a1);

        a2 = new JRadioButton("Fixed Dposit Account");
        a2.setFont(new Font("Ralway", Font.BOLD,14));
        a2.setForeground(Color.WHITE);
        a2.setBackground(new Color(3,41,20));
        a2.setBounds(500,160,170,30);
        add(a2);

        a3 = new JRadioButton("Current Account");
        a3.setFont(new Font("Ralway", Font.BOLD,14));
        a3.setForeground(Color.WHITE);
        a3.setBackground(new Color(3,41,20));
        a3.setBounds(300,190,170,30);
        add(a3);

        a4 = new JRadioButton("Demat Account");
        a4.setFont(new Font("Ralway", Font.BOLD,14));
        a4.setForeground(Color.WHITE);
        a4.setBackground(new Color(3,41,20));
        a4.setBounds(500,190,170,30);
        add(a4);

        ButtonGroup a1234 = new ButtonGroup();
        a1234.add(a1);
        a1234.add(a2);
        a1234.add(a3);
        a1234.add(a4);

    //Card No. text    
        labelCard = new JLabel("Card Number :");
        labelCard.setFont(new Font("Raleway", Font.BOLD, 20));
        labelCard.setForeground(Color.WHITE);
        labelCard.setBounds(100,220,600,30);
        add(labelCard);

        labelCardno = new JLabel("(Your 16 digit card number)");
        labelCardno.setFont(new Font("Raleway", Font.BOLD, 10));
        labelCardno.setForeground(Color.WHITE);
        labelCardno.setBounds(102,245,600,30);
        add(labelCardno);

        labelCardNO = new JLabel("XXXX-XXXX-XXXX-XXXX");
        labelCardNO.setFont(new Font("Raleway", Font.BOLD, 20));
        labelCardNO.setForeground(Color.WHITE);
        labelCardNO.setBounds(300,220,600,30);
        add(labelCardNO);

        labelCardNo = new JLabel("(It will appear on your ATM Card)");
        labelCardNo.setFont(new Font("Raleway", Font.BOLD, 10));
        labelCardNo.setForeground(Color.WHITE);
        labelCardNo.setBounds(302,245,600,30);
        add(labelCardNo);

    //PIN text    
        labelPIN = new JLabel("PIN Number :");
        labelPIN.setFont(new Font("Raleway", Font.BOLD, 20));
        labelPIN.setForeground(Color.WHITE);
        labelPIN.setBounds(100,275,600,30);
        add(labelPIN);

        labelPINno = new JLabel("(4-digit Password)");
        labelPINno.setFont(new Font("Raleway", Font.BOLD, 10));
        labelPINno.setForeground(Color.WHITE);
        labelPINno.setBounds(102,300,600,30);
        add(labelPINno);

        labelPINNO = new JLabel("****");
        labelPINNO.setFont(new Font("Raleway", Font.BOLD, 20));
        labelPINNO.setForeground(Color.WHITE);
        labelPINNO.setBounds(300,280,600,30);
        add(labelPINNO);

    //Services text    
        labelService = new JLabel("Sevices Required :");
        labelService.setFont(new Font("Raleway", Font.BOLD, 20));
        labelService.setForeground(Color.WHITE);
        labelService.setBounds(100,330,600,30);
        add(labelService);

    //Services Radiobutton    
        s1 = new JCheckBox("ATM CARD");
        s1.setFont(new Font("Ralway", Font.BOLD,14));
        s1.setForeground(Color.WHITE);
        s1.setBackground(new Color(3,41,20));
        s1.setBounds(300,330,150,30);
        add(s1);

        s2 = new JCheckBox("Internet Banking");
        s2.setFont(new Font("Ralway", Font.BOLD,14));
        s2.setForeground(Color.WHITE);
        s2.setBackground(new Color(3,41,20));
        s2.setBounds(500,330,150,30);
        add(s2);

        s3 = new JCheckBox("Mobile Banking");
        s3.setFont(new Font("Ralway", Font.BOLD,14));
        s3.setForeground(Color.WHITE);
        s3.setBackground(new Color(3,41,20));
        s3.setBounds(300,360,150,30);
        add(s3);

        s4 = new JCheckBox("EMAIL Alerts");
        s4.setFont(new Font("Ralway", Font.BOLD,14));
        s4.setForeground(Color.WHITE);
        s4.setBackground(new Color(3,41,20));
        s4.setBounds(500,360,150,30);
        add(s4);

        s5 = new JCheckBox("Cheque Book");
        s5.setFont(new Font("Ralway", Font.BOLD,14));
        s5.setForeground(Color.WHITE);
        s5.setBackground(new Color(3,41,20));
        s5.setBounds(300,390,150,30);
        add(s5);

        s6 = new JCheckBox("E-Statement");
        s6.setFont(new Font("Ralway", Font.BOLD,14));
        s6.setForeground(Color.WHITE);
        s6.setBackground(new Color(3,41,20));
        s6.setBounds(500,390,150,30);
        add(s6);

    //Declaration by user
        s7 = new JCheckBox("I hereby declare that the above details are true to the best of my knowledge.", false);
        s7.setFont(new Font("Ralway", Font.BOLD,14));
        s7.setForeground(Color.WHITE);
        s7.setBackground(new Color(3,41,20));
        s7.setBounds(100,470,550,30);
        add(s7);

    //SUBMIT button
        submit = new JButton("SUBMIT");
        submit.setFont(new Font("Raleway",Font.BOLD,10));
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLACK);
        submit.setBounds(500,520,100,30);
        submit.addActionListener(this);
        add(submit);

    //CANCEL Button    
        cancel = new JButton("CANCEL");
        cancel.setFont(new Font("Raleway", Font.BOLD,10));
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.setBounds(200,520,100,30);
        cancel.addActionListener(this);
        add(cancel);

//****for carring application form no
        this.formno = formno;
//****        

    //Background Image
        ImageIcon iii1 = new ImageIcon(ClassLoader.getSystemResource("Icon\\bg.jpeg"));
        Image iii2 = iii1.getImage().getScaledInstance(850,780, Image.SCALE_DEFAULT);
        ImageIcon iii3 = new ImageIcon(iii2);
        JLabel bgImg = new JLabel(iii3);
        bgImg.setBounds(0,0,750,780);
        add(bgImg);

        setLayout(null);
        setUndecorated(true);
        setSize(750,650);
        setLocation(360,40);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String accountType = "";
        if(a1.isSelected()){
            accountType = "Saving Account";
        } else if (a2.isSelected()){
            accountType = "Fixed Deposit Account";
        } else if (a3.isSelected()){
            accountType = "Current Account";
        } else if (a4.isSelected()){
            accountType = "Demat Account";
        }else {
            accountType = "";
        }
        Random ran = new Random();
        long first8 = (ran.nextLong(90000000)) + 1479966200000000L;
        String cardno = String.valueOf(first8);

        long first4 = (ran.nextLong(9000)) + 1000L;
        String PINno = String.valueOf(first4);

        String ser = "";
        for (int i = 1; i<=6; i++){
            if(i == 1){
                if (s1.isSelected()){
                    ser = ser + "ATM CARD, ";
                }
            }
            if (i == 2){
                if(s2.isSelected()){
                    ser = ser + "Internet Banking, ";
                }
            }
            if (i == 3){
                if(s3.isSelected()){
                    ser = ser + "Mobile Banking, ";
                }
            }
            if (i == 4){
                if(s4.isSelected()){
                    ser = ser + "EMAIL Alerts, ";
                }
            }
            if (i == 5){
                if(s5.isSelected()){
                    ser = ser + "Cheque Book, ";
                }
            }
            if (i == 6){
                if(s6.isSelected()){
                    ser = ser + "E-Statement";
                }
            }
        }
        try {
            if (e.getSource()==submit){
                if(accountType==""){
                    JOptionPane.showMessageDialog(null,"Fill all the necessary Fields");
                }else if (s7.isSelected()==false){
                    JOptionPane.showMessageDialog(null,"Please give your Declaration");
                }else {
                    Link con3 = new Link();
                    PreparedStatement ps1 = con3.connection.prepareStatement("INSERT INTO signup3 VALUES('"+formno+"','"+accountType+"','"+cardno+"','"+PINno+"','"+ser+"')");
                    PreparedStatement ps2 = con3.connection.prepareStatement("INSERT INTO login VALUES('"+formno+"','"+cardno+"','"+PINno+"')");
                    ps1.executeUpdate();
                    ps2.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Card No. : " + cardno + "\n PIN : " + PINno + "\nNote it for further Uses");
                    new Deposit(PINno, cardno);
                    setVisible(false);
                }

            }else if (e.getSource()==cancel){
                setVisible(false);
                new Login(PINno, cardno);
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SignUp3("");
    }
}
