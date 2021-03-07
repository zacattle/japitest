package junit5;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @Tag 注解用于标记测试类或测试方法，在右键执行测试时，可进行配置，只执行指定tag的测试类或测试方法
 * 
 * 
 * @TestInstance(TestInstance.Lifecycle.PER_CLASS) 注解表示只存在一个测试实例，如果不标记该注解，默认每个测试方法执行时会新建一个新的实例
 *
 */
@Tag("fast")
@Tag("model")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TagTest {
	
    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
    	System.out.println(this);
    	System.out.println("测试");
    	
    }
    
    @Test
    @Tag("taxes1")
    void testingTaxCalculation1() {
    	System.out.println(this);
    	System.out.println("测试1");
    	
    }
    
    

}
