package childCare.rmi.concurrent;

public interface Kindergarden {
	
	int NUMBER_OF_CHILDREN_PER_NANNY = 3;
	
	boolean bringChildren(int numOfChildren);
	void takeChildren(int numOfChildren);
	
	void nannyEnter();
	void nannyExit();
	
}
