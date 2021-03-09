package logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

public class CustomConfigrationTest {
	
	final static Logger logger = LoggerFactory.getLogger(CustomConfigrationTest.class);

	public static void main(String[] args) {
		
	    // assume SLF4J is bound to logback in the current environment
	    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
	    
	    try {
	      JoranConfigurator configurator = new JoranConfigurator();
	      configurator.setContext(context);
	      // Call context.reset() to clear any previous configuration, e.g. default 
	      // configuration. For multi-step configuration, omit calling context.reset().
	      context.reset(); 
	      // 参数设置配置文件地址
	      configurator.doConfigure(args[0]);
	    } catch (JoranException je) {
	      // StatusPrinter will handle this
	    }
	    StatusPrinter.printInCaseOfErrorsOrWarnings(context);

	    logger.info("Entering application.");

	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    logger.info("Exiting application.");
	  

	}

}
