package CoffeeMachine;

import java.util.*;

enum Coffee{
    ESPRSESSO(250,0,16,4),
    LATTE(350,75,20,7),
    CAPPUCCINO(200,100,12,6);

    public final int cwater,cmilk,cbeans,cmoney;

    Coffee(int cwater, int cmilk, int cbeans, int cmoney){
        this.cwater = cwater;
        this.cmilk = cmilk;
        this.cbeans = cbeans;
        this.cmoney = cmoney;
    }
}

class Machine {
    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;
    private int money = 550;

    public boolean included = true;

    private final Map<String,Coffee> typeCoffee = new HashMap<String, Coffee>();
    private final Map<String, String> function = new HashMap<String, String>();

    public String AddIngridients(int water,int milk, int beans, int money){
        if(this.water-water>=0 && this.milk-milk>=0 && this.beans-beans>=0 && this.cups-1>=0){
            this.water -= water;
            this.milk -= milk;
            this.beans -= beans;
            this.cups -= 1;
            this.money += money;
            return "Кофе готово";
        }
        return "Недостаточно ингридиентов";
    }

    public void buyCofee(){
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back – to main menu:");
        Scanner input = new Scanner(System.in);
        String number = input.next();
        if(!Objects.equals(number,"back")){
            Coffee type_coffee = this.typeCoffee.get(input.nextLine());
            System.out.println(AddIngridients(type_coffee.cwater,type_coffee.cmilk,type_coffee.cbeans,type_coffee.cmoney));
        }

    }

    public void takeMoney(){
        System.out.println("I gave you " + this.money);
        this.money = 0;
    }

    public void setAllIngridient(){
        Scanner input = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        this.water += input.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        this.milk += input.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        this.beans += input.nextInt();
        System.out.println("Write how many disposable coffee cups you want to add:");
        this.cups += input.nextInt();
    }

    public void preparation(){
        this.typeCoffee.put("1",Coffee.ESPRSESSO);
        this.typeCoffee.put("2",Coffee.LATTE);
        this.typeCoffee.put("3",Coffee.CAPPUCCINO);
    }

    public void functionSelection(String move){
        switch (move){
            case "buy":
                buyCofee();
                break;
            case "fill":
                setAllIngridient();
                break;
            case "take":
                takeMoney();
                break;
            case "remaining":
                System.out.println(getAllIngridient());
                break;
            case "exit":
                included = false;
                break;

        }
    }

    public String getAllIngridient(){
        return "The coffee machine has:\n" +
                this.water + " of water\n" +
                this.milk + " of milk\n" +
                this.beans + " of coffee beans\n" +
                this.cups + " of disposable cups\n" +
                this.money + " of money";
    }



}

public class CoffeeMachine {

    public static void main(String[] args) {
        Machine person = new Machine();
        person.preparation();
        System.out.println(person.getAllIngridient());
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\nWrite action (buy, fill, take):");
            person.functionSelection(input.next());
        }
        while (person.included);
    }
}