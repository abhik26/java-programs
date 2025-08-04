package abhik26.java_programs.dsa_interview_questions;

public class ReverseNumbersInString {
	public static void main(String[] args) {
        // String str = "abc123def456xx";
        // String str = "abc123def456xx78";
        String str = "012abc123def456xx";
        int startIndex = -1;
        String result = "";
        
        for (int i = 0; i < str.length(); i++) {
            if (!str.substring(i, i+1).matches("[0-9]")) {
                result += str.substring(i, i+1);
                startIndex = -1;
            } else {
                if (startIndex < 0) {
                    startIndex = i;
                    result += str.substring(i, i+1);
                } else if (startIndex >= 0) {
                    result = result.substring(0, startIndex) + str.substring(i, i+1) + result.substring(startIndex);
                }
            }
        }
        
        System.out.println("\nresult: " + result);
    }
}
