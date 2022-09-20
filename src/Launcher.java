

public class Launcher { 

  public static int fibonacci(int n) {
    if (n <= 1) return n;
    else return fibonacci(n-1) + fibonacci(n-2);
  }
  

    
    
  public static void main(String[] args) {
    System.out.println("Bienvenue");

    //une variable une instance de la classe java.util.Scanner dont le constructeur prendra comme paramètre System.in 
    var scanner = new java.util.Scanner(System.in);


    //while input != quit or fibo  print "unknown command" and ask for input again
    String input = null;
    while (!"quit".equals(input = scanner.nextLine())) {
      if(input.equals("fibo")){
        Integer n = null;
        System.out.println("Donnez moi un nombre");
        String number = scanner.nextLine();
        try{
          int numberInt = Integer.parseInt(number);
          System.out.println("Le nombre de fibonacci est " + fibonacci(numberInt));
        } catch (NumberFormatException e) {
          System.out.println("Vous n'avez pas entré un nombre");
        }
      }
      else {
        System.out.println("unknown command");
      }
    }

  }
}
    







