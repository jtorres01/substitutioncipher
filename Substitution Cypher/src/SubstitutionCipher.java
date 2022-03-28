
import java.io.*;
import java.util.*;

public class SubstitutionCipher {

	public static void main(String[] args) throws FileNotFoundException {

		//SEQUENCE OF EVENTS
		//ask user if they want to encrypt or decrypt, run respective methods
		
		//if they choose encryption
			//ask if user wants to use existing key or make new one
			//if new key save it and use for encryption
			//user enters message to encrypt, name and save it
		
		//if they choose decryption
			//ask for name of encrypted file and key file
			//run decrypt method on file using key
			//save decrypted file
		
		runMenu();
	}
	
	public static void runMenu() throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Substitution Cipher Main Menu");
		System.out.println("This is substitution-cipher key generator. Choose an option to continue.");
		
		//MENU
		boolean running = true;
		while(running == true) {
			System.out.println("ENCRYPT (1) or DECRYPT (2) or QUIT (3) ? :");
			String choice = scan.next();
			
			if(choice.equals("1")) {
				Encryption();
			}
			if(choice.equals("2")) {
				Decryption();
			}
			if(choice.equals("3")) {
				System.out.println("Do you want to quit? (yes/no): ");
				if(scan.next().equals("yes")) {
					running = false;
				}
			}
		}
	}
	
	public static void Encryption() throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		String key = ""; //empty key string, will be defined later
		
		
		System.out.println("Encrypting");
		 
		
		boolean check = true;
		while(check == true) { //loops if entry isnt 1 or 2
			
			System.out.println("CREATE NEW KEY (1) or USE EXISTING KEY (2) or GO BACK (3) ? : ");
			String choice = scan.next();
			
			//create new key
			if(choice.equals("1")) {
				check=false;
				key = keygen();
				saveFile(key, "Key");
			}
			
			//use existing key
			if(choice.equals("2")) {
				
				System.out.println("Enter the exact name of the key file: "); //must include file extension .txt
				String keyName = scan.next();
				File keyFile = new File(keyName);
				
				if(!keyFile.exists()) {
					System.out.println("File not found!");
					System.out.println("Please make sure it is typed correct and includes .txt extension\n");
				}
				else {
					check=false;
					System.out.println("Key file found!\n");
					//gets file contents and writes it to a string, updates key variable
					StringBuilder fileContents = new StringBuilder((int)keyFile.length());
					try (Scanner scanner = new Scanner(keyFile)) {
				        while(scanner.hasNextLine()) {
				            fileContents.append(scanner.nextLine() + System.lineSeparator());
				        }
				        key = fileContents.toString();
				    }
				}
			}
			if(choice.equals("3")) {
				runMenu();
			}
		}
						
		String phrase = inputText(); //gets user input for text to encrypt
		String enc = encrypt(phrase, key); //encrypts user input
		System.out.println("Encrypted text: " + enc + "\n");		
		saveFile(enc, "Encrypted");
	}
	
	public static void Decryption() throws FileNotFoundException {
		//get file names, write them to strings, send them to decrypt method
		System.out.println("~~ Decryption Selected ~~");
		System.out.println("Generate encrypted text using existing key and encrypted file");
		Scanner scan = new Scanner(System.in);
		String key = "";
		String file = "";
		
		//used for error handling if file doesnt exist
		boolean keycheck = true;
		boolean filecheck = true;
		
		//GET KEY FILE NAME FROM USER
		//IF IT EXISTS, CONVERT IT TO STRING
		while(keycheck == true) {
			System.out.println("Enter full name of KEY file(including .txt extension): ");
			String userInputKey = scan.next();
			File keyFile = new File(userInputKey);
			
//			if(userInputKey.equals("q")) {
//				main(null);
//			}
			
			if(!keyFile.exists()) {
				System.out.println("File not found!");
				System.out.println("Please make sure it is typed correct and includes .txt extension\n");
			}
			else {
				keycheck = false; //breaks loop if file exists
				StringBuilder fileContents = new StringBuilder((int)keyFile.length());
				try (Scanner scanner = new Scanner(keyFile)) {
			        while(scanner.hasNextLine()) {
			            fileContents.append(scanner.nextLine() + System.lineSeparator());
			        }
			        key = fileContents.toString();
			    }
				System.out.println("Key file found!\n");
			}
		}
		
		//GET ENC FILE NAME FROM USER
		//IF IT EXISTS, CONVERT IT TO STRING
		while(filecheck == true) {
			System.out.println("Enter full name of encrypted file: ");
			String userInputFile = scan.next();
			File encFile = new File(userInputFile);
			if(!encFile.exists()) {
				System.out.println("File not found!");
				System.out.println("Please make sure it is typed correct and includes .txt extension\n");
			}
			else {
				filecheck = false; //breaks loop if file exists
				StringBuilder efileContents = new StringBuilder((int)encFile.length());
				try (Scanner escanner = new Scanner(encFile)) {
			        while(escanner.hasNextLine()) {
			            efileContents.append(escanner.nextLine() + System.lineSeparator());
			        }
			        file = efileContents.toString();
			    }
				System.out.println("Encrypted file found!\n");
			}
		}
		
		String dec = decrypt(file, key); //generates decrypted string using file and key
		System.out.println("Here is your decrypted message: " + dec);
		saveFile(dec, "Decrypted");
	}
	
	public static String keygen() {
		System.out.println("Enter a lowercase letter to map to each letter of the alphabet.\n");
		
		boolean found = false; //used to check for duplicates before adding value to key array
		Scanner in = new Scanner(System.in);
		
		char[] alphabet = new char[] 
				{'a','b','c','d','e','f','g','h','i','j','k','l','m',
				'n','o','p','q','r','s','t','u','v','w','x','y','z'};
		char[] key = new char[26];
		
		for(int i = 0; i < key.length; i++) {
			//iterate thru empty key array, adding user entered values each iteration
			
			found = false; //reset bool for duplicate check
			
			//print the alphabet array
			System.out.print("plaintext = ");
			for(int x=0; x<alphabet.length; x++) {
				System.out.print("(" + alphabet[x] + "), ");
			}
			System.out.println();
			
			//get character from user
			System.out.println("\n Enter a lowercase letter: ");
			char value = in.next().charAt(0);	
			
			//check for duplicate
			for(char z : key) {
				if(z == value) { found = true; }
			}

			//if value entered is duplicate
			while(found == true) {
				found = false;
				System.out.println("That letter has already been used, try another: ");
				value = in.next().charAt(0);
				
				//check for duplicate
				for(char z : key) {
					if(z == value) { found = true; }
				}
			}
			
			//if no duplicate, add to key
			if(found == false) {
				System.out.println(value);
				key[i] = value;
			}
			
			//print the key array
			System.out.print("key       = ");
			for(int n=0; n<key.length; n++) {
				System.out.print("(" + key[n] + "), ");
			}
			System.out.println();
		}
		
		System.out.println("\nKey has been generated!\n");
		return new String(key);
	}
	
	public static void saveFile(String str, String type) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		boolean check = true;
		
		while(check == true) { //loops to check if file already exists
			System.out.println("What would you like to name your " + type +" file: ");
			String fileName = in.next();
			
			//appends file type to the name
			if(type.equals("Key")) {
				fileName+="KEY.txt";
			}
			
			if(type.equals("Encrypted")) {
				fileName+="ENC.txt";
			}
			
			if(type.equals("Decrypted")) {
				fileName+="DEC.txt";
			}
			
			File file = new File(fileName);
			
			if(file.exists()) {
				System.out.println("A file already exists with that name!");
				System.out.println("Please enter a different name\n");
			}
			
			else { //breaks loop and saves file
				check = false;
				PrintWriter writer = new PrintWriter(file);
				writer.print(str);
				writer.close();
				System.out.println(type + " file saved succesfully! Saved as '"+fileName+"\n");
			}
		}
		
	}
	
	public static char charSwap(char x, String key, String type) {
		//char x is the current character in the for loop through the input text
		//generate a new char to return that matches with the key
		
		//*ONLY QUALM* make it ignore case so capital letters stay
		char newChar = ' ';
		char[] alphabet = new char[] 
				{'a','b','c','d','e','f','g','h','i','j','k','l','m',
				'n','o','p','q','r','s','t','u','v','w','x','y','z'};
		
		//String alph = "abcdefghijklmnopqrstuvwxyz";
		
		if(type == "encrypt") {
			//iterate through alphabet and find match, save index
			for(int i=0; i<alphabet.length; i++) {
				
				//if x is a letter, swap it
				if(Character.toLowerCase(x) == alphabet[i]) {	
					newChar = key.charAt(i);
				}
				
				//if its a symbol dont swap it
				if(!Character.isLetter(x)) {
					return x;
				}
			}
		}
		
		if(type == "decrypt") {
			for(int z=0; z<key.length()-2; z++) {
				//if x is a letter, swap it
				if(x == key.charAt(z)) {
						newChar = alphabet[z];
					//}
				}
				//if its a symbol dont swap it
				if(!Character.isLetter(x)) {
					return x;
				}
			}
		}
		return newChar;
		
	}
	
	public static String inputText() {
		//get user input text to be encrypted
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the phrase you would like to encrypt:");
		String phrase = in.nextLine();
		return phrase;
	}
			
	public static String encrypt(String phrase, String key) {
		//iterate through user input text, swap each character with the key
		String enc = "";
		for(int i=0; i<phrase.length(); i++) {
			char x = charSwap(phrase.charAt(i), key, "encrypt"); 
			enc += x;
		}
		return enc;
	}
	
	public static String decrypt(String encText, String key) {
		//iterate through encrypted text and swap each character
		String dec = "";
		for(int i=0; i<encText.length(); i++) {
			char x = charSwap(encText.charAt(i), key, "decrypt");
			dec += x;
		}
		return dec;
	}
}
