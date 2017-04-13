package com.rdarida.rrs.view.toolbar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ToolBarButton extends JButton {
	private static final long serialVersionUID = 1L;
	public static final String ICON_CUBE_MIXED     = "mixed";
	public static final String ICON_CUBE_SOLVED    = "solved";
	public static final String ICON_CUBE_PLAY      = "play";
	public static final String ICON_TWIST_PREVIOUS = "previous";
	public static final String ICON_TWIST_NEXT     = "next";
	public static final String ICON_VIEW_RESET     = "reset";
	public static final String ICON_ABOUT          = "about";
	
	public ToolBarButton(String icon) {
		super();
		setBorder(new EmptyBorder(2, 2, 2, 2));
		setOpaque(true);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setIcon(new ImageIcon(getClass().getResource("icons/" + icon + ".png")));
	}
}