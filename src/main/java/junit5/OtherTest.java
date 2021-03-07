package junit5;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

/**
 * Assumptions（假设）与失败的断言直接相反，失败的假设不会导致测试失败；相反，失败的假设会导致测试中止。
 * 当继续执行给定的测试方法没有意义时，通常会使用假设—例如，如果测试依赖于当前运行时环境中不存在的内容。
 * 
 * 
 * @author zacattle
 *
 */
public class OtherTest {
	
	// Assumptions(假设) 相关方法测试
    @Test
    void testOnlyOnCiServer() {
        assumeTrue("CI2".equals(System.getenv("ENV")));
        System.out.println("这个代码不会得到执行");
        // remainder of test
    }

    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "终止测试: not on developer workstation");
        // remainder of test
    }

    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
                    // perform these assertions only on the CI server
                    assertEquals(2, 2);
                });

        // perform these assertions in all environments
        assertEquals("a string1", "a string");
    }
    
    
    // 条件启用测试方法
    
    /**
     * 可选 LINUX,MAC,SOLARIS,WINDOWS,OTHER
     */
    @Test
    @EnabledOnOs(OS.MAC)
    void onlyOnMacOs() {
        // ...
    	System.out.println("系统");
    }
    
    @Test
    @EnabledOnOs({ OS.LINUX,OS.WINDOWS })
    void onLinuxOrWindows() {
        // ...
    	System.out.println("系统");
    }
    
    @Test
    @DisabledOnOs(OS.WINDOWS)
    void notOnWindows() {
        // ...
    }
    
    // 查看 org.junit.jupiter.api.condition 包，查看更多条件运行测试方法的不同注解，
    // 比如 @EnabledOnJre(JAVA_8)，@EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*"),@EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")等
    
    
    
    
    
    

}

@Tag("fast")
@Tag("model")
class TagDemo {
	
    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
    }
    
}















