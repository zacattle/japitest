package junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * 简单测试方法
 */
public class SimpleTest {
	
	@BeforeAll //该注解标记的方法必须为静态方法
    static void beforeAll() {
		System.out.println("beforeAll");
    }
	
	/**
	 * 每个测试方法之前执行，不包含表示为@Disabled注解的方法
	 */
	@BeforeEach
	void beforeEach() {
		System.out.println("beforeEach");
	}
	
	

    @Test
    void firstTest() {
        assertEquals(2, 1+1);
    }
    
    
    @Test
    void failingTest() {
        fail("a failing test");
    }
    
    @Test
    @Disabled("表示不会被执行的方法，可用在单个方法或整个类上")
    void skippedTest() {
        // not executed
    	System.out.println("beforeAll");
    }
    
    @AfterEach
    void afterEach() {
    	System.out.println("afterEach");
    }

    @AfterAll //该注解标记的方法必须为静态方法
    static void afterAll() {
    	System.out.println("afterAll");
    }
    
    
    

}
