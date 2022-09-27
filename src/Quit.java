import java.util.Scanner;

class Quit implements Command {
    public String name() {
      return "quit";
    }
    public boolean run(Scanner scanner) {
      return true;
    }
  }
  
