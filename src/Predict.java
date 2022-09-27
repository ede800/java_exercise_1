import java.util.Scanner;


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
    
    
  