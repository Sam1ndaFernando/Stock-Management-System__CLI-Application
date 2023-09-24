import java.util.Scanner;

class Demo {
    static Scanner input = new Scanner(System.in);
    static String username = "Saminda";
    static String password = "1234";

    static String[][] supplier_array = new String[0][2];

    static String[] category_array = new String[0];

    static String[][] item_array = new String[0][6];



    public static void main(String[] args) {
        loadLoginPage();
        dashboard();
    }

    private static void loadLoginPage() {
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                                LOGIN PAGE                                 |");
        System.out.println("+---------------------------------------------------------------------------+");

        System.out.print("User Name : ");
        String inputted_username = input.next();

        String inputted_password = null;

        while (!inputted_username.equals(username)) {
            System.out.println("User name invalid. please try again!");
            System.out.print("\nUser Name : ");
            inputted_username = input.next();
        }

        System.out.print("Password : ");
        inputted_password = input.next();

        while (!inputted_password.equals(password)) {
            System.out.println("Password is incorrect. please try again!");
            System.out.print("\nPassword : ");
            inputted_password = input.next();
        }

        dashboard();

    }

    private static void dashboard() {
        clearConsole();

        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                 WELCOME TO IJSE STOCK MANAGEMENT SYSTEM                   |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        System.out.printf("%-48s%-48s\n", "[1] Change the credentials", "[2] Supplier Manage");
        System.out.printf("%-48s%-48s\n", "[3] Stock Manage", "[4] Logout");
        System.out.println("[5] Exit the system ");

        int option = 0;
        do {
            System.out.print("\nEnter an option to continue > ");
            option = input.nextInt();
        } while (!(option > 0 && option < 6));

        switch (option) {
            case 1:
                changeTheCredentials();
            case 2:
                supplierManage();
            case 3:
                stockManage();
            case 4:
                logout();
            case 5:
                exitTheSystem();
        }
    }

    private static void exitTheSystem() {
        clearConsole();
        System.exit(0);
    }

    private static void logout() {
        clearConsole();
        loadLoginPage();
    }

    private static void changeTheCredentials() {
        clearConsole();

        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                            CREDENTIAL MANAGE                              |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        System.out.print("Please enter the user name to verify it's you : ");
        String inputted_username = input.next();

        while (!inputted_username.equals(username)) {
            System.out.println("Invalid user name. Try again!\n");
            System.out.print("Please enter the user name to verify it's you : ");
            inputted_username = input.next();
            System.out.println();
        }

        System.out.println("Hey " + username);

        System.out.print("Enter your current password : ");
        String inputted_password = input.next();

        while (!inputted_password.equals(password)) {
            System.out.println("Incorrect password. Try again!\n");
            System.out.print("Enter your current password : ");
            inputted_password = input.next();
            System.out.println();
        }

        System.out.print("Enter your new password : ");
        inputted_password = input.next();
        password = inputted_password;

        System.out.println("Password changed successfully! Do you want to go home page (Y/N) : ");
        String answer = input.next();

        if (answer.equals("y") || answer.equals("Y")) {
            dashboard();
        }
    }

    private static void supplierManage() {
        clearConsole();

        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                             SUPPLIER MANAGE                               |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        System.out.printf("%-48s%-48s\n", "[1] Add supplier", "[2] Update supplier");
        System.out.printf("%-48s%-48s\n", "[3] Delete supplier", "[4] View suppliers");
        System.out.printf("%-48s%-48s\n", "[5] Search supplier", "[6] Home Page");

        int option = 0;
        do {
            System.out.print("\nEnter an option to continue > ");
            option = input.nextInt();
        } while (!(option > 0 && option < 7));


        switch (option) {
            case 1:
                addSupplier("SupplierManage");
            case 2:
                updateSupplier();
            case 3:
                deleteSupplier();
            case 4:
                viewSuppliers();
            case 5:
                searchSupplier();
            case 6:
                homepage();
        }
    }

    private static void homepage() {
        clearConsole();
        dashboard();
    }

    //---------------------------  manage supplier  -----------------------------------------

