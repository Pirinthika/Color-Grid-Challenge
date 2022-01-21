import java.util.*;

public class Display {

    public static void main(String[] args) {
        int column = 0;
        int row = 0;
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number of Columns of the grid ");
        column = sc.nextInt();
        
        System.out.print("Enter Number of Rows of the grid ");
        row = sc.nextInt();
        
        System.out.println("Enter the colors, enter 'e' to exit");
        int colorNo = 1;
        ArrayList<String> colorList = new ArrayList<>();
        while(true) {
        	System.out.print(colorNo++ +") Color: ");
        	String i = sc.next().toUpperCase();
        	if(i.equalsIgnoreCase("e"))
        		break;
        	if(colorList.contains(i.substring(0,1)))
        		System.out.println("Duplicate color");
        	else
        		colorList.add(i.substring(0, 1));
        }
        
        Search process = new Search();
        process.initialize(column, row, colorList);
        process.displayGrid();

        Color colorBlock = process.getLargeBlock();

        if (colorBlock != null) {
            System.out.println();
            System.out.println("Largest connecting block of same color is ");
            System.out.println();
            process.displayGridWithBlocks(colorBlock);
        }
        
        sc.close();
    }


}