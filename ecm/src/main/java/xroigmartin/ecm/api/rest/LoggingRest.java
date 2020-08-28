package xroigmartin.ecm.api.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingRest {

	private static final Logger logger = LogManager.getLogger(LoggingRest.class);
	 
    @RequestMapping("/")
    public String index() {
    	for(int i=0; i < 1000; i++) {
	        logger.trace("A TRACE a Message");
	        logger.debug("A DEBUG Message");
	        logger.info("An INFO Message");
	        logger.warn("A WARN Message");
	        logger.error("An ERROR Message");
    	}
 
        return "Howdy! Check out the Logs to see the output...";
    }
    
}