    private static void searchSupplier() {
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                               SEARCH SUPPLIER                              |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        String option = "y";
        while (option.equals("y") || option.equals("Y")) {
            System.out.print("Supplier ID : ");
            String inputted_supplier_id = input.next();

            boolean validity = checkSupplierValidity(inputted_supplier_id);

            while (!validity) {
                System.out.println("Can't find supplier id. Try again!\n");
                System.out.print("Supplier ID : ");
                inputted_supplier_id = input.next();
                validity = checkSupplierValidity(inputted_supplier_id);
            }

            String name = getSupplierName(inputted_supplier_id);
            System.out.println("Supplier Name : " + name);

            System.out.print("Found Successfully! Do you want to find another supplier (Y/N) : ");
            option = input.next();
            System.out.println();

        }
        clearConsole();
        supplierManage();

    }

    private static void viewSuppliers() {
        clearConsole();
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                              VIEW SUPPLIERS                               |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        System.out.println("+----------------------+----------------------+");
        System.out.printf("|%-22s|%-22s|\n", "SUPPLIER ID", "SUPPLIER NAME");
        System.out.println("+----------------------+----------------------+");

        for (int i = 0; i < supplier_array.length; i++) {
            System.out.printf("|%-22s|%-22s|\n", supplier_array[i][0], supplier_array[i][1]);
        }
        System.out.println("+----------------------+----------------------+");

        System.out.print("Do you want to go supplier manage page (Y/N) : ");
        String option = input.next();

        if (option.equals("y") || option.equals("Y")) {
            clearConsole();
            supplierManage();
        }

    }

    private static void deleteSupplier() {
        clearConsole();

        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                              DELETE SUPPLIER                              |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        String option = "y";
        while (option.equals("y") || option.equals("Y")) {
            System.out.print("Supplier ID : ");
            String inputted_supplier_id = input.next();
            boolean valid = checkSupplierValidity(inputted_supplier_id);

            while (!valid) {
                System.out.println("Can't find supplier id. Try again!\n");
                System.out.print("Supplier ID : ");
                inputted_supplier_id = input.next();
                valid = checkSupplierValidity(inputted_supplier_id);
                System.out.println();
            }

            String[][] temp = new String[supplier_array.length - 1][2];

            int k = 0;
            for (int i = 0; i < supplier_array.length; i++) {
                if (inputted_supplier_id.equals(supplier_array[i][0])) {
                    continue;
                }
                temp[k][0] = supplier_array[i][0];
                temp[k][1] = supplier_array[i][1];
                k++;
            }
            supplier_array = temp;

            System.out.print("Deleted successfully! Do you want to delete another supplier (Y/N) : ");
            option = input.next();
        }

        clearConsole();
        supplierManage();

    }

    private static void updateSupplier() {
        clearConsole();

        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                              UPDATE SUPPLIER                              |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        String option = "y";
        while (option.equals("y") || option.equals("Y")) {
            System.out.print("Supplier ID : ");
            String inputted_supplier_id = input.next();
            boolean valid = checkSupplierValidity(inputted_supplier_id);

            while (!valid) {
                System.out.println("Can't find supplier id. Try again!\n");
                System.out.print("Supplier ID : ");
                inputted_supplier_id = input.next();
                System.out.println();
                valid = checkSupplierValidity(inputted_supplier_id);
            }

            String supplier_name = getSupplierName(inputted_supplier_id);
            System.out.println("Supplier Name : " + supplier_name);

            System.out.print("Enter the new supplier Name : ");
            String inputted_supplier_name = input.next();

            for (int i = 0; i < supplier_array.length; i++) {
                if (inputted_supplier_id.equals(supplier_array[i][0])) {
                    supplier_array[i][1] = inputted_supplier_name;
                }
            }
            System.out.print("Updated successfully! Do you want to update another supplier (Y/N) : ");
            option = input.next();
        }

        clearConsole();
        supplierManage();

    }

    private static String getSupplierName(String inputtedSupplierId) {
        for (int i = 0; i < supplier_array.length; i++) {
            if (supplier_array[i][0].equals(inputtedSupplierId)) {
                return supplier_array[i][1];
            }
        }
        return null;
    }

