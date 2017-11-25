package com.theoryapp.model.sets;

public abstract class Set {
	private String name;
	private Object[] elements;
	private int size;

	public Set(String name) {
		this.name = name;
		elements = new Object[20];
		size = 0;
	}

	public Set(String name, Object[] elements) {
		this(name);
		setElements(elements);
	}

	public Object[] getElements() {
		return elements;
	}

	public void setElements(Object[] elements) {
		boolean arrayValid = true;
		for (int i = 0; i < elements.length; i++)
			if (elements[i].equals("")) {
				elements[i] = null;
				arrayValid = false;
			}

		this.elements = arrayValid ? elements : getArrayWithRealSize(elements);
		this.size = this.elements.length;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	private Object[] getArrayWithRealSize(Object[] tmp) {
		int size = 0;
		for (int i = 0; i < tmp.length; i++)
			if (tmp[i] != null)
				size++;
		Object[] array = new Object[size];
		int index = 0;
		for (int i = 0; i < tmp.length; i++)
			if (tmp[i] != null)
				array[index++] = tmp[i];
		return array;
	}

	public boolean insert(Object o) {
		if (o != null)
			if (size < elements.length) {
				elements[size++] = o;
				return true;
			}
		return false;
	}

	public boolean remove(Object o) {
		if (size == 1) {
			elements[0] = null;
			size--;
			return true;
		}

		for (int i = 0; i < elements.length; i++)
			if (elements[i] != null && elements[i].equals(o)) {
				for (int j = i; j < elements.length - 1; j++)
					elements[j] = elements[j + 1];
				elements[elements.length - 1] = null;
				size--;
				return true;
			}
		return false;
	}

	public boolean isMember(Object o) {
		for (int i = 0; i < elements.length; i++)
			if (elements[i] != null)
				if (elements[i].equals(o))
					return true;
		return false;
	}

	public boolean isSubset(Set s) {
		if (size == 0)
			return true;
		else if (s.size < size)
			return false;
		boolean subset = true;
		for (int i = 0; i < size; i++)
			if (!s.isMember(elements[i])) {
				subset = false;
				break;
			}
		return subset;
	}

	public Object[] intersectionHelper(Set s) {
		int length = s.size < size ? s.size : size;
		int index = 0;
		Object[] intersect = new Object[length];
		for (int i = 0; i < s.elements.length; i++)
			if (isMember(s.elements[i])) {
				intersect[index] = s.elements[i];
				index++;
			}
		return getArrayWithRealSize(intersect);
	}

	public Object[] unionHelper(Set s) {
		Object[] union = new Object[size + s.size];
		int i;
		for (i = 0; i < size; i++)
			union[i] = elements[i];
		for (int j = 0; j < s.size; j++)
			if (!isMember(s.elements[j])) {
				union[i] = s.elements[j];
				i++;
			}
		return getArrayWithRealSize(union);
	}

	public Object[] differenceHelper(Set s) {
		Object[] difference = new Object[size];
		int index = 0;
		for (Object o : elements)
			if (o != null)
				if (!s.isMember(o)) {
					difference[index] = o;
					index++;
				}
		return getArrayWithRealSize(difference);
	}

	public Object[] crossProductHelper(Set s) {
		Object[] crossProduct = new Object[size * s.size];
		int index = 0;
		for (int i = 0; i < size; i++)
			for (int j = 0; j < s.size; j++)
				crossProduct[index++] = "(" + elements[i] + ", " + s.elements[j] + ")";
		return crossProduct;
	}

	public abstract Set union(Set s);

	public abstract Set intersection(Set s);

	public abstract Set difference(Set s);

	public abstract Set crossProduct(Set s);

	public String toString() {
		String s = name + ": { ";
		for (Object o : elements)
			if (o != null)
				s += (o + ", ");
		s = s.substring(0, size == 0 ? s.length() : s.length() - 2);
		s += " }";
		return s;
	}
}