package SortTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

class Sort{
    private Scanner input = new Scanner(System.in);
    private ArrayList<String> arguments;
    private ArrayList<Integer> forNums = new ArrayList<>();
    private ArrayList<String> forString = new ArrayList<>();
    private ArrayList<Integer> forIndex = new ArrayList<>();
    private ArrayList<Integer> weedOutInt = new ArrayList<>();
    private ArrayList<String> listArg = new ArrayList<>();

    private Map<String, String> chooseDataType = new HashMap<String, String>();



    public void chooseSorting() throws FileNotFoundException {
        switch (chooseDataType.get("-sortingType")){
            case "naturally":
                naturally();
                break;
            case "byCount":
                count();
                break;
        }
    }

    private void timeInputWord() throws FileNotFoundException {
        if (!workWithInputFile()){
            if (Objects.equals(chooseDataType.get("-dataType"), "line")){
                do {
                    String anything = input.nextLine();
                    forString.add(anything);
                    forIndex.add(anything.length());
                }while (input.hasNext());
            }
            else if (Objects.equals(chooseDataType.get("-dataType"), "word")){
                do {
                    String anything = input.next();
                    forString.add(anything);
                    forIndex.add(anything.length());
                }while (input.hasNext());
            }
        }
        chooseSorting();
    }


    private void timeInputInt(){
        String anything = null;
        try {
            do {
                if (workWithInputFile()){
                    break;
                }
                else{
                    anything = input.next();
                    forNums.add(Integer.parseInt(anything));
                }

            }while (input.hasNext());
            chooseSorting();
        }catch (NumberFormatException e){
            System.out.println(anything + " is not a long. It will be skipped.");
        }
        catch (FileNotFoundException e){
            System.out.println("Error");
        }

    }

    private boolean workWithInputFile() throws FileNotFoundException {
        if (arguments.contains("-inputFile")){
            String seperator = File.separator;
            String path = seperator + "home" + seperator + "artem" + seperator + "IdeaProjects" + seperator + "java_facult" + seperator + "src" + seperator + "SortTools" + seperator  + (listArg.get(listArg.indexOf("-inputFile")+1)) + ".txt";
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            if(arguments.contains("line")){
                while (scanner.hasNextLine()){
                    forString.add(scanner.nextLine());
                }
            }
            else if (arguments.contains("word")){
                while (scanner.hasNext()){
                    forString.add(scanner.next());
                }
            }
            else if (arguments.contains("long")){
                while (scanner.hasNext()){
                    forNums.add(Integer.parseInt(scanner.next()));
                }
            }
            scanner.close();
            return true;
        }
        return false;

    }

    public void chooseType() throws FileNotFoundException {
        switch (chooseDataType.get("-dataType")){
            case "word":
                timeInputWord();
                break;
            case "long":
                timeInputInt();
                break;
            case "line":
                timeInputWord();
                break;
        }

    }

    private void naturally() throws FileNotFoundException {
        if(forNums.size()!=0){
            Collections.sort(forNums);

            if(arguments.contains("-outputFile")){
                File file = new File("output.txt");
                PrintWriter pw = new PrintWriter(file);
                pw.print("Sorted data: ");
                for (int i: forNums){
                    pw.print(i + " ");
                }
                pw.close();
            }
            else {
                System.out.print("Sorted data: ");
                for (int i: forNums){
                    System.out.print(i + " ");
                }
            }

        }
        else if (forString.size()!=0){
            Map<String,Integer> sortStr = new HashMap<>();
            for (String value : forString) {
                sortStr.put(value,value.length());
            }
            ArrayList<Integer> sortValue = new ArrayList<Integer>(sortStr.values());
            Collections.sort(sortValue);
            for (int i:sortValue){
                for (String f:sortStr.keySet()){
                    if (sortStr.get(f)==i){
                        if(!arguments.contains("-outputFile")){
                            if (arguments.contains("-outputFile")){
                                File file = new File("output.txt");
                                PrintWriter pw = new PrintWriter(file);
                                pw.println(f);
                                pw.close();
                            }
                            else{
                                System.out.println(f);
                            }

                        }
                    }
                }
            }
        }

    }

    private void count() throws FileNotFoundException {
        if(forNums.size()!=0){
            Map<Integer,Integer> sortInt = new HashMap<>();
            for(int m:forNums){
                if(!weedOutInt.contains(m)){
                    weedOutInt.add(m);
                }
            }
            for(int i:weedOutInt){
                sortInt.put(i,Collections.frequency(forNums,i));
            }
            sortValue(sortInt, forNums.size());
        }
    }



    private void sortValue(Map<Integer,Integer> notSortMap, int size) throws FileNotFoundException {
        ArrayList<Integer> count = new ArrayList<>(notSortMap.values());
        Collections.sort(count);
        System.out.println(count);
        Map<Integer,Integer> newSort = new HashMap<>();
        for (int i:count){
            for (int f:notSortMap.keySet()){
                if (i==notSortMap.get(f) && (!newSort.containsKey(f) || newSort.get(f)!=i)){
                    if (arguments.contains("-outputFile")){
                        File file = new File("output.txt");
                        PrintWriter pw = new PrintWriter(file);
                        pw.println(f + ": " + i + "time(s)," + findProcent(i, size));
                        pw.close();
                    }
                    else {
                        System.out.println(f + ": " + i + "time(s)," + findProcent(i, size));
                    }

                    newSort.put(f,i);
                }
            }
        }
    }


    private String findProcent(int countElement, int countAll){
        int procent = countElement*100/countAll;
        return procent + "%";
    }


    public void menu(String[] args) throws FileNotFoundException {
        createArgInList();
        if(examination(args)){
            chooseDataType.put(args[0],args[1]);
            chooseDataType.put(args[2],args[3]);
            chooseType();
        }

    }

    private void createArgInList(){
        listArg.add("-sortingType");
        listArg.add("-dataType");
        listArg.add("long");
        listArg.add("line");
        listArg.add("word");
        listArg.add("naturally");
        listArg.add("byCount");
        listArg.add("-inputFile");
        listArg.add("-outputFile");
    }

    public boolean examination(String[] args){
        arguments = new ArrayList<String>(List.of(args));
        if (arguments.indexOf("-sortingType")==arguments.size()-1
                || arguments.indexOf("-sortingType") == arguments.indexOf("-dataType")-1
                || arguments.indexOf("-sortingType") == arguments.indexOf("-inputFile")-1
                || arguments.indexOf("-sortingType") == arguments.indexOf("-outFile")-1){

            System.out.println("No sorting type defined!");
            return false;

        }
        else if(arguments.indexOf("-dataType")==arguments.size()-1
                || arguments.indexOf("-dataType") == arguments.indexOf("-sortingType")-1
                || arguments.indexOf("-dataType") == arguments.indexOf("-inputFile")-1
                || arguments.indexOf("-dataType") == arguments.indexOf("-outputFile")-1){
            System.out.println("No data type defined! ");
            return false;
        }
        for (String s:arguments){
            if (!listArg.contains(s)){
                if (listArg.contains("-inputFile") || listArg.contains("-outputFile")){
                    listArg.add(args[args.length-1]);
                    return true;
                }
                System.out.println(s + " is not a valid parameter. It will be skipped");
                return false;
            }
        }

        return true;
    }

}

public class sorttools {
    public static void main(String[] args) throws FileNotFoundException {
        Sort person = new Sort();
        person.menu(args);




    }
}
