import java.util.*;
import java.io.*;

public class ShellScript {
	public static void main(String[] args) throws FileNotFoundException {
		File arabicFile = new File("Diary_50_Arabic_EditsStandardized_20150917 (1).html");
		Scanner arabicScanner = new Scanner(arabicFile);
		File diaryFile = new File("Diary_50_20160719_links.html");
		Scanner diaryScanner = new Scanner(diaryFile);

      PrintStream idList = new PrintStream("id list.html");
      findIdCell(arabicScanner, idList);
	}
	// scan through file to find ID-cell from given diary file
	// copies ID A50_... from something like: <td class="ID-cell">A50_004_02:002</td>
	// forms new string that looks like: <td class="ID-cell"> <a id="A50_004_01:001"></a> A50_004_01:001</td>
   	// parameters:
   	//    input - diary file 
	//	  idList - outputs each id created to new file
	public static void findIdCell(Scanner input, PrintStream idList) {
		while (input.hasNextLine()) {
			String line = input.nextLine();
         String result = "<td class=\"ID-cell\"> <a id=\"";
			if (line.contains("<td class=\"ID-cell\">A50")) {
            String id = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
            result += id + "\"> " + id + "</td>";
            idList.println(result);
			}
		}
	}
	// method to replace (Scanner file, String replaceWith)
	// find and replace
	// insert before the string <a id = "string"> </a>
	// keep a list of all the ids that we created
	public static void replace(Scanner input, String replaceWith) {

	}
}
