import java.util.Scanner;

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
