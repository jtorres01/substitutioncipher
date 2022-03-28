import java.util.Random;

public class SubCypher {

	public static void main(String[] args) {
		
		char base[] = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		char code[] = new char[26];
		code = encrypt(base,code);
		//for(int i =0; i< code.length;i++) {
			//System.out.println(code[i]);
		//}
		
	}
	
	public static char[] encrypt(char[] base,char code[]) {
		Random random = new Random();
		int tracker[] = new int[26];
		int spot = random.nextInt(25-0) +0;
		System.out.println(spot);
		for(int i =0; i<= base.length-1; i++) {
			spot = random.nextInt(25-0) +0;
			System.out.println(spot);
			if(numChecker(tracker,spot)) {
				tracker[i] = spot;
				
				
			}
			
			//tracker[i] = spot;
			//System.out.println(spot);
			/*if(numChecker(tracker,spot)) {		
			}
			else{
				spot = random.nextInt(25-0) +0;
			}
			System.out.println(spot);
			*/
		}
		return code;
	}
	
	//this need to check if the "num" number is already in the array, return false
	public static boolean numChecker(int[] tracker, int num) {
		for(int o =0; o<tracker.length;o++) {
			if(num == tracker[o]) {
				return false;
			}	
		}
		//true means that this number is unused
		return true;
	}

}
