package com.theoryapp.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class DfaPanel extends JPanel {
	private JPanel down;
	private JPanel tmp;
	private JScrollPane scrollPane;
	private JTextPane result;
	private JTextField states;
	private JTextField alphabet;
	private JTextField transitions;
	private JComboBox<String> startState;
	private JTextField acceptStates;
	private JTextField inputString;
	private JButton test;
	private JButton back;

	public DfaPanel() {
		setLayout(new BorderLayout(20, 20));
		preparePanel();
	}

	public JTextField getStates() {
		return states;
	}

	public JTextField getAlphabet() {
		return alphabet;
	}

	public JTextField getInputString() {
		return inputString;
	}

	public JButton getTest() {
		return test;
	}

	public JButton getBack() {
		return back;
	}

	public JTextPane getResult() {
		return result;
	}

	public JPanel getDown() {
		return down;
	}

	public JPanel getTmp() {
		return tmp;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JTextField getTransitions() {
		return transitions;
	}

	public JComboBox<String> getStartState() {
		return startState;
	}

	public JTextField getAcceptStates() {
		return acceptStates;
	}

	private void preparePanel() {
		back = new JButton("Back");
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		back.setFocusable(false);
		JPanel antiResize = new JPanel(new FlowLayout(FlowLayout.LEFT));
		antiResize.add(back);
		JLabel logo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("res/logo.png")));
		JLabel title = new JLabel("DFA Simulator");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
		JSeparator lineBreak = new JSeparator(SwingConstants.HORIZONTAL);
		tmp = new JPanel(new BorderLayout());
		tmp.add(title, BorderLayout.NORTH);
		tmp.add(lineBreak, BorderLayout.CENTER);
		JPanel logoPanel = new JPanel(new BorderLayout());
		logoPanel.add(antiResize, BorderLayout.NORTH);
		logoPanel.add(logo, BorderLayout.CENTER);
		logoPanel.add(tmp, BorderLayout.SOUTH);
		KeyListener kL1 = new KeyListener() {
			public void keyTyped(KeyEvent e) {

			}

			public void keyReleased(KeyEvent e) {
				startState.setEnabled(true);
				startState.setModel(
						new DefaultComboBoxModel<String>(states.getText().trim().replaceAll(" ", "").split(",")));
				if (states.getText().isEmpty()) {
					startState.setModel(new DefaultComboBoxModel<String>(new String[] { "No states defined." }));
					startState.setEnabled(false);
				}
			}

			public void keyPressed(KeyEvent e) {

			}
		};
		states = new JTextField();
		states.setToolTipText("Ex: 00, 01, 10, 11");
		states.setPreferredSize(new Dimension(0, 40));
		states.addKeyListener(kL1);
		JLabel statesLabel = new JLabel("States ( Q )");
		statesLabel.setLabelFor(states);
		statesLabel.setHorizontalAlignment(JLabel.CENTER);
		statesLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
		alphabet = new JTextField();
		alphabet.setPreferredSize(new Dimension(0, 40));
		alphabet.setToolTipText("Ex: 0, 1");
		JLabel alphabetLabel = new JLabel("Alphabet ( Σ )");
		alphabetLabel.setLabelFor(alphabet);
		alphabetLabel.setHorizontalAlignment(JLabel.CENTER);
		transitions = new JTextField();
		transitions.setPreferredSize(new Dimension(0, 40));
		transitions.setToolTipText("Ex: (00, 1) = 01, (00, 0) = 00");
		JLabel transitionsLabel = new JLabel("Transitions ( δ )");
		transitionsLabel.setLabelFor(transitions);
		transitionsLabel.setHorizontalAlignment(JLabel.CENTER);
		startState = new JComboBox<String>(new String[] { "No states defined." });
		startState.setEnabled(false);
		startState.setPreferredSize(new Dimension(0, 40));
		JLabel startStateLabel = new JLabel("Start State ( q0 )");
		startStateLabel.setLabelFor(startState);
		startStateLabel.setHorizontalAlignment(JLabel.CENTER);
		acceptStates = new JTextField();
		acceptStates.setPreferredSize(new Dimension(0, 40));
		acceptStates.setToolTipText("Ex: 10, 11");
		JLabel acceptStatesLabel = new JLabel("Accept States ( F )");
		acceptStatesLabel.setLabelFor(acceptStates);
		acceptStatesLabel.setHorizontalAlignment(JLabel.CENTER);
		JPanel sets = new JPanel(new GridLayout(6, 2, 5, 5));
		sets.add(statesLabel);
		sets.add(alphabetLabel);
		sets.add(states);
		sets.add(alphabet);
		sets.add(transitionsLabel);
		sets.add(startStateLabel);
		sets.add(transitions);
		sets.add(startState);
		sets.add(acceptStatesLabel);
		sets.add(new JLabel()); // Fill the gap.
		sets.add(acceptStates);
		test = new JButton("Test");
		test.setFocusPainted(false);
		test.setCursor(new Cursor(Cursor.HAND_CURSOR));
		test.setToolTipText("Test the string on the defined automata.");
		antiResize = new JPanel();
		antiResize.add(test);
		JPanel up = new JPanel(new BorderLayout(10, 10));
		up.add(antiResize, BorderLayout.SOUTH);
		lineBreak = new JSeparator(SwingConstants.HORIZONTAL);
		tmp = new JPanel(new BorderLayout());
		tmp.add(lineBreak, BorderLayout.NORTH);
		inputString = new JTextField();
		inputString.setPreferredSize(new Dimension(400, 40));
		inputString.setToolTipText("Ex: 01011100001");
		JLabel inputStringLabel = new JLabel("Input String");
		inputStringLabel.setLabelFor(inputString);
		inputStringLabel.setHorizontalAlignment(JLabel.CENTER);
		JPanel grid = new JPanel(new GridLayout(2, 1, 5, 5));
		grid.add(inputStringLabel);
		grid.add(inputString);
		antiResize = new JPanel();
		antiResize.add(grid);
		tmp.add(antiResize, BorderLayout.CENTER);
		up.add(tmp, BorderLayout.CENTER);
		tmp = new JPanel(new BorderLayout(10, 10));
		tmp.add(sets, BorderLayout.CENTER);
		antiResize = new JPanel();
		antiResize.setPreferredSize(new Dimension(10, 0));
		tmp.add(antiResize, BorderLayout.WEST);
		antiResize = new JPanel();
		antiResize.setPreferredSize(new Dimension(10, 0));
		tmp.add(antiResize, BorderLayout.EAST);
		up.add(tmp, BorderLayout.NORTH);
		JLabel resultHeadline = new JLabel("Result");
		resultHeadline.setHorizontalAlignment(JLabel.CENTER);
		result = new JTextPane();
		result.setEditable(false);
		scrollPane = new JScrollPane(result, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(0, 60));
		lineBreak = new JSeparator(SwingConstants.HORIZONTAL);
		down = new JPanel(new BorderLayout(10, 10));
		down.add(lineBreak, BorderLayout.NORTH);
		down.add(resultHeadline, BorderLayout.CENTER);
		down.add(scrollPane, BorderLayout.SOUTH);
		tmp = new JPanel();
		tmp.setPreferredSize(new Dimension(0, 60));
		add(logoPanel, BorderLayout.NORTH);
		add(up, BorderLayout.CENTER);
		add(tmp, BorderLayout.SOUTH);
	}

	public void showResult() {
		remove(tmp);
		add(down, BorderLayout.SOUTH);
		repaint();
		revalidate();
	}

	private void clear() {
		states.setText("");
		alphabet.setText("");
		transitions.setText("");
		startState.setModel(new DefaultComboBoxModel<String>(new String[] { "No states defined." }));
		startState.setEnabled(false);
		acceptStates.setText("");
		inputString.setText("");
		remove(down);
		add(tmp, BorderLayout.SOUTH);
		repaint();
		revalidate();
	}

	public void welcome() {
		clear();
		Timer t = new Timer(400, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMessage();
			}
		});
		t.setRepeats(false);
		t.start();
	}

	private void showMessage() {
		JOptionPane.showMessageDialog(this,
				"This program helps you test your implemented DFA, \ndefine the DFA and enter an input string to test it, \nHover over each form for an example of an input.\nAll DFA forms are required.\nA blank input string will be considered an empty string.\nTransitions are written like \nEx: (state, input)=nextState - (state, input)=nextState");
	}
}
