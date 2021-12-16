package kdp.diningSavages.onePot.concurrent;

public class Test {
	
	public static final int NUM_SAVAGES = 5;
	
	public static void main(String[] args) {
		
		Pot pot = new SemaphorePot();
		
		@SuppressWarnings("unused")
		Cook cook = new Cook(pot);
		
		Savage savages[] = new Savage[NUM_SAVAGES];
		for(int i = 0; i < NUM_SAVAGES; i++)
			savages[i] = new Savage(pot,i);
		
		
	}

}
