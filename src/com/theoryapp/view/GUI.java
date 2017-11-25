package com.theoryapp.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private MainPanel mainPanel;
	private SetsPanel setsPanel;
	private DfaPanel dfaPanel;
	private JPanel currentPanel;

	public GUI() {
		prepareFrame();
	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public SetsPanel getSetsPanel() {
		return setsPanel;
	}

	public DfaPanel getDfaPanel() {
		return dfaPanel;
	}

	public JPanel getCurrentPanel() {
		return currentPanel;
	}

	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public void setAss1Panel(SetsPanel setsPanel) {
		this.setsPanel = setsPanel;
	}

	public void setAss2Panel(DfaPanel dfaPanel) {
		this.dfaPanel = dfaPanel;
	}

	public void setCurrentPanel(JPanel currentPanel) {
		this.currentPanel = currentPanel;
	}

	private void prepareFrame() {
		setTitle("Theory of Computation App");
		setIconImage(new ImageIcon(getClass().getClassLoader().getResource("res/logo.png")).getImage());
		setMinimumSize(new Dimension(850, 850));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout());
		InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = getRootPane().getActionMap();
		AbstractAction back = new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				if (currentPanel instanceof SetsPanel)
					((SetsPanel) currentPanel).getBack().doClick();
				else if (currentPanel instanceof DfaPanel)
					((DfaPanel) currentPanel).getBack().doClick();
			}
		};
		AbstractAction submit = new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				if (currentPanel instanceof SetsPanel)
					((SetsPanel) currentPanel).getCalculate().doClick();
				else if (currentPanel instanceof DfaPanel)
					((DfaPanel) currentPanel).getTest().doClick();
			}
		};
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "back");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "submit");
		actionMap.put("back", back);
		actionMap.put("submit", submit);

		UIManager.put("Button.font", new Font(Font.MONOSPACED, Font.PLAIN, 18));
		UIManager.put("RadioButton.font", new Font(Font.MONOSPACED, Font.PLAIN, 18));
		UIManager.put("ComboBox.font", new Font(Font.MONOSPACED, Font.PLAIN, 18));
		UIManager.put("TextField.font", new Font(Font.MONOSPACED, Font.PLAIN, 18));
		UIManager.put("TextArea.font", new Font(Font.MONOSPACED, Font.PLAIN, 18));
		UIManager.put("Label.font", new Font(Font.MONOSPACED, Font.PLAIN, 18));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// Do nothing
		}
		mainPanel = new MainPanel();
		getContentPane().add(mainPanel, "Main");
		currentPanel = mainPanel;
	}
}
