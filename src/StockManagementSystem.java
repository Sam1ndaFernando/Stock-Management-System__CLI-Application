import java.util.Arrays;
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

        clearConsole();
        HomePage();

//        ChangeTheCredentials();
//        SupplierManage();
        //AddSupplier();
        //updateSupplier();
    }

    private static void login() {

        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\t\t LOGIN PAGE \t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println();

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

                case 2 :
                    clearConsole();
                    SupplierManage();
                    break;
                case 3 :
                case 4 :
                    clearConsole();
                    logout(4);
                case 5 :
                    clearConsole();
                    System.exit(5);
            }
        }
    }

    private static void SupplierManage() {
        int option;
        while(true){
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\t\t\t\t\tSUPPLIER MANAGE\t\t\t\t\t\t\t\t\t\t\t|");
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println();
            System.out.println("[1] Add Supplier          "+"                  "+"[2] Update Supplier");
            System.out.println("[3] Delete Supplier       "+"                  "+"[4] View Supplier  ");
            System.out.println("[5] Search Supplier       "+"                  "+"[6] Home Page      ");

            System.out.println();

            System.out.print("Enter an option to continue > ");
            option = input.nextInt();

            switch (option){
                case 1 :
                    clearConsole();
                    AddSupplier();
                    break;

                case 2 :
                    clearConsole();
                    updateSupplier();
                    break;

                case 3 :
                    clearConsole();
                    DeleteSupplier();
                    break;
            }
        }
    }
    private static void DeleteSupplier() {        ///explain
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\tDELETE SUPPLIER\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println();
        while (true){
            System.out.print("Supplier id : ");
            String id = input.next();

            boolean isValidSupplierID = checkSupplierId(id);
            if (isValidSupplierID) {
                for (int i = 0; i < supplies.length; i++) {
                    if (supplies[i][0].equals(id)) {
                        String[][] updateSuppliers = new String[supplies.length-1][2];

                        int count=0;
                        for (int j = 0; j < supplies.length; j++) {
                            if (j!=i) {
                                updateSuppliers[count]=supplies[j];
                                count++;
                            }
                        }
                        supplies = updateSuppliers;
                        System.out.println("deleted successfully! ");
                        System.out.print("Do you want to delete another(Y/N) ? ");
                        String answer = input.next();

                        if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("y")) {
                            clearConsole();
                            DeleteSupplier();
                            break;
                        }
                        if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("n")) {
                            clearConsole();
                            SupplierManage();
                            break;
                        }
                        //return;
                    }
                }
            }else{
                System.out.println("can't find supplier id. try again!");
            }
        }

    }

    private static boolean checkSupplierId(String id) {
        //check Supplier id and return it

        for (int i = 0; i < supplies.length; i++) {
            if (id.equals(supplies[i][0])) {           //explain
                return true;
            }
        }
        return false;
    }
    private static int getIndexString(String id) {
        for (int i = 0; i < supplies.length; i++) {
            if (id.equals(supplies[i][0])) {
                return i;
            }
        }
        return 0;
    }
    private static void updateSupplier() {
        boolean duplicate;

        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\tUPDATE SUPPLIER\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println();
        while (true){
            System.out.print("Supplier ID : ");
            String id = input.next();
            duplicate=checkDuplicate(id);

            String answer;
            String readStudentName= readStudentName(id);
            if (duplicate==false) {
                System.out.println("can't find supplier id. try again!");
            } else if (duplicate==true) {
                System.out.print("Supplier Name : ");
                String newName = input.next();

                // update name on student array
                for (int i = 0; i < supplies.length; i++) {
                    if (id.equals(supplies[i][0])) {
                        supplies[i][1]=newName;
                    }
                }
                System.out.println(Arrays.deepToString(supplies));
                System.out.print("added successfully! ");
                System.out.print("Do you want to update another suplier(Y/N) ? ");
                answer = input.next();

                if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("y")) {
                    clearConsole();
                    updateSupplier();;
                    break;
                }
                if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("n")) {
                    clearConsole();
                    SupplierManage();
                }
            }
        }
    }
    private static String readStudentName(String id) {
        for (int i = 0; i < supplies.length; i++) {
            for (int j = 0; j < supplies[i].length; j++) {
                if (id.equals(supplies[i][0])) {
                    return supplies[i][1];      // check suppliyer id and return his name
                }
            }
        }
        return "";
    }

    private static void AddSupplier() {
        boolean duplicate;

            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\t\t\t\t\tADD SUPPLIER\t\t\t\t\t\t\t\t\t\t\t|");
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println();
        while (true){
            System.out.print("Supplier id : ");
            String id = input.next();

            duplicate = checkDuplicate(id);

            if (duplicate==true) {
                System.out.println("alrady exists. try another supplier id!");
                //break;
            } else if (duplicate==false) {
                System.out.print("Supplier Name : ");
                String name = input.next();

                extendAddSupplier(id,name);
               //extendMarksStudent();
                String answr ;

                do{
                    System.out.println();
                    System.out.print("added successfully! ");
                    System.out.print("Do you want to add another supplier(Y/N) ?");    ///
                    answr=input.next();

                    if (answr.equalsIgnoreCase("Y") || answr.equalsIgnoreCase("y")){
                        clearConsole();
                        AddSupplier();
                        break;
                    }

                    if (answr.equalsIgnoreCase("N") || answr.equalsIgnoreCase("n")) {
                        System.out.println();
                        clearConsole();
                        SupplierManage();
                        break;
                    }
                    //System.out.println("code loku wadii hu@#! ");
                    return;

            }while (true);
            }
        }
    }

//    private static int getUserAnswer() {
//        char answr = input.next().charAt(0);
//
//        if (answr == 'Y' || answr == 'y') {
//            return  1;
//        } else if (answr=='N' || answr=='n') {
//            return 2;
//        }else {
//            return 0;
//        }
//    }

    private static void extendAddSupplier(String id, String name) {              //Assign temp array values
        String[][] temp = new String[supplies.length+1][2];

        for (int i = 0; i < supplies.length; i++) {
            for (int j = 0; j < supplies[i].length; j++) {
                temp[i][j]= supplies[i][j];
            }
        }
        supplies = temp;

        supplies[supplies.length-1][0] = id;   ///// ?????????????????????????   explain
        supplies[supplies.length-1][1]=name;
        System.out.println(Arrays.deepToString(supplies));
    }
    private static boolean checkDuplicate(String id) {
        for (int i = 0; i < supplies.length; i++) {
            if (supplies[i][0].equals(id)) {
                return true;
            }
        }
        return false;
    }
    private static void logout(int i) {
        if (i==4) {
            login();
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

                        System.out.print("Password changed successfully! Do you go to home page (Y/N): ");                        /// continue the progtrammme
                        String goToHomePage = input.next();

                        if (goToHomePage.equalsIgnoreCase("Y") || goToHomePage.equalsIgnoreCase("y")){
                            clearConsole();
                            HomePage();
                            break;
                        }

                        if (goToHomePage.equalsIgnoreCase("N") || goToHomePage.equalsIgnoreCase("n")) {
                            System.out.println();
                            break;
                        }
                        //System.out.println("code loku wadii hu@#! ");
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

