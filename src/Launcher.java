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





class Fibo implements Command {
  public String name() {
    return "fibo";
  }
  public boolean run(Scanner scanner) {
    System.out.println("Donnez moi un nombre");
    String number = scanner.nextLine();
    try {
      int numberInt = Integer.parseInt(number);
      System.out.println("Le nombre de fibonacci est " + fibonacci(numberInt));
    } catch (NumberFormatException e) {
      System.out.println("Vous n'avez pas entré un nombre");
    }
    return false;
  }
  private static int fibonacci(int n) {
    if (n <= 1) return n;
    else return fibonacci(n-1) + fibonacci(n-2);
  }
}

class Freq implements Command {
  public String name() {
    return "freq";
  }
  public boolean run(Scanner scanner) {
    System.out.println("Donnez moi le chemin de votre fichier");
    String path = scanner.nextLine();
    try {
      String content = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(path)));
      content = content.replaceAll("[^a-zA-Z0-9\\s]", " ");
      content = content.toLowerCase();
      String[] words = content.split("\\s+");
      if(words.length == 0){
        System.out.println("Le fichier est vide");
      }
      else{
        java.util.HashMap<String, Integer> wordFrequency = new java.util.HashMap<String, Integer>();
        for(String word : words){
          if(wordFrequency.containsKey(word)){
            wordFrequency.put(word, wordFrequency.get(word) + 1);
          }
          else{
            wordFrequency.put(word, 1);
          }
        }
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
      }
    } catch (java.io.IOException e) {
      System.out.println("Le fichier n'existe pas");
    }
    return false;
  }
}


class Predict implements Command {
  public String name() {
    return "predict";
  }
  public boolean run(Scanner scanner) {
    System.out.println("Donnez moi le chemin de votre fichier");
    String path = scanner.nextLine();
    try {
      String content = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(path)));
      content = content.replaceAll("[^a-zA-Z0-9\\s]", " ");
      content = content.toLowerCase();
      String[] words = content.split("\\s+");

      if(words.length == 0){
        System.out.println("Le fichier est vide");
      }
      else{
        String word = scanner.nextLine();
        word = word.toLowerCase();
        // check if the word is not in the file print error
        if(!java.util.Arrays.asList(words).contains(word)){
          System.out.println("Le mot n'est pas dans le fichier");
        }
        else{
          //list named mostprobablewords
          java.util.List<String> mostProbableWords = new java.util.ArrayList<String>();
          //add word in mostprobablewords
          mostProbableWords.add(word);

          //check the most probable word after the last word of mostprobablewords and add it to the list
          for(int i = 0; i < 20; i++){
            String lastWord = mostProbableWords.get(mostProbableWords.size() - 1);
            java.util.HashMap<String, Integer> wordFrequency = new java.util.HashMap<String, Integer>();
            for(int j = 0; j < words.length - 1; j++){
              if(words[j].equals(lastWord)){
                if(wordFrequency.containsKey(words[j + 1])){
                  wordFrequency.put(words[j + 1], wordFrequency.get(words[j + 1]) + 1);
                }
                else{
                  wordFrequency.put(words[j + 1], 1);
                }
              }
            }
            java.util.List<java.util.Map.Entry<String, Integer>> sortedWordFrequency = new java.util.ArrayList<java.util.Map.Entry<String, Integer>>(wordFrequency.entrySet());
            java.util.Collections.sort(sortedWordFrequency, new java.util.Comparator<java.util.Map.Entry<String, Integer>>(){
              public int compare(java.util.Map.Entry<String, Integer> entry1, java.util.Map.Entry<String, Integer> entry2){
                return entry2.getValue().compareTo(entry1.getValue());
              }
            });
            mostProbableWords.add(sortedWordFrequency.get(0).getKey());
          }
          // print the list
          String sentence = "";
          for(String w : mostProbableWords){
            sentence += w + " ";
          }
          System.out.println(sentence);

        }           
        
      }
    } catch (java.io.IOException e) {
      System.out.println("Le fichier n'existe pas");
    }
    return false;
  }
}
  
  







