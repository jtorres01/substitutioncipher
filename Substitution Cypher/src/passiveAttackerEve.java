import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class passiveAttackerEve {

	
	
	public static void main(String[] args) {
		
		
		
		
		System.out.println("What file do you want to read");
		System.out.println("Make sure you add the extension .txt");
	}
	
	public static String getFileString() throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		boolean breakOut = true;
		String text = "";
		System.out.println("What file do you want to read");
		System.out.println("Make sure you add the extension .txt");
		String userInputName = scan.next();
		File textFile = new File(userInputName);
		while(breakOut == true) {
			
		if(!textFile.exists()) {
			System.out.println("File not found!");
			System.out.println("Please make sure it is typed correct and includes .txt extension\n");
		}
		else {
			breakOut = false; //breaks loop if file exists
			StringBuilder efileContents = new StringBuilder((int)textFile.length());
			try (Scanner scanner = new Scanner(textFile)) {
		        while(scanner.hasNextLine()) {
		            efileContents.append(scanner.nextLine() + System.lineSeparator());
		        }
		        text = efileContents.toString();
		    }
			System.out.println("File found!\n");
			return text;
		}
		}
		return text;
	}
}
