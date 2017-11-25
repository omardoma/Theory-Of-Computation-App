package com.theoryapp.model.sets;

public class StringSet extends Set {

	public StringSet(String name) {
		super(name);
	}

	public StringSet(String name, Object[] elements) {
		super(name, elements);
	}

	public Set union(Set s) {
		return new StringSet("(" + getName() + " \u222A " + s.getName() + ")", super.unionHelper(s));
	}

	public Set intersection(Set s) {
		return new StringSet("(" + getName() + " \u2229 " + s.getName() + ")", super.intersectionHelper(s));
	}

	public Set difference(Set s) {
		return new StringSet("(" + getName() + " - " + s.getName() + ")", super.differenceHelper(s));
	}

	public Set crossProduct(Set s) {
		return new StringSet("(" + getName() + " x " + s.getName() + ")", super.crossProductHelper(s));
	}
}