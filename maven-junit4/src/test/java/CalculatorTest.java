import com.me.junit.Calculator;
import org.junit.Test;

public class CalculatorTest {
    private Calculator calculator = new Calculator();
    // 这是手动填写的，和原来目录结构不一样，为了演示
    // 1.与原方法保持一致
    // 2.或在原方法名前增加test前缀testAdd
    // 逻辑和预期相符则正确，并不是看是否异常或者有感叹号
    @Test
    public void testAdd() {
        int result = calculator.add(1, 2);
        System.out.println(result);
    }

    @Test
    public void testSubtract() {
        int result = calculator.subtract(1, 2);
        System.out.println(result);
    }

    @Test
    public void testMultiply() {
        int result = calculator.multiply(1, 2);
        System.out.println(result);
    }

    @Test
    public void testDivide() {
        double result = calculator.divide(1, 2);
        System.out.println(result);
    }

    @Test
    public void testDivide1() {
        // 测试除以0异常
        double result = calculator.divide(1, 0);
        System.out.println(result);
    }
}
