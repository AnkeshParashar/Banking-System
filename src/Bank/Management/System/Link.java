package Bank.Management.System;

import java.sql.*;

// con1  -->  SignUp1
// con2  -->  SignUp2
// con3  -->  SignUp3
// con4  -->  Deposit
// con5  -->  Login
// con6  -->  Withdrawal
// con7  -->  BalanceEnquiry
// con8  -->  FastCash
// con9  -->  ChangePin
// con10 -->  Mini

public class Link {

    Connection connection;
    Statement statement;

    //LINK constructor is public so that it can be called in other classes
    public Link(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankData","root","1234567890");
            statement = connection.createStatement();

        } catch(Exception e){
            e.printStackTrace();

        }
    }
    public static void main(String[] args) {
        
    }
}
