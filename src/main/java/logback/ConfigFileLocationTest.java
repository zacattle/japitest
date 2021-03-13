package logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.util.ContextInitializer;

public class ConfigFileLocationTest {
	
	public static void main(String[] args) {
		// 自定义配置文件地址
		System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "E:\\workspace\\eclipse\\japitest\\src\\main\\resources\\logback-test2.xml");
		// 
		Logger log = LoggerFactory.getLogger(ConfigFileLocationTest.class);
		log.info("测试");
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("自动侦测配置文件变化");
		
		
	}
	

}
