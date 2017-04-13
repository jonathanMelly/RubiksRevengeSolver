package com.rdarida.rrs.view.toolbar;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	private ToolBarButton solveCube;
	private ToolBarButton play;
	private ToolBarButton previous;
	private ToolBarButton next;
	
	public ToolBar(ActionListener actionListener) {
		super();
		
		setFloatable(false);
		
		ToolBarButton button = new ToolBarButton(ToolBarButton.ICON_CUBE_MIXED);
		button.setName("NewCube");
		button.setToolTipText("New Cube");
		button.addActionListener(actionListener);
		add(button);
		
		addSeparator();
		
		solveCube = new ToolBarButton(ToolBarButton.ICON_CUBE_SOLVED);
		solveCube.setName("SolveCube");
		solveCube.setToolTipText("Solve Cube");
		solveCube.addActionListener(actionListener);
		add(solveCube);
		
		play = new ToolBarButton(ToolBarButton.ICON_CUBE_PLAY);
		play.setName("Play");
		play.setToolTipText("Play");
		play.addActionListener(actionListener);
		add(play);
		
		previous = new ToolBarButton(ToolBarButton.ICON_TWIST_PREVIOUS);
		previous.setName("PreviousTwist");
		previous.setToolTipText("Previous Twist");
		previous.addActionListener(actionListener);
		add(previous);
		
		next = new ToolBarButton(ToolBarButton.ICON_TWIST_NEXT);
		next.setName("NextTwist");
		next.setToolTipText("Next Twist");
		next.addActionListener(actionListener);
		add(next);
		
		addSeparator();
		
		button = new ToolBarButton(ToolBarButton.ICON_ABOUT);
		button.setName("Solution");
		button.setToolTipText("Solution");
		button.addActionListener(actionListener);
		add(button);
		
		addSeparator();
		
		button = new ToolBarButton(ToolBarButton.ICON_VIEW_RESET);
		button.setName("ResetView");
		button.setToolTipText("Reset View");
		button.addActionListener(actionListener);
		add(button);
		
		setEnabled(false);
	}
	
	@Override
	public void addSeparator() {
		JSeparator separator = new JSeparator(JSeparator.VERTICAL);
		separator.setMaximumSize(new Dimension(2, 36));
		
		super.addSeparator();
		add(separator);
		super.addSeparator();
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
		next.setEnabled(hasNext);
		previous.setEnabled(hasPrevious);
	}
	
	public void disableAll() {
		play.setEnabled(false);
		previous.setEnabled(false);
		next.setEnabled(false);
	}
}