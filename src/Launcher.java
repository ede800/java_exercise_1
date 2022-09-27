import java.util.*;


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
    






















