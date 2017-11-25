package com.theoryapp.model.dfa;

import java.util.Map;

import com.theoryapp.model.sets.StringSet;

public class DFA {
	private StringSet states;
	private StringSet alphabet;
	private Map<String, Map<String, String>> transitions;
	private String startState;
	private StringSet acceptStates;
	private String currentState;
	private String map;

	public DFA(StringSet states, StringSet alphabet, Map<String, Map<String, String>> transitions, String startState,
			StringSet acceptStates) {
		this.states = states;
		this.alphabet = alphabet;
		this.transitions = transitions;
		this.startState = startState;
		this.acceptStates = acceptStates;
		currentState = startState;
		map = "";
	}

	public StringSet getStates() {
		return states;
	}

	public StringSet getAlphabet() {
		return alphabet;
	}

	public Map<String, Map<String, String>> getTransitions() {
		return transitions;
	}

	public String getStartState() {
		return startState;
	}

	public StringSet getAcceptStates() {
		return acceptStates;
	}

	public String getCurrentState() {
		return currentState;
	}
	
	public String getMap() {
		return map;
	}

	public boolean testString(String inputTape) {
		if(inputTape.trim().isEmpty())
			return true;
		for (int i = 0; i < inputTape.length(); i++) {
			String head = inputTape.charAt(i) + "";
			boolean inAlphabet = false;
			for (int j = 0; j < alphabet.getElements().length; j++)
				if (head.equals(alphabet.getElements()[j])) {
					inAlphabet = true;
					break;
				}
			if (!inAlphabet)
				return false;
			map+=currentState + "-> ";
			currentState = transitions.get(currentState).get(head);
		}
		for (int i = 0; i < acceptStates.getElements().length; i++)
			if (currentState.equals(acceptStates.getElements()[i]))
				return true;
		return false;
	}

	public DFA merge(DFA otherDFA) {
		return null;
	}
}
