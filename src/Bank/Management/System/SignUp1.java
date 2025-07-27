package Bank.Management.System;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
//import java.time.Instant;
// import java.time.LocalDate;
// import java.time.Period;
//import java.time.ZoneId;
// import java.time.format.DateTimeFormatter;
import java.util.*;

public class SignUp1 extends JFrame implements ActionListener{

    JLabel labelApp, labelPage, labelDetails, labelName, labelFName, labelDOB, dobFormat, labelAge, labelGender, labelMStatus, labelEmail, labelAdd, labelCity, labelState, labelPin;
    JTextField textName, textFName, textEmail, textAdd, textCity, textState, textPin;
    JDateChooser dateChoose;
    JTextArea area;
    JRadioButton g1, g2, m1, m2;
    JButton age, next;
    int dobMonthInt;

//to generate random no.     
    Random no = new Random();
    long digit4 = (no.nextLong(9000)) +1000L;
    String random = " " + String.valueOf(digit4);

    SignUp1(){
        super("Bank Management System : Application Form (Page-1)");

    //BANK Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icon\\bank.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel bankImg = new JLabel(i3);
        bankImg.setBounds(25,15,70,70);
        add(bankImg);        

    //Application Form text        
        labelApp = new JLabel("APPLICATION FORM NO. :" + random);
        labelApp.setFont(new Font("Raleway", Font.BOLD,30));
        labelApp.setForeground(Color.WHITE);
        labelApp.setBounds(140,20,600,40);
        add(labelApp);

    //Page : 1 text    
        labelPage = new JLabel("Page : 1");
        labelPage.setFont(new Font("Raleway", Font.BOLD,22));
        labelPage.setForeground(Color.WHITE);
        labelPage.setBounds(330,70,600,40);
        add(labelPage);

    //Personal Dtails text    
        labelDetails = new JLabel("Personal Details");
        labelDetails.setFont(new Font("Raleway", Font.BOLD,25));
        labelDetails.setForeground(Color.WHITE);
        labelDetails.setBounds(280,100,600,40);
        add(labelDetails); 

    //Name text    
        labelName = new JLabel("Name :");
        labelName.setFont(new Font("Raleway", Font.BOLD,20));
        labelName.setForeground(Color.WHITE);
        labelName.setBounds(100,160,600,30);
        add(labelName);
   
        textName = new JTextField();
        textName.setFont(new Font("Raleway", Font.BOLD,13));
        textName.setBounds(300,165,400,22);
        add(textName);

    //Father's Name text    
        labelFName = new JLabel("Father's Name :");
        labelFName.setFont(new Font("Raleway", Font.BOLD,20));
        labelFName.setForeground(Color.WHITE);
        labelFName.setBounds(100,190,600,30);
        add(labelFName);

        textFName = new JTextField();
        textFName.setFont(new Font("Raleway", Font.BOLD,13));
        textFName.setBounds(300,195,400,22);
        add(textFName);
    
    //DOB text + Calendar 
        labelDOB = new JLabel("Date of Birth :");
        labelDOB.setFont(new Font("Raleway", Font.BOLD,20));
        labelDOB.setForeground(Color.WHITE);
        labelDOB.setBounds(100,220,600,30);
        add(labelDOB);

        dateChoose = new JDateChooser();
        dateChoose.setForeground(new Color(100,100,100));
        dateChoose.setBounds(300,225,200,22);
        add(dateChoose);

    //AGE Calculation
        age = new JButton("Age :");
        age.setFont(new Font("Raleway", Font.BOLD,16));
        age.setForeground(Color.WHITE);
        age.setBackground(Color.BLACK);
        age.setBounds(525,225,75,27);
        age.addActionListener(this);
        add(age);

        area = new JTextArea();
        area.setFont(new Font("Raleway", Font.BOLD, 15));
        area.setBackground(Color.WHITE);
        area.setBounds(620,225,80,25);
        area.setEditable(false);
        add(area);

    //Gender + Radiobutton    
        labelGender = new JLabel("Gender :");
        labelGender.setFont(new Font("Raleway", Font.BOLD,20));
        labelGender.setForeground(Color.WHITE);
        labelGender.setBounds(100,250,600,30);
        add(labelGender);

        g1 = new JRadioButton("Male");
        g1.setFont(new Font("Ralway", Font.BOLD,14));
        g1.setForeground(Color.WHITE);
        g1.setBackground(Color.BLACK);
        g1.setBounds(300,250,100,30);
        add(g1);

        g2 = new JRadioButton("Female");
        g2.setFont(new Font("Ralway", Font.BOLD,14));
        g2.setForeground(Color.WHITE);
        g2.setBackground(Color.BLACK);
        g2.setBounds(400,250,100,30);
        add(g2);

        ButtonGroup g1g2 = new ButtonGroup();
        g1g2.add(g1);
        g1g2.add(g2);

    //Marital Status text    
        labelMStatus = new JLabel("Marital Status :");
        labelMStatus.setFont(new Font("Raleway", Font.BOLD,20));
        labelMStatus.setForeground(Color.WHITE);
        labelMStatus.setBounds(100,280,600,30);
        add(labelMStatus);

        m1 = new JRadioButton("Married");
        m1.setFont(new Font("Ralway", Font.BOLD,14));
        m1.setForeground(Color.WHITE);
        m1.setBackground(Color.BLACK);
        m1.setBounds(300,280,100,30);
        add(m1);

        m2 = new JRadioButton("Unmarried");
        m2.setFont(new Font("Ralway", Font.BOLD,14));
        m2.setForeground(Color.WHITE);
        m2.setBackground(Color.BLACK);
        m2.setBounds(400,280,100,30);
        add(m2);

        ButtonGroup m1m2 = new ButtonGroup();
        m1m2.add(m1);
        m1m2.add(m2);

    //Email text    
        labelEmail = new JLabel("Email Address :");
        labelEmail.setFont(new Font("Raleway", Font.BOLD,20));
        labelEmail.setForeground(Color.WHITE);
        labelEmail.setBounds(100,310,600,30);
        add(labelEmail);

        textEmail = new JTextField();
        textEmail.setFont(new Font("Raleway", Font.BOLD,13));
        textEmail.setBounds(300,315,400,22);
        add(textEmail);

    //Address text    
        labelAdd = new JLabel("Address :");
        labelAdd.setFont(new Font("Raleway", Font.BOLD,20));
        labelAdd.setForeground(Color.WHITE);
        labelAdd.setBounds(100,340,600,30);
        add(labelAdd);

        textAdd = new JTextField();
        textAdd.setFont(new Font("Raleway", Font.BOLD,13));
        textAdd.setBounds(300,345,400,22);
        add(textAdd);

    //City text    
        labelCity = new JLabel("City :");
        labelCity.setFont(new Font("Raleway", Font.BOLD,20));
        labelCity.setForeground(Color.WHITE);
        labelCity.setBounds(100,370,600,30);
        add(labelCity);
    
        textCity = new JTextField();
        textCity.setFont(new Font("Raleway", Font.BOLD,13));
        textCity.setBounds(300,375,400,22);
        add(textCity);

    //State text    
        labelState = new JLabel("State :");
        labelState.setFont(new Font("Raleway", Font.BOLD,20));
        labelState.setForeground(Color.WHITE);
        labelState.setBounds(100,400,600,30);
        add(labelState);

        textState = new JTextField();
        textState.setFont(new Font("Raleway", Font.BOLD,13));
        textState.setBounds(300,405,400,22);
        add(textState);

    //Pin Code text    
        labelPin = new JLabel("Pin Code :");
        labelPin.setFont(new Font("Raleway", Font.BOLD,20));
        labelPin.setForeground(Color.WHITE);
        labelPin.setBounds(100,430,600,30);
        add(labelPin);

        textPin = new JTextField();
        textPin.setFont(new Font("Raleway", Font.BOLD,13));
        textPin.setBounds(300,435,400,22);
        add(textPin);

    //NEXT Button    
        next = new JButton("NEXT >");
        next.setFont(new Font("Raleway", Font.BOLD,10));
        next.setForeground(Color.WHITE);
        next.setBackground(Color.BLACK);
        next.setBounds(600,470,100,30);
        next.addActionListener(this);
        add(next);

    //Background Image
        ImageIcon iii1 = new ImageIcon(ClassLoader.getSystemResource("Icon\\bg.jpeg"));
        Image iii2 = iii1.getImage().getScaledInstance(850,780, Image.SCALE_DEFAULT);
        ImageIcon iii3 = new ImageIcon(iii2);
        JLabel bgImg = new JLabel(iii3);
        bgImg.setBounds(0,0,750,780);
        add(bgImg);

    //JFrame     
        setLayout(null);
        setUndecorated(true);
        setSize(750,650);
        setLocation(360,40);
        setVisible(true);
    }

//Action performed by buttons    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String formno = random;
            String name = textName.getText();
            String fname = textFName.getText();
            String areaAge = area.getText();
            String chooseDob = ((JTextField) dateChoose.getDateEditor().getUiComponent()).getText();
            String year;
            
