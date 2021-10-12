package chatbot;

import java.util.Scanner;

public class chatbot {
    public static void main(String[] args) {
        System.out.println("Hello! My name is Alex.\n" +
                "I was created in 2021.\n" + "Please, remind me your name.");
        Scanner in = new Scanner(System.in);
        String name = in.next();
        System.out.println("What a great name you have," + name + "!\n" + "Let me guess your age.\n" +
                "Enter remainders of dividing your age by 3, 5 and 7.");
        int remainder3 = in.nextInt();
        int remainder5 = in.nextInt();
        int remainder7 = in.nextInt();
        System.out.println("Your age is " + (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105 + "; that's a good time to start programming!\n" +
                "Now I will prove to you that i can count to any number you want.");
        int num = in.nextInt();
        for(int i = 0;i != num + 1; i++){
            System.out.println(i + "!");
        }
        System.out.println("Давай проверим тест\n" + "1)17\n" + "2)18\n" + "3)19\n" + "4)20");
        while (true){
            int quest = in.nextInt();
            if (quest != 2){
                System.out.println("Please, try again");
            }else{
                System.out.println("Great, you right!");
                break;
            }

            }
        }


    }

