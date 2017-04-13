package com.rdarida.rrs.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.rdarida.rrs.cube.Cube;

public class NewCubeDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ColoredButton[] buttons;
	private JButton reset;
	
	public NewCubeDialog(Gui gui) {
		super(gui);
		
		setTitle("New Cube");
		setSize(Gui.WIDTH / 2, Gui.HEIGHT / 2);
		setResizable(false);
		setModal(true);
		
		createButtons(gui);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		
		JButton button = new JButton("Scramble");
		button.setName("NewCubeDialog.Scramble");
		button.addActionListener(gui);
		panel.add(button);
		
		reset = new JButton("Reset");
		reset.addActionListener(this);
		panel.add(reset);
		
		button = new JButton("Ok");
		button.setName("NewCubeDialog.Ok");
		button.addActionListener(gui);
		panel.add(button);
		
		getContentPane().add(panel, BorderLayout.SOUTH);
	}
	
	private void createButtons(ActionListener actionListener) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 4, 2, 2));
		
		buttons = new ColoredButton[Cube.SIZE];
		int cnt = 0;
		byte color = Cube.W;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (i == 1 || j == 1) {
					JPanel subPanel = new JPanel();
					subPanel.setLayout(new GridLayout(Cube.N, Cube.N, 2, 2));
					
					for (int r = 0; r < Cube.N; r++) {
						for (int c = 0; c < Cube.N; c++) {
							ColoredButton button = new ColoredButton(color);
							button.addActionListener(this);
							subPanel.add(button);
							buttons[cnt++] = button;
						}
					}
					
					panel.add(subPanel);
					color++;
				}
				else {
					panel.add(new JLabel());
				}
			}
		}
		
		getContentPane().add(panel, BorderLayout.CENTER);
	}
	
	public byte[] getColors() {
		byte[] result = new byte[Cube.SIZE];
		
		for (int i = 0; i < Cube.SIZE; i++) {
			result[i] = buttons[i].getColor();
		}
		
		return result;
	}
	
	@Override
	public void setVisible(boolean visible) {
		setLocationRelativeTo(getOwner());
		super.setVisible(visible);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(reset)) {
			for (ColoredButton button : buttons) {
				button.reset();
			}
		}
		else if (event.getSource().getClass().getName().endsWith("ColoredButton")) {
			ColoredButton button = (ColoredButton) event.getSource();
			button.nextColor();
		}
	}
}

class ColoredButton extends JButton {
	private static final long serialVersionUID = 1L;
	private byte defaultColor;
	private byte color;
	
	public byte getColor() {
		return color;
	}
	
	public ColoredButton(byte color) {
		super();
		
		defaultColor = color;
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setOpaque(true);
		reset();
	}
	
	public void reset() {
		color = defaultColor;
		setBackground(Gui.COLORS[color]);
	}
	
	public void nextColor() {
		color = (byte) ((color + 1) % Cube.NUM_FACES);
		setBackground(Gui.COLORS[color]);
	}
}