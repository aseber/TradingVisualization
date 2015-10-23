
public class Counter {

	private long accesses;
	private long compares;
	private long sets;
	
	public Counter() {}
	
	public void incrementAccesses() {
		
		accesses++;
		
	}
	
	public void incrementCompares() {
		
		compares++;
		
	}
	
	public void incrementSets() {
		
		sets++;
		
	}
	
	public long getAccesses() {
		
		return accesses;
		
	}
	
	public long getCompares() {
		
		return compares;
		
	}
	
	public long getSets() {
		
		return sets;
		
	}
	
	public void resetCounters() {
		
		accesses = 0;
		compares = 0;
		sets = 0;
		
	}
	
}
