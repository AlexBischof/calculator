package calculator;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculatorParams implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Integer a;
    private Integer b;

    public CalculatorParams() {
    }

    public CalculatorParams(Integer a, Integer b) {
	this.a = a;
	this.b = b;
    }

    public Integer getA() {
	return a;
    }

    public Integer getB() {
	return b;
    }

    @Override
    public String toString() {
	return "CalculatorParams [a=" + a + ", b=" + b + "]";
    }

}