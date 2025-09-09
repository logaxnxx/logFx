/**
 * Implement each of the 10 methods tested in JUnitTests.java. Study the tests
 * to determine how the methods should work.
 */
public class Java1Review {
	
	public static double quotient;
	
	//private static int minFound;
	
	private static double sum;
	private static double count;
	
	//divides two doubles and returns their quotient
	public static double divide (double first, double second) {
		
		quotient = first / second;
		return quotient;
	}
	
	//tests whether the number given is divisible by seven
	public static boolean isDivisibleBy7(int number) {
		
		if(number % 7 == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//finds the minimum value from the given three integer values
	public static int findMin(int first, int second, int third) {
		
		if (first < second && first < third) {
			return first;
		}
		else if (second < third && second < first) {
			return second;
		}
		else {
			return third;
		}
	}
	
	//finds the minimum value from a give set of an array of integers
	public static int findMin(int[] numList) {
		//the first number in the array is the min value
		int minVal = numList[0];
		
		//for loop starts at the second element and will compare 
		//that new element with min value and will overwrite when it finds a new
		//min value
		for(int i = 1; i < numList.length; i++) {
			if(numList[i] < minVal) {
				minVal = numList[i];
			}
		}
		return minVal;
	}
	
	//calculates the average of an array of integers
	public static double average(int[] numList) {
		
		for (int i = 0; i < numList.length; i++) {
			
			sum += numList[i]; 
			count++;
		}
		
		return sum / count;
	}
	
	//converts a string to lower case returning a new array
	public static void toLowerCase(String[] wordArr) {
		for(int i = 0; i < wordArr.length; i++) {
			wordArr[i].toLowerCase();
		}
	}
	//converts a string array into lower case but leaves the give array un-change 
	//so it creates a new array
	public static String[] toLowerCaseCopy(String[] wordArr) {
		String[] lowerCaseWordArr = new String[wordArr.length]; 
		
		//&& is unnecessary but adds a double check that the new array is the same length
		//as the given array
		for (int i = 0; i < wordArr.length && i < lowerCaseWordArr.length; i++) {
			lowerCaseWordArr[i] = wordArr[i].toLowerCase();
		}
		
		return lowerCaseWordArr;
	}

	
	//removes the duplicates from an array and replaces it with a zero
	public static void removeDuplicates(int[] numList) {
		int sample;
		
		for(int i = 0; i < numList.length - 1; i++) {
			sample = numList[i];
			
			for(int j = i+1; j < numList.length; j++) {
				if(sample == numList[j]) {
					numList[i] = 0;
					numList[j] = 0;
				}
			}
		}
	}
	
	//overload the main method -- idek what to do? 
	public static String main(String input) {
		return "Overloaded main method was passed \"" + input + "\".";
	}
}
