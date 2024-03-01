import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenericsKbArrayApp {

   public static String[][] addElement(String[][] array, String[] element) {
        int newArrayLength = array.length + 1;
        String[][] newArray = new String[newArrayLength][3];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        newArray[newArrayLength - 1] = element;

        return newArray;
  }
  public static void main(String[] args) {
  
   Scanner scanner = new Scanner(System.in);

   System.out.println("Choose an action from the menu:");
   System.out.println("1. Load a knowledge base from a file");
   System.out.println("2. Add a new statement to the knowledge base");
   System.out.println("3. Search for an item in the knowledge base by term");
   System.out.println("4. Search for a item in the knowledge base by term and sentence");
   System.out.println("5. Quit");
   System.out.println("");
   System.out.printf("Enter your choice: ");
   int userChoice = scanner.nextInt();
   //System.out.printf("userChoice: "+userChoiced);
   System.out.println("");
   //String garbage = scanner.nextLine();
   String fileName = "";
   switch (userChoice) {
      case 1:
         System.out.printf("Enter file name: ");
         fileName = scanner.next();
         System.out.println("file name: "+fileName);
         break;
      case 2:
         System.out.println("2 Selected");
         break;
      case 3:
         System.out.println("3 Selected");
         break;
      case 4:
         System.out.println("4 Selected");
         break;
      case 5:
         System.out.println("5 Selected");
         break;
      default:
         System.out.println("Invalid choice");
         break;
   }
   
   
    try {
      File myObj = new File(fileName);
      Scanner myReader = new Scanner(myObj);
      String [][] allLines = new String [50000][3];
      int count = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] parts = data.split("\t");
        allLines[count] = parts;
        for (String part : parts) {
            //System.out.println(part);
        }
        //System.out.println(data);
        //break;
        count++;
      }
      String[] addArray = {"isolated organ", "yo add'a", "0.7458134293556212"};
      Boolean updated = false;
      for (String[] line : allLines){
         if (addArray[0].equals(line[0])) {
            //update
            line[1] = addArray[1];
            if (Double.parseDouble(addArray[2]) > Double.parseDouble(line[2])) {
               line[2] = addArray[2];
            }
            updated = true;
            break;
         }
      }
      if (!updated) {
         allLines = addElement(allLines, addArray);
      }
      System.out.println("Knowledge base loaded successfully.");
      //System.out.println(allLines[2][0]);
      //System.out.println(allLines[2][1]);
      //System.out.println(allLines[2][2]);

      //System.out.println("There are "+count+ " lines.");
      
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("File doesn't exist.");
      e.printStackTrace();
    }
  }
}