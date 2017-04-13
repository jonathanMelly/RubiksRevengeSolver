package com.rdarida.rrs.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class ProgressDialog extends JDialog implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1L;
	private Gui gui;
	private JProgressBar progressBar;
	private JButton stop;
	
	public ProgressDialog(Gui gui) {
		super(gui);
		
		this.gui = gui;
		setTitle("Generating Distance Database");
		setSize(Gui.WIDTH / 2, Gui.HEIGHT / 4);
		setResizable(false);
		setModal(true);
		addWindowListener(this);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(4, 4, 4, 4));
		panel.setLayout(new GridLayout(3, 1, 6, 6));
		
		JLabel description = new JLabel("This can take upwards of 60 minutes.");
		panel.add(description);
		
		progressBar = new JProgressBar(0, 100);
		panel.add(progressBar);
		
		stop = new JButton("Stop");
		stop.addActionListener(this);
		panel.add(stop);
		
		getContentPane().add(panel, BorderLayout.CENTER);
	}
	
	public void setProgress(int value) {
		progressBar.setValue(value);
	}
	
	@Override
	public void setVisible(boolean visible) {
		setLocationRelativeTo(getOwner());
		super.setVisible(visible);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		gui.quit();
	}

	@Override
	public void windowClosing(WindowEvent event) {
		gui.quit();
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