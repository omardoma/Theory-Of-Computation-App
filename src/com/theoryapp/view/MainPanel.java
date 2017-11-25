package com.theoryapp.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	private JButton sets;
	private JButton dfaSim;
	private ArrayList<JButton> assignments;

	public MainPanel() {
		setLayout(new BorderLayout(0, 100));
		preparePanel();
	}

	public JButton getSets() {
		return sets;
	}

	public JButton getDfaSim() {
		return dfaSim;
	}

	public ArrayList<JButton> getAssignments() {
		return assignments;
	}

	private void preparePanel() {
		sets = new JButton("Sets Manipulator");
		sets.setFocusPainted(false);
		sets.setCursor(new Cursor(Cursor.HAND_CURSOR));
		dfaSim = new JButton("DFA Simulator");
		dfaSim.setFocusPainted(false);
		dfaSim.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel logo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("res/logo.png")));
		logo.setPreferredSize(new Dimension(0, 200));
		JTextArea welcome = new JTextArea(
				"Welcome, this program helps you study CSEN502 by letting you test and compute results for several algorithms and problems that you should be solving yourself through out the course to compare them with your output.");
		welcome.setEditable(false);
		welcome.setLineWrap(true);
		welcome.setWrapStyleWord(true);
		welcome.setOpaque(false);
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttons.setPreferredSize(new Dimension(0, 200));
		buttons.add(sets);
		buttons.add(dfaSim);
		JPanel fillGaps = new JPanel();
		fillGaps.setPreferredSize(new Dimension(100, 0));
		add(fillGaps, BorderLayout.WEST);
		fillGaps = new JPanel();
		fillGaps.setPreferredSize(new Dimension(100, 0));
		add(fillGaps, BorderLayout.EAST);
		add(logo, BorderLayout.NORTH);
		add(welcome, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		assignments = new ArrayList<JButton>();
		assignments.add(sets);
		assignments.add(dfaSim);
	}
}
