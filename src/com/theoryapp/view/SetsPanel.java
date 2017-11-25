package com.theoryapp.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class SetsPanel extends JPanel {
	private JPanel down;
	private JPanel tmp;
	private JScrollPane scrollPane;
	private JTextPane result;
	private JTextField set1;
	private JTextField set2;
	private JRadioButton union;
	private JRadioButton intersection;
	private JRadioButton difference;
	private JRadioButton crossProduct;
	private JRadioButton subset;
	private ButtonGroup operation;
	private JButton calculate;
	private JButton switchSets;
	private JButton back;

	public SetsPanel() {
		setLayout(new BorderLayout(20, 20));
		preparePanel();
	}

	public JTextField getSet1() {
		return set1;
	}

	public JTextField getSet2() {
		return set2;
	}

	public ButtonGroup getOperation() {
		return operation;
	}

	public JButton getCalculate() {
		return calculate;
	}

	public JButton getSwitchSets() {
		return switchSets;
	}

	public JButton getBack() {
		return back;
	}

	public JTextPane getResult() {
		return result;
	}

	public JRadioButton getUnion() {
		return union;
	}

	public JRadioButton getIntersection() {
		return intersection;
	}

	public JRadioButton getDifference() {
		return difference;
	}

	private void preparePanel() {
		back = new JButton("Back");
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		back.setFocusable(false);
		JPanel antiResize = new JPanel(new FlowLayout(FlowLayout.LEFT));
		antiResize.add(back);
		JLabel logo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("res/logo.png")));
		JLabel title = new JLabel("Set Manipulator");
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
		set1 = new JTextField();
		set1.setToolTipText("Ex: 1, 2, 3, 4");
		set1.setPreferredSize(new Dimension(0, 40));
		JLabel set1Label = new JLabel("Set 1");
		set1Label.setLabelFor(set1);
		set1Label.setHorizontalAlignment(JLabel.CENTER);
		set2 = new JTextField();
		set2.setPreferredSize(new Dimension(0, 40));
		set2.setToolTipText("Ex: A, B, C, D");
		JLabel set2Label = new JLabel("Set 2");
		set2Label.setLabelFor(set1);
		set2Label.setHorizontalAlignment(JLabel.CENTER);
		JPanel sets = new JPanel(new GridLayout(2, 2, 5, 5));
		sets.add(set1Label);
		sets.add(set2Label);
		sets.add(set1);
		sets.add(set2);
		switchSets = new JButton("\u21B9");
		switchSets.setFocusable(false);
		switchSets.setToolTipText("Switch Sets");
		union = new JRadioButton("Union");
		union.setActionCommand("Union");
		union.setSelected(true);
		union.setFocusPainted(false);
		intersection = new JRadioButton("Intersection");
		intersection.setFocusPainted(false);
		intersection.setActionCommand("Intersection");
		difference = new JRadioButton("Difference");
		difference.setFocusPainted(false);
		difference.setActionCommand("Difference");
		crossProduct = new JRadioButton("Cross Product");
		crossProduct.setFocusPainted(false);
		crossProduct.setActionCommand("Cross Product");
		subset = new JRadioButton("Subset");
		subset.setFocusPainted(false);
		subset.setActionCommand("Subset");
		operation = new ButtonGroup();
		operation.add(union);
		operation.add(intersection);
		operation.add(difference);
		operation.add(crossProduct);
		operation.add(subset);
		JPanel operationButtons = new JPanel();
		operationButtons.add(union);
		operationButtons.add(intersection);
		operationButtons.add(difference);
		operationButtons.add(crossProduct);
		operationButtons.add(subset);
		calculate = new JButton("Calculate");
		calculate.setFocusPainted(false);
		calculate.setToolTipText("Calculate result set.");
		calculate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		antiResize = new JPanel();
		antiResize.add(calculate);
		lineBreak = new JSeparator(SwingConstants.HORIZONTAL);
		tmp = new JPanel(new BorderLayout());
		tmp.add(antiResize, BorderLayout.NORTH);
		tmp.add(lineBreak, BorderLayout.CENTER);
		JPanel up = new JPanel(new BorderLayout(10, 10));
		up.add(operationButtons, BorderLayout.CENTER);
		up.add(tmp, BorderLayout.SOUTH);
		tmp = new JPanel(new BorderLayout(10, 10));
		antiResize = new JPanel();
		antiResize.add(switchSets);
		tmp.add(antiResize, BorderLayout.NORTH);
		antiResize = new JPanel(new BorderLayout());
		antiResize.setPreferredSize(new Dimension(10, 0));
		tmp.add(antiResize, BorderLayout.WEST);
		antiResize = new JPanel(new BorderLayout());
		antiResize.setPreferredSize(new Dimension(10, 0));
		tmp.add(antiResize, BorderLayout.EAST);
		tmp.add(sets, BorderLayout.CENTER);
		up.add(tmp, BorderLayout.NORTH);
		JLabel resultHeadline = new JLabel("Result");
		resultHeadline.setHorizontalAlignment(JLabel.CENTER);
		resultHeadline.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		result = new JTextPane();
		result.setEditable(false);
		scrollPane = new JScrollPane(result, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(0, 100));
		down = new JPanel(new BorderLayout(10, 10));
		down.add(resultHeadline, BorderLayout.NORTH);
		down.add(scrollPane, BorderLayout.CENTER);
		tmp = new JPanel();
		tmp.setPreferredSize(new Dimension(0, 100));
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
		set1.setText("");
		set2.setText("");
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
				"This program helps you compute common \nset operations, \nSet elements are written like Ex: A, B, C\nA blank set field will be considered an empty set.");
	}
}
