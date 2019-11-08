package lab.recursion;

import java.util.ArrayList;

class Recursive {
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private static ArrayList<Integer> reversedList = new ArrayList<Integer>();

	// this one builds a list containing values from 1 to n
	public static ArrayList<Integer> buildList(int n) {
		// write this in terms of a recursive call using a smaller n
		ArrayList<Integer> tempList = null;
		if (n <= 0) {
			return new ArrayList<>();
		} else {
			tempList = new ArrayList<>();
			tempList.add(0, n);
			tempList.addAll(0, buildList(n - 1));
		}
		return tempList;
	}

	// this one reverses a list in-place
	public static ArrayList<Integer> reverse(ArrayList<Integer> lst) {
		if (lst.size() > 1) {
			int temp = lst.get(0);
			lst.remove(0);
			reverse(lst);
			lst.add(temp);
		}
		return lst;
	}

	// return the sum of all Integers in the ArrayList
	// this should not change the lst argument
	public static int add(ArrayList<Integer> lst) {
		return add(lst, 0);
	}

	// Print out all the contents of the argument
	// this should not change the lst argument
	public static void print(ArrayList<Integer> lst) {
		print(lst, 0);
		return;
	}

	private static int add(ArrayList<Integer> lst, int index) {
		if (index == lst.size()) {
			return 0;
		}
		return lst.get(index) + add(lst, index + 1);

	}

	private static void print(ArrayList<Integer> lst, int index) {
		if (index == lst.size()) {
			return;
		}
		System.out.print(lst.get(index) + ", ");
		print(lst, index + 1);
	}

	public static void main(String[] args) {
		ArrayList<Integer> lst = Recursive.buildList(5);
		System.out.println(lst);

		reverse(lst);
		System.out.println(lst);

		Recursive.print(lst);
		System.out.println();
		System.out.println(Recursive.add(lst));
	}

}