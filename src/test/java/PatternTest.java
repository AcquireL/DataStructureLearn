import java.util.regex.Pattern;

public class PatternTest {
    public static void main(String[] args) {
        Pattern p= Pattern.compile("bs_test");
        String pattern = p.pattern();//返回 \w+
        System.out.println(pattern);
    }
}
