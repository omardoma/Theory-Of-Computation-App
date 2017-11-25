package com.theoryapp.model.sets;

public class IntegerSet extends Set {
	public IntegerSet(String name) {
		super(name);
	}

	public IntegerSet(String name, Object[] elements) {
		super(name, elements);
	}

	private Set bubbleSort(Set s) {
		for (int i = 0; i < s.getElements().length - 1; i++)
			for (int j = 0; j < s.getElements().length - i - 1; j++)
				if ((Integer) s.getElements()[j] > (Integer) s.getElements()[j + 1]) {
					Object tmp = s.getElements()[j];
					s.getElements()[j] = s.getElements()[j + 1];
					s.getElements()[j + 1] = tmp;
				}
		return s;
	}

	public Set union(Set s) {
		return bubbleSort(new IntegerSet("(" + getName() + " \u222A " + s.getName() + ")", super.unionHelper(s)));
	}

	public Set intersection(Set s) {
		return bubbleSort(
				new IntegerSet("(" + getName() + " \u2229 " + s.getName() + ")", super.intersectionHelper(s)));
	}

	public Set difference(Set s) {
		return bubbleSort(new IntegerSet("(" + getName() + " - " + s.getName() + ")", super.differenceHelper(s)));
	}

	public Set crossProduct(Set s) {
		return bubbleSort(new IntegerSet("(" + getName() + " x " + s.getName() + ")", super.crossProductHelper(s)));
	}
}