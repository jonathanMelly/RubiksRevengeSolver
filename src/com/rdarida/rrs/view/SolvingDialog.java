package com.rdarida.rrs.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.rdarida.rrs.view.toolbar.ToolBar;

public class SolvingDialog extends JDialog implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1L;
	private Gui gui;
	private JLabel information;
	private JProgressBar progressBar;
	private JButton stop;
	
	public SolvingDialog(Gui gui) {
		super(gui);
		
		this.gui = gui;
		setTitle("Solving");
		setSize(Gui.WIDTH / 2, Gui.HEIGHT / 4);
		setResizable(false);
		setModal(true);
		addWindowListener(this);
		
		Container panel = getContentPane();
		panel.setLayout(new BorderLayout());
		
		ImageIcon icon = new ImageIcon(ToolBar.class.getResource("icons/solving.gif"));
		JLabel iconLabel = new JLabel(icon);
		iconLabel.setBorder(new EmptyBorder(0, 20, 0, 20));
		panel.add(iconLabel, BorderLayout.WEST);
		
		information = new JLabel(
			"<html>Searching for solution.</html>",
			SwingConstants.LEFT
		);
		
		panel.add(information, BorderLayout.CENTER);
		
		JPanel south = new JPanel();
		south.setBorder(new EmptyBorder(4, 4, 4, 4));
		south.setLayout(new GridLayout(2, 1, 4, 4));
		
		progressBar = new JProgressBar(0, 100);
		south.add(progressBar);
		
		stop = new JButton("Stop");
		stop.setPreferredSize(new Dimension(100, 30));
		stop.addActionListener(this);
		south.add(stop);
		
		panel.add(south, BorderLayout.SOUTH);
	}
	
	@Override
	public void setVisible(boolean b) {
		setLocationRelativeTo(getOwner());
		super.setVisible(b);
	}
	
	public void setProgress(int value) {
		progressBar.setValue(value);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		gui.stopSolving();
	}
	
	@Override
	public void windowClosing(WindowEvent event) {
		gui.stopSolving();
	}
	
	@Override
	public void windowActivated(WindowEvent event) {}
	
	@Override
	public void windowClosed(WindowEvent event) {}
	
	@Override
	public void windowDeactivated(WindowEvent event) {}
	
	@Override
	public void windowDeiconified(WindowEvent event) {}
	
	@Override
	public void windowIconified(WindowEvent event) {}
	
	@Override
	public void windowOpened(WindowEvent event) {}
}