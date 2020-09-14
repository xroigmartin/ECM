package xroigmartin.ecm.api.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingRest {

	private static final Logger logger = LogManager.getLogger(LoggingRest.class);
	 
    @RequestMapping("/")
    public String index() throws InterruptedException{
    	int loop = 0;
    	
    	do {
	    	for(int i=0; i < 1000; i++) {
	    		if(i%2==0) {
	    			logger.trace("A TRACE a Message");
	    		}
	    		if(i%3==0) {
	    			logger.debug("A DEBUG Message");
	    		}
	    		if(i%5==0) {
	    			logger.info("An INFO Message");
	    		}
	    		if(i%7==0) {
	    			logger.warn("A WARN Message");
	    		}
	    		if(i%11==0) {
	    			logger.error("An ERROR Message");
	    		}
	    		if(i%13==0) {
	    			logger.fatal("An ERROR Message");
	    		}
	    	}
	    	Thread.sleep(1000);
	    	loop++;
    	}while(loop < 10);
 
        return "Howdy! Check out the Logs to see the output...";
    }
    
}
