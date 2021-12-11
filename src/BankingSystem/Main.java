package BankingSystem;


import java.sql.*;
import java.util.*;

interface funcBank{
    void createAccount(Base db);
    void menuBank(Base db);
    void logInAccount(Base db);
}

interface funcBase{
    void createTable();
    void connectBase();
    void insertBase(String nuberCard, String pin);
}


class Base implements funcBase {
    private final String URL = "jdbc:sqlite:card.s3db.db";
    private Connection connect = null;

    public Base(){
        connectBase();
    }

    @Override
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS card (\n" +
                " id integer PRIMERY KEY,\n" +
                " number text,\n" +
                " pin text,\n" +
                " balance INTEGER DEFAULT 0)";
        try{
            Statement statement = connect.createStatement();
            statement.execute(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private void exitBase(){
        try {
            if (connect!=null){
                connect.close();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void connectBase() {
        try {
            connect = DriverManager.getConnection(URL);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insertBase(String numberCard, String pin) {
        String insert = "INSERT INTO card(number, pin) VALUES(?,?) ";
        try {
            PreparedStatement preparateStatement = connect.prepareStatement(insert);
            preparateStatement.setString(1,numberCard);
            preparateStatement.setString(2,pin);
            preparateStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int checkBalance(String numberCard){
        String table = "SELECT balance FROM card WHERE number = ?";
        int balance = 0;
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(table);
            preparedStatement.setString(1,numberCard);
            ResultSet resultSet = preparedStatement.executeQuery();
            balance = resultSet.getInt("balance");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return balance;

    }

    public void addMoney( String numberCard, int money){
        String table = "UPDATE card SET balance = ? WHERE number = ?";
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(table);
            preparedStatement.setInt(1, checkBalance(numberCard) + money);
            preparedStatement.setString(2,numberCard);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteCard(String numberCard){
        String table = "DELETE FROM card WHERE number = ?";
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(table);
            preparedStatement.setString(1,numberCard);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void doTransfer(String numberCard ,String transferCard,int money){
        String table = "UPDATE card SET balance = ? WHERE number = ?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(table);
            preparedStatement.setInt(1,checkBalance(numberCard) - money);
            preparedStatement.setString(2,numberCard);
            preparedStatement.executeUpdate();
            preparedStatement.setInt(1,checkBalance(transferCard) + money);
            preparedStatement.setString(2,transferCard);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}


abstract class inBank{
    Scanner input = new Scanner(System.in);
    private String numCard;
    private Map<String,Integer> checkBalance = new HashMap<>();
    private boolean inAccount = true;


    public boolean connectIn(String numCard,Base db){
        this.numCard = numCard;
        if (!checkBalance.containsKey(this.numCard)){
            checkBalance.put(this.numCard,0);
        }
        String exit = "";
        while (this.inAccount || exit.equals("exit")){
            System.out.println("1. Balance\n" +
                    "2. Add income\n" +
                    "3. Do transfer\n" +
                    "4. Close account\n" +
                    "5. Log out\n" +
                    "0. Exit\n");

            switch (input.nextInt()){
                case 1:
                    System.out.println("Balance: " + db.checkBalance(this.numCard));
                    break;
                case 2:
                    System.out.println("Enter income:");
                    db.addMoney(this.numCard,input.nextInt());
                    break;
                case 3:
                    System.out.println("Enter card number:");
                    String numberCard = input.next();
                    System.out.println("Enter how much money you want to transfer:");
                    int money = input.nextInt();
                    db.doTransfer(this.numCard,numberCard,money);
                    System.out.println("Success!");
                    break;
                case 4:
                    db.deleteCard(this.numCard);
                    System.out.println("The account has been closed!");
                    break;
                case 5:
                    this.inAccount = false;
                    break;
                case 0:
                    this.inAccount = false;
                    break;

            }
        }
        return this.inAccount;
    }

}



class connectBank extends inBank implements funcBank {
    Scanner input = new Scanner(System.in);
    private Map<String,String> personAccount = new HashMap<>();
    private String numberCurd;
    private String pinCurd;
    private boolean inSistem = true;

    @Override
    public void menuBank(Base db){
        while (inSistem){
            System.out.println("1. Create an account\n2. Log into account\n0. Exit\n");
            int choose = input.nextInt();
            switch (choose){
                case 1:
                    createAccount(db);
                    break;
                case 2:
                    logInAccount(db);
                    break;
                case 0:
                    inSistem = false;
                    break;
            }
        }
    }

    @Override
    public void createAccount(Base db){
        lunaTrue();
        System.out.println("Your card has been created\n" +
                    "Your card number:\n" +
                    this.numberCurd +
                    "\nYour card PIN:\n" +
                    this.pinCurd);
        personAccount.put(this.numberCurd,this.pinCurd);
        db.insertBase(this.numberCurd,this.pinCurd);


    }

    private void lunaTrue() {
        int sumNums = 1;
        while (sumNums%10!=0){
            this.numberCurd="400000";
            this.pinCurd = "";
            sumNums = 0;
            for (int i = 0; i < 10; i++) {
                this.numberCurd +=(int) Math.floor(Math.random() * 10);
                if (i < 4) {
                    this.pinCurd += (int)Math.floor(Math.random() * 10);
                }
            }
            for (int f = 0; f < 16; f++) {
                if (f % 2 == 1) {
                    sumNums += Integer.parseInt(String.valueOf(this.numberCurd.charAt(f)));
                } else {
                    int num = Integer.parseInt(String.valueOf(this.numberCurd.charAt(f))) * 2;
                    if (num > 9) {
                        String twoNums = String.valueOf(num);
                        num = Integer.parseInt(String.valueOf(twoNums.charAt(0))) + Integer.parseInt(String.valueOf(twoNums.charAt(1)));
                    }
                    sumNums += num;
                }
            }

        }
        }

    @Override
    public void logInAccount(Base db){
        System.out.println("Enter your card number:");
        String loginCurd = input.next();
        System.out.println("Enter your PIN:");
        String loginPin = input.next();
        if (personAccount.containsKey(loginCurd) && Objects.equals(personAccount.get(loginCurd), loginPin)){
            System.out.println("You have successfully logged in!");
            this.inSistem = connectIn(this.numberCurd,db);    //Переход в другой класс

        }
    }

}

public class Main {
    public static void main(String[] args) {
        connectBank person = new connectBank();
        Base db = new Base();
        db.createTable();
        person.menuBank(db);



    }

}
