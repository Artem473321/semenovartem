package Hang_man;


import java.util.*;


class Game{
    private final ArrayList<String> secretWordList = new ArrayList<>();
    private final ArrayList<String> lettersList = new ArrayList<>();
    private final ArrayList<String> allLettersList = new ArrayList<>();

    public int health = 8;

    public static final int MIN_HEALTH = 0;

    public void startgame(){
        Scanner input = new Scanner(System.in);
        while (true) {
            if (getHealth() == Game.MIN_HEALTH) {
                break;
            }
            boolean situation = printWord();
            if(!situation){
                break;
            }
            System.out.println("\nGuess the word:");
            String letter = input.next();
            String alfabet = "qwertyuiopasdfghjklzxcvbnm";
            if (!(letter.length() == 1)) {
                System.out.println("You should input a single letter.");
                continue;
            } else if (!alfabet.contains(letter)) {
                System.out.println("Please enter a lowercase English letter.");
            }
            examinate(letter);
        }
    }

    public void generateWord(){
        ArrayList<String> test = new ArrayList<>();
        Random index = new Random();
        test.add("python");
        test.add("kotlin");
        test.add("java");
        test.add("javascript");
        String[] word = test.get(index.nextInt(4)).split("");
        this.secretWordList.addAll(Arrays.asList(word));
    }

    public boolean printWord(){
        int num = 0;
        for (String secretWord : secretWordList) {
            if (this.lettersList.contains(secretWord)) {
                System.out.print(secretWord);
            } else {
                System.out.print("-");
                num += 1;
            }
        }
        if(num==0){
            System.out.println("\nThanks for playing!\n" +
                    "We'll see how well you did in the next stage\n");
            return false;

        }
        return true;
    }

    public int getHealth() {
        return health;
    }

    public void examinate(String letter){
        if(secretWordList.contains(letter) && !this.allLettersList.contains(letter)){
            this.lettersList.add(letter);
            this.allLettersList.add(letter);
        }
        else if(this.allLettersList.contains(letter)){
            System.out.println("You've already guessed this letter.");
        }
        else{
            this.health-=1;
            System.out.println("That letter doesn't appear in the word");
            this.allLettersList.add(letter);
        }
    }
}



public class hang_man {
    public static void main(String[] args) {
        print("HANGMAN");
        Game person = new Game();
        person.generateWord();
        menu(person);
    }

    public static void print(String element){
        System.out.println(element);
    }

    public static void menu(Game person){
        Scanner inp = new Scanner(System.in);
        String text = "";
        while(!Objects.equals(text,"play")){
            print("Type \"play\" to play the game, \"exit\" to quit:");
            text = inp.next();
            if(Objects.equals(text, "exit")){
                break;
            }
            if(Objects.equals(text, "play")){
                person.startgame();
            }

        }
    }
}

