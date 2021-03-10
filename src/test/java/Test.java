import java.util.Arrays;
import java.util.List;

public class Test {
    private int value;
    public Test(int n){
        value=n;
    }
    public Test(Test test){
        value=test.value;
    }
    void print(){
        System.out.println(value);
    }
    public static void main(String[] args) {
        Test t=new Test(10);
        Test t1= new Test(t);
        t1.print();

    }
}
