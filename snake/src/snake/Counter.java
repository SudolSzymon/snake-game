package snake;

/**
 *
 *
 *
 */
public class Counter {
	// How many of this type exist in the simulation.
	private int count;

	/**
	 * Provide a name for one of the simulation types.
	 *
	 * @param name
	 * 
	 */
	public Counter() {

		count = 0;
	}

	/**
	 * @return The current count for this type.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Increment the current count by one.
	 */
	public void increment() {
		count++;
	}

	/**
	 * Reset the current count to zero.
	 */
	public void reset() {
		count = 0;
	}
}
