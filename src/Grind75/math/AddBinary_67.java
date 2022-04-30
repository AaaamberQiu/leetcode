package Grind75.math;

public class AddBinary_67 {

    public String addBinary(String a, String b) {
        if(a.length() < b.length()) return addBinary(b, a);
        StringBuilder sb = new StringBuilder();

        // a is the shorter string
        int i = a.length()-1, j = b.length()-1;
        int carry = 0;
        while(i >= 0){
            int x = Integer.parseInt(a.substring(i, i+1));
            int y = Integer.parseInt(b.substring(j, j+1));
            sb.append((x + y + carry)%2);
            carry = (x + y + carry)/2;
            i--;
            j--;
        }

        while(j >= 0){
            int y = Integer.parseInt(b.substring(j, j+1));
            sb.append((y + carry)%2);
            carry = (y + carry)/2;
            j--;
        }

        if(carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}
