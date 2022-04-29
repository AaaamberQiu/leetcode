package Grind75;

public class ValidPalindrome_125 {

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        s = s.trim();
        if(s.length() <= 1) return true;

        int start = 0, end = s.length()-1;
        while(start < end){
            // while里面加while记得边界
            while(start < end && !isAlphanumeric(s.charAt(start))) start++;
            while(start < end && !isAlphanumeric(s.charAt(end))) end--;
            if(start < end && s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }


    public boolean isAlphanumeric(char c){
        return Character.isDigit(c) || Character.isAlphabetic(c);
    }
}
