package com.github.hrothwell.datastructures.array;

import java.util.Comparator;

/**
 * Helper class to locate selected values from an Array of Comparable.
 * 
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public class Selection {

	/**
	 * Swap the two locations. 
	 * <p>
	 * Does nothing if pos1 == pos2
	 * 
	 * @param ar    An array of objects
	 * @param pos1  position of first element to swap
	 * @param pos2  position of second element to swap
	 * @exception   NullPointerException if ar is null
	 * @exception   ArrayIndexOutOfBoundsException if either pos1 or pos2 is invalid
	 */
	public static void swap (Object[]ar, int pos1, int pos2) {
		if (pos1 == pos2) { return; }
		
		Object tmp = ar[pos1];
		ar[pos1] = ar[pos2];
		ar[pos2] = tmp;
	}

	/**
	 * In linear time, group an array into two parts, those less than a certain value 
	 * (left), and those greater than or equal to a certain value (right).
	 * 
	 * @param ar           An array of Comparable objects
	 * @param left         lower bound index position (0 &le; left &lt; ar.length)
	 * @param right        upper bound index position (0 &le; left &lt; ar.length)
	 * @param pivotIndex   index around which the partition is being made.
	 * @param comparator   Externalize the comparison of two objects into this method. 
	 * @return             location of the pivot index properly positioned.
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static int partition (Object ar[], int left, int right, int pivotIndex, Comparator comparator) {
		Object pivot = ar[pivotIndex];
		swap (ar, right, pivotIndex);         // move pivot to the end of the array
		
		int store = left;
		for (int idx = left; idx < right; idx++) {
			if (comparator.compare(ar[idx], pivot) <= 0) {
				swap (ar, idx, store);
				store++;
			}
		}
		
		swap (ar, right, store);              // move pivot to its final place
		return store;		
	}

	/**
	 * Select an appropriate pivot within the [left, right] range.
	 * 
	 * For now, pick 'middle' of three values [left, mid, right]
	 * 
	 * @param ar          Array of Comparable objects
	 * @param left        The left-bounds within which to search (0 &le; left &lt; ar.length)
	 * @param right       The right-bounds within which to search (0 &le; right &lt; ar.length)
	 * @param comparator  Externalize the comparison of two objects into this method. 
	 * @return            index of the pivot within the ar[] array (0 &le; index &lt; ar.length)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static int selectPivotIndex (Object ar[], int left, int right, Comparator comparator) {
		int midIndex = (left+right)/2;
		
		int lowIndex = left;
		
		if (comparator.compare(ar[lowIndex], ar[midIndex]) >= 0) {
			lowIndex = midIndex;
			midIndex = left;
		} 
		
		// when we get here, we know that ar[lowIndex] < ar[midIndex]
		
		// select middle of [low,mid] and ar[right]
		if (comparator.compare(ar[right], ar[lowIndex]) <= 0) {
			return lowIndex;  // right .. low .. mid     so we return 
		} else if (comparator.compare(ar[right], ar[midIndex]) <= 0) {
			return right;  // low .. right .. mid
		}
		
		return midIndex; // why not
	}

	/**
	 * Select the kth value in an array (1 &le; k &le; right-left+1) through recursive partitioning.
	 * 
	 * Note that ar[] is altered during the execution of this method. left must be &le; right.
	 * Externally provided comparator is used.
	 * 
	 * @param ar          Array of objects
	 * @param k           The position in sorted order of the desired location (1 &le; k &le; right-left+1)
	 * @param left        The left-bounds within which to search (0 &le; left &lt; ar.length)
	 * @param right       The right-bounds within which to search (0 &le; right &lt; ar.length)
	 * @param comparator  Externalize the comparison of two objects into this method.
	 * @return            The Comparable object which is the kth in sorted order.
	 */
	@SuppressWarnings("rawtypes")
	public static Object select (Object[]ar, int k, int left, int right, Comparator comparator) {
		do {
			int idx = selectPivotIndex (ar, left, right, comparator);
			int pivotIndex = partition (ar, left, right, idx, comparator);

			if (left+k-1 == pivotIndex) {
				return ar[pivotIndex];
			} 

			// continue the loop, narrowing the range as appropriate.
			if (left+k-1 < pivotIndex) {
				// we are within the left-hand side of the pivot. k can stay the same
				right = pivotIndex - 1;
			} else {
				// we are within the right-hand side of the pivot. k must decrease by 
				// the size being removed.
				k -= (pivotIndex-left+1);
				left = pivotIndex + 1;				
			}
		} while (true);
	}
}
