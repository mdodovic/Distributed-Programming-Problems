package kdp.diningSavages.multiplePots.concurrent;

public class Test {

	public static final int NUM_SAVAGES = 5;
	public static final int NUM_FOOD_TYPES = 4;
	public static final int MAX_FOOD_PER_POT = 3;
	
	public static void main(String[] args) {
		
		Pot pot = new SemaphorePot(NUM_FOOD_TYPES, MAX_FOOD_PER_POT);
		
		new CookGui(pot);
		
		SavageGui[] savages = new SavageGui[NUM_SAVAGES];
		for(int i = 0; i < NUM_SAVAGES; i++)
			savages[i] = new SavageGui(pot, i);
			
	}

}
