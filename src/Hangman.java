import java.util.*;


class Game{
    private ArrayList<String> secretWord = new ArrayList<>();
    private ArrayList<String> letters = new ArrayList<>();
    private ArrayList<String> allLetters = new ArrayList<>();
    private int health = 8;
    public static final int MIN_HEALTH = 0;

    public void generateWord(){
        ArrayList<String> test = new ArrayList<>();
        Random index = new Random();
        test.add("python");
        test.add("kotlin");
        test.add("java");
        test.add("javascript");
        String[] word = test.get(index.nextInt(4)).split("");
        this.secretWord.addAll(Arrays.asList(word));
    }

    public void printWord(){
        int num = 0;
        for (String s : secretWord) {
            if (this.letters.contains(s)) {
                System.out.print(s);
            } else {
                System.out.print("-");
                num += 1;
            }
        }
        if(num==0){
            System.out.println("\nThanks for playing!\n" +
                    "We'll see how well you did in the next stage\n");
        }
    }

    public int getHealth() {
        return health;
    }

    public void examinate(String letter){
        if(secretWord.contains(letter) && !this.allLetters.contains(letter)){
            this.letters.add(letter);
            this.allLetters.add(letter);
        }
        else if(this.allLetters.contains(letter)){
            System.out.println("You've already guessed this letter.");
        }
        else{
            this.health-=1;
            System.out.println("That letter doesn't appear in the word");
            this.allLetters.add(letter);
        }
    }
}



public class Hangman {
    public static void main(String[] args) {
        String alfabet = "qwertyuiopasdfghjklzxcvbnm";
        print("HANGMAN");
        Game person = new Game();
        person.generateWord();
        Scanner input = new Scanner(System.in);
        menu();
        while (true) {
            if(person.getHealth() == Game.MIN_HEALTH){
                System.exit(0);
            }
            person.printWord();
            print("\nGuess the word:");
            String letter = input.next();
            if (!(letter.length() ==1)){
                print("You should input a single letter.");
                continue;
            }

            else if (!alfabet.contains(letter)){
                print("Please enter a lowercase English letter.");
                continue;
            }
            person.examinate(letter);

        }
    }

    public static void print(String element){
        System.out.println(element);
    }

    public static void menu(){
        while(true){
            Scanner inp = new Scanner(System.in);
            print("Type \"play\" to play the game, \"exit\" to quit:");
            String text = inp.next();
            if(Objects.equals(text, "play")){
                break;
            }
            else if(Objects.equals(text, "exit")){
                System.exit(0);
            }
        }
    }
}
