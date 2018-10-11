
import java.util.*;
import java.io.*;

public class ShellScript {
   public static void main(String[] args) throws FileNotFoundException {
      File arabicFile = new File("Diary_50_Arabic_EditsStandardized_20150917 (1).html");
      Scanner arabicScanner = new Scanner(arabicFile);
      File diaryFile = new File("Diary_50_20160719_links.html");
      Scanner diaryScanner = new Scanner(diaryFile);
   
      PrintStream idList = new PrintStream("D50_id list.html");
      PrintStream output = new PrintStream("D50_diary.html");
      PrintStream links = new PrintStream("D50_links.html");
      findAndReplace(arabicScanner, idList, output);
      replace(diaryScanner, links);
   }
   // scan through file to find ID-cell from given diary file
   // copies ID A50_... from something like: <td class="ID-cell">A50_004_02:002</td>
   // forms new string that looks like: <td class="ID-cell"> <a id="A50_004_01:001"></a> A50_004_01:001</td>
   // parameters:
   //    input - diary file 
   //    idList - output file for list of new IDs
   //    output - output file for replaced code for html file
   public static void findAndReplace(Scanner input, PrintStream idList, PrintStream output) {
      while (input.hasNextLine()) {
         String line = input.nextLine();
         String result = "<td class=\"ID-cell\"> <a id=\"";
         if (line.contains("<td class=\"ID-cell\">A50")) {
            String id = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
            result += id + "\"></a> " + id + "</td>";
               
            idList.println(result);
            output.println("      " + result);
         } else {
            output.println(line);
         }
      }
   }
   // method to replace (Scanner file, String replaceWith)
   // find and replace
   // insert before the string <a id = "string"> </a>
   // keep a list of all the ids that we created
   public static void replace(Scanner input, PrintStream links) {
      while(input.hasNextLine()) {
         String line = input.nextLine();
         String insert = "<a href = \"Diary_50_Arabic_EditsStandardized_20150917 (1).html#";
         if (line.contains("[A50_")) {
            //need to delete line and then insert above and reinsert below
            String id = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
            String link = insert + id + "\">Link to Arabic</a>";
            links.println(link);
            links.println(line);
         } else {
            links.println(line);
         }
      }
   }
}
