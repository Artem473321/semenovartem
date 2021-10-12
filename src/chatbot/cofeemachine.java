package chatbot;

import java.util.*;

class Machine {
    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;
    private int money = 550;


    public String ingridients(int water,int milk, int beans, int money){
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

    public void buyCofee(String num){
        if (Objects.equals(num, "1")){
            System.out.println(ingridients(250,0,16,4));
        }
        else if (Objects.equals(num, "2")){
            System.out.println(ingridients(350,75,20,7));
        }
        else if (Objects.equals(num, "3")){
            System.out.println(ingridients(200,100,12,6));
        }
    }

    public void takeMoney(){
        System.out.println("I gave you " + this.money);
        this.money = 0;
    }

    public void setAllIngridient(Scanner input){
        System.out.println("Write how many ml of water you want to add:");
        this.water += input.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        this.milk += input.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        this.beans += input.nextInt();
        System.out.println("Write how many disposable coffee cups you want to add:");
        this.cups += input.nextInt();
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


public class cofeemachine {

    public static void main(String[] args) {

        Machine person = new Machine();
        System.out.println(person.getAllIngridient());
        Scanner input = new Scanner(System.in);
        String move;
        do {
            System.out.println("\nWrite action (buy, fill, take):");
            move = input.next();
            switch (move){
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, or back:");
                    String num = input.next();
                    switch (num){
                        case "back":
                            break;
                        case "1": case "2": case "3":
                            person.buyCofee(num);
                            break;

                    }
                    break;
                case "fill":
                    person.setAllIngridient(input);
                    break;
                case "take":
                    person.takeMoney();
                    break;
                case "remaining":
                    System.out.println(person.getAllIngridient());
                    break;

            }
        }
        while (!move.equals("exit"));
    }
}