    private static void addSupplier(String from) {
        clearConsole();

        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                                ADD SUPPLIER                               |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        String option = "y";
        while (option.equals("y") || option.equals("Y")) {
            System.out.print("Supplier ID : ");
            String inputted_supplier_id = input.next();
            boolean valid = checkSupplierValidity(inputted_supplier_id);

            while (valid) {
                System.out.print("Already exists. Try another supplier id : ");
                inputted_supplier_id = input.next();
                valid = checkSupplierValidity(inputted_supplier_id);
                System.out.println();
            }

            System.out.print("Supplier Name : ");
            String inputted_supplier_name = input.next();

            supplier_array = extendSupplierArray(supplier_array);
            supplier_array[supplier_array.length - 1][0] = inputted_supplier_id;
            supplier_array[supplier_array.length - 1][1] = inputted_supplier_name;

            System.out.print("Added successfully! Do you want to add another supplier (Y/N) : ");
            option = input.next();
            System.out.println();
        }
        clearConsole();
        if (from.equals("AddItem")) {
            addItem();
        } else {
            supplierManage();
        }

    }

    private static String[][] extendSupplierArray(String[][] supplier_array) {
        String[][] temp = new String[supplier_array.length + 1][2];
        for (int i = 0; i < supplier_array.length; i++) {
            temp[i][0] = supplier_array[i][0];
            temp[i][1] = supplier_array[i][1];
        }
        return temp;
    }

    private static boolean checkSupplierValidity(String inputtedSupplierId) {
        for (int i = 0; i < supplier_array.length; i++) {
            if (supplier_array[i][0].equals(inputtedSupplierId)) {
                return true;
            }
        }
        return false;
    }


    //-------------------------- stock manage --------------------------------
    private static void stockManage() {
        clearConsole();


        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                              STOCK MANAGE                                 |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        System.out.printf("%-48s%-48s\n", "[1] Manage Item Categories", "[2] Add Item");
        System.out.printf("%-48s%-48s\n", "[3] Get Items Supplier wise", "[4] View Items");
        System.out.printf("%-48s%-48s\n", "[5] Rank Items Per Unit Price", "[6] Home Page");

        int option = 0;
        do {
            System.out.print("\nEnter an option to continue > ");
            option = input.nextInt();
        } while (!(option > 0 && option < 7));


        switch (option) {
            case 1:
                manageItemCategories();
            case 2:
                addItem();
            case 3:
                getItemSupplierWise();
            case 4:
                viewItems();
            case 5:
                rankItemsPerUnitPrice();
            case 6:
                homepage();
        }
    }

