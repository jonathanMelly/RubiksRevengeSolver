package com.rdarida.rrs.view;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private JMenuItem solveCube;
	private JMenuItem play;
	private JMenuItem previous;
	private JMenuItem next;
	
	public MenuBar(ActionListener actionListener) {
		super();
		
		createFileMenu(actionListener);
		createCubeMenu(actionListener);
		createHelpMenu(actionListener);
		
		setEnabled(false);
	}
	
	private void createFileMenu(ActionListener actionListener) {
		JMenu file = new JMenu("File");
		
		JMenuItem item = new JMenuItem("New Cube...");
		item.setName("NewCube");
		item.addActionListener(actionListener);
		file.add(item);
		
		file.addSeparator();
		
		item = new JMenuItem("Exit");
		item.setName("Exit");
		item.addActionListener(actionListener);
		file.add(item);
		
		add(file);
	}
	
	private void createCubeMenu(ActionListener actionListener) {
		JMenu cube = new JMenu("Cube");
		
		solveCube = new JMenuItem("Solve");
		solveCube.setName("SolveCube");
		solveCube.addActionListener(actionListener);
		cube.add(solveCube);
		
		cube.addSeparator();
		
		play = new JMenuItem("Play");
		play.setName("Play");
		play.addActionListener(actionListener);
		cube.add(play);
		
		previous = new JMenuItem("Previous Twist");
		previous.setName("PreviousTwist");
		previous.addActionListener(actionListener);
		cube.add(previous);
		
		next = new JMenuItem("Next Twist");
		next.setName("NextTwist");
		next.addActionListener(actionListener);
		cube.add(next);
		
		cube.addSeparator();
		
		JMenuItem item = new JMenuItem("Solution");
		item.setName("Solution");
		item.addActionListener(actionListener);
		cube.add(item);
		
		cube.addSeparator();
		
		item = new JMenuItem("Reset View");
		item.setName("ResetView");
		item.addActionListener(actionListener);
		cube.add(item);
		
		add(cube);
	}
	
	private void createHelpMenu(ActionListener actionListener) {
		JMenu help = new JMenu("Help");
		
		JMenuItem item = new JMenuItem("About...");
		item.setName("About");
		item.addActionListener(actionListener);
		help.add(item);
		
		add(help);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		solveCube.setEnabled(!enabled);
		play.setEnabled(enabled);
		previous.setEnabled(enabled);
		next.setEnabled(enabled);
	}
	
	public void setNextAndPrevious(boolean hasNext, boolean hasPrevious) {
		play.setEnabled(hasNext);
		previous.setEnabled(hasPrevious);
		next.setEnabled(hasNext);
	}
	
	public void disableAll() {
		play.setEnabled(false);
		previous.setEnabled(false);
		next.setEnabled(false);
	}
}
