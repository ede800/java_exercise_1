import java.util.Scanner;

// public class Launcher { 

//   public static int fibonacci(int n) {
//     if (n <= 1) return n;
//     else return fibonacci(n-1) + fibonacci(n-2);
//   }
  

    
    
//   public static void main(String[] args) {
//     System.out.println("Bienvenue");

//     //une variable une instance de la classe java.util.Scanner dont le constructeur prendra comme paramètre System.in 
//     var scanner = new java.util.Scanner(System.in);


//     //while input != quit or fibo  print "unknown command" and ask for input again
//     String input = null;
//     while (!"quit".equals(input = scanner.nextLine())) {
//       if(input.equals("fibo")){
//         Integer n = null;
//         System.out.println("Donnez moi un nombre");
//         String number = scanner.nextLine();
//         try{
//           int numberInt = Integer.parseInt(number);
//           System.out.println("Le nombre de fibonacci est " + fibonacci(numberInt));
//         } catch (NumberFormatException e) {
//           System.out.println("Vous n'avez pas entré un nombre");
//         }
//       }
//       else if(input.equals("freq")){
//         System.out.println("Donnez moi le chemin de votre fichier");
//         //lire le contenu du fichier et afficher les 3 mots les plus fréquents
//         String path = scanner.nextLine();
//         try{
//           String content = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(path)));
//           //remove all punctuation by replacing it with a space
//           content = content.replaceAll("[^a-zA-Z0-9\\s]", " ");
//           //lowercase all the words
//           content = content.toLowerCase();
//           //split the string into an array of words
//           String[] words = content.split("\\s+");
//           //is blank
//           if(words.length == 0){
//             System.out.println("Le fichier est vide");
//           }

//           else{
//             //create a hashmap to store the words and their frequency
//             java.util.HashMap<String, Integer> wordFrequency = new java.util.HashMap<String, Integer>();
//             for(String word : words){
//               if(wordFrequency.containsKey(word)){
//                 wordFrequency.put(word, wordFrequency.get(word) + 1);
//               }
//               else{
//                 wordFrequency.put(word, 1);
//               }
//             }
//             //sort the hashmap by value
//             java.util.List<java.util.Map.Entry<String, Integer>> sortedWordFrequency = new java.util.ArrayList<java.util.Map.Entry<String, Integer>>(wordFrequency.entrySet());
//             java.util.Collections.sort(sortedWordFrequency, new java.util.Comparator<java.util.Map.Entry<String, Integer>>(){
//               public int compare(java.util.Map.Entry<String, Integer> entry1, java.util.Map.Entry<String, Integer> entry2){
//                 return entry2.getValue().compareTo(entry1.getValue());
//               }
//             });
//             String sentence = "";
//             sentence += sortedWordFrequency.get(0).getKey() + " ";
//             sentence += sortedWordFrequency.get(1).getKey() + " ";
//             sentence += sortedWordFrequency.get(2).getKey();
//             System.out.println(sentence);
//           }

//         } catch (java.io.IOException e) {
//           System.out.println("Le fichier n'existe pas");
//         }
//       }
//       else {
//         System.out.println("unknown command");
//       }
//     }

//   }
// }
interface Command {
  public String name();
  public boolean run(Scanner scanner); 
  public static void main(String[] args) {
    System.out.println("Bienvenue");
    var scanner = new java.util.Scanner(System.in);
    Command[] commands = {
      new Quit(),
      new Fibo(),
      new Freq()
    };
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
    Integer n = null;
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
  public static int fibonacci(int n) {
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







