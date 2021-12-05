package Tictactoe;

import java.util.*;

class Game{
    private String[] field = new String[]{"_","_","_","_","_","_","_","_","_"};
    private final int[][] winSituation = new int[][]{{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6}};
    private final Map<String,Integer> coordinate = new HashMap<String, Integer>();
    private String playerWin = "";


    public boolean gameSituation = true;

    public void createDictionary(){
        coordinate.put("1 1", 0);
        coordinate.put("1 2", 1);
        coordinate.put("1 3", 2);
        coordinate.put("2 1", 3);
        coordinate.put("2 2", 4);
        coordinate.put("2 3", 5);
        coordinate.put("3 1", 6);
        coordinate.put("3 2", 7);
        coordinate.put("3 3", 8);

    }

    private String getField() {
        return "---------\n" +
                "| " + field[0] + " " + field[1] + " " + field[2] + " |\n" +
                "| " + field[3] + " " + field[4] + " " + field[5] + " |\n" +
                "| " + field[6] + " " + field[7] + " " + field[8] + " |\n" +
                "---------";
    }

    public void setField(String coord) {
        int num = 0;
        if(!coordinate.containsKey(coord)){
            System.out.println("Coordinates should be from 1 to 3!");
            return;
        }
        for (String s : field) {
            if (Objects.equals(s, "_")){
                num+=1;
            }
        }
        if (!Objects.equals(this.field[coordinate.get(coord)], "_")) {
            System.out.println("This cell is occupied! Choose another one!");
            return;
        }

        if(num%2==0){
            this.field[coordinate.get(coord)] = "O";
        }
        else if (num%2==1){
            this.field[this.coordinate.get(coord)] = "X";
        }
        System.out.println(getField());
    }

    public boolean examination(){
        if (shadowCheck().size()>1 || numberOfSivols()){
            System.out.println("Impossible");
            return false;
        }
        else if(checkEmptiness()==0){
            System.out.println("Draw");
            return false;
        }
        else if(playerWin.length()==1){
            System.out.println(playerWin + " Win");
            return false;
        }
        return true;
    }

    private int checkEmptiness(){
        int numb = 0;
        for (String i: field){
            if(Objects.equals(i, "_")){
                numb+=1;
            }
        }
        return numb;
    }

    private boolean numberOfSivols(){
        int number_X = 0;
        int number_O = 0;
        for (String s : field) {
            if (Objects.equals(s, "X")) {
                number_X += 1;
            } else if (Objects.equals(s, "O")) {
                number_O += 1;
            }
        }
        return Math.abs(number_O - number_X) > 1;
    }

    private ArrayList<String> shadowCheck(){
        String[] simbols = new String[]{"X","O"};
        ArrayList<String> win_simbols = new ArrayList<>();
        for (int[] ints : this.winSituation) {
            for (String simbol : simbols) {
                if (Objects.equals(field[ints[0]], simbol) && Objects.equals(field[ints[1]], simbol) && Objects.equals(field[ints[2]], simbol)) {
                    win_simbols.add(simbol);
                }
            }
        }
        if(win_simbols.size()==1){
            playerWin = win_simbols.get(0);
        }
        return win_simbols;
    }
}


public class tictaсtoe {
    public static void main(String[] args) {
        Game person = new Game();
        person.createDictionary();
        Scanner input = new Scanner(System.in);
        boolean situation = true;
        while (situation){
            person.setField(input.nextLine());
            situation = person.examination();
        }
    }
}
