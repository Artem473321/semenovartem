package DinnerParty;

import java.util.*;

class Party{
    private ArrayList<String> names = new ArrayList<String>();
    private HashMap<String,Float> dinner = new HashMap<>();
    private final Scanner input = new Scanner(System.in);

    private final Random rand = new Random();

    String luckyMan;

    public void addPeople(){
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter the number of friends joining (including you):");
        int countNames = inp.nextInt();
        if(countNames>0){
            System.out.println("Enter the name of every friend (including you), each on a new line:");
            for(int i = 0;i<countNames;i++){
                this.names.add(inp.next());
            }
            addDinnerMoney(countNames);
        }
        else {
            System.out.println("No one is joining for the party");
        }

    }

    private void withLucky(int num, float payMans){
        this.luckyMan = names.get(rand.nextInt(this.names.size()));
        System.out.println( this.luckyMan + " is the lucky one!");
        payMans = num/(this.names.size()-1);
    }

    private void addDinnerMoney(int countNames){

        System.out.println("Enter the total amount:");
        int num = input.nextInt();
        System.out.println("Do you want to use the \"Who is lucky?\" feature? Write Yes/No:");
        String lucky = input.next();
        float payMans = num/this.names.size();
        if (Objects.equals(lucky, "Yes")){
            withLucky(num, payMans);
        }
        else{
            System.out.println("No one is going to be lucky");

        }

        for (String name : names) {
            if (!Objects.equals(name, this.luckyMan)){
                this.dinner.put(name, payMans);
            }
            else {
                float n = 0;
                this.dinner.put(name, n);
            }

        }
    }

    public String getDinner() {
        return dinner.toString();
    }
}


public class Main {
    public static void main(String[] args) {
        Party objects = new Party();
        objects.addPeople();
        System.out.println(objects.getDinner());
    }
}