    private static void rankItemsPerUnitPrice() {
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                            RANKED UNIT PRICE                              |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        String[][] sorted_item_array = item_array;

        for (int i = 0; i < item_array.length; i++) {
            String[] temp = null;
            for (int j = 0; j < sorted_item_array.length - 1; j++) {
                if (Double.parseDouble(sorted_item_array[j][2]) > Double.parseDouble(sorted_item_array[j + 1][2])) {
                    temp = sorted_item_array[j + 1];
                    sorted_item_array[j + 1] = sorted_item_array[j];
                    sorted_item_array[j] = temp;
                }
            }
        }

        System.out.println();
        System.out.println("+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
        System.out.printf("|%-22s|%-22s|%-22s|%-22s|%-22s|%-22s|\n", "SID", "CODE", "DESC", "PRICE", "QTY", "CAT");
        System.out.println("+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+");

        for (int j = 0; j < sorted_item_array.length; j++) {
            System.out.printf("|%-22s|%-22s|%-22s|%-22s|%-22s|%-22s|\n", item_array[j][5], item_array[j][0], item_array[j][1], item_array[j][2], item_array[j][3], item_array[j][4]);
            System.out.println("+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
        }
        System.out.println();


        System.out.print("Do you want to go stock manage page (Y/N) : ");
        String option = input.next();

        if (option.equals("y") || option.equals("Y")) {
            stockManagement();
        }
    }

    private static void viewItems() {
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                               VIEW ITEMS                                  |");
        System.out.println("+---------------------------------------------------------------------------+\n");


        for (int i = 0; i < category_array.length; i++) {
            System.out.println(category_array[i] + " : ");

            System.out.println("+----------------------+----------------------+----------------------+----------------------+----------------------+");
            System.out.printf("|%-22s|%-22s|%-22s|%-22s|%-22s|\n", "SID", "CODE", "DESC", "PRICE", "QTY");
            System.out.println("+----------------------+----------------------+----------------------+----------------------+----------------------+");

            for (int j = 0; j < item_array.length; j++) {
                if (category_array[i].equals(item_array[j][4])) {
                    System.out.printf("|%-22s|%-22s|%-22s|%-22s|%-22s|\n", item_array[j][5], item_array[j][0], item_array[j][1], item_array[j][2], item_array[j][3]);
                    System.out.println("+----------------------+----------------------+----------------------+----------------------+----------------------+");

                }
            }
            System.out.println();
        }

        //System.out.println("+----------------------+----------------------+----------------------+----------------------+----------------------+");


        System.out.print("Do you want to go stock manage page (Y/N) : ");
        String option = input.next();

        if (option.equals("y") || option.equals("Y")) {
            stockManagement();
        }
    }

    private static void getItemSupplierWise() {
        clearConsole();

        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                            SEARCH SUPPLIER                                |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        String option = "y";

        while (option.equals("y") || option.equals("Y")) {
            System.out.print("\nEnter supplier Id : ");
            String inputted_supplier_id = input.next();

            boolean supplierValidity = checkSupplierValidity(inputted_supplier_id);

            while (!supplierValidity) {
                System.out.println("Invalid supplier ID! Try again.");
                System.out.print("Enter supplier Id : ");
                inputted_supplier_id = input.next();
                supplierValidity = checkSupplierValidity(inputted_supplier_id);
            }

            String supplierName = getSupplierName(inputted_supplier_id);
            System.out.println("Supplier Name : " + supplierName);

            showItemsSupplierWiseInTable(inputted_supplier_id);
            System.out.print("Search Successfully! Do you want to another search (Y/N) : ");
            option = input.next();

        }
        clearConsole();
        stockManagement();

    }

    private static void showItemsSupplierWiseInTable(String inputtedSupplierId) {

        System.out.println("+----------------------+----------------------+----------------------+----------------------+----------------------+");
        System.out.printf("|%-22s|%-22s|%-22s|%-22s|%-22s|\n", "ITEM CODE", "DESCRIPTION", "UNIT PRICE", "QTY ON HAND", "CATEGORY");
        System.out.println("+----------------------+----------------------+----------------------+----------------------+----------------------+");

        int count = 0;
        for (int i = 0; i < item_array.length; i++) {
            if (item_array[i][5].equals(inputtedSupplierId)) {
                count++;
            }
        }

        String[][] temp = new String[count][6];
        int index = 0;
        for (int i = 0; i < item_array.length; i++) {
            if (item_array[i][5].equals(inputtedSupplierId)) {
                for (int j = 0; j < 6; j++) {
                    temp[index][j] = item_array[i][j];
                }
                index++;
            }
        }
        for (int i = 0; i < temp.length; i++) {
            String category_name = temp[i][4];
            System.out.printf("|%-22s|%-22s|%-22s|%-22s|%-22s|\n", temp[i][0], temp[i][1], temp[i][2], temp[i][3], category_name);
        }
        System.out.println("+----------------------+----------------------+----------------------+----------------------+----------------------+");
    }

    private static void addItem() {
        clearConsole();

        String main_option = "y";
        while (main_option.equals("y") || main_option.equals("Y")) {

            System.out.println("+---------------------------------------------------------------------------+");
            System.out.println("|                                ADD ITEM                                   |");
            System.out.println("+---------------------------------------------------------------------------+\n");

            boolean hasItemCategory = !(category_array.length == 0);
            if (!hasItemCategory) {
                System.out.println("OOPS! It seems that you don't have any item categories in the system.");
                System.out.print("Do you want to add a new item category (Y/N) : ");
                String option = input.next();
                if (option.equals("y") || option.equals("Y")) {
                    addNewItemCategory("AddItem");
                } else {
                    clearConsole();
                    stockManagement();
                }
            }
            boolean hasSupplier = !(supplier_array.length == 0);
            if (!hasSupplier) {
                System.out.println("OOPS! It seems that you don't have any suppliers in the system.");
                System.out.print("Do you want to add a new supplier (Y/N) : ");
                String option = input.next();

                if (option.equals("y") || option.equals("Y")) {
                    addSupplier("AddItem");
                } else {
                    clearConsole();
                    stockManagement();
                }
            }


            System.out.print("Item Code : ");
            String inputted_item_code = input.next();

            showSupplierList();

            System.out.print("Enter the supplier number : ");
            String inputted_supplier_number = input.next();

            showItemCategories();

            System.out.print("Enter the category number : ");
            String inputted_category_number = input.next();

            System.out.print("\nDescription : ");
            String inputted_description = input.next();

            System.out.print("Unit Price : ");
            String inputted_unit_price = input.next();

            System.out.print("Qty On Hand : ");
            String inputted_qty_on_hand = input.next();

            item_array = incrementItemArray();

            //get category name by category number
            String item_category_name = category_array[Integer.parseInt(inputted_category_number) - 1];

            //get supplier name by supplier number
            String item_supplier_id = supplier_array[Integer.parseInt(inputted_supplier_number) - 1][0];

            item_array[item_array.length - 1][0] = inputted_item_code;
            item_array[item_array.length - 1][1] = inputted_description;
            item_array[item_array.length - 1][2] = inputted_unit_price;
            item_array[item_array.length - 1][3] = inputted_qty_on_hand;
            item_array[item_array.length - 1][4] = item_category_name;
            item_array[item_array.length - 1][5] = item_supplier_id;

            System.out.print("Added successfully! Do you want add another item (Y/N) : ");
            main_option = input.next();
            System.out.println();
            clearConsole();
        }

        stockManagement();
    }

    private static String[][] incrementItemArray() {
        String[][] temp = new String[item_array.length + 1][6];
        for (int i = 0; i < item_array.length; i++) {
            for (int j = 0; j < item_array[0].length; j++) {
                temp[i][j] = item_array[i][j];
            }
        }
        return temp;
    }

    private static void showItemCategories() {
        System.out.println("\nItem Categories : ");
        System.out.println("+----------------------+----------------------+");
        System.out.printf("|%-22s|%-22s|\n", "#", "CATEGORY NAME");
        System.out.println("+----------------------+----------------------+");

        for (int i = 0; i < category_array.length; i++) {
            System.out.printf("|%-22s|%-22s|\n", i + 1, category_array[i]);
        }
        System.out.println("+----------------------+----------------------+");

    }

    private static void showSupplierList() {
        System.out.println("\nSupplier List : ");
        System.out.println("+----------------------+----------------------+----------------------+");
        System.out.printf("|%-22s|%-22s|%-22s|\n", "#", "SUPPLIER ID", "SUPPLIER NAME");
        System.out.println("+----------------------+----------------------+----------------------+");

        for (int i = 0; i < supplier_array.length; i++) {
            System.out.printf("|%-22s|%-22s|%-22s|\n", i + 1, supplier_array[i][0], supplier_array[i][1]);
        }
        System.out.println("+----------------------+----------------------+----------------------+");

    }

    private static void manageItemCategories() {
        clearConsole();
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                          MANAGE ITEM CATEGORY                             |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        System.out.printf("%-48s%-48s\n", "[1] Add New Item Category", "[2] Delete Item Category");
        System.out.printf("%-48s%-48s\n", "[3] Update Item Category", "[4] Stock Management");

        int option = 0;
        do {
            System.out.print("\nEnter an option to continue > ");
            option = input.nextInt();
        } while (!(option > 0 && option < 5));

        switch (option) {
            case 1:
                addNewItemCategory("ManageCategory");
            case 2:
                deleteItemCategory();
            case 3:
                updateItemCategory();
            case 4:
                stockManagement();
        }
    }

    private static void stockManagement() {
        clearConsole();
        stockManage();
    }

    private static void updateItemCategory() {
        clearConsole();

        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                           UPDATE ITEM CATEGORY                            |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        String option = "y";
        while (option.equals("y") || option.equals("Y")) {
            System.out.print("Item Category : ");
            String inputted_category = input.next();
            boolean valid = checkCategoryValidity(inputted_category);

            while (!valid) {
                System.out.println("Can't find Category. Try again!\n");
                System.out.print("Item Category : ");
                inputted_category = input.next();
                valid = checkCategoryValidity(inputted_category);
                System.out.println();
            }

            System.out.print("Enter new item category : ");
            String inputted_update_category = input.next();

            for (int i = 0; i < category_array.length; i++) {
                if (inputted_category.equals(category_array[i])) {
                    category_array[i] = inputted_update_category;
                }
            }

            updateItemArrayAfterUpdateCategory(inputted_category, inputted_update_category);

            System.out.print("Updated successfully! Do you want to update another category (Y/N) : ");
            option = input.next();
        }

        clearConsole();
        manageItemCategories();
    }

    private static void updateItemArrayAfterUpdateCategory(String inputtedCategory, String inputtedUpdateCategory) {
        for (int i = 0; i < item_array.length; i++) {
            if (item_array[i][4].equals(inputtedCategory)) {
                item_array[i][4] = inputtedUpdateCategory;
            }
        }
    }

    private static void deleteItemCategory() {
        clearConsole();

        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                           DELETE ITEM CATEGORY                            |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        String option = "y";
        while (option.equals("y") || option.equals("Y")) {
            System.out.print("Item Category : ");
            String inputted_category = input.next();
            boolean valid = checkCategoryValidity(inputted_category);

            while (!valid) {
                System.out.println("Can't find Category. Try again!\n");
                System.out.print("Item Category : ");
                inputted_category = input.next();
                valid = checkCategoryValidity(inputted_category);
                System.out.println();
            }

            String[] temp = new String[category_array.length - 1];

            int k = 0;
            for (int i = 0; i < category_array.length; i++) {
                if (inputted_category.equals(category_array[i])) {
                    continue;
                }
                temp[k] = category_array[i];
                k++;
            }
            category_array = temp;

            updateItemArrayAfterDeleteCategory(inputted_category);

            System.out.print("Deleted successfully! Do you want to delete another category (Y/N) : ");
            option = input.next();
        }

        clearConsole();
        supplierManage();
    }

    private static void updateItemArrayAfterDeleteCategory(String inputtedCategory) {
        for (int i = 0; i < item_array.length; i++) {
            if (inputtedCategory.equals(item_array[i][4])) {
                item_array[i][4] = null;
            }
        }
    }

    private static void addNewItemCategory(String from) {
        clearConsole();
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                             ADD ITEM CATEGORY                             |");
        System.out.println("+---------------------------------------------------------------------------+\n");

        String option = "y";

        while (option.equals("y") || option.equals("Y")) {
            System.out.print("Enter the new item category : ");
            String inputted_category = input.next();

            boolean validity = checkCategoryValidity(inputted_category);
            if (!validity) {
                category_array = incrementCategoryArray();
                category_array[category_array.length - 1] = inputted_category;
            }

            System.out.print("Added successfully! Do you want to add another category (Y/N) : ");
            option = input.next();
            System.out.println();
        }
        if (from.equals("AddItem")) {
            addItem();
        } else {
            manageItemCategories();
        }
    }

    private static String[] incrementCategoryArray() {
        String[] temp = new String[category_array.length + 1];
        for (int i = 0; i < category_array.length; i++) {
            temp[i] = category_array[i];
        }
        return temp;
    }

    private static boolean checkCategoryValidity(String inputtedCategory) {
        for (int i = 0; i < category_array.length; i++) {
            if (category_array[i].equals(inputtedCategory)) {
                return true;
            }
        }
        return false;
    }

    private final static void clearConsole() {
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