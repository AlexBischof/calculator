package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CalculatorTest {

    @Test
    public void test() {
	assertThat(new Calculator().add(new CalculatorParams(1, 2))).isEqualTo(3);
    }
}
