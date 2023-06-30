public class Example {
    public static void main(String[] args) {

         String item[][] = new String[1][6];

         item[0][0]="hello";
         item[0][1]="hello";
         item[0][2]="hello";
         item[0][3]="hello";

        System.out.println("+-------------------------------------------------------------------+");
        System.out.println("|\tItem ID\t\t|\tDescription\t\t|\tUnit Price\t|\tQty on Hand\t|");
        System.out.println("+-------------------------------------------------------------------+");


        for (int i = 0; i < item.length; i++) {

                System.out.printf("|\t%-10s\t|\t%-18s\b\b|\t%-10s\t|\t%-12s\b |\n", item[i][0], item[i][1], item[i][2], item[i][3]);

        }

        System.out.println("+-------------------------------------------------------------------+");
    }
}
