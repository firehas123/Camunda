package RandomJavaClass;

import java.util.logging.Logger;
import org.camunda.bpm.client.ExternalTaskClient;
import java.awt.Desktop;
import java.net.URI;

public class MyClass {
	 private final static Logger LOGGER = Logger.getLogger(MyClass.class.getName());

	  public static void main(String[] args) {
	    ExternalTaskClient client = ExternalTaskClient.create()
	        .baseUrl("http://localhost:8080/engine-rest")
	        .asyncResponseTimeout(10000) // long polling timeout
	        .build();

	    // subscribe to an external task topic as specified in the process
	    client.subscribe("Hassan-Task")
	        .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
	        .handler((externalTask, externalTaskService) -> {
	          // Put your business logic here

	          // Get a process variable
	          String item = (String) externalTask.getVariable("item");
	          int amount = (int) externalTask.getVariable("amount");
	          boolean isChecked = externalTask.getVariable("approved");
	  
	        	
	          LOGGER.info("Charging credit card with an amount of '" + amount + "'€ for the item '" + item + "'..."+" approved = "+ isChecked);

	          try {
	              Desktop.getDesktop().browse(new URI("https://docs.camunda.org/get-started/quick-start/complete"));
	          } catch (Exception e) {
	              e.printStackTrace();
	          }

	          // Complete the task
	          externalTaskService.complete(externalTask);
	        })
	        .open();
	  }
}
