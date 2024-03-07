import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenericsKbArrayApp {
   static String [][] allLines = new String [50000][3];
   public static Boolean loadFile(String fileName){
      try {
         File myObj = new File(fileName);
         Scanner myReader = new Scanner(myObj);
         int count = 0;
         while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] parts = data.split("\t");
            allLines[count] = parts;
            count++;
         }
         myReader.close();
         return true;
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         return false;
      }
   }
   public static Boolean addStatement(String[] addArray){
      Boolean updated = false;
      for (String[] line : allLines){
         if (addArray[0].equals(line[0])) {
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
      return !updated;
   }
   public static String[] searchForItem(String term){
      String[] empty = new String[3];
      for (String[] line : allLines){
         if (term.equals(line[0])) {
            return line;
         }
      }
      return empty;
   }
   public static String[] searchByTermSentence(String term, String sentence){
      String[] empty = new String[3];
      for (String[] line : allLines){
         if (term.equals(line[0]) && sentence.equals(line[1])) {
            return line;
         }
      }
      return empty;
   }

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
   String fileName = "";
   int userChoice;
   while (true){
      System.out.println("Choose an action from the menu:");
      System.out.println("1. Load a knowledge base from a file");
      System.out.println("2. Add a new statement to the knowledge base");
      System.out.println("3. Search for an item in the knowledge base by term");
      System.out.println("4. Search for a item in the knowledge base by term and sentence");
      System.out.println("5. Quit");
      System.out.println("");
      System.out.printf("Enter your choice: ");
      userChoice = scanner.nextInt();
      System.out.println("");
      switch (userChoice) {
         case 1:
            System.out.printf("Enter file name: ");
            fileName = scanner.next();
            if (loadFile(fileName)) {
               System.out.println("Knowledge base loaded successfully.");
            } else{
               System.out.println("File doesn't exist.");
            }
            break;
         case 2:
            String[] addArray = new String[3];
            System.out.printf("Enter the term: ");
            String garbage = scanner.nextLine();
            addArray[0] = scanner.nextLine();
            System.out.printf("Enter the statement: ");
            addArray[1] = scanner.nextLine();
            System.out.printf("Enter the confidence score: ");
            addArray[2] = scanner.nextLine();
            if (addStatement(addArray)){
               System.out.println("Statement for term "+addArray[0]+" has been added.");
            } else {
               System.out.println("Statement for term "+addArray[0]+" has been updated.");
            }
            break;
         case 3:
            System.out.printf("Enter the term: ");
            garbage = scanner.nextLine();
            String term = scanner.nextLine();
            String[] item = searchForItem(term);
            if (item[0] == null){
               System.out.println("statement not found.");
            } else {
               System.out.println("Statement found: "+item[1]+" (Confidence score: "+item[2]+")");
            }
            break;
         case 4:
            System.out.printf("Enter the term: ");
            garbage = scanner.nextLine();
            term = scanner.nextLine();
            System.out.printf("Enter the statement to search for: ");
            String statement = scanner.nextLine();
            item = searchByTermSentence(term, statement);
            if (item[0] == null){
               System.out.println("statement not found.");
            } else {
               System.out.println("The statement was found and has a confidence score of "+item[2]+".");
            }
            break;
         case 5:
            System.exit(0);
            break;
         default:
            System.out.println("Invalid choice");
            break;
      }
   }
  }
}