import java.util.Scanner;

public class StockManagementSystem {
    static Scanner input = new Scanner(System.in);
    static String login[][] = new String[1][2];   //{{"Saminda", "1234"}};
    static String supplies[][] = new String[0][2];
    static String item[][] = new String[0][5];



    public static void main(String[] args) {
        login[0][0] = "saminda";
        login[0][1] = "1234";
        login();
        ChangeTheCredentials();

//        System.out.println("+---------------------------------------------------------------------------------------+");
//        System.out.println("|\t\t\t\t\t\t\t\t\t LOGIN PAGE \t\t\t\t\t\t\t\t\t\t|");
//        System.out.println("+---------------------------------------------------------------------------------------+");
//        System.out.println();
//
//        login();


    }

    private static void login() {

        System.out.print("User name: ");
        String username = input.next();

        boolean isValidUsername = verifyingCredentials(username);

        String password;

        if (isValidUsername) {

            do {
                System.out.print("Password: ");
                password = input.next();

                boolean isValidPassword = verifyingCredentialsPassword(password);
                if (isValidPassword) {
                    clearConsole();
                    HomePage();
                    //System.out.println("PIT thama !");
                } else {
                    System.out.println("password is incorrect. please try again!");
                }
            } while (true);
        } else {
            System.out.println("username is invalid. please try again!");
            login();
        }
    }


    private static boolean verifyingCredentials(String username) {
        for (String[] user : login) {
            if (user[0].equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static boolean verifyingCredentialsPassword(String password) {
        for (String[] user : login) {
            if (user[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    private static void HomePage() {
        int option;
        while (true) {
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\t\t\t WELCOME TO GDSE STOCK MANAGEMENT SYSTEM \t\t\t\t\t\t|");
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println();
            System.out.println("[1] Change the Credentials"+"                  "+"[2] Supplier Manage");
            System.out.println("[3] Stock Manage          "+"                  "+"[4] Log out        ");
            System.out.println("[5] Exit the System       "+"                                        ");

            System.out.println();

            System.out.print("Enter an option to continue > ");
            option = input.nextInt();

            switch (option) {
                case 1 :
                    clearConsole();
                    ChangeTheCredentials();
                    break;
            }
        }
    }

    private static void ChangeTheCredentials() {
        String username = "";
        String password = "";

        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\tCREDENTIAL MANAGE\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println();


        do {
            System.out.print("Please enter the user name to verify it's you : ");
            username = input.next();

            boolean isValidUsername = verifyingCredentials(username);

            if (isValidUsername) {
                System.out.println("Hey " + username);

                do {
                    System.out.print("Enter your current password : ");
                    password = input.next();

                    boolean isValidPassword = verifyingCredentialsPassword(password);

                    if (isValidPassword) {

                        System.out.print("Enter your New password : ");
                        String Newpassword = input.next();

                        login[0][1] = Newpassword;

                        System.out.println("Password changed successfully! Do you go to home page (Y/N): ");  /// continue the progtrammme
                        return;
                    }
                }while (true);
            }
        } while (true) ;
    }
    private static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.equals("Linux")) {
                System.out.print("\033\143");
            } else if (os.equals("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
//handle the exception
            System.err.println(e.getMessage());
        }
    }
}

