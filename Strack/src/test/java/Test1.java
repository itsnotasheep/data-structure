import org.junit.Test;
import java.util.Stack;
public class Test1 {

    @Test
    public void Demo1(){
        ArrayStack<Integer> stack=new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }

    @Test
    public void Demo2(){
        System.out.println(isValid("){{[]}}"));
    }

    public boolean isValid(String s){
        Stack<Character> stack=new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if (c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar=stack.pop();
                if (c == '(' && topChar != ')'){
                    return false;
                }else if (c == '[' && topChar != ']'){
                    return false;
                }else if (c == '{' && topChar != '}'){
                    return false;
                }
            }
        }
        return stack.isEmpty();

    }
}
