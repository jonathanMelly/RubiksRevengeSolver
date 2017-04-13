package com.rdarida.rrs.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.jogamp.opengl.util.Animator;
import com.rdarida.rrs.RubiksRevengeSolver;
import com.rdarida.rrs.cube.Twist;
import com.rdarida.rrs.solver.SolutionListener;
import com.rdarida.rrs.view.cube3d.Cube3D;
import com.rdarida.rrs.view.toolbar.ToolBar;

public class Gui extends JFrame implements ActionListener, SolutionListener {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final Color[] COLORS = {
		Color.WHITE,
		Color.RED,
		Color.BLUE,
		new Color(1f, 0.5f, 0f),
		Color.GREEN,
		Color.YELLOW
	};
	
	private RubiksRevengeSolver controller;
	private ProgressDialog progressDialog;
	private NewCubeDialog newCubeDialog;
	private SolvingDialog solvingDialog;
	private SolutionDialog solutionDialog;
	private ImageIcon icon;
	private ImageIcon about;
	private MenuBar menuBar;
	private ToolBar toolBar;
	private Cube3D cube;
	private Animator animator;
	private Timer timer;
	
	public Gui(RubiksRevengeSolver controller) {
		super();
		
		this.controller = controller;
	}
	
	public void start() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGui();
			}
		});
	}
	
	private void createAndShowGui() {
		progressDialog = new ProgressDialog(this);
		newCubeDialog = new NewCubeDialog(this);
		solvingDialog = new SolvingDialog(this);
		solutionDialog = new SolutionDialog(this);
		icon = new ImageIcon(ToolBar.class.getResource("icons/icon.png"));
		about = new ImageIcon(ToolBar.class.getResource("icons/about_large.png"));
		
		setTitle("Rubik's Revenge Solver");
		setIconImage(icon.getImage());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		
		menuBar = new MenuBar(this);
		setJMenuBar(menuBar);
		
		toolBar = new ToolBar(this);
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		cube = new Cube3D();
		getContentPane().add(cube, BorderLayout.CENTER);
		
		animator = new Animator(cube);
		animator.start();
		
		setVisible(true);
		
		timer = new Timer(true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				int progress = controller.getPercentage();
				
				if (progress < 100) {
					progressDialog.setProgress(progress);
				}
				else {
					timer.cancel();
					progressDialog.setVisible(false);
				}
			}
		}, 0, 5000);
		
		if (controller.getPercentage() < 100) {
			progressDialog.setVisible(true);
		}
	}
	
	private boolean showConfirm(String message) {
		int result = JOptionPane.showConfirmDialog(
			this,
			message,
			"Select an Option",
			JOptionPane.YES_NO_OPTION
		);
		
		return result == 0 ? true : false;
	}
	
	private void showError(String message) {
		JOptionPane.showMessageDialog(
			this,
			message,
			"Error",
			JOptionPane.ERROR_MESSAGE
		);
	}
	
	public void quit() {
		if (showConfirm("Are you sure to close the Rubik's Revenge Solver?")) {
			System.exit(0);
		}
	}
	
	public void stopSolving() {
		if (showConfirm("Are you sure to stop?")) {
			controller.stopSolving();
			solvingDialog.setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Component component = (Component) event.getSource();
		
		if (component == null) return;
		
		String name = component.getName();
		
		if (name.equals("NewCube")) {
			if (showConfirm("Are you sure to reset the current coloring?")) {
				animator.stop();
				newCubeDialog.setVisible(true);
			}
		}
		else if (name.equals("SolveCube")) {
			Timer timer = new Timer(true);
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					int progress = controller.getSolverProgress();
					
					if (progress < 100) {
						solvingDialog.setProgress(progress);
					}
					else timer.cancel();
				}
			}, 10, 500);
			
			controller.solveCube(this);
			solvingDialog.setVisible(true);
		}
		else if (name.equals("Play")) {
			if (!cube.isTwistPlaying()) {
				menuBar.disableAll();
				toolBar.disableAll();
				
				Timer timer = new Timer(true);
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						if (cube.hasNext()) {
							cube.playNextTwist();
						}
						else {
							timer.cancel();
							menuBar.setNextAndPrevious(cube.hasNext(), cube.hasPrevious());
							toolBar.setNextAndPrevious(cube.hasNext(), cube.hasPrevious());
						}
					}
				}, 500, 2000);
			}
		}
		else if (name.equals("PreviousTwist")) {
			if (!cube.isTwistPlaying()) {
				cube.playPreviousTwist();
				menuBar.setNextAndPrevious(cube.hasNext(), cube.hasPrevious());
				toolBar.setNextAndPrevious(cube.hasNext(), cube.hasPrevious());
			}
		}
		else if (name.equals("NextTwist")) {
			if (!cube.isTwistPlaying()) {
				cube.playNextTwist();
				menuBar.setNextAndPrevious(cube.hasNext(), cube.hasPrevious());
				toolBar.setNextAndPrevious(cube.hasNext(), cube.hasPrevious());
			}
		}
		else if (name.equals("Solution")) {
			solutionDialog.setVisible(true);
		}
		else if (name.equals("ResetView")) {
			cube.resetView();
		}
		else if (name.equals("About")) {
			JOptionPane.showMessageDialog(
				this,
				"Rubik's Revenge Solver\nCreated by Róbert Darida",
				"About",
				JOptionPane.INFORMATION_MESSAGE,
				about
			);
		}
		else if (name.equals("Exit")) {
			System.exit(0);
		}
		else if (name.equals("NewCubeDialog.Scramble")) {
			try {
				controller.scrambleCube();
				solutionDialog.clear();
				menuBar.setEnabled(false);
				toolBar.setEnabled(false);
				cube.reset(controller.getColoring());
				newCubeDialog.setVisible(false);
				animator.start();
			}
			catch (Exception exception) {
				showError(exception.getMessage());
			}
		}
		else if (name.equals("NewCubeDialog.Ok")) {
			try {
				controller.resetCube(newCubeDialog.getColors());
				solutionDialog.clear();
				menuBar.setEnabled(false);
				toolBar.setEnabled(false);
				cube.reset(controller.getColoring());
				newCubeDialog.setVisible(false);
				animator.start();
			}
			catch (Exception exception) {
				showError(exception.getMessage());
			}
		}
		else System.out.println(name);
	}

	@Override
	public void setSolution(ArrayList<Twist> solution) {
		if (cube.setSolution(solution)) {
			solutionDialog.addSolution(new ArrayList<Twist>(solution));
			
			menuBar.setEnabled(true);
			menuBar.setNextAndPrevious(cube.hasNext(), cube.hasPrevious());
			
			toolBar.setEnabled(true);
			toolBar.setNextAndPrevious(cube.hasNext(), cube.hasPrevious());
		}
		
		solvingDialog.setVisible(false);
	}
}