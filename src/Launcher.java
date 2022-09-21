import java.util.Scanner;


public class Launcher { 
  public static void main(String[] args) {
  System.out.println("Bienvenue");
  var scanner = new java.util.Scanner(System.in);
  // une variable de type List<Command> et contenant une instance de chaque implémentation de Command
  java.util.List<Command> commands = new java.util.ArrayList<Command>();
  commands.add(new Freq());
  commands.add(new Fibo());
  commands.add(new Quit());
  commands.add(new Predict());
  String input = null;
  while (!"quit".equals(input = scanner.nextLine())) {
    boolean found = false;
    for (Command command : commands) {
      if (command.name().equals(input)) {
        found = true;
        command.run(scanner);
        break;
      }
    }
    if (!found) {
      System.out.println("unknown command");
    }
  }
}
    
    
}

interface Command {
  public String name();
  public boolean run(Scanner scanner); 
  
}

class Quit implements Command {
  public String name() {
    return "quit";
  }
  public boolean run(Scanner scanner) {
    return true;
  }
}

// refactored Fibo without references of command names
class Fibo implements Command {
  public String name() {
    return "fibo";
  }
  public boolean run(Scanner scanner) {
    System.out.println("Entrez un nombre");
    int n = scanner.nextInt();
    System.out.println(fibonacci(n));
    return false;
  }
  public static int fibonacci(int n) {
    if (n <= 1) return n;
    else return fibonacci(n-1) + fibonacci(n-2);
  }
}

// refactored Freq without references of command names
class Freq implements Command {
  public String name() {
    return "freq";
  }
  public boolean run(Scanner scanner) {
    System.out.println("Entrez un fichier");
    String fileName = scanner.nextLine();
    try {
      java.io.File file = new java.io.File(fileName);
      java.util.Scanner fileScanner = new java.util.Scanner(file);
      java.util.HashMap<String, Integer> wordFrequency = new java.util.HashMap<String, Integer>();
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        String[] words = line.split(" ");
        for(String word : words){
          if(wordFrequency.containsKey(word)){
            wordFrequency.put(word, wordFrequency.get(word) + 1);
          }
          else{
            wordFrequency.put(word, 1);
          }
        }
      }
      //sort the hashmap by value
      java.util.List<java.util.Map.Entry<String, Integer>> sortedWordFrequency = new java.util.ArrayList<java.util.Map.Entry<String, Integer>>(wordFrequency.entrySet());
      java.util.Collections.sort(sortedWordFrequency, new java.util.Comparator<java.util.Map.Entry<String, Integer>>(){
        public int compare(java.util.Map.Entry<String, Integer> entry1, java.util.Map.Entry<String, Integer> entry2){
          return entry2.getValue().compareTo(entry1.getValue());
        }
      });
      String sentence = "";
      sentence += sortedWordFrequency.get(0).getKey() + " ";
      sentence += sortedWordFrequency.get(1).getKey() + " ";
      sentence += sortedWordFrequency.get(2).getKey();
      System.out.println(sentence);
    } catch (java.io.IOException e) {
      System.out.println("Le fichier n'existe pas");
    }
    return false;
  }
}


// refactored Predict without references of command names
class Predict implements Command {
  public String name() {
    return "predict";
  }
  public boolean run(Scanner scanner) {
    System.out.println("Entrez un fichier");
    String fileName = scanner.nextLine();
    try {
      java.io.File file = new java.io.File(fileName);
      java.util.Scanner fileScanner = new java.util.Scanner(file);
      java.util.HashMap<String, java.util.List<String>> wordFrequency = new java.util.HashMap<String, java.util.List<String>>();
      String previousWord = null;
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        String[] words = line.split(" ");
        for(String word : words){
          if(previousWord != null){
            if(wordFrequency.containsKey(previousWord)){
              wordFrequency.get(previousWord).add(word);
            }
            else{
              java.util.List<String> list = new java.util.ArrayList<String>();
              list.add(word);
              wordFrequency.put(previousWord, list);
            }
          }
          previousWord = word;
        }
      }
      System.out.println("Entrez un mot");
      String word = scanner.nextLine();
      if(wordFrequency.containsKey(word)){
        java.util.List<String> list = wordFrequency.get(word);
        System.out.println(list.get((int)(Math.random() * list.size())));
      }
      else{
        System.out.println("Le mot n'existe pas");
      }
    } catch (java.io.IOException e) {
      System.out.println("Le fichier n'existe pas");
    }
    return false;
  }
}
