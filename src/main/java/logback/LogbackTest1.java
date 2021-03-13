package logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

/**
 * 简单测试
 * @author zacattle
 *
 */
public class LogbackTest1 {
	
	/**
	 * logback核心组件包含 Logger, Appender 和 Layouts
	 * 
	 * Logger：进行日志输出，Logger中包含Appender
	 * Appender：输出日志到指定的目的地 Appender中包含Layouts
	 * Layouts：对日志进行格式化
	 * 
	 * logger的名字会被自动赋值为 logback.LogbackTest1 即类的简单名字
	 *
	 * logback 默认在类路径下先后查找logback-test.xml,logback.groovy,logback.xml,查找META-INFO/services/ch.qos.logback.classic.spi.Configurator目录实现类
	 * 如果都没有发现相应的配置的话，会通过 BasicConfigurator默认启用一个 ConsoleAppender（表示日志输出的目的地） 进行控制台输出
	 * 
	 * 其他方式的输出需要进行配置（文件输出，数据库输出等）
	 * 日志输出级别分为：(TRACE, DEBUG, INFO, WARN and ERROR) 低到高等级(TRACE < DEBUG < INFO <  WARN < ERROR)，定义在ch.qos.logback.classic.Level类中
	 * 每个日志logger最终确定的日志输出级别，通过向上查询第一个非空的logger,ROOT logger的默认日志输出级别为 DEBUG
	 */
	static Logger logger = LoggerFactory.getLogger(LogbackTest1.class);
	
	public static void main(String[] args) {

		// 不同的日志级别输出，因为默认的级别为DEBUG,所以 logger.trace("logger简单测试");这行不会得到输出
		logger.trace("logger简单测试");
		logger.info("info简单测试");
		logger.debug("debug简单测试");
		logger.warn("warn简单测试");
		logger.error("error简单测试");
		

		// 更改日志级别
	    ch.qos.logback.classic.Logger realLogger = (ch.qos.logback.classic.Logger)logger;
	    realLogger.setLevel(Level.WARN);
	    
		logger.trace("logger level trace测试");
		logger.info("logger level info测试");
		logger.debug("logger level debug测试");
		logger.warn("logger level warn测试");
		logger.error("logger level error简单测试");
		
		realLogger.setLevel(Level.DEBUG);
		
		
		// 测试日志的层级关系,所有Logger默认的父层级为root
		Logger parent = LoggerFactory.getLogger("com.test");
		ch.qos.logback.classic.Logger realParentLogger = (ch.qos.logback.classic.Logger)parent;
		realParentLogger.info("parent默认的日志级别-继承自root:"+realParentLogger.getLevel());
		
		Logger child1 = LoggerFactory.getLogger("com.test.child2");
		ch.qos.logback.classic.Logger realChild1Logger = (ch.qos.logback.classic.Logger)child1;
		realChild1Logger.info("child1默认的日志级别:"+realChild1Logger.getLevel());
		
		
		Logger child2 = LoggerFactory.getLogger("com.test.child2");
		ch.qos.logback.classic.Logger realChild2Logger = (ch.qos.logback.classic.Logger)child2;
		realChild2Logger.info("child2默认的日志级别:"+realChild2Logger.getLevel());
		realChild2Logger.trace("不会得到输出");
		realChild2Logger.setLevel(Level.INFO);
		realChild2Logger.warn("warn级别能够得到输出");
		realChild2Logger.debug("realChild2Logger-DEBUG日志，不会得到输出");
		
		
		
		
		
		
		
		
		
		
		
	    
	}
	
	
	

}