        //DOB and AGE Calculation
            if (e.getSource()==age){
                if(!chooseDob.matches("[A-Za-z]{3}+[\\s]+[0-9]{1,2}+[,]+[\\s]+[0-9]{4}") || chooseDob.equals("")){ //|| !dob.matches("[A-Za-z]{3}+$1+[0-9]{1,2}+,+$2+[0-9]{4}")
                        JOptionPane.showMessageDialog(null,"Please enter your Date of Birth.");
                }else {       
                    if(chooseDob.length() == 11){
                    year = chooseDob.substring(7,11);
                    }else{
                        year = chooseDob.substring(8,12);
                    }
                    int dobYearInt = Integer.parseInt(year);
                    String month = chooseDob.substring(0,3);
                    switch(month){
                        case "Jan" : dobMonthInt = 1;
                        break;
                        case "Feb" : dobMonthInt = 2;
                        break;
                        case "Mar" : dobMonthInt = 3;
                        break;
                        case "Apr" : dobMonthInt = 4;
                        break;
                        case "May" : dobMonthInt = 5;
                        break;
                        case "Jun" : dobMonthInt = 6;
                        break;
                        case "Jul" : dobMonthInt = 7;
                        break;
                        case "Aug" : dobMonthInt = 8;
                        break;
                        case "Sep" : dobMonthInt = 9;
                        break;
                        case "Oct" : dobMonthInt = 10;
                        break;
                        case "Nov" : dobMonthInt = 11;
                        break;
                        case "Dec" : dobMonthInt = 12;
                        break;
                    }             
                    Date today = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    String todayString = formatter.format(today);
                    String todayYearSub = todayString.substring(6,10);
                    String todayMonthSub = todayString.substring(3,5);
                    int todayYearInt = Integer.parseInt(todayYearSub);
                    int todayMonthInt = Integer.parseInt(todayMonthSub);
                    int age = todayYearInt - dobYearInt;
                    int ageMonth = todayMonthInt - dobMonthInt;
                    if (ageMonth < 0){
                        age = age - 1;
                        String finalAge = Integer.toString(age);
                        area.setText("");
                        area.append(finalAge);
                    }else if (ageMonth > 0){
                        String finalAge = Integer.toString(age);
                        area.setText("");
                        area.append(finalAge);
                    }
                }
            }
            String gender = "";
            if(g1.isSelected()){
                gender = "Male";
            } else if (g2.isSelected()){
                gender = "Female";
            }
            String mstatus = "";
            if(m1.isSelected()){
                mstatus = "Married";
            } else if (m2.isSelected()){
                mstatus = "Unmarried";
            }
            String email = textEmail.getText();
            String address = textAdd.getText();
            String city = textCity.getText();
            String state = textState.getText();
            String pin = textPin.getText();

