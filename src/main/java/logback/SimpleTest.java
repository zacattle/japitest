package logback;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class SimpleTest {
	
	/**
	 * logger 的名字会被自动赋值为 logback.SimpleTest 即类的简单名字
	 * 当没有配置文件被发现时，默认启用一个 ConsoleAppender（表示日志输出的目的地） 进行控制台输出
	 * 其他方式的输出需要进行配置（文件输出，数据库输出等）
	 * 日志输出级别分为：(TRACE, DEBUG, INFO, WARN and ERROR) 低到高等级(TRACE < DEBUG < INFO <  WARN < ERROR)，定义在ch.qos.logback.classic.Level类中
	 * 每个日志logger最终确定的日志输出级别，通过向上查询第一个非空的logger,ROOT logger的默认日志输出级别为 DEBUG
	 */
	Logger logger = LoggerFactory.getLogger(SimpleTest.class);
	
	@Test
	void simpleTest(){
		logger.trace("logger简单测试");
		logger.info("info简单测试");
		logger.debug("debug简单测试");
		logger.warn("warn简单测试");
		logger.error("error简单测试");
		
		// 输出内部状态
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	    StatusPrinter.print(lc);
	    
	    // 得到最顶级的logger 所有logger的父
	    Logger rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
	    rootLogger.info(rootLogger.getName());
	    
	    
	}
	
	
	

}