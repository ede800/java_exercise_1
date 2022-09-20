

public class Launcher { 
    
  public static void main(String[] args) {
    System.out.println("Bienvenue");

    //une variable une instance de la classe java.util.Scanner dont le constructeur prendra comme paramètre System.in 
    var scanner = new java.util.Scanner(System.in);

    String input = scanner.nextLine();

    System.out.println("Vous avez tapé " + input);

    //if (input.equals("quit")) stop the program
    if (input.equals("quit")) {
      System.out.println("Au revoir");
      System.exit(0);
    }
    else {
      System.out.println("unknown command");
    }


}
}



