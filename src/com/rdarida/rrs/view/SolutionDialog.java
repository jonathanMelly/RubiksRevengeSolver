package com.rdarida.rrs.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import com.rdarida.rrs.cube.Twist;

public class SolutionDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	
	public SolutionDialog(Gui gui) {
		super(gui);
		
		setTitle("Solution");
		setSize(Gui.WIDTH / 2, Gui.HEIGHT / 3);
		setResizable(false);
		setModal(true);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		getContentPane().add(textArea, BorderLayout.CENTER);
	}
	
	public void addSolution(ArrayList<Twist> solution) {
		ListIterator<Twist> iterator = solution.listIterator();
		StringBuilder text = new StringBuilder();
		
		while (iterator.hasNext()) {
			text.append(iterator.next().toString());
			
			if (iterator.hasNext()) {
				text.append(", ");
			}
		}
		
		textArea.setText(text.toString());
	}
	
	public void clear() {
		textArea.setText("");
	}
	
	@Override
	public void setVisible(boolean visible) {
		setLocationRelativeTo(getOwner());
		super.setVisible(visible);
	}
}