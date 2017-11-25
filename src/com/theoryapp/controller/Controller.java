package com.theoryapp.controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.theoryapp.model.dfa.DFA;
import com.theoryapp.model.sets.Set;
import com.theoryapp.model.sets.StringSet;
import com.theoryapp.view.SetsPanel;
import com.theoryapp.view.DfaPanel;
import com.theoryapp.view.GUI;
import com.theoryapp.view.MainPanel;

public class Controller implements ActionListener {
	private GUI gui;

	public Controller() {
		gui = new GUI();
		addMainPanelListeners();
		gui.setVisible(true);
	}

	public static void main(String[] args) {
		new Controller();
	}

	public void actionPerformed(ActionEvent e) {

		if (gui.getCurrentPanel() instanceof MainPanel) {
			if (e.getSource() == gui.getMainPanel().getSets()) {
				if (gui.getSetsPanel() == null) {
					gui.setAss1Panel(new SetsPanel());
					addAss1PanelListeners();
					gui.getContentPane().add(gui.getSetsPanel(), "Assignment1");
				}
				((CardLayout) gui.getContentPane().getLayout()).show(gui.getContentPane(), "Assignment1");
				gui.getSetsPanel().welcome();
				gui.setCurrentPanel(gui.getSetsPanel());
			} else if (e.getSource() == gui.getMainPanel().getDfaSim()) {
				if (gui.getDfaPanel() == null) {
					gui.setAss2Panel(new DfaPanel());
					addAss2PanelListeners();
					gui.getContentPane().add(gui.getDfaPanel(), "Assignment2");
				}
				((CardLayout) gui.getContentPane().getLayout()).show(gui.getContentPane(), "Assignment2");
				gui.getDfaPanel().welcome();
				gui.setCurrentPanel(gui.getDfaPanel());
			}
		} else if (gui.getCurrentPanel() instanceof SetsPanel) {
			if (e.getSource() == gui.getSetsPanel().getCalculate()) {
				String[] tmp = gui.getSetsPanel().getSet1().getText().isEmpty() ? new String[0]
						: gui.getSetsPanel().getSet1().getText().trim().replaceAll(" ", "").split(",");
				Set set1 = new StringSet("Set 1", tmp);
				tmp = gui.getSetsPanel().getSet2().getText().isEmpty() ? new String[0]
						: gui.getSetsPanel().getSet2().getText().trim().replaceAll(" ", "").split(",");
				Set set2 = new StringSet("Set 2", tmp);
				calculateSet(set1, set2, gui.getSetsPanel().getOperation().getSelection().getActionCommand());
				gui.getSetsPanel().showResult();
			} else if (e.getSource() == gui.getSetsPanel().getSwitchSets()) {
				String tmp = gui.getSetsPanel().getSet1().getText();
				gui.getSetsPanel().getSet1().setText(gui.getSetsPanel().getSet2().getText());
				gui.getSetsPanel().getSet2().setText(tmp);
			} else {
				((CardLayout) gui.getContentPane().getLayout()).first(gui.getContentPane());
				gui.setCurrentPanel(gui.getMainPanel());
			}

		} else if (gui.getCurrentPanel() instanceof DfaPanel) {
			if (e.getSource() == gui.getDfaPanel().getTest()) {
				if (gui.getDfaPanel().getStates().getText().trim().isEmpty()
						|| gui.getDfaPanel().getAlphabet().getText().trim().isEmpty()
						|| gui.getDfaPanel().getTransitions().getText().trim().isEmpty()
						|| gui.getDfaPanel().getAcceptStates().getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(gui.getDfaPanel(), "All DFA forms are required.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String[] tmp = gui.getDfaPanel().getStates().getText().isEmpty() ? new String[0]
						: gui.getDfaPanel().getStates().getText().trim().replaceAll(" ", "").split(",");
				StringSet states = new StringSet("Q", tmp);
				tmp = gui.getDfaPanel().getAlphabet().getText().isEmpty() ? new String[0]
						: gui.getDfaPanel().getAlphabet().getText().trim().replaceAll(" ", "").split(",");
				StringSet alphabet = new StringSet("Σ", tmp);
				tmp = gui.getDfaPanel().getTransitions().getText().isEmpty() ? new String[0]
						: gui.getDfaPanel().getTransitions().getText().trim().replaceAll(" ", "").split("-");
				StringSet transitions = new StringSet("δ", tmp);
				String startState = ((String) (gui.getDfaPanel().getStartState().getSelectedItem()));
				tmp = gui.getDfaPanel().getAcceptStates().getText().isEmpty() ? new String[0]
						: gui.getDfaPanel().getAcceptStates().getText().trim().replaceAll(" ", "").split(",");
				StringSet acceptStates = new StringSet("F", tmp);
				testDFAInputTape(states, alphabet, transitions, startState, acceptStates,
						gui.getDfaPanel().getInputString().getText());
				gui.getDfaPanel().showResult();

			} else {
				((CardLayout) gui.getContentPane().getLayout()).first(gui.getContentPane());
				gui.setCurrentPanel(gui.getMainPanel());
			}
		}
	}

	private void addMainPanelListeners() {
		for (JButton button : gui.getMainPanel().getAssignments())
			button.addActionListener(this);
	}

	private void addAss1PanelListeners() {
		gui.getSetsPanel().getSwitchSets().addActionListener(this);
		gui.getSetsPanel().getCalculate().addActionListener(this);
		gui.getSetsPanel().getBack().addActionListener(this);
	}

	private void addAss2PanelListeners() {
		gui.getDfaPanel().getTest().addActionListener(this);
		gui.getDfaPanel().getBack().addActionListener(this);
	}

	private void calculateSet(Set op1, Set op2, String operation) {
		Object result = null;
		switch (operation) {
		case "Union":
			result = op1.union(op2);
			break;
		case "Intersection":
			result = op1.intersection(op2);
			break;
		case "Difference":
			result = op1.difference(op2);
			break;
		case "Cross Product":
			result = op1.crossProduct(op2);
			break;
		case "Subset":
			result = "(Set 1 \u2282 Set 2): " + op1.isSubset(op2);
			break;
		}
		gui.getSetsPanel().getResult().setText(result.toString());
	}

	private void testDFAInputTape(StringSet states, StringSet alphabet, Set transitions, String startState,
			StringSet acceptStates, String inputTape) {
		// int j = 0;
		// for (int i = 0; i < transitions.getElements().length; i+=j) {
		// boolean watch = false;
		// int count = 0;
		// if (transitions.getElements()[i].equals("=")) {
		// watch = true;
		// for (j = i; j < transitions.getElements().length; j++) {
		// if (transitions.getElements()[i].equals("-")) {
		// watch = false;
		// }
		// if (transitions.getElements()[i].equals("(")) {
		//
		// }
		// }
		// }
		// }

		Map<String, Map<String, String>> transitionMap = new HashMap<String, Map<String, String>>();
		for (int i = 0; i < transitions.getElements().length; i++) {
			String current = ((String) transitions.getElements()[i]).replaceAll(" ", "").substring(1).replace(")", "");
			String[] part1 = current.split(",");
			if (part1.length == 0) {
				JOptionPane.showMessageDialog(gui.getCurrentPanel(),
						"Transitions format is uncorrect,\nPlease write it like the following example's format:\n(0, 0)=0 - (0,1)=1 - (1,0)=1 - (1,1)=0",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (transitionMap.get(part1[0]) == null)
				transitionMap.put(part1[0], new HashMap<String, String>());
			String[] part2 = part1[1].split("=");
			if (part2.length <= 1) {
				JOptionPane.showMessageDialog(gui.getCurrentPanel(),
						"Transitions format is uncorrect,\nPlease write it like the following example's format:\n(0, 0)=0 - (0,1)=1 - (1,0)=1 - (1,1)=0",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			transitionMap.get(part1[0]).put(part2[0], part2[1]);
		}
		DFA dfa = new DFA(states, alphabet, transitionMap, startState, acceptStates);
		boolean accepted = dfa.testString(inputTape);
		String result = accepted ? "accepted" : "rejected";
		if (inputTape.trim().isEmpty())
			inputTape = "\u03B5";
		gui.getDfaPanel().getResult()
				.setText("String: " + inputTape.trim() + " was " + result + ".\nTransitions: " + dfa.getMap().substring(0, dfa.getMap().length()-3));
	}
}
