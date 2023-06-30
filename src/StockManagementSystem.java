import java.util.Arrays;
import java.util.Scanner;

public class StockManagementSystem {
    static Scanner input = new Scanner(System.in);
    static String login[][] = new String[1][2];   //{{"Saminda", "1234"}};
    static String supplies[][] = new String[0][2];
    static String item[][] = new String[0][6];

    static String inputtedSupplierId="";
    public static void main(String[] args) {
        login[0][0] = "saminda";
        login[0][1] = "1234";


        login();

        clearConsole();
        HomePage();
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
                    clearConsole();
                    StockManage();
                    break;

                case 4 :
                    clearConsole();
                    logout(4);
                case 5 :
                    clearConsole();
                    System.exit(5);
            }
        }
    }
    private static void StockManage() {
        int option;
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\tSTOCK MANAGEMENT\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println();
        System.out.println("[1] Manage Item Categories"+"                  "+"[2] Add Item       ");
        System.out.println("[3] Get Items Supplier Wise"+"                 "+"[4] View Items     ");
        System.out.println("[5] Rank Items Per Unit Price"+"               "+"[6] Home Page      ");
        System.out.println();
        System.out.print("Enter an option to continue > ");
        option = input.nextInt();

        switch (option){
            case 1 :
                clearConsole();
                ManageItemCategories();
                break;

            case 2 :
                clearConsole();
                addItem();
                break;

            case 3 :
                clearConsole();
                GetItemsSupplierWise();

            case 4 :
                clearConsole();
                ViewItems();

            case 5 :
                clearConsole();
                //rankedUnitPrice();

            case 6 :
                clearConsole();
                HomePage();
        }
    }

    private static void ViewItems() {
        System.out.println("+----------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\tVIEW ITEMS BY CATEGORY\t\t\t\t\t\t\t\t\t|");
        System.out.println("+----------------------------------------------------------------------------------------+");
        System.out.println();
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|\tItem ID\t\t|\tDescription\t\t|\tUnit Price\t|\tQty on Hand\t\t|");
        System.out.println("+-----------------------------------------------------------------------+");


        for (int i = 0; i < item.length; i++) {
            String categary = item[i][4];
            System.out.println(categary+" : ");
            System.out.printf("|\t%-10s\t|\t%-18s\b\b|\t%-10s\t|\t%-12s\t|\n",item[i][0],item[i][1],item[i][2],item[i][3]);
            System.out.println("+-----------------------------------------------------------------------+");
        }
        System.out.println();
        System.out.println("Do you want to go stock manage page?(Y/N) ");
        String answer = input.next();
        if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("y")) {
            clearConsole();
            StockManage();
        } else if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("n")) {
            clearConsole();
            ViewItems();
        }
    }
    private static void GetItemsSupplierWise() {
        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\tSEARCH ITEM SUPPLIER WISE\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.println();
        System.out.print("Enter the Supplier ID: ");
        String id = input.next();
        String name = "";

        for (int i = 0; i < supplies.length; i++) {
            if (supplies[i][0].equals(id)) {
                name = supplies[i][1];
                break;
            }
        }
        System.out.println("Supplier Name: " + name);
        System.out.println();

        System.out.println("+-------------------------------------------------------------------+");
        System.out.println("|\tItem ID\t\t|\tDescription\t\t|\tUnit Price\t|\tQty on Hand\t|");
        System.out.println("+-------------------------------------------------------------------+");

        for (int i = 0; i < item.length; i++) {
            if (item[i][5].equals(id)) {
                System.out.printf("|\t%-10s\t|\t%-18s\b\b|\t%-10s\t|\t%-12s\b |\n", item[i][0], item[i][1], item[i][2], item[i][3]);
            }
        }

        System.out.println("+-------------------------------------------------------------------+");
        System.out.println();
        System.out.print("Search successfully! ");
        System.out.print("Do you want to search another item (Y/N)? ");
        String answer = input.next();


        if (answer.equalsIgnoreCase("Y")) {
            clearConsole();
            GetItemsSupplierWise();
        } else if (answer.equalsIgnoreCase("N")) {
            clearConsole();
            StockManage();
        } else {
            System.out.println("Invalid choice. Please try again.");
            GetItemsSupplierWise();
        }
    }
    private static void addItem() {
        boolean hasCategories = false;

        for (int i = 0; i < item.length - 1; i++) {
            if (item[i][4] != null) {
                hasCategories = true;
                break;
            }
        }

        boolean hasSuppliers = false;

        for (int i = 0; i < supplies.length - 1; i++) {
            if (supplies[i][0] != null) {
                hasSuppliers = true;
                break;
            }
        }

        if (!hasCategories) {
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\t\t\t\t\t\tADD ITEM\t\t\t\t\t\t\t\t\t\t\t|");
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println();
            System.out.println("OOPS! It seems that you don't have any item categories in the system.");
            System.out.print("Do you want to add a new item category? (Y/N) ");
            String answer = input.next();

            if (answer.equalsIgnoreCase("Y")) {
                clearConsole();
                AddNewItemCategory();
            } else if (answer.equalsIgnoreCase("N")) {
                clearConsole();
                StockManage();
            }
        } else if (!hasSuppliers) {
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\t\t\t\t\t\tADD ITEM\t\t\t\t\t\t\t\t\t\t\t|");
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println();
            System.out.println("OOPS! It seems that you don't have any item suppliers in the system.");
            System.out.print("Do you want to add a new supplier category? (Y/N) ");
            String answer = input.next();

            if (answer.equalsIgnoreCase("Y")) {
                clearConsole();
                AddSupplier();
            } else if (answer.equalsIgnoreCase("N")) {
                clearConsole();
                StockManage();
            }
        } else {
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\t\t\t\t\t\tADD ITEM\t\t\t\t\t\t\t\t\t\t\t|");
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println();

            System.out.print("Item id: ");
            String id = input.next();
            System.out.println();

            System.out.println("+---------------------------------------------------------------+");
            System.out.println("|\t #\t\t\t|\tSupplier ID\t\t\t|\tSupplier Name\t\t|");
            System.out.println("+---------------------------------------------------------------+");

            for (int i = 0; i < supplies.length; i++) {
                System.out.printf("|\t %-10s |\t%-18s\t|\t%-18s\t|\n", (i + 1), supplies[i][0], supplies[i][1]);
            }

            System.out.println("+---------------------------------------------------------------+");
            System.out.println();
            System.out.print("Enter the Supplier number > ");
            int supplierNumber = input.nextInt();

            if (supplierNumber > 0 && supplierNumber <= supplies.length) {
                String supplierId = supplies[supplierNumber - 1][0];

                System.out.println("+-------------------------------------------+");
                System.out.println("|\t #\t\t\t|\tCategory name\t\t\t|");
                System.out.println("+-------------------------------------------+");

                for (int k = 0; k < item.length; k++) {
                    System.out.printf("|\t %-10s |\t%-18s\t\t|\n", (k + 1), item[k][4]);
                }

                System.out.println("+-------------------------------------------+");
                System.out.println();
                System.out.print("Enter the Category number > ");
                int categoryNumber = input.nextInt();

                if (categoryNumber > 0 && categoryNumber <= item.length) {
                    String category = item[categoryNumber - 1][4];

                    System.out.print("Description: ");
                    String description = input.next();
                    System.out.println();
                    System.out.print("Unit Price: ");
                    String unitPrice = input.next();
                    System.out.println();
                    System.out.print("Qty on hand: ");
                    String qty = input.next();
                    System.out.println();

                    item[item.length - 1][0] = id;
                    item[item.length - 1][1] = description;
                    item[item.length - 1][2] = unitPrice;
                    item[item.length - 1][3] = qty;
                    item[item.length - 1][4] = category;
                    item[item.length - 1][5] = supplierId;

                    System.out.print(item[item.length - 1][0] + ", ");
                    System.out.print(item[item.length - 1][1] + ", ");
                    System.out.print(item[item.length - 1][2] + ", ");
                    System.out.print(item[item.length - 1][3] + ", ");
                    System.out.print(item[item.length - 1][4] + ", ");
                    System.out.print(item[item.length - 1][5]);
                    System.out.println();

                    extendAddItems(id, description, unitPrice, qty, category);

                    System.out.println("Added successfully!");
                    System.out.print("Do you want to add another Item (Y/N)? ");
                    String answer = input.next();

                    if (answer.equalsIgnoreCase("Y")) {
                        clearConsole();
                        addItem();
                    } else if (answer.equalsIgnoreCase("N")) {
                        clearConsole();
                        StockManage();
                    }
                }
            }
        }
    }
    private static void ManageItemCategories() {
        int option;
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\tMANAGE ITEM CATEGORY\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println();
        System.out.println("[1] Add New Item Category "+"             "+"[2] Delete Item Category");
        System.out.println("[3] Update Item Category  "+"             "+"[4] Stock Managment     ");
        System.out.println();

        System.out.print("Enter an option to continue > ");
        option = input.nextInt();

        switch (option){

            case 1 :
                clearConsole();
                AddNewItemCategory();
                break;

            case 2 :
                clearConsole();
                DeleteItemCategory();
                break;

            case 3 :
                clearConsole();
                UpdateItemCategory();
                break;

            case 4 :
                clearConsole();
                StockManage();
                break;
        }
    }

    private static void UpdateItemCategory() {
        /////////////////////////////////////////////////////////////////////////////////////
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\t\tUPDATE ITEM CATEGORY\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println();
        while (true){
            System.out.print("Item Category : ");
            String Category = input.next();
//            boolean duplicate = checkDuplicate(Category);
            boolean checkItemCategory = checkItemCategory(Category);

            if (!checkItemCategory) {
                System.out.println("can't find Item Category. try again!");
            }
            if (checkItemCategory) {
                System.out.print(" Update Item Category :");
                String updateCategory = input.next();
                for (int i = 0; i < item.length; i++) {
                    if (item[i][4].equals(Category)) {
                        item[i][4] = updateCategory;
                    }
                }
            }
            System.out.println("updated successfully! ");
            System.out.print("Do you want to delete another(Y/N) ? ");
            String answer = input.next();

            if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("y")) {
                clearConsole();
                UpdateItemCategory();
                break;
            }
            if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("n")) {
                clearConsole();
                ManageItemCategories();
                break;
            }
        }
    }

    private static void DeleteItemCategory() {
        /////////////////////////////////////////////////////////////////////////////////////
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\t\tDELETE ITEM CATEGORY\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println();

        while(true){
            System.out.print("Item Category : ");
            String id = input.next();

            boolean isValidSupplierID = checkItemCategory(id);  //////////////////////

            if (isValidSupplierID) {
                for (int i = 0; i < item.length; i++) {
                    if (item[i][4].equals(id)) {
                        String[][] deleteItem =  new String[item.length-1][5];
                        int count =0;
                        for (int j = 0; j < item.length; j++) {
                            if (j!=i) {
                                deleteItem[count]=item[j];
                                count++;
                            }
                        }
                        item=deleteItem;
                        System.out.println("deleted successfully! ");
                        System.out.print("Do you want to delete another(Y/N) ? ");
                        String answer = input.next();

                        if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("y")) {
                            clearConsole();
                            DeleteItemCategory();
                            break;
                        }
                        if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("n")) {
                            clearConsole();
                            StockManage();
                            break;
                        }
                    }
                }
            }else{
                System.out.println("can't find Item Category. try again!");
            }
        }
    }
    private static boolean checkItemCategory(String id) {
        for (int i = 0; i < item.length; i++) {
            if (item[i][4].equals(id)) {
                return true;
            }
        }
        return false;
    }
    private static boolean checkItemId(String id){

        for (int i = 0; i < item.length; i++) {
            if (item[i][0].equals(id)) {
                return true;
            }
        }
        return false;
    }
    private static void AddNewItemCategory() {
        boolean duplicate = true;
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\t\tADD ITEM CATEGORY\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println();

        while (duplicate == true) {
            System.out.print("Enter the new item category : ");
            String category = input.next();

            extendAddItems("","","","",category);
            System.out.println();
            System.out.println("For test only !!! "+Arrays.toString(item));

            System.out.print("Added successfully! ");
            System.out.print("Do you want to add another category(Y/N) ? ");
            String answer = input.next();

            System.out.println(item.length);
            if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("y")) {
                clearConsole();
                AddNewItemCategory();
                break;
            }
            if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("n")) {
                clearConsole();
                StockManage();
                break;
            }
        }
    }
    private static void extendAddItems(String id, String desc, String unitPrice, String Qty, String Category) {
        String[][] temp = new String[item.length + 1][6];

        for (int i = 0; i < item.length; i++) {
            for (int j = 0; j < item[i].length; j++) {
                temp[i][j] = item[i][j];
            }
        }

        temp[item.length][0] = id;
        temp[item.length][1] = desc;
        temp[item.length][2] = unitPrice;
        temp[item.length][3] = Qty;
        temp[item.length][4] = Category;
        temp[item.length][5] = "";

        item = temp;
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

                case 4 :
                    clearConsole();
                    ViewSupplier();
                    break;

                case 5 :
                    clearConsole();
                    SearchSupplier();
                    break;

                case 6 :
                    clearConsole();
                    HomePage();
                    break;
            }
        }
    }

    private static void SearchSupplier() {
        String answer;
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\tSEARCH SUPPLIER\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println();
        while (true) {
            System.out.print("Supplier ID: ");
            answer = input.next();

            boolean found = false;

            for (int i = 0; i < supplies.length; i++) {
                if (answer.equals(supplies[i][0])) {
                    System.out.println("Supplier Name: " + supplies[i][1]);
                    found = true;
                    System.out.print("Search successfully! ");
                    System.out.print("Do you want to Search another(Y/N) ? ");
                    answer = input.next();

                    if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("y")) {
                        clearConsole();
                        SearchSupplier();
                        break;
                    }
                    if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("n")) {
                        clearConsole();
                        SupplierManage();
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("can't find supplier id. try again!");
            } else {
                break;
            }
        }
    }
    private static void ViewSupplier() {
        String answer;
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\tVIEW SUPPLIER\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");

        while (true){
            System.out.println();
            System.out.println("+-----------------------------------------------+");
            System.out.println("|\tSupplier ID\t\t|\t\tSupplier Name\t\t|");
            System.out.println("+-----------------------------------------------+");
            for (int i = 0; i < supplies.length; i++) {
                System.out.printf("|\t%-12s\t|\t\t%-14s\t\t|\n", supplies[i][0], supplies[i][1]);
            }
            System.out.println("+-----------------------------------------------+");

            System.out.println();
            System.out.print("Do you want to go supplier manage page(Y/N) ? ");
            answer = input.next();

            if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("y")) {
                clearConsole();
                SupplierManage();
                break;
            }
            if (answer == null) {
                if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("n")) {
                    clearConsole();
                    break;
                }
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
                        }//return;
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
        //sam1nda Fernando
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\tUPDATE SUPPLIER\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println();
        while (true){
            System.out.print("Supplier ID : ");
            String id = input.next();
            duplicate=checkDuplicate(id);

            String answer;
            if (duplicate==false) {
                System.out.println("can't find supplier id. try again!");
            } else if (duplicate==true) {
                System.out.print("Supplier Name : ");
                String newName = input.next();

                for (int i = 0; i < supplies.length; i++) {
                    if (id.equals(supplies[i][0])) {
                        supplies[i][1]=newName;
                    }
                }
                System.out.println(Arrays.deepToString(supplies));
                System.out.print("added successfully! ");
                System.out.print("Do you want to update another suplier(Y/N) ? ");
                answer = input.next();
                //sam1nda Fernando
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
    //sam1nda Fernando
    private static void AddSupplier() {
        boolean duplicate;

        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\t\t\t\tADD SUPPLIER\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println();
        while (true){
            System.out.print("Supplier id : ");
            String id = input.next();
            inputtedSupplierId=id;

            duplicate = checkDuplicate(id);

            if (duplicate) {
                System.out.println("already exists. try another supplier id!");
                //break;
            } else if (!duplicate) {
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
                    //System.out.println("code wadii hu@#! ");
                    return;
                }while (true);
            }
        }
    }
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
                    //sam1nda fernando
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

