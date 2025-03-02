import java.util.*;
import java.util.stream.Collectors;

public class OrderAppService {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        OrderAppService app = new OrderAppService();
        //Application title
        System.out.println("\n\n\n" +
                "   ____          _             __  __                                                   _      _____           _                 \n" +
                "  / __ \\        | |           |  \\/  |                                                 | |    / ____|         | |                \n" +
                " | |  | |_ __ __| | ___ _ __  | \\  / | __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_  | (___  _   _ ___| |_ ___ _ __ ___  \n" +
                " | |  | | '__/ _` |/ _ \\ '__| | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __|  \\___ \\| | | / __| __/ _ \\ '_ ` _ \\ \n" +
                " | |__| | | | (_| |  __/ |    | |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_   ____) | |_| \\__ \\ ||  __/ | | | | |\n" +
                "  \\____/|_|  \\__,_|\\___|_|    |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__| |_____/ \\__, |___/\\__\\___|_| |_| |_|\n" +
                "                                                         __/ |                                        __/ |                      \n" +
                "                                                        |___/                                        |___/                       \n");

        AppData.loadData();
        app.displayMainMenu();

    }

    public void displayMainMenu(){
        System.out.println("-----------------------------------");
        System.out.println("Main Menu");
        System.out.println("-----------------------------------");

    }



}
