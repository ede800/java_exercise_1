import java.util.*;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans le launcher");

        Scanner scanner = new Scanner(System.in);

        List<Command> commands = new ArrayList<>();
        commands.add(new Quit());
        commands.add(new Fibo());
        commands.add(new Freq());
        commands.add(new Predict());

        String res;
        boolean quit;
        while (true){
            res = scanner.nextLine().toLowerCase();
            quit = false;
            for (Command command : commands){
                if (command.name().equalsIgnoreCase(res)){
                    if (command.run(scanner)){
                        return;
                    }
                    quit = true;
                }
            }
            if (!quit){
                System.out.println("Unknown command");
            }
        }
    }
}
    






















