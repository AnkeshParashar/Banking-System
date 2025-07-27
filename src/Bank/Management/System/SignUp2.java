package Bank.Management.System;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.*;

public class SignUp2 extends JFrame implements ActionListener{

//****for carring application form no
    String formno;
//****

    JLabel labelPage, labelDetails, labelApp, labelReligion, labelCategory, labelIncome, labelEducation, labelOccupation, labelPAN, labelAadhar;
    JComboBox<String> rBox, cBox, iBox, eBox, oBox;
    JTextField textPAN, textAadhar;
    JButton next;

    SignUp2(String formno){
        super("Bank Management System : Application Form (Page-2)");

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
        labelPage = new JLabel("Page : 2");
        labelPage.setFont(new Font("Raleway", Font.BOLD,22));
        labelPage.setForeground(Color.WHITE);
        labelPage.setBounds(330,70,600,40);
        add(labelPage);

    //Aditional Details text    
        labelDetails = new JLabel("Additional Details");
        labelDetails.setFont(new Font("Raleway", Font.BOLD,25));
        labelDetails.setForeground(Color.WHITE);
        labelDetails.setBounds(270,100,600,40);
        add(labelDetails);  
        
    // Religion drop down menu
        labelReligion = new JLabel("Religion :");
        labelReligion.setFont(new Font("Raleway", Font.BOLD, 20));
        labelReligion.setForeground(Color.WHITE);   
        labelReligion.setBounds(100,160,600,30);
        add(labelReligion);

        String religion [] = {"Select Options.....","HINDU","MUSLIM","SIKH","CHRISTIAN","OTHER"};
        rBox = new JComboBox<>(religion);
        rBox.setFont(new Font("Raleway", Font.BOLD, 13));
        rBox.setForeground(Color.BLACK);
        rBox.setBackground(Color.WHITE);
        rBox.setBounds(300,165,400,22);
        add(rBox);

    // Category drop down menu
        labelCategory = new JLabel("Category :");
        labelCategory.setFont(new Font("Raleway", Font.BOLD, 20));
        labelCategory.setForeground(Color.WHITE);
        labelCategory.setBounds(100,190,600,30);
        add(labelCategory);

        String category [] = {"Select Options.....","General","OBC","SC","ST","OTHER"};
        cBox = new JComboBox<>(category);
        cBox.setFont(new Font("Raleway", Font.BOLD, 13));
        cBox.setForeground(Color.BLACK);
        cBox.setBackground(Color.WHITE);
        cBox.setBounds(300,195,400,22);
        add(cBox);

    // Income drop down menu
        labelIncome = new JLabel("Income :");
        labelIncome.setFont(new Font("Raleway", Font.BOLD, 20));
        labelIncome.setForeground(Color.WHITE);
        labelIncome.setBounds(100,220,600,30);
        add(labelIncome);

        String income [] = {"Select Options.....","Below 1 Lakh","Between 1 to 8 Lakhs","Above 8 Lakhs"};
        iBox = new JComboBox<>(income);
        iBox.setFont(new Font("Raleway", Font.BOLD, 13));
        iBox.setForeground(Color.BLACK);
        iBox.setBackground(Color.WHITE);
        iBox.setBounds(300,225,400,22);
        add(iBox);

    // Educational drop down menu
        labelEducation = new JLabel("Educational :");
        labelEducation.setFont(new Font("Raleway", Font.BOLD, 20));
        labelEducation.setForeground(Color.WHITE);
        labelEducation.setBounds(100,250,600,30);
        add(labelEducation);

        String education [] = {"Select Options.....","Non Graduate","Graduate","Post Graduate","Others"};
        eBox = new JComboBox<>(education);
        eBox.setFont(new Font("Raleway", Font.BOLD, 13));
        eBox.setForeground(Color.BLACK);
        eBox.setBackground(Color.WHITE);
        eBox.setBounds(300,255,400,22);
        add(eBox);

    // Occupation drop down menu
        labelOccupation = new JLabel("Occupation :");
        labelOccupation.setFont(new Font("Raleway", Font.BOLD, 20));
        labelOccupation.setForeground(Color.WHITE);
        labelOccupation.setBounds(100,280,600,30);
        add(labelOccupation);

        String occupation [] = {"Select Options.....","Salaried","Self-Employed","Business","Student","Retired","Others"};
        oBox = new JComboBox<>(occupation);
        oBox.setFont(new Font("Raleway", Font.BOLD, 13));
        oBox.setForeground(Color.BLACK);
        oBox.setBackground(Color.WHITE);
        oBox.setBounds(300,285,400,22);
        add(oBox);

    //PAN Number
        labelPAN = new JLabel("PAN Number :");
        labelPAN.setFont(new Font("Raleway", Font.BOLD, 20));
        labelPAN.setForeground(Color.WHITE);
        labelPAN.setBounds(100,310,600,30);
        add(labelPAN);

        textPAN = new JTextField();
        textPAN.setFont(new Font("Raleway", Font.BOLD,13));
        textPAN.setBounds(300,315,400,22);
        add(textPAN);

    //Aadhar Number
        labelAadhar = new JLabel("Aadhar Number :");
        labelAadhar.setFont(new Font("Raleway", Font.BOLD, 20));
        labelAadhar.setForeground(Color.WHITE);
        labelAadhar.setBounds(100,340,600,30);
        add(labelAadhar);

        textAadhar = new JTextField();
        textAadhar.setFont(new Font("Raleway", Font.BOLD,13));
        textAadhar.setBounds(300,345,400,22);
        add(textAadhar);

    //NEXT Button
        next = new JButton("NEXT>");
        next.setFont(new Font("Raleway",Font.BOLD,10));
        next.setForeground(Color.WHITE);
        next.setBackground(Color.BLACK);
        next.setBounds(600,390,100,30);
        next.addActionListener(this);
        add(next);

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
        
        String religion = (String) rBox.getSelectedItem();
        String category = (String) cBox.getSelectedItem();
        String income = (String) iBox.getSelectedItem();
        String education = (String) eBox.getSelectedItem();
        String occupation = (String) oBox.getSelectedItem();
        String PAN = textPAN.getText();
        String aadhar = textAadhar.getText();

        try {
            if(religion.equals("") || category.equals("") || income.equals("") || education.equals("") || occupation.equals("") || PAN.equals("") || aadhar.equals("") || religion.equals("Select Options.....") || category.equals("Select Options.....") || income.equals("Select Options.....") || education.equals("Select Options.....") || occupation.equals("Select Options.....")) {
                JOptionPane.showMessageDialog(null,"Fill all the necessary Fields");
            }else if(!aadhar.matches("[0-9]{12}")){
                JOptionPane.showMessageDialog(null,"Please enter your proper Aadhar Card Number");
            }else if(!PAN.matches("[A-Z0-9]{10}") || !PAN.matches("[A-Z]{5}\\d{4}.*")){
                JOptionPane.showMessageDialog(null,"Please enter your proper PAN Card Number");
            }else {
                int option = JOptionPane.showConfirmDialog(null,"You cannot change the above data once it is saved." + "\n" + "Do you want to Continue?");
                if(option == JOptionPane.OK_OPTION){
                    Link con2 = new Link();
                    PreparedStatement ps = con2.connection.prepareStatement("INSERT INTO signup2 VALUES('"+formno+"','"+religion+"','"+category+"','"+income+"','"+education+"','"+occupation+"','"+PAN+"','"+aadhar+"')");
                    ps.executeUpdate();

                    new SignUp3(formno);
                    setVisible(false);
                }else {
                    return;
                }
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SignUp2(""); //****for carring application form no(first)
    }
}
