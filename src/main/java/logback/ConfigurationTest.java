package logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.util.ContextInitializer;

public class ConfigurationTest {

	public static void main(String[] args) {
		
		// 自定义配置文件地址
		System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "D:\\workplace\\eclipse\\japitest\\src\\main\\resources\\logback-config.xml");
		Logger log = LoggerFactory.getLogger(ConfigFileLocationTest.class);
		log.info("info");
		log.debug("debug");
		

	}

}