            if(e.getSource()==next){                
                if(name.equals("")|| fname.equals("") || chooseDob.equals("") || areaAge.equals("") || gender.equals("") || mstatus.equals("") || email.equals("") || address.equals("") || city.equals("") || state.equals("") || pin.equals("") ){
                    JOptionPane.showMessageDialog(null,"Fill all the necessary Fields");
                }else if(!name.matches("(?=.*[A-Za-z])[A-Za-z\\s]{2,30}")){
                    JOptionPane.showMessageDialog(null,"Name should be in between 2 to 30 characters");
                }else if(!fname.matches("(?=.*[A-Za-z])[A-Za-z\\s]{2,30}")){
                    JOptionPane.showMessageDialog(null,"Father's Name should be in between 2 to 30 characters");
                }else if(!areaAge.matches("[0-9]{1,3}")){
                    JOptionPane.showMessageDialog(null,"Please calculate your age.");
                    area.setText("");
                }else if(!email.matches("[A-Za-z@!#$%&'*+-/=?^_`{|}~0-9]{5,60}") || !email.contains("@") || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}")){
                    JOptionPane.showMessageDialog(null,"Please enter the valid E-mail");
                }else if(!address.matches("(?=.*[A-Za-z])[A-Za-z@!#$%&'*+-/=?^_`{|}~0-9\\s]{3,100}")){
                    JOptionPane.showMessageDialog(null,"Address should be in between 3 to 100 characters");
                }else if(!city.matches("(?=.*[A-Za-z])[A-Za-z@!#$%&'*+-/=?^_`{|}~0-9\\s]{2,30}")){
                    JOptionPane.showMessageDialog(null,"City name should be in between 2 to 30 characters");
                }else if(!state.matches("(?=.*[A-Za-z])[A-Za-z@!#$%&'*+-/=?^_`{|}~0-9\\s]{3,30}") ){
                    JOptionPane.showMessageDialog(null,"State should be in between 3 to 30 characters");
                }else if(!pin.matches("[0-9]{6}")){
                    JOptionPane.showMessageDialog(null,"PIN Code should be of 6 digits");
                }else {
                    int option = JOptionPane.showConfirmDialog(null,"You cannot change the above data once it is saved.\nDo you want to Continue?");
                    if (option == JOptionPane.OK_OPTION ){ 
                        //forming object of connection class which was named as Link
                        Link con1 = new Link();
                        PreparedStatement ps = con1.connection.prepareStatement("INSERT INTO signup1 VALUES('"+formno+"','"+name+"','"+fname+"','"+chooseDob+"','"+gender+"','"+mstatus+"','"+email+"','"+address+"','"+city+"','"+state+"','"+pin+"','"+areaAge+"')");
                        ps.executeUpdate();

                        //to go to the new sing up window(new signup class)
                        new SignUp2(formno);
                        setVisible(false);
                    }else {
                        return;
                    }
                }
            }    
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SignUp1();
    }
}
