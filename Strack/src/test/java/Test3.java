import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Test3 {

    @Test
    public void demo1(){

        Date date=new Date();
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSSXXX");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        String str=sdf2.format(date);
        System.out.println(str);



    }
}
