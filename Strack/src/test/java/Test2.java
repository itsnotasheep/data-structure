import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Random;
import java.util.Stack;
class Solution {
    public int reverse(int x) {
        System.out.println(x);
        String str=x+"";
        StringBuilder builder=new StringBuilder();
        if (str.charAt(0) == '-'){
            builder.append(str.charAt(0));
        }
        for (int length = str.length()-1; length > 0; length--) {
            char c = str.charAt(length);
            builder.append(c);
        }
        if (str.charAt(0) != '-'){
            builder.append(str.charAt(0));
        }
        System.out.println(builder.toString());
        Integer integer=null;
        try {
            integer=Integer.valueOf(builder.toString());
        } catch (NumberFormatException e) {
            integer=0;
//            e.printStackTrace();
        }
        return integer;
    }

    public static void main(String[] args) {
        Solution solution=new Solution();
        Random random=new Random();

        System.out.println(solution.reverse(Integer.MAX_VALUE));
    }


}