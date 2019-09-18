import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Test1 {

    @Test
    public void Demo1(){
        Array<Integer> array=new Array<>(4);
        array.addLast(4);
        array.addLast(24);
        array.addLast(34);
        array.addLast(44);
        System.out.println(array);

        array.add(1,4);
        System.out.println(array);

        array.remove(2);
        System.out.println(array);
//
//        System.out.println(array.findAll(4));
//
//        System.out.println(array.removeAllElement(4));
//        System.out.println(array);
    }

    @Test
    public void Demo2(){
        Array<Student> array=new Array<>();
        array.addLast(new Student("Alice",100));
        array.addLast(new Student("Bob",66));
        array.addLast(new Student("Charlie",88));
        Student a=new Student("Charlie",88);
        array.addLast(a);
        System.out.println(array);

        Array charlie = array.findAll(a);
        System.out.println(charlie);
    }

    @Test
    public void Demo3() throws UnsupportedEncodingException {
        String fileName="CNPCIC 油田(区块)生产数据.xlsx";
        String str=new String(fileName.getBytes(),"ISO-8859-1");
        String str2= URLEncoder.encode(fileName,"UTF-8");
        System.out.println(str2);
        String replace = str2.replace("+", "%20");
        System.out.println(replace);
        //CNPCIC%252B%E6%B2%B9%E7%94
    }
}
