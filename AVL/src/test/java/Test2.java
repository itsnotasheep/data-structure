import org.junit.Test;

public class Test2 {
    @Test
    public void demo1(){
        String str = "{0}你好{1}";
        String[] arr = {"", "(t)"};
        for (int i = 0; i < arr.length; i++) {
            str = str.replace("{" + i + "}", arr[i]);
        }
        System.out.println(str);
    }
}
