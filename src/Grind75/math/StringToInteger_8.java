package Grind75.math;

public class StringToInteger_8 {

    public static int myAtoi(String s) {
        s = s.trim();
        int startIndex = 0;
        if(s.length() == 0) return 0;

        int sign = 1;
        if(s.charAt(startIndex) == '-' || s.charAt(startIndex) == '+'){
            sign = s.charAt(startIndex) == '-' ? -1 : 1;
            startIndex+=1;
        }

        int ret = 0;
        for(int i = startIndex; i < s.length(); i++){
            if(!Character.isDigit(s.charAt(i))) break;
            int num = s.charAt(i) - '0';
            if(sign < 0){
                ret = -ret;
                if(ret < Integer.MIN_VALUE/10 ||ret * 10 < Integer.MIN_VALUE + num) return Integer.MIN_VALUE;
            }else{
                if(ret > Integer.MAX_VALUE/10 || ret * 10 > Integer.MAX_VALUE - num) return Integer.MAX_VALUE;
            }
            ret = ret * 10 + num;
        }
        return ret * sign;

    }

    public static void main(String[] args) {
        int ret = myAtoi("   -42");
        System.out.println(ret);
    }

}
