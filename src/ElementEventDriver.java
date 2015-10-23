import java.util.HashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ElementEventDriver { // class that drives the region flashing thing

	private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
	@SuppressWarnings("rawtypes")
	private static HashMap<ElementEvent, Future> eventMap = new HashMap<ElementEvent, Future>();

	public ElementEventDriver() {
		
		executor.setMaximumPoolSize(5);
		
	}
	
	public void registerEvent(ElementEvent event, int time) {
		
		synchronized(eventMap) {
			
			if (!eventMap.containsKey(event)) {
				
				eventMap.put(event, executor.schedule(event, time, TimeUnit.MILLISECONDS));
				event.drawBeginning();
				
			} else {
				
				eventMap.remove(event).cancel(true);
				eventMap.put(event, executor.schedule(event, time, TimeUnit.MILLISECONDS));
				event.drawBeginning();
				
			}
			
		}
		
	}
	
	public static void removeEvent(ElementEvent event) {
		
		synchronized(eventMap) {
			
			eventMap.remove(event);
			
		}
		
	}
	
	public static int size() {
		
		synchronized(eventMap) {
			
			return eventMap.size();
			
		}
		
	}
	
}
