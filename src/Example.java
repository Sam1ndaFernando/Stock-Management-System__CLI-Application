public class Example {
    public static void main(String[] args) {
        String item[][] = new String[5][6];



        System.out.println("+---------------------------------------------------------------------------------------------------+");
        System.out.println("|\tSupplier id \t\t|\tItem code\t|\tDescription\t\t|\tPrice\t|\tQty\t\t|\tCategory\t|");
        System.out.println("+---------------------------------------------------------------------------------------------------+");
        for (int i = 0; i < item.length; i++) {
            item[i][0]="hello";
            item[i][1]="hello";
            item[i][2]="hello";
            item[i][3]="hello";
            item[i][4]="hello";
            item[i][5]="hello";

            System.out.printf("|\t%-18s\t|\t%-10s\t|\t%-12s\t|\t%-6s\t|\t%-4s\t|\t%-8s\t|\n", item[i][5], item[i][0], item[i][1], item[i][2], item[i][3], item[i][4]);
        }
        System.out.println("+---------------------------------------------------------------------------------------------------+");
    }
}
