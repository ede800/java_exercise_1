import java.util.Scanner;

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
